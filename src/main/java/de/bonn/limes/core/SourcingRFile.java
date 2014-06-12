/*
 * #Copyright (C) Peeyush Sahu <peeyush215[at]gmail.com>
 * #
 * #This program is free software: you can redistribute it and/or modify
 * #it under the terms of the GNU General Public License as published by
 * #the Free Software Foundation, either version 3 of the License, or
 * #(at your option) any later version.
 * #
 * #This program is distributed in the hope that it will be useful,
 * #but WITHOUT ANY WARRANTY; without even the implied warranty of
 * #MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * #GNU General Public License for more details.
 * #
 * #You should have received a copy of the GNU General Public License
 * #along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.bonn.limes.core;

/**
 *
 * @author peeyush
 */
import static de.bonn.limes.core.StartRserve.isRserveRunning;
import static de.bonn.limes.core.StartRserve.launchRserve;
import static de.bonn.limes.gui.GeneMinerUI.homePath;
import java.io.File;
import java.io.IOException;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
 
public class SourcingRFile {
    
    /** checks whether Rserve is running and if that's not the case it attempts to start it using the defaults for the platform where it is run on. This method is meant to be set-and-forget and cover most default setups. For special setups you may get more control over R with <<code>launchRserve</code> instead.
     * @return  */
	public boolean checkLocalRserve() {
		if (isRserveRunning()) return true;
		String osname = System.getProperty("os.name");
		if (osname != null && osname.length() >= 7 && osname.substring(0,7).equals("Windows")) {
			System.out.println("Windows: query registry to find where R is installed ...");
			String installPath = null;
			try {
				Process rp = Runtime.getRuntime().exec("reg query HKLM\\Software\\R-core\\R");
				StreamHog regHog = new StreamHog(rp.getInputStream(), true);
				rp.waitFor();
				regHog.join();
				installPath = regHog.getInstallPath();
			} catch (IOException | InterruptedException rge) {
				System.out.println("ERROR: unable to run REG to find the location of R: "+rge);
				return false;
			}
			if (installPath == null) {
				System.out.println("ERROR: canot find path to R. Make sure reg is available and R was installed with registry settings.");
				return false;
			}
			return launchRserve(installPath+"\\bin\\R.exe");
		}
		return (launchRserve("R") || /* try some common unix locations of R */
			((new File("/Library/Frameworks/R.framework/Resources/bin/R")).exists() && launchRserve("/Library/Frameworks/R.framework/Resources/bin/R")) ||
			((new File("/usr/local/lib/R/bin/R")).exists() && launchRserve("/usr/local/lib/R/bin/R")) ||
			((new File("/usr/lib/R/bin/R")).exists() && launchRserve("/usr/lib/R/bin/R")) ||
			((new File("/usr/local/bin/R")).exists() && launchRserve("/usr/local/bin/R")) ||
			((new File("/sw/bin/R")).exists() && launchRserve("/sw/bin/R")) ||
			((new File("/usr/common/bin/R")).exists() && launchRserve("/usr/common/bin/R")) ||
			((new File("/opt/bin/R")).exists() && launchRserve("/opt/bin/R"))
			);
	}
            
        //public static void main(String args[]) throws RserveException, REXPMismatchException, REngineException, InterruptedException{
        public void analyse(float threshold,int synonym) throws RserveException, REXPMismatchException, REngineException, InterruptedException {       
                //int synonym = 1;
                //float threshold = (float) 0.2;
                //homePath = "/home/peeyush/GeneMiner_output";
                SourcingRFile sr = new SourcingRFile();
                sr.checkLocalRserve();
                RConnection c = new RConnection();                
                c.assign(".tmp.", "source(\"/home/peeyush/Desktop/palindrome.R\")");
                REXP r = c.parseAndEval("try(eval(parse(text=.tmp.)),silent=TRUE)");
                if (r.inherits("try-error")) System.err.println("Error: "+r.toString());
                else {                 
                    c.parseAndEval("source(\"/home/peeyush/Desktop/palindrome.R\")");
                    System.out.println("Script is compiling....");
                    Thread.sleep(1000);
                    // call the function. Return true
                    String path = "palindrome(\""+homePath+"\","+threshold+","+synonym+")";
                    System.out.println(path);
                    REXP generate_heatmap_pVal = c.parseAndEval("palindrome(\""+homePath+"\","+threshold+","+synonym+")");
                    System.out.println("Number of cell types with enrichment:   "+generate_heatmap_pVal.asInteger()); // prints 1 => true
                }
                                
                System.out.println("Rserver shutting down....");
                c.shutdown();
       
            }
 
}
