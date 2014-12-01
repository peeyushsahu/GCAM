/*
 * Copyright (C) 2014 Vishal Siramshetty <srmshtty[at]gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.mpg.molgen.cpdb;

import de.bonn.limes.core.ReadTextFile;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class PathwayEnricher {

    protected Cpdb cpdbService = new Cpdb();
    protected CpdbPortType cpdbPort = cpdbService.getCpdbPort();
    private final String entityType = "genes";
    private final String fsetType = "P";
    private final String accType = "hgnc-symbol";
    private final float pValue = (float) 0.05;
    private List<String> cpdbIdsFg = new ArrayList<>();
    private List<String> cpdbIdsBg = new ArrayList<>();
    private Holder<List<String>> holderFg = new Holder<>(cpdbIdsFg);
    private Holder<List<String>> holderBg = new Holder<>(cpdbIdsBg);
    private Holder<List<String>> holderPNames = new Holder<>();
    private Holder<List<String>> holderPDesc = new Holder<>();
    private Holder<List<String>> holderOEnt = new Holder<>();
    private Holder<List<String>> holderAEnt = new Holder<>();
    private Holder<List<String>> holderPValues = new Holder<>();
    private Holder<List<String>> holderQValues = new Holder<>();

    private List<String> inputGenes = new ArrayList<>();

    public PathwayEnricher(List<String> inputGenes) {
        this.inputGenes = inputGenes;
    }

    public String getCPDBVersion() {

        String version = cpdbPort.getCpdbVersion();

        StringBuilder v = new StringBuilder();
        v.append("Cpdb v").append(version.substring(4, version.length()));

        return v.toString();
    }

    public List<EPathway> fetchEnrichedPathways() throws SOAPFaultException {
        List<EPathway> ePathways = new ArrayList<>();
        try {
            cpdbPort.mapAccessionNumbers(accType, inputGenes, new Holder<>(inputGenes), holderFg);

            List<String> mappedGenes = holderFg.value;
            List<String> eGenes = new ArrayList<>();
            for (String m : mappedGenes) {
                if (!m.isEmpty()) {
                    eGenes.add(m);
                }
            }
            holderFg.value = eGenes;
            cpdbPort.overRepresentationAnalysis(entityType, fsetType, holderFg.value, holderBg.value, accType, pValue, holderPNames, holderPDesc, holderOEnt, holderAEnt, holderPValues, holderQValues);
            System.out.println("OR Analysis started");
            for (int i = 0; i < holderPNames.value.size(); i++) {

                EPathway eP = new EPathway();

                // get name and origin
                String name = holderPNames.value.get(i);
                String pName = getPName(name);
                String origin = getPOrigin(name);

                // check if the pathway is from either KEGG or Reactome
                if (origin.equals("KEGG") || origin.equals("Reactome") || origin.equals("Biocarta")) {
                    // set name and origin
                    eP.setName(pName);
                    eP.setOrigin(origin);

                    // set the identifier
                    String id = getPId(holderPDesc.value.get(i));
                    eP.setIdentifier(id);

                    // set the matched and total entities
                    int matched = Integer.parseInt(holderOEnt.value.get(i));
                    eP.setMatchedEntities(matched);

                    String totalE = holderAEnt.value.get(i);
                    String[] all = totalE.split("\\s+");

                    int total = Integer.parseInt(all[0]);
                    eP.setTotalEntities(total);

                    // set the P and Q values
                    double p = Double.parseDouble(holderPValues.value.get(i));
                    eP.setPvalue(p);
                    double q = Double.parseDouble(holderQValues.value.get(i));
                    eP.setQvalue(q);

                    ePathways.add(eP);
                }

            }
            return ePathways;
        } catch (SOAPFaultException ex) {
            System.err.println(ex.getCause());
        }
        return ePathways;
    }

    private String getPOrigin(String name) {

        int l = name.lastIndexOf("(");
        name = name.substring(0, name.length() - 1);
        String origin = name.substring(l + 1, name.length());

        return origin;
    }

    private String getPName(String name) {
        int l = name.lastIndexOf("(");
        name = name.substring(0, l - 1);
        return name;
    }

    private String getPId(String desc) {

        String[] descs = desc.split(";;");
        String url = descs[2];
        if (url.contains("www.genome.jp")) {
            // kegg id
            int k = url.lastIndexOf(":");
            String keggId = url.substring(k + 1, url.length());
            return keggId;
        } else {
            //reactome id
            int r = url.lastIndexOf("=");
            String reactomeId = url.substring(r + 1, url.length());
            return reactomeId;
        }

    }
/*
    public static void main(String[] args) {

        ReadTextFile reader = new ReadTextFile();
        List<String> gList = reader.extract("/home/peeyush/Desktop/APP.csv");
        PathwayEnricher enricher = new PathwayEnricher(gList);
        List<EPathway> ePaths = enricher.fetchEnrichedPathways();
        System.out.println("Total pathways enriched: " + ePaths.size());
        for (EPathway eP : ePaths) {
            System.out.println(eP.getName() + " " + eP.getPvalue());
        }
    }
*/
}
