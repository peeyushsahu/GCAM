/*
 * Copyright (C) 2014 peeyush
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.bonn.limes.core;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author peeyush
 */
public class ListOperations {

    public List divideTreemap(TreeMap abstracts) {        
        Object[] keyList = abstracts.keySet().toArray();
        List<SortedMap> dividedAbsList = new ArrayList<>(); // To hold multiple SortedMaps

        int size = abstracts.size();
        System.out.println("Size:"+size);
        //System.out.println(abstracts);
        
        int divList = size / 4;
        int lastElement = divList-1;
        int firstElement = 0;
        System.out.println("Check Point:1");
        
        for (int i = 1; i <= 4; i++) {
            System.out.println("i:"+i);
            if (i < 4) {
                dividedAbsList.add((SortedMap) abstracts.subMap(keyList[firstElement], true, keyList[lastElement], true));
                firstElement = lastElement+1;
                lastElement = lastElement + divList;
                System.out.println("Last:"+lastElement);
            } else {
                dividedAbsList.add((SortedMap) abstracts.subMap(keyList[firstElement], true, keyList[size-1], true));
            }
        }

        return dividedAbsList;
    }
    
    /**
     * This method will combine multiple Maps to single map
     * @param occurrence
     * @return 
     */
    public TreeMap joinMaps(List<TreeMap<String, ArrayList>> occurrence){
        TreeMap<String, ArrayList> abnerResults = new TreeMap<>();
        for(TreeMap map : occurrence){
            Object[] keyMap = map.keySet().toArray();
            for(Object key : keyMap){
                abnerResults.put(key.toString(), (ArrayList) map.get(key));
            }
        }
        return abnerResults;
        
    }
}
