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
package de.bonn.limes.gui;

import de.bonn.limes.core.AbstractReposite;
import de.bonn.limes.core.AbstractTagger;
import de.bonn.limes.core.CheckSynonymes;
import de.bonn.limes.core.Entity2cell;
import de.bonn.limes.core.FindDirectoryAddress;
import de.bonn.limes.core.ReadTextFile;
import de.bonn.limes.document.PubMedAbstract;
import de.bonn.limes.entities.Occurrenceobj;
import de.bonn.limes.utils.Utility;
import de.mpg.molgen.cpdb.EPathway;
import de.mpg.molgen.cpdb.PathwayEnricher;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author peeyush
 */
public class GeneMinerUI extends javax.swing.JFrame {

    private JTree tree;
    private int totAbs;
    private List<String> queries = new ArrayList<>();
    private List<String> all_genes = new ArrayList<>();
    private List<String> new_all_genes = new ArrayList<>();
    private TreeMap<String, List> abstracts = new TreeMap<>();
    private TreeMap<String, ArrayList> abnerResults;
    private File geneList;
    private List<String> entities2compare = new ArrayList<>();
    private ArrayList<Occurrenceobj> occurrenceResult = new ArrayList();
    private ReadTextFile reader = new ReadTextFile();
    private List<List<String>> synonyms = new ArrayList<>();
    public static String dirPath;
    public static String homePath;

    /**
     * Creates new form GeneMinerUI
     */
    public GeneMinerUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outerPanel = new javax.swing.JPanel();
        resultPanel = new javax.swing.JPanel();
        abstractPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        abstractShow = new javax.swing.JEditorPane();
        ProgressBar = new javax.swing.JProgressBar();
        statusBar = new javax.swing.JLabel();
        queryPanel = new javax.swing.JPanel();
        inputFile = new javax.swing.JTextField();
        uploadFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        additionalQuery = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fetchAbstracts = new javax.swing.JButton();
        synonymCheck = new javax.swing.JCheckBox();
        synonymHuman = new javax.swing.JRadioButton();
        synonymMouse = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        NerAnalysis = new javax.swing.JMenuItem();
        HeatMap = new javax.swing.JMenuItem();
        MenuPathway = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();
        guide = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resultPanel.setFont(new java.awt.Font("Serif", 0, 15)); // NOI18N
        resultPanel.setName(""); // NOI18N

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        abstractPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        abstractShow.setEditable(false);
        abstractShow.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        abstractShow.setMinimumSize(new java.awt.Dimension(100, 23));
        abstractShow.setPreferredSize(new java.awt.Dimension(100, 23));
        jScrollPane1.setViewportView(abstractShow);

        ProgressBar.setString("");
        ProgressBar.setStringPainted(true);
        ProgressBar.setVerifyInputWhenFocusTarget(false);

        statusBar.setFont(new java.awt.Font("Serif", 1, 15)); // NOI18N
        statusBar.setForeground(new java.awt.Color(253, 6, 21));
        statusBar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusBar.setToolTipText("");

        javax.swing.GroupLayout abstractPanelLayout = new javax.swing.GroupLayout(abstractPanel);
        abstractPanel.setLayout(abstractPanelLayout);
        abstractPanelLayout.setHorizontalGroup(
            abstractPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
            .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(abstractPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        abstractPanelLayout.setVerticalGroup(
            abstractPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abstractPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        queryPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        queryPanel.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 15)); // NOI18N

        inputFile.setText("path/to/geneList.csv");

        uploadFile.setText("File");
        uploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel1.setText("Choose  input file:");

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel2.setText("Additional query word(s):");

        fetchAbstracts.setText("Fetch Abstracts");
        fetchAbstracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchAbstractsActionPerformed(evt);
            }
        });

        synonymCheck.setText("Add synonyms for");

        synonymHuman.setText("Human OR");

        synonymMouse.setText("Mouse");

        javax.swing.GroupLayout queryPanelLayout = new javax.swing.GroupLayout(queryPanel);
        queryPanel.setLayout(queryPanelLayout);
        queryPanelLayout.setHorizontalGroup(
            queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(queryPanelLayout.createSequentialGroup()
                        .addGroup(queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(queryPanelLayout.createSequentialGroup()
                        .addGroup(queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(queryPanelLayout.createSequentialGroup()
                                .addComponent(synonymCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(synonymHuman)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(synonymMouse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fetchAbstracts))
                            .addComponent(additionalQuery, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(inputFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        queryPanelLayout.setVerticalGroup(
            queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queryPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadFile))
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(additionalQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fetchAbstracts)
                    .addComponent(synonymCheck)
                    .addComponent(synonymHuman)
                    .addComponent(synonymMouse))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout outerPanelLayout = new javax.swing.GroupLayout(outerPanel);
        outerPanel.setLayout(outerPanelLayout);
        outerPanelLayout.setHorizontalGroup(
            outerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerPanelLayout.createSequentialGroup()
                .addComponent(resultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(queryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abstractPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        outerPanelLayout.setVerticalGroup(
            outerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerPanelLayout.createSequentialGroup()
                .addComponent(queryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abstractPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(resultPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Analysis");

        NerAnalysis.setText("Named Entity Recignition");
        NerAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NerAnalysisActionPerformed(evt);
            }
        });
        jMenu2.add(NerAnalysis);

        HeatMap.setText("EnrichmentAnalysis");
        HeatMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeatMapActionPerformed(evt);
            }
        });
        jMenu2.add(HeatMap);

        MenuPathway.setText("PathwayAnalysis");
        MenuPathway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPathwayActionPerformed(evt);
            }
        });
        jMenu2.add(MenuPathway);

        jMenuBar1.add(jMenu2);

        help.setText("Help");

        about.setText("About");
        help.add(about);

        guide.setText("Guide");
        guide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guideActionPerformed(evt);
            }
        });
        help.add(guide);

        jMenuBar1.add(help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * this method writes synonym for query genes
     */
    private void WriteSynonyms(){
        
        BufferedWriter br = null;
            try {
                br = new BufferedWriter(new FileWriter(homePath+"/synonymList.csv"));
                StringBuilder csvFile = new StringBuilder();

                for (String gene : all_genes){
                    csvFile.append(gene);
                    System.out.println("Gene:   "+gene);
                    for(List eliaseList:synonyms){
                        List<String> geneList = eliaseList;
                       for(String eliase:geneList){
                            if(gene.toLowerCase().equals(eliase.toLowerCase())){
                                for(String elias:geneList){
                                    if(!elias.toLowerCase().equals(gene.toLowerCase())){
                                        csvFile.append(",");
                                        csvFile.append(elias);
                                    }
                                }                                
                                //break; 
                            }                            
                        }                       
                    }
                    csvFile.append("\n");
                }
                br.write(csvFile.toString());
                br.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                }
        }
    
    private void NerAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NerAnalysisActionPerformed

        // start NER process
        UITagger tagger = new UITagger();
        tagger.execute();
    }//GEN-LAST:event_NerAnalysisActionPerformed

    /**
     * This is Swing threading for NER 
     */
    
    class UITagger extends SwingWorker<Void, Void> {
        @Override
        protected void done() {
            ProgressBar.setVisible(false);
        }

        @Override
        protected Void doInBackground() throws Exception {
            statusBar.setText("NER & occurrence analysis running.....");
            ProgressBar.setMaximum(totAbs);
            ProgressBar.setVisible(true);
            if (!abstracts.isEmpty()) {
                abnerResults = new TreeMap<>();
                AbstractTagger nerTagger = new AbstractTagger(abstracts);
                abnerResults = nerTagger.tagAbstracts();
                abstracts = nerTagger.getAbstracts();

            } 
            else {
            }
            UIoccurrence occurrence = new UIoccurrence();
            occurrence.execute();
            return null;

        }

    }
    
    /**
     * This is for the occurrence analysis
     */

    class UIoccurrence extends SwingWorker<Void, Void> {

        @Override
        protected void done() {
            ProgressBar.setVisible(false);
            JOptionPane.showMessageDialog(null, "NER & Occurrence analysis finished");

        }

        @Override
        protected Void doInBackground() throws Exception {
            //statusBar.setText("Occurrence analysis started.");
            ProgressBar.setVisible(true);
            // step 5: Occurrence analysis of cell types in named entity
            ReadTextFile cellEntity = new ReadTextFile();
            entities2compare = cellEntity.extract("/home/peeyush/Desktop/cellTypes.csv");
            Entity2cell occurrenceTable = new Entity2cell();
            occurrenceResult = occurrenceTable.compare((ArrayList<String>) entities2compare, abnerResults);
            ProgressBar.setValue(0);
            //writing gene with synonyms
            WriteSynonyms();

            // This part writes output to a .csv format
            BufferedWriter br = null;
            try {
                br = new BufferedWriter(new FileWriter(homePath+"/firstResult.csv"));
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
                    System.out.println("Gene name before writing: " + result.getGene());
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
                Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;

        }

    }


    private void fetchAbstractsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetchAbstractsActionPerformed

        AbstractLoader loader = new AbstractLoader();
        loader.execute();
    }//GEN-LAST:event_fetchAbstractsActionPerformed

    class AbstractLoader extends SwingWorker<Void, Void> {
        
        @Override
        protected void done() {
            ProgressBar.setVisible(false);
            statusBar.setText("Total Abstracts: " + totAbs);
            JOptionPane.showMessageDialog(null, "Fetch successful!");

        }

        @Override
        protected Void doInBackground() {
            statusBar.setText("Downloading abstracts....");
            ProgressBar.setVisible(true);
            
            //check for synonyms
            if(synonymCheck.isSelected()){
                System.out.println("Synonyms are being considered");
                if(synonymHuman.isSelected()){
                    synonyms = reader.extract("/home/peeyush/NetBeansProjects/GeneMiner/Human_synonym.csv", ",");
                    CheckSynonymes cSynonym = new CheckSynonymes(all_genes);
                    new_all_genes = cSynonym.withSynonym(synonyms);
                    System.out.println("Size of Human synonym list:   "+new_all_genes.size());
                }
                if(synonymMouse.isSelected()){
                    synonyms = reader.extract("/home/peeyush/NetBeansProjects/GeneMiner/Mouse_synonym.csv", ",");
                    CheckSynonymes cSynonym = new CheckSynonymes(all_genes);
                    new_all_genes = cSynonym.withSynonym(synonyms);
                    System.out.println("Size of Mouse synonym list:   "+new_all_genes.size());  
                }
            }
            else{
                new_all_genes = reader.extract(geneList.getAbsolutePath());
            }
            
            // step 2: get additional query words
            String additionalQ = additionalQuery.getText().trim();
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

            // step 3: fetch the abstracts and save
            AbstractReposite abstractFetcher = new AbstractReposite();
            try {
                System.out.println("Fetching is working");
                abstracts = abstractFetcher.getAbstracts(queries);
                System.out.println("Total abstracts: " + abstracts.size());
                for (Map.Entry<String, List> abs : abstracts.entrySet()) {
                    System.out.println(abs.getKey() + " " + abs.getValue().size());
                }

                totAbs = buildTree(abstracts);
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

    }

    // This is to generate tree from genes and their PMIDS
    private Integer buildTree(TreeMap<String, List> abstracts) {
        int absCount = 0;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Abstracts");
        for (Map.Entry<String, List> abs : abstracts.entrySet()) {

            DefaultMutableTreeNode parentChild = new DefaultMutableTreeNode(abs.getKey());
            root.add(parentChild);

            ArrayList<PubMedAbstract> objAbs = (ArrayList<PubMedAbstract>) abs.getValue();
            int NoAbstracts = 0;
            for (PubMedAbstract abst : objAbs) { //this will form tree for only 10 abstract per gene in GUI
                NoAbstracts++;
                absCount++;
                DefaultMutableTreeNode child = new DefaultMutableTreeNode("PMID:" + abst.getPMID());
                parentChild.add(child);
                if(NoAbstracts == 10)break;
            }
        }
        tree = new JTree(root);
        tree.setShowsRootHandles(true);
        System.out.println("Total nodes: " + root.getChildCount());
        System.out.println("Total siblings: " + root.getSiblingCount());
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // adding selection listener
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                displayAbstract();
            }
        });

        JScrollPane sp = new JScrollPane(tree);
        sp.setViewportView(tree);
        resultPanel.revalidate();
        resultPanel.repaint();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(BorderLayout.CENTER, sp);
        return absCount;
    }

    private void displayAbstract() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        TreeNode parent = node.getParent();
        String gene = parent.toString();
        if (node != null) {
            Object nodeInfo = node.getUserObject();
            if (node.isLeaf()) {
                String pmid = (String) nodeInfo;
                System.out.println("Abstract selected: " + pmid);
                String[] id = pmid.split(":");
                int ID = Integer.parseInt(id[1]);
                String fullAbstract = getCurrentAbstract(ID, gene);
                abstractShow.setContentType("text/html");
                abstractShow.setText(fullAbstract);
                abstractShow.setCaretPosition(0);

            }
        }
    }

    private String getCurrentAbstract(int id, String gene) {

        List<PubMedAbstract> results = this.abstracts.get(gene);

        for (PubMedAbstract abst : results) {
            int pmid = abst.getPMID();
            if (id == pmid) {
                //System.out.println(abst.getUpdatedAbstract()==null);
                if (abst.getUpdatedAbstract() == null) {
                    return abst.getCompleteAbstract();
                } else {
                    return abst.getUpdatedAbstract();
                }
            }
        }

        return null;
    }

    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileActionPerformed
        
        geneList = Utility.UI.getFile(queryPanel, new FileNameExtensionFilter(" CSV File (*.csv) ", "csv"));
        if (geneList.exists()) {
            inputFile.setText(geneList.getAbsolutePath());
            FindDirectoryAddress createDir = new FindDirectoryAddress();
            createDir.getpath();
            all_genes = reader.extract(geneList.getAbsolutePath());
        }
    }//GEN-LAST:event_uploadFileActionPerformed

    private void HeatMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeatMapActionPerformed

        enrichmentAnalysis performAnalysis = new enrichmentAnalysis();
        performAnalysis.runthis();
    }//GEN-LAST:event_HeatMapActionPerformed

    private void guideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guideActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guideActionPerformed

    private void MenuPathwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPathwayActionPerformed

        List<String> genes = all_genes;
        System.out.println(genes.toString());
        PathwayEnricher enricher = new PathwayEnricher(genes);
        List<EPathway> ePathways = enricher.fetchEnrichedPathways();
        if (!ePathways.isEmpty()) {
            for (EPathway eP : ePathways) {
                System.out.println("EPathway: " + eP.toString());
            }
        } else {
            System.out.println("No pathways enriched.");
        }

    }//GEN-LAST:event_MenuPathwayActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the SeaGlass look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("Seaglass LAF not available using Ocean.");
            try {
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } catch (UnsupportedLookAndFeelException e2) {
                System.err.println("Unable to use Ocean LAF using default.");
            }
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GeneMinerUI ui = new GeneMinerUI();
                ui.setVisible(true);
                ProgressBar.setVisible(false);
                GraphicsConfiguration gc = ui.getGraphicsConfiguration();
                Rectangle bounds = gc.getBounds();
                Dimension size = ui.getPreferredSize();
                ui.setLocation((int) ((bounds.width / 2) - (size.getWidth() / 2)),
                        (int) ((bounds.height / 2) - (size.getHeight() / 2)));
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem HeatMap;
    private javax.swing.JMenuItem MenuPathway;
    private javax.swing.JMenuItem NerAnalysis;
    public static javax.swing.JProgressBar ProgressBar;
    private javax.swing.JMenuItem about;
    private javax.swing.JPanel abstractPanel;
    private javax.swing.JEditorPane abstractShow;
    private javax.swing.JTextField additionalQuery;
    private javax.swing.JButton fetchAbstracts;
    private javax.swing.JMenuItem guide;
    private javax.swing.JMenu help;
    private javax.swing.JTextField inputFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel outerPanel;
    private javax.swing.JPanel queryPanel;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JLabel statusBar;
    private javax.swing.JCheckBox synonymCheck;
    private javax.swing.JRadioButton synonymHuman;
    private javax.swing.JRadioButton synonymMouse;
    private javax.swing.JButton uploadFile;
    // End of variables declaration//GEN-END:variables
}
