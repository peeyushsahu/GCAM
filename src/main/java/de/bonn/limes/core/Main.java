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

import static de.bonn.limes.core.FindDirectoryAddress.dirPath;
import static de.bonn.limes.core.FindDirectoryAddress.homePath;
import java.util.ArrayList;
import java.util.List;
import de.bonn.limes.entities.Occurrenceobj;
import de.bonn.limes.gui.GeneMinerUI;
import de.bonn.limes.gui.enrichmentAnalysis;
import de.mpg.molgen.cpdb.EPathway;
import de.mpg.molgen.cpdb.PathwayEnricher;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RserveException;
import java.io.FileNotFoundException;

/**
 *
 * @author peeyush
 */
public class Main {

    /**
     * this method writes synonym for query genes
     */
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, FileNotFoundException {
        String filepath = "/home/peeyush/Desktop/testgene.csv";
        System.out.println(filepath);
        String addQuery = "";
        int maxAbs = 10;
        int perRun = 10;
        int synonym = 0;
        int human = 1;
        int mouse = 0;
        int thread = 4;
        float Ethreshold = (float) 0.3;
        float Pthreshold = (float) 0.05;
        String test = "fisher";
        boolean pathway = true;
        
        for (int i = 0; i < args.length; i++) {
            String[] parameter = args[i].split("=");
            if (parameter[0].contains("filepath")) {
                filepath = parameter[1].trim();
            }
            if (parameter[0].contains("addQuery")) {
                addQuery = parameter[1].trim();
            }
            if (parameter[0].contains("maxAbs")) {
                maxAbs = Integer.parseInt(parameter[1].trim());
            }
            if (parameter[0].contains("perRun")) {
                perRun = Integer.parseInt(parameter[1].trim());
            }
            if (parameter[0].contains("synonym")) {
                synonym = Integer.parseInt(parameter[1].trim());
            }
            if (parameter[0].contains("mouse")) {
                mouse = Integer.parseInt(parameter[1].trim());
                human = 0;
            }
            if (parameter[0].contains("Pthreshold")) {
                Pthreshold = Float.parseFloat(parameter[1].trim());
            }
            if (parameter[0].contains("test")) {
                test = parameter[1].trim();
            }
            if (parameter[0].contains("thread")) {
                thread = Integer.parseInt(parameter[1].trim());
            }
        }
        
        //check if user provided required parameters
        if (filepath == null){
            throw new FileNotFoundException("Please provide path to genename.csv");
        }
        
        FindDirectoryAddress createDir = new FindDirectoryAddress();
        createDir.getpath();

        ReadTextFile read;
        List<String> new_all_genes = new ArrayList<>();
        List<String> synonyms = new ArrayList<>();
        List<String> all_genes = new ArrayList<>();
        List<String> queries = new ArrayList<>();
        TreeMap<String, ArrayList> abstracts = new TreeMap<>();
        List<TreeMap<String, ArrayList>> abnerResults = new ArrayList<>();
        TreeMap<String, ArrayList> abnerResult = new TreeMap();
        List<String> entities2compare = new ArrayList<>();
        ArrayList<Occurrenceobj> occurrenceResult = new ArrayList();

        System.out.println("Check point: 1");
        read = new ReadTextFile();
        all_genes = read.extract(filepath);
        System.out.println(all_genes);
        CheckSynonymes cSynonym = new CheckSynonymes(all_genes);
        //check for synonyms
        if (synonym == 1) {
            System.out.println("Synonyms are being considered");
            if (human == 1) {
                read = new ReadTextFile();
                synonyms = read.extract(dirPath + "/resources/Human_synonym.csv", ",");
                new_all_genes = cSynonym.withSynonym(synonyms);
                System.out.println("Size of Human synonym list:   " + new_all_genes.size());
            }
            if (mouse == 1) {
                read = new ReadTextFile();
                synonyms = read.extract(dirPath + "/resources/Mouse_synonym.csv", ",");
                new_all_genes = cSynonym.withSynonym(synonyms);
                System.out.println("Size of Mouse synonym list:   " + new_all_genes.size());
            }
            cSynonym.WriteSynonyms(synonyms);
        } else {
            new_all_genes = all_genes;
        }

        System.out.println("Check point: 2");

        System.out.println("Check point: 3");
        System.out.println(new_all_genes);
        // step 2: get additional query words
        String additionalQ = addQuery;
        System.out.println(addQuery.isEmpty());
        if (!additionalQ.isEmpty()) {

            if (additionalQ.contains(",")) {
                String[] adds = additionalQ.split(",");
                List<String> qWords = Arrays.asList(adds);

                for (String gene : new_all_genes) {

                    StringBuilder queryBuider = new StringBuilder();
                    if (!qWords.isEmpty()) {
                        queryBuider.append(gene);
                        for (String q : qWords) {
                            queryBuider.append(" AND ").append(q);
                        }
                        queries.add(queryBuider.toString());
                    } else {
                        queries.add(gene);
                    }
                }
            } else {
                for (String gene : new_all_genes) {
                    StringBuilder queryBuider = new StringBuilder();
                    queryBuider.append(gene).append(" AND ").append(additionalQ);
                    queries.add(queryBuider.toString());
                }
            }
        } else {
            for (String gene : new_all_genes) {
                queries.add(gene);
            }
        }
        System.out.println("Check point: 4");
        // step 3: fetch the abstracts and save
        System.out.println(queries);
        AbstractReposite abstractFetcher = new AbstractReposite();
        try {
            System.out.println("Fetching is working");
            abstracts = abstractFetcher.getAbstracts(queries, maxAbs, perRun);
            //System.out.println("Total abstracts: " + abstracts.size());
            for (Map.Entry<String, ArrayList> abs : abstracts.entrySet()) {
                //System.out.println(abs.getKey() + " " + abs.getValue().size());
            }

        } catch (InterruptedException ex) {
            System.err.println("Abstract fetching is intrupted");
        }

        // step 4: This will perform NER on all abstracts
        TimerManager countTime = new TimerManager();
        countTime.getTimeElapsed("seconds");
        
        if (!abstracts.isEmpty()) {
            if (abstracts.size() > 20) {
                // call method for multithreading
                ListOperations mult = new ListOperations();
                System.out.println("Using multithreading with no. of threads: "+thread);
                abnerResult = mult.MultithreadigRunable(abstracts);
               
            } else {
                        AbstractTagger nerTagger = new AbstractTagger(abstracts);
                        abnerResult = nerTagger.tagAbstracts();
                //abstracts = nerTagger.getAbstracts();
            }
        } else {
            System.err.println("Fetched abstract list is empty.");
        }
        double time = countTime.getElapsedTime();
        System.out.println("Time elapsed in NER: "+time);
        
        
        // step 5: This will perform the occurence analysis
        read = new ReadTextFile();
        entities2compare = read.extract(dirPath + "/resources/cellTypes.csv");
        System.out.println(entities2compare);
        Entity2cell occurrenceTable = new Entity2cell();
        occurrenceResult = occurrenceTable.compare((ArrayList<String>) entities2compare, abnerResult);

        // This part writes output to a .csv format
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(homePath + "/firstResult.csv"));
            StringBuilder csvFile = new StringBuilder();

            for (Occurrenceobj result : occurrenceResult) {
                TreeMap<String, Integer> cellnames = result.getOccurrence();
                for (Map.Entry<String, Integer> cells : cellnames.entrySet()) {
                    csvFile.append(",");
                    csvFile.append(cells.getKey());

                }
                csvFile.append("\n");
                break;
            }

            for (Occurrenceobj result : occurrenceResult) {

                csvFile.append(result.getGene());
                //System.out.println("Gene name before writing: " + result.getGene());
                TreeMap<String, Integer> occurre = result.getOccurrence();

                for (Map.Entry<String, Integer> entry : occurre.entrySet()) {
                    csvFile.append(",");
                    Integer cellcount = entry.getValue();
                    csvFile.append(String.valueOf(cellcount));
                }

                csvFile.append("\n");
            }
            br.write(csvFile.toString());
            br.close();
        } catch (IOException ex) {
            System.err.println("Writer can not be initialized");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.err.println("Occurrence tabel can not be written");
            }
        }

        //Running R for statistical analysis
        //String test = "fisher";
        //float Ethreshold;
        //float Pthreshold;
        try {
            SourcingRFile DoinR = new SourcingRFile();
            DoinR.checkLocalRserve();
            DoinR.analyse(Pthreshold, Ethreshold, synonym, test);

        } catch (RserveException ex) {
            Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (REXPMismatchException | REngineException ex) {
        } catch (InterruptedException ex) {
            Logger.getLogger(enrichmentAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }

        // This will perform the pthway analysis
        System.out.println(all_genes.toString());
        PathwayEnricher enricher = new PathwayEnricher(all_genes);
        List<EPathway> ePathways = enricher.fetchEnrichedPathways();

        // This part writes output to a pathways in csv format
        BufferedWriter path = null;
        try {
            path = new BufferedWriter(new FileWriter(homePath + "/enrichedPathways.csv"));
            StringBuilder csvFile = new StringBuilder();
            csvFile.append("Name of Pathway");
            csvFile.append(",");
            csvFile.append("Origin database");
            csvFile.append(",");
            csvFile.append("P-value");
            csvFile.append(",");
            csvFile.append("Q-value");
            csvFile.append("\n");
            if (!ePathways.isEmpty()) {
                for (EPathway eP : ePathways) {
                    System.out.println("EPathway: " + eP.toString());
                    csvFile.append(eP.getName());
                    csvFile.append(",");
                    csvFile.append(eP.getOrigin());
                    csvFile.append(",");
                    csvFile.append(eP.getPvalue());
                    csvFile.append(",");
                    csvFile.append(eP.getPvalue());
                    csvFile.append("\n");
                }
            } else {
                System.out.println("No pathways enriched.");
            }
            path.write(csvFile.toString());
            path.close();
        } catch (IOException ex) {
            System.err.println("Writer can not be initialized");
        } finally {
            try {
                path.close();
            } catch (IOException ex) {
                System.err.println("Occurrence tabel can not be written");
            }
        }
    }

}
