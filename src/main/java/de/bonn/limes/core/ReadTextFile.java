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
    String fileName;
    List<List<String>> synonymList = new ArrayList<>();
    List<String> synonymes;

    /**
     * This method will read text file 
     * Any text file can be used after defining the delimiter
     * @param path
     * @return 
     */
    public List extract(String path) {

        this.fileName = path;
        BufferedReader br;
        String line;
        String delimiter = "\n";

        try {

            br = new BufferedReader(new FileReader(fileName));
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
    
    /**
     * This method is to read synonym file
     * @param path
     * @param delim
     * @return 
     */
    
    public List extract(String path, String delim) { // it is delimeter for gene and synonyms prefrable is comma(",")

        this.fileName = path;
        BufferedReader br;
        String line;
        String newLineDelimiter = "\n";
        String listDelimiter = delim;

        try {

            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                // use comma seprated file to retrieve text
                String[] Sline = line.split(newLineDelimiter); 
                String[] synonym = Sline[0].split(listDelimiter);
                synonymes = new ArrayList<>();
                for(String aliase:synonym){
                    if(!aliase.equals("0")){
                   synonymes.add(aliase);
                    }
                }
               synonymList.add(synonymes);
            }
            return synonymList;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Input output exception");
        }
        return null;
    }
   /*
    public static void main(String args[]) {

        ReadTextFile Fread = new ReadTextFile();
        List<List<String>> names;
        names = (List<List<String>>) Fread.extract("/home/peeyush/Desktop/testFile.csv",",");
        for(List<String> name:names){
            System.out.println("Length of big list"+names.size());
            System.out.println("String: "+name);
            System.out.println("length: "+name.size());
        }

    }    
    */
}
