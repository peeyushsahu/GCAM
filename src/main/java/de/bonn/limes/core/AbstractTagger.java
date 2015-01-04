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
import de.bonn.limes.gui.GeneMinerUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peeyush
 * This class performs the NER on all fetched abstracts.
 * This class is for tagging abstracts with the named entity to show them in display with 
 * yellow highlight.
 * This updates abstract text with html quoted text.
 *
 */
public class AbstractTagger {

    private TreeMap<String, ArrayList> abnerResults = new TreeMap<>();
    private TreeMap<String, ArrayList> abstracts;

    public TreeMap<String, ArrayList> getAbstracts() {
        return abstracts;
    }

    public AbstractTagger(TreeMap<String, ArrayList> abstracts) {
        this.abstracts = abstracts;
    }
/**
 * This method returns abner tagged abstracts as a treemap.
 * @return TreeMap
 */
    public TreeMap tagAbstracts() {        
        
        TreeMap<String, ArrayList> allAbstracts = new TreeMap<>(abstracts);
        try {
            
            if (allAbstracts.size() > 40) {
                // call method for multithreading
                ListOperations mult = new ListOperations();
                System.out.println("Using multithreading with no. of threads");
                abnerResults = mult.MultithreadigRunable(allAbstracts);
               
            } else {
                        AbnerAnalysis absTagger = new AbnerAnalysis(allAbstracts);
                        abnerResults = absTagger.NERanalysis();
            }
            
            //abnerResults = absTagger.NERanalysis();
            
            
            for (Map.Entry<String, ArrayList> tag : abnerResults.entrySet()) {
                String gene = tag.getKey();
                ArrayList<EntityTaged> taggedEs = tag.getValue();
                //System.out.println("Gene: "+gene);
                //System.out.println("No. of abstract: "+taggedEs.size());
                 ArrayList<PubMedAbstract> aList = allAbstracts.get(gene);
                for (EntityTaged tagE : taggedEs) {
                    int pmid = tagE.getPMID();
                    String absText = getAbstract(pmid, gene);
                    List<String> tags = tagE.getTaggedentity();
                    //System.out.println("Size of taged entity: "+tags.size());

                    for (String t : tags) {
                        //System.out.println("tags: "+t);
                        String tagedT = "<font style=\"background-color: yellow\">" + t + "</font>";
                        absText = absText.replace(t, tagedT);
                    }
                    
                    
                    //List<PubMedAbstract> aList = allAbstracts.get(gene);

                    for (PubMedAbstract a : aList) {
                        if (a.getPMID() == pmid) {
                            String newabs = absText;
                            a.setCompleteAbstract(newabs);
                         }
                    }
                                                              
               }
                   allAbstracts.put(gene, aList);

            }

        } catch (IOException ex) {
            Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.abstracts = allAbstracts;
        return abnerResults;
    }

    private String getAbstract(int id, String gene) {

        List<PubMedAbstract> results = abstracts.get(gene);

        for (PubMedAbstract abst : results) {
            int pmid = abst.getPMID();
            if (id == pmid) {
                return abst.getCompleteAbstract();
            }
        }

        return null;
    }

}
