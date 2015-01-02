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

import de.bonn.limes.entities.EntityTaged;
import de.bonn.limes.entities.Occurrenceobj;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author peeyush
 *
 * this class will compare the entities recognized by ABNER with the dictionary
 * of cell-type entities provided by user
 */
public class Entity2cell {

    TreeMap<String,ArrayList> foundentity; //entities found using ABNER
    ArrayList<String> entitytoCompare; //cell-type list to compare
    TreeMap<String, Integer> matches;
    Occurrenceobj occuresult;// = new Occurrenceobj();
    ArrayList<Occurrenceobj> listofoccuresult = new ArrayList();
    String gene;
    ArrayList<EntityTaged> found = new ArrayList();
    
   
    /**
     * This method counts the occurrence of celltypes in tagged entities. 
     * @param cellentity
     * @param abnerEntity
     * @return 
     */

    public ArrayList<Occurrenceobj> compare(ArrayList cellentity, TreeMap abnerEntity) {
        
        
        ArrayList<String> tagedEntity = new ArrayList();
        this.entitytoCompare = cellentity; //this is a arraylist of String          
        this.foundentity = abnerEntity; //this is a arraylist of a class object which provides
        //recognized entities from the abstracts
     
        
        for (Map.Entry<String, ArrayList> genes : foundentity.entrySet()){
            gene = genes.getKey();
            //System.out.println("Gene user defined:  "+gene);
            occuresult = new Occurrenceobj();
            this.found = genes.getValue();
            matches = new TreeMap();
            //System.out.println("Size of Abstracts(Entity2cell):  "+found.size());
            //System.out.println("Size of gene list:  "+entitytoCompare.size());
            for (String cells : entitytoCompare){
                int hit = 0;
                //System.out.println("cells: "+cells);
                //System.out.println("Hit: "+hit);
                matches.put(cells, hit);
                
                for(EntityTaged tags:found){
                    
                    tagedEntity = tags.getTaggedentity();
                    //System.out.println("PMIDs per gene: "+tags.getPMID()); //WORKING
                    //System.out.println("+++++++++++++++++"+tagedEntity);
                    if (tagedEntity.size() > 0) {
                        for (String abner : tagedEntity){
                            //System.out.println(abner);
                            //System.out.println(cells);
                            if (abner.toLowerCase().contains(cells.toLowerCase())) {
                                hit += 1;
                                //System.out.println(abner);
                                //System.out.println(cells);                                
                                //System.out.println("++++++++++++" + hit);
                                matches.put(cells, hit);
                        }
                    }
                }
            }
        
           }
        occuresult.setOccurrence(matches);
            //System.out.println("Gene to be put: "+gene);
        occuresult.setGene(gene);
        listofoccuresult.add(occuresult);
        
        }
        //System.out.println(listofoccuresult.size());
        //for(Occurrenceobj test:listofoccuresult){
            //System.out.println("Test for occoj: "+test.getGene());
            //System.out.println("Size of cell list:  "+test.getOccurrence().size());
            //System.out.println("Fkey of cell list:  "+test.getOccurrence());
            //System.out.println("Lkey of cell list:  "+test.getOccurrence().lastKey());
            //System.out.println("Size of cell macrophage:  "+test.getOccurrence().get("vitreous"));
            //System.out.println("Size of cell macrophage:  "+test.getOccurrence().get("macrophage"));
        //}
        return listofoccuresult;
        
    }
}
