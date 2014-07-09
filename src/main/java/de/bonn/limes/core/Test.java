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
import de.bonn.limes.entities.Occurrenceobj;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author peeyush
 * 
 */

public class Test {
    
    
    

    public static void main(String args[]) throws IOException, InterruptedException {
        List<String> genes =  new ArrayList();
        List<String> entity = new ArrayList();
        Map<String, List> abstracts = new TreeMap();
        TreeMap<String, EntityTaged> abner = new TreeMap();
        List<String> entity2compare = new ArrayList();
        ArrayList<Occurrenceobj> occurrenceResult = new ArrayList();
        Integer maxabs = 3500;
        Integer persec = 500;

        // To read a user provided CSV file

            ReadTextFile geneNames = new ReadTextFile();
            genes = geneNames.extract("/home/peeyush/Desktop/geneList.csv");
            System.out.println(genes.toString());
        
    
        //Downloading abstracts from given genes list
    
       
        AbstractReposite TotalAbs = new AbstractReposite();
        abstracts = TotalAbs.getAbstracts((List<String>) genes, maxabs, persec);
   
    
        //Writing abstracts into a text file
       
        for (Map.Entry<String, List> abs : abstracts.entrySet()) {
            BufferedWriter bre = new BufferedWriter(new FileWriter("/home/peeyush/Desktop/abstracts/abstracts"+abs.getKey()+".html"));
            StringBuilder csvFile1 = new StringBuilder();
            ArrayList<PubMedAbstract> objAbs = (ArrayList<PubMedAbstract>) abs.getValue();
            for (PubMedAbstract abst : objAbs) {

                csvFile1.append(abst.getCompleteAbstract());
                csvFile1.append("\n");
            }
        bre.write(csvFile1.toString());
        bre.close();
        }
                
        //NER Tagging abstract usign ABNER
        
         AbnerAnalysis abnerRes = new AbnerAnalysis((TreeMap) abstracts);
         abner = abnerRes.NERanalysis();
        
         //To read a user provided CSV file
         
         ReadTextFile cellEntity = new ReadTextFile();
         entity2compare =  cellEntity.extract("/home/peeyush/Desktop/cellTypes.csv");
        
         //Finding occurrence for every celltype in taged entitiy
         
         Entity2cell occurrenceTable = new Entity2cell();
         occurrenceResult = occurrenceTable.compare((ArrayList<String>) entity2compare,abner);
        
        
         // To write a text file of output
        
         BufferedWriter br = new BufferedWriter(new FileWriter("/home/peeyush/Desktop/firstResult.csv"));
         StringBuilder csvFile = new StringBuilder();
        /*
         for(Occurrenceobj result : occurrenceResult){
             TreeMap<String, Integer> cellnames = result.getOccurrence();
             for(Map.Entry<String, Integer> cells : cellnames.entrySet()){
                 csvFile.append(",");
                 csvFile.append(cells.getKey());
                 
                
            }
             csvFile.append("\n");
             break;
         }
         */
         for(Occurrenceobj result : occurrenceResult){
         TreeMap<String, Integer> cellnames = result.getOccurrence();    
         for(Map.Entry<String, Integer> cells : cellnames.entrySet()){
                 csvFile.append(",");
                 csvFile.append(cells.getKey());
                 csvFile.append(cells.getValue());
                
            }  
         csvFile.append("\n");
              
         csvFile.append(result.getGene());
         System.out.println("Gene name before writing: "+result.getGene());
         TreeMap<String, Integer> occurre = result.getOccurrence();
            
         for(Map.Entry<String, Integer> entry : occurre.entrySet()){
         csvFile.append(",");
         Integer cellcount = entry.getValue();
         csvFile.append(String.valueOf(cellcount));
         }
                   
         csvFile.append("\n");
         }
         br.write(csvFile.toString());
         br.close();
         
    }
}