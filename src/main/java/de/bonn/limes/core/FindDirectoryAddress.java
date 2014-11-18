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

import java.io.File;

/**
 *
 * @author peeyush
 */
public class FindDirectoryAddress {
    
 /**
  * This returns path to the current working directory, create output directory and returns path.
  * @return 
  */
    public static String dirPath;
    public static String homePath;
 public void getpath(){
    dirPath = System.getProperty("user.dir");
    System.out.println("dirPath directory=  "+dirPath);
    String home = System.getProperty("user.home");
    boolean success = (new File(home+"/GCAM_output")).mkdir();
    homePath = new StringBuilder().append(home).append("/GCAM_output").toString();
    System.out.println("Home directory=  "+homePath);
    System.out.println("Directory created: "+success);
 }
    //public static void main(String[] args) {
        //FindDirectoryAddress hs = new FindDirectoryAddress();
        //hs.getpath();
    //}
}
