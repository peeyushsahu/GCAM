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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peeyush 
 * this class is written for reading gene list or entity
 * dictionary from user
 *
 */
public class ReadTextFile {

    List<String> entityNames = new ArrayList();
    String csvFile="";

    /*
     * This method will read text file 
     * Any text file can be used after defining the delimiter
     * Return arraylist
     */
    public List extract(String path) {

        this.csvFile = path;
        BufferedReader br = null;
        String line = "";
        String delimiter = "\n";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma seprated file to retrieve text
                String[] Sline = line.split(delimiter);
                entityNames.add(Sline[0]);

            }
            return entityNames;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //public static void main(String args[]) {

      //  ReadTextFile Fread = new ReadTextFile();
      //  System.out.println(Fread.extract());
    //}
}
