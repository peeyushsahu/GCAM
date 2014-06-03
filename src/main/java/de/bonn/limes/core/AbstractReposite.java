
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
import de.bonn.limes.pubmed.PubMedFetcher;
import de.bonn.limes.pubmed.PubMedRecord;
import de.bonn.limes.pubmed.PubmedSearch;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.axis2.AxisFault;
import org.xml.sax.SAXException;
import com.google.common.collect.Lists;

/**
 *
 * @author peeyush
 * 
 * this class will use the gene names to retrieve pubmed abstracts 
 */
public class AbstractReposite {
    
    List<String> Glist; //To store gene list from user
    
    
    public TreeMap<String,List> getAbstracts(List<String> GeneList) throws InterruptedException{
        this.Glist = GeneList;
        try {
            TreeMap<String,List> geneWidAbstract = new TreeMap();// holds all PubMedAbstract object for gene list with genes
            List<PubMedAbstract> abstracts; // holds all PubMedAbstract object for gene list
        
            
            for (String gene: Glist){
                System.out.println(gene.length());
                if(gene.length()!=0){
                abstracts = new ArrayList<>();
                System.out.println("Gene for abstract:  "+gene);
                List<Integer> ids = new PubmedSearch().getPubMedIDs(gene,3500);
                
                System.out.println("+++++++++++++++++++"+ids.isEmpty());
                if(ids.get(0)!= 0){
                System.out.println(ids);
                    
                
                // if PMID list has less than 50 ids
                    if(ids.size()< 500){
                    List<PubMedRecord> records = new PubMedFetcher().getPubMedRecordForIDs(ids);
                        if(records == null){
                            System.out.println("No abstract to show..");
                        }

                        for (PubMedRecord record : records) {

                            PubMedAbstract abs = new PubMedAbstract();
                            abs.setAbstractText(record.getAbstract());
                            abs.setPMID(record.getPubMedID());
                            abs.setTitle(record.getTitle());
                            abs.setYear(String.valueOf(record.getYear()));
                            abstracts.add(abs);

                            /* WORKING
                             System.out.println("+++++++++++++++++++++++++++"+record.getTitle());
                            */
                        }       
                    }
              
                // if
                    
                else{                     
                    int countabst=0;    
                    List<List<Integer>> parts = Lists.partition(ids,500);
                    for(List<Integer> Slist:parts){
                        
                        System.out.println(Slist.size());
                        List<PubMedRecord> records = new PubMedFetcher().getPubMedRecordForIDs(Slist);
                        countabst++;
                        //System.out.println(gene);
                        System.out.println("For "+gene+" number of abstracts Fetched:   "+countabst*500);
            
                
            
                         for (PubMedRecord record : records) {

                            PubMedAbstract abs = new PubMedAbstract();
                            abs.setAbstractText(record.getAbstract());
                            abs.setPMID(record.getPubMedID());
                            abs.setTitle(record.getTitle());
                            abs.setYear(String.valueOf(record.getYear()));
                            abstracts.add(abs);
                         }
                    } 
                }          
         }   
                
                System.out.println("Size of Abstracts:  "+abstracts.size());
             geneWidAbstract.put(gene, abstracts);
               
                }    
        }
         
            return geneWidAbstract;
}
    
          catch (AxisFault ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (RemoteException ex) {
                          Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (ParserConfigurationException ex) {
            Logger.getLogger(AbstractReposite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    

    }
}
