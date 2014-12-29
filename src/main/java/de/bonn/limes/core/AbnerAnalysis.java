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

import de.bonn.limes.document.PubMedAbstract;
import de.bonn.limes.entities.EntityTaged;
import de.bonn.limes.gcam.abner.MyTagger;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import java.util.List;
import static de.bonn.limes.gui.GeneMinerUI.progressbarCount;
import java.io.File;
import java.util.Collections;

/**
 *
 * @author peeyush
 */
public class AbnerAnalysis {
    
    private TreeMap <String,ArrayList> abstractList = new TreeMap();
    private List<PubMedAbstract> abstracts = new ArrayList<>();
    private EntityTaged NERentity;
    
    private TreeMap<String,ArrayList> abnerResult = new TreeMap();
    
    
    
    public AbnerAnalysis(TreeMap abstractReposite){
        this.abstractList = abstractReposite;
        progressbarCount = 0;
    }
    
    
    /**
     * Returns arraylist of object entityTaged stores all information about NER using ABNER.
     * @return
     * @throws IOException 
     */
    
    public TreeMap NERanalysis() throws IOException{
    
    MyTagger t = new MyTagger(new File("/home/peeyush/Documents/netbeans_projects/GCAM-1.0/dependencies/nlpba.crf"));
    Integer PMID = null;   
    ArrayList <EntityTaged> NerResultList;// = new ArrayList();
    List <String> entity = new ArrayList<>();
    String gene;
    EntityTaged test;    
        int count = 1;
        for(Map.Entry<String, ArrayList> entry : abstractList.entrySet()){
            
            NerResultList = new ArrayList();
            gene = entry.getKey(); // gene name
            abstracts = entry.getValue();
            //System.out.println("Abstract size:  "+abstracts.size());
            
            for (PubMedAbstract iter :abstracts) {
                progressbarCount++;
                NERentity = new EntityTaged();
                String s = iter.getAbstractText();
                //System.out.println(s);
                PMID = iter.getPMID();
                
                //starting of ABNER
                String[] cell_type = t.getEntities(s,"CELL_TYPE");
                if(cell_type.length > 0){
                    for (String cell_t:cell_type) {
                    //System.out.println(cell_t);
                    entity.add(cell_t);
                    }
                }
                //System.out.println("################################################################");
                String[] cell_line = t.getEntities(s,"CELL_LINE");
                //System.out.println("This is the abstract: "+s);
                if(cell_line.length > 0){
                    for (String cell_l:cell_line) {
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
        return abnerResult;
             
    }
    


}
