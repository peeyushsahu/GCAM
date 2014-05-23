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
package de.bonn.limes.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author peeyush
 */
public class CellLine {
    
    //private String cellline;
    //use nested hash map
    Map<String,Integer> celllineMap;
    
    public CellLine(){
        celllineMap = new HashMap<>();
        
        
            }

    /**
     *
     * @param cellline
     * @return 
     */
    public Map<String,Integer> SearchUnique(String cellline){
        
        if(celllineMap.containsKey(cellline)){
                int value = celllineMap.get(cellline);
                celllineMap.put(cellline,value+1);
                //System.out.println("Occurrance of CellLines:"+cellline+" Occurance :"+celllineMap.get(cellline));
        }
        else{
                celllineMap.put(cellline,1);
        }
        return celllineMap;
        //Printing hash map
        
        //for (String name: celllineMap.keySet()){

          //  String key =name.toString();
            //String value = celllineMap.get(name).toString();  
            //System.out.println(key + " " + value);  
        //} 
        
    }
}
