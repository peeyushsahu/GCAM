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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peeyush
 */
public class CheckSynonymes {
    
    private List<String> genes;
    private List<String> geneWidSynonym;
    private List<List<String>> synonyms;

    public CheckSynonymes(List allGenes) {
        this.geneWidSynonym = new ArrayList();
        this.genes = allGenes;
    }
    
    public List withSynonym(List synonym){
        this.synonyms = synonym;
        for(String gene:genes){
            System.out.println("Gene:"+gene);
            for(List eliaseList:synonyms){
                List<String> geneList = eliaseList;
               for(String eliase:geneList){
                    if(gene.toLowerCase().equals(eliase.toLowerCase())){
                        System.out.println("elisase:"+eliase);
                        for(String elias:geneList){
                            System.out.println("elias:"+elias);
                            geneWidSynonym.add(elias);
                        }
                        System.out.println("size of aliase list:"+geneList.size());
                        //break; 
                    }
                    
                }                
            }
        }
        return geneWidSynonym;
        
    }
    
}
