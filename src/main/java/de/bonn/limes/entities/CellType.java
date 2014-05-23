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
public class CellType {
    
    //private String cellType;
    Map<String,Integer> cellTypeMap;
    
    public CellType(){
        cellTypeMap = new HashMap<>();
        
       }

    /**
     *
     * @param cellType
     */
    public void SearchUnique(String cellType){
        
        if(cellTypeMap.containsKey(cellType)){
                int value = cellTypeMap.get(cellType);
                cellTypeMap.put(cellType,value+1);
        }
        else{
                cellTypeMap.put(cellType,1);
        }
    }
}
