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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author peeyush
 */
public class ListOperations {
    
    public TreeMap NERmultithreading(TreeMap abstracts, int thread) throws InterruptedException, ExecutionException {

        ListOperations breakList = new ListOperations();
        //variable to store sum
        ArrayList<TreeMap<String, ArrayList>> abnerResultM = new ArrayList<>();
        TreeMap<String, ArrayList> abnerResult = new TreeMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(thread);
        List<Callable<TreeMap>> callableList = new ArrayList<>();
        //Send abstract TreeMap for division in SortedMaps
        List<SortedMap> divTreemap = breakList.divideTreemap(abstracts);
        //AbstractTagger nerTagger = new AbstractTagger();
        System.out.println("Size of divTreeMap:"+divTreemap.size());
        
        for (final SortedMap maps : divTreemap) {
            System.out.println("SortedMap: "+maps.size());
            //System.out.println("SortedMap: "+maps);
            Object[] mapKey = maps.keySet().toArray();
            callableList.add(new Callable<TreeMap>(){
                
                @Override
                public TreeMap call() throws Exception {
                    AbstractTagger nerTagger = new AbstractTagger(maps);
                    return nerTagger.tagAbstracts();
                }
                    });
        }
        System.out.println("Size of callable: "+callableList.size());
        //Returns after all tasks complete
        List<Future<TreeMap>> resFuture = executor.invokeAll(callableList);

        //Print results as future objects
        for (Future<TreeMap> future : resFuture) {
            System.out.println("Status of future : " + future.isDone());
            abnerResultM.add(future.get());
        }
        executor.shutdown();
        abnerResult = breakList.joinMaps(abnerResultM);
        System.out.println("Size of abner result: "+abnerResultM.size());
        System.out.println("This is the abner map: "+abnerResultM);
        return abnerResult;
    }

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
