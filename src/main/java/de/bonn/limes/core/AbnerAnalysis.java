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

import abner.Tagger;
import de.bonn.limes.document.PubMedAbstract;
import de.bonn.limes.entities.EntityTaged;
import static de.bonn.limes.gui.GeneMinerUI.ProgressBar;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author peeyush
 */
public class AbnerAnalysis {
    
    TreeMap <String,ArrayList> abstractList = new TreeMap();
    List<PubMedAbstract> abstracts = new ArrayList<>();
    EntityTaged NERentity;
    
    TreeMap<String,ArrayList> abnerResult = new TreeMap();
    
    
    
    public AbnerAnalysis(TreeMap abstractReposite){
        this.abstractList = abstractReposite; 
    }
    
    
    /*
    Returns arraylist of object entityTaged stores all information about NER using ABNER
    */
    
    public TreeMap NERanalysis() throws IOException{
    
    Tagger t = new Tagger();
    Integer PMID = null;   
    ArrayList <EntityTaged> NerResultList;// = new ArrayList();
    ArrayList <String> entity;
    String gene;
    EntityTaged test;    
        int count = 1;
        for(Map.Entry<String, ArrayList> entry : abstractList.entrySet()){
            
            NerResultList = new ArrayList();
            gene = entry.getKey(); // gene name
            abstracts = entry.getValue();
            //System.out.println("Abstract size:  "+abstracts.size());
            
            for (PubMedAbstract iter :abstracts) {
                ProgressBar.setValue(count++);
                ProgressBar.repaint();
                NERentity = new EntityTaged();
                entity = new ArrayList();
                String s = iter.getAbstractText();
                //System.out.println(s);
                PMID = iter.getPMID();
                
                //starting of ABNER
                
                //System.out.println("################################################################");
                //System.out.println("[CELL_TYPE SEGMENTS]");
                //System.out.println("Prob is after here");
                String[] cell_type = t.getEntities(s,"CELL_TYPE");
                //System.out.println(cell_type.length);
                if(cell_type.length > 0){
                    for (String cell_t:cell_type) {
                    //System.out.println(prots[i]);
                    //NERabs.get(abs.getPMID()).add(prots[i]);
                    entity.add(cell_t);
                    }
                }
                //System.out.println("################################################################");
                //System.out.println("[CELL_LINE SEGMENTS]");
                String[] cell_line = t.getEntities(s,"CELL_LINE");
                if(cell_line.length > 0){
                    for (String cell_l:cell_line) {
                    //System.out.println(prot[i]);
                    //NERabs.get(abs.getPMID()).add(prot[i]);
                    entity.add(cell_l);
                }
             }
            
           
            NERentity.setPMID(PMID);
                //System.out.println(PMID);
            NERentity.setTaggedentity((ArrayList) entity);
                //System.out.println(entity.size());
            NerResultList.add(NERentity);   
            }
           
            //System.out.println("Size of abstractList:   "+NerResultList.size());
            //System.out.println("Name of gene:   "+gene);
            abnerResult.put(gene, NerResultList);
                                    
        }   
        /*
        for(Map.Entry<String,ArrayList> precheck : abnerResult.entrySet()){
            System.out.println("AbnerAnalysis:key"+precheck.getKey());
            System.out.println("AbnerAnalysis:value"+precheck.getValue());
            System.out.println("Size of arraylist:  "+precheck.getValue().size());
            for(Object ent:precheck.getValue()){
                test =(EntityTaged) ent;
                System.out.println("entity"+test.getTaggedentity());
            }
        }
       */
        return abnerResult;
             
    }
    


}
