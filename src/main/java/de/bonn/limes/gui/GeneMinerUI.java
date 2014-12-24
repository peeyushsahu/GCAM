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
import static de.bonn.limes.core.FindDirectoryAddress.dirPath;
import static de.bonn.limes.core.FindDirectoryAddress.homePath;
import de.bonn.limes.core.ListOperations;
import de.bonn.limes.core.ReadTextFile;
import de.bonn.limes.core.TimerManager;
import de.bonn.limes.document.PubMedAbstract;
import de.bonn.limes.entities.Occurrenceobj;
import de.bonn.limes.utils.Utility;
import de.mpg.molgen.cpdb.EPathway;
import de.mpg.molgen.cpdb.PathwayEnricher;
import java.awt.BorderLayout;
import java.awt.Desktop;
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
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
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
    private List<String> all_genes;
    private List<String> new_all_genes;
    private TreeMap<String, List> abstracts;
    private TreeMap<String, ArrayList> abnerResults;
    private File geneList;
    private List<String> entities2compare = new ArrayList<>();
    private ArrayList<Occurrenceobj> occurrenceResult = new ArrayList();
    private ReadTextFile reader = new ReadTextFile();
    private List<List<String>> synonyms;
    public static Integer synonymCheck = 0;
    public static Integer humanSynonym;
    public static Integer maxAbstract = 0;
    public static Integer abstractperSec = 0;
    public static Integer progressbarCount = 0;
    private String osname;
    private String seprator;
    private static final Logger GCAMLog = Logger.getLogger("de.limes.bonn");
    

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
        fetchSettings = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        EnrichmentMenu = new javax.swing.JMenu();
        NerAnalysis = new javax.swing.JMenuItem();
        HeatMap = new javax.swing.JMenuItem();
        MenuPathway = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        guide = new javax.swing.JMenuItem();
        about = new javax.swing.JMenuItem();

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

        inputFile.setForeground(new java.awt.Color(132, 132, 132));
        inputFile.setText("path/to/geneList.csv");

        uploadFile.setText("File");
        uploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel1.setText("Choose  input file:");

        additionalQuery.setForeground(new java.awt.Color(132, 132, 132));

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel2.setText("Additional query word(s):");

        fetchAbstracts.setText("Fetch Abstracts");
        fetchAbstracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchAbstractsActionPerformed(evt);
            }
        });

        fetchSettings.setText("Settings for fetch");
        fetchSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fetchSettingsActionPerformed(evt);
            }
        });

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
                                .addComponent(fetchSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
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
                    .addComponent(fetchSettings))
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

        jMenuBar1.setName(""); // NOI18N

        EnrichmentMenu.setText("Analysis");

        NerAnalysis.setText("Named Entity Recignition");
        NerAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NerAnalysisActionPerformed(evt);
            }
        });
        EnrichmentMenu.add(NerAnalysis);

        HeatMap.setText("EnrichmentAnalysis");
        HeatMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeatMapActionPerformed(evt);
            }
        });
        EnrichmentMenu.add(HeatMap);

        MenuPathway.setText("PathwayAnalysis");
        MenuPathway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPathwayActionPerformed(evt);
            }
        });
        EnrichmentMenu.add(MenuPathway);

        jMenuBar1.add(EnrichmentMenu);

        help.setText("Help");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        guide.setText("Manual");
        guide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guideActionPerformed(evt);
            }
        });
        help.add(guide);

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        help.add(about);

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

    private void NerAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NerAnalysisActionPerformed

        // start NER process
        UITagger tagger = new UITagger();
        tagger.execute();
    }//GEN-LAST:event_NerAnalysisActionPerformed

    /**
     * This returns the separator for file system
     */
    
    private void sep4os(){
        osname = System.getProperty("os.name");
        if (osname != null && osname.length() >= 7 && osname.substring(0,7).equals("Windows")) {
            seprator = "\\";
        }
        else{
            seprator = "/";
        }
        System.out.println("Separator is set");
    }
    
    /**
     * This is Swing threading for NER
     */
    class UITagger extends SwingWorker<Void, Void> {

        @Override
        protected void done() {
            ProgressBar.setVisible(false);
            statusBar.setText("NER & Occurrence analysis successful!.");
        }

        @Override
        protected Void doInBackground() throws Exception {
            statusBar.setText("NER & occurrence analysis in process...");
            ProgressBar.setMaximum(totAbs);
            ProgressBar.setVisible(true);
            if (!abstracts.isEmpty()) {
                if (abstracts.size() > 10) {
                // call method for multithreading
                ListOperations mult = new ListOperations();
                System.out.println("Using multithreading with no. of threads: "+3);
                abnerResults = mult.NERmultithreading(abstracts, 3);
                } 
                else {
                    AbstractTagger nerTagger = new AbstractTagger(abstracts);
                    abnerResults = nerTagger.tagAbstracts();
                //abstracts = nerTagger.getAbstracts();
                }
            } else {
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
            Utility.UI.showInfoMessage(getRootPane(), "NER & Occurrence analysis successful!");

        }

        @Override
        protected Void doInBackground() throws Exception {
            //statusBar.setText("Occurrence analysis started.");
            ProgressBar.setVisible(true);
            // step 5: Occurrence analysis of cell types in named entity
            ReadTextFile cellEntity = new ReadTextFile();
            // entities2compare = cellEntity.extract(dirPath + "/cellTypes.csv");
            entities2compare = cellEntity.extract("resources"+seprator+"cellTypes.csv");
            Entity2cell occurrenceTable = new Entity2cell();
            occurrenceResult = occurrenceTable.compare((ArrayList<String>) entities2compare, abnerResults);
            ProgressBar.setValue(0);


            // This part writes output to a .csv format
            BufferedWriter br = null;
            try {
                br = new BufferedWriter(new FileWriter(homePath + seprator+"firstResult.csv"));
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
            if (totAbs > 0) {
                statusBar.setText("Total Abstracts: " + totAbs);
                Utility.UI.showInfoMessage(getRootPane(), "Fetch successful!");
            }

        }

        @Override
        protected Void doInBackground() {
            if (geneList == null) {
                Utility.UI.showInfoMessage(getRootPane(), "No input file selected");
            } else {

                CheckSynonymes cSynonym = new CheckSynonymes(all_genes);
                //TimerManager timer = new TimerManager();
                //timer.getTimeElapsed(0.0, "minutes");
                statusBar.setText("Fetching abstracts...");
                ProgressBar.setVisible(true);
                new_all_genes = new ArrayList<>();
                synonyms = new ArrayList<>();
                abstracts = new TreeMap<>();

                //check for synonyms
                if (synonymCheck == 1) {
                    System.out.println("Synonyms are being considered");
                    if (humanSynonym == 1) {
                        // synonyms = reader.extract(dirPath + "/Human_synonym.csv", ",");
                        synonyms = reader.extract("resources"+seprator+"Human_synonym.csv", ",");
                        new_all_genes = cSynonym.withSynonym(synonyms);
                        //writing gene with synonyms
                        cSynonym.WriteSynonyms(new_all_genes);
                        System.out.println("Size of Human synonym list:   " + new_all_genes.size());
                    }
                    if (humanSynonym == 0) {
                        //synonyms = reader.extract(dirPath + "/Mouse_synonym.csv", ",");
                        synonyms = reader.extract("resources"+seprator+"Mouse_synonym.csv", ",");
                        new_all_genes = cSynonym.withSynonym(synonyms);
                        System.out.println("Size of Mouse synonym list:   " + new_all_genes.size());
                        //writing gene with synonyms
                        cSynonym.WriteSynonyms(new_all_genes);
                    }
                } else {
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
                    System.out.println("Fetching is progress...");
                    abstracts = abstractFetcher.getAbstracts(queries, maxAbstract, abstractperSec);
                    totAbs = buildTree(abstracts);
                } catch (InterruptedException ex) {
                    GCAMLog.log(Level.SEVERE, ex.getLocalizedMessage());
                }
            }

            return null;

        }

    }

    /**
     * This is to generate tree from genes and their PMIDS
     * @param abstracts
     * @return 
     */
    private Integer buildTree(TreeMap<String, List> abstracts) {
        int absCount = 0;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Abstracts");
        for (Map.Entry<String, List> abs : abstracts.entrySet()) {

            DefaultMutableTreeNode parentChild = new DefaultMutableTreeNode(abs.getKey());
            root.add(parentChild);

            ArrayList<PubMedAbstract> objAbs = (ArrayList<PubMedAbstract>) abs.getValue();
            int NoAbstracts = 0;
            absCount = absCount + objAbs.size();
            for (PubMedAbstract abst : objAbs) { //this will form tree for only 10 abstract per gene in GUI
                NoAbstracts++;
                //absCount++;
                DefaultMutableTreeNode child = new DefaultMutableTreeNode("PMID:" + abst.getPMID());
                parentChild.add(child);
                if (NoAbstracts == 50) {
                    break;
                }
            }
        }
        tree = new JTree(root);
        tree.setShowsRootHandles(true);
        //System.out.println("Total nodes: " + root.getChildCount());
        //System.out.println("Total siblings: " + root.getSiblingCount());
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
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
/**
 * This method displays abstract if pmid is selected.
 */
    private void displayAbstract() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        TreeNode parent = node.getParent();
        String gene = parent.toString();
        if (node != null) {
            Object nodeInfo = node.getUserObject();
            if (node.isLeaf()) {
                String pmid = (String) nodeInfo;
                //System.out.println("Abstract selected: " + pmid);
                String[] id = pmid.split(":");
                int ID = Integer.parseInt(id[1]);
                String fullAbstract = getCurrentAbstract(ID, gene);
                abstractShow.setContentType("text/html");
                abstractShow.setText(fullAbstract);
                abstractShow.setCaretPosition(0);

            }
        }
    }
/**
 * This method retrieve the user selected pmid.
 * @param id
 * @param gene
 * @return 
 */
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
    
    /**
     * Open upload option.
     * @param evt 
     */

    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileActionPerformed

        geneList = Utility.UI.getFile(getRootPane(), new FileNameExtensionFilter(" CSV File (*.csv) ", "csv"));
        if (geneList == null) {
            GCAMLog.log(Level.WARNING, "No file selected.");
        } else {
            if (geneList.isFile()) {
                all_genes = new ArrayList<>();
                inputFile.setText(geneList.getAbsolutePath());
                FindDirectoryAddress createDir = new FindDirectoryAddress();
                createDir.getpath();
                all_genes = reader.extract(geneList.getAbsolutePath());
                System.out.println("Input gene list:    "+all_genes);
                sep4os();
            }
        }

    }//GEN-LAST:event_uploadFileActionPerformed

    private void HeatMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeatMapActionPerformed
        statusBar.setText("Select parameters for statistical analysis.");
        enrichmentAnalysis performAnalysis = new enrichmentAnalysis();
        performAnalysis.runthis();
    }//GEN-LAST:event_HeatMapActionPerformed

    private void guideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guideActionPerformed
        sep4os();
        dirPath = System.getProperty("user.dir");     
        File usrManual = new File(dirPath+seprator+"Manual.htm");
        System.out.println(dirPath+seprator+"Manual.htm");
        try {
            Desktop.getDesktop().open(usrManual);
        } catch (IOException ex) {
            Logger.getLogger(GeneMinerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_guideActionPerformed

    private void MenuPathwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPathwayActionPerformed
        if (geneList == null) {
            Utility.UI.showInfoMessage(getRootPane(), "No input file selected");
        } else {

            statusBar.setText("Pathway enrichment in progress...");
            List<String> genes = all_genes;
            PathwayEnricher enricher = new PathwayEnricher(genes);
            List<EPathway> ePathways = enricher.fetchEnrichedPathways();

            // This part writes output to a .csv format
            BufferedWriter br = null;
            try {
                br = new BufferedWriter(new FileWriter(homePath + seprator + "enrichedPathways.csv"));
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
                        csvFile.append(eP.getName());
                        csvFile.append(",");
                        csvFile.append(eP.getOrigin());
                        csvFile.append(",");
                        csvFile.append(eP.getPvalue());
                        csvFile.append(",");
                        csvFile.append(eP.getPvalue());
                        csvFile.append("\n");
                    }
                    statusBar.setText("Pathway enrichment successful!");
                    Utility.UI.showInfoMessage(getRootPane(), "Enriched pathways saved to: " + homePath + seprator +"enrichedPathways.csv");

                } else {
                    statusBar.setText("No pathways found for the input genes");
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
    }//GEN-LAST:event_MenuPathwayActionPerformed

    private void fetchSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fetchSettingsActionPerformed
        AbstractSettings abstractSetting = new AbstractSettings();
        abstractSetting.setabstract();
    }//GEN-LAST:event_fetchSettingsActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        revalidate();
        repaint();
        About about = new About();
        about.run_about();
    }//GEN-LAST:event_aboutActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
  
    }//GEN-LAST:event_helpActionPerformed

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
                ui.setTitle("GCAM:A Gene CellType Association Miner");
                ui.setVisible(true);
                ProgressBar.setVisible(false);
                Utility.UI.adjustScreenPosition(ui);
                Utility.UI.addUIClosingListener(ui);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu EnrichmentMenu;
    private javax.swing.JMenuItem HeatMap;
    private javax.swing.JMenuItem MenuPathway;
    private javax.swing.JMenuItem NerAnalysis;
    public static javax.swing.JProgressBar ProgressBar;
    private javax.swing.JMenuItem about;
    private javax.swing.JPanel abstractPanel;
    private javax.swing.JEditorPane abstractShow;
    private javax.swing.JTextField additionalQuery;
    private javax.swing.JButton fetchAbstracts;
    private javax.swing.JButton fetchSettings;
    private javax.swing.JMenuItem guide;
    private javax.swing.JMenu help;
    private javax.swing.JTextField inputFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel outerPanel;
    private javax.swing.JPanel queryPanel;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JLabel statusBar;
    private javax.swing.JButton uploadFile;
    // End of variables declaration//GEN-END:variables
}
