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

package de.bonn.limes.gui;

import de.bonn.limes.core.ReadTextFile;
import static de.bonn.limes.gui.GeneMinerUI.dirPath;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peeyush
 */
public class testing {
    
   private List<String> cellarray = new ArrayList<>();
   ReadTextFile celllist = new ReadTextFile();
   
   public void atest(){
      cellarray = celllist.extract("/home/peeyush/NetBeansProjects/GeneMiner/cellTypes.csv");
      for(String cell : cellarray){
          System.out.println("Cell type:    "+cell);
        }
   }
               
    public static void main(String[] args) {
        testing tes = new testing();
        tes.atest();
    }
}