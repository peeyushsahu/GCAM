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

import static de.bonn.limes.gui.GeneMinerUI.abstractperSec;
import static de.bonn.limes.gui.GeneMinerUI.humanSynonym;
import static de.bonn.limes.gui.GeneMinerUI.maxAbstract;
import static de.bonn.limes.gui.GeneMinerUI.synonymCheck;
import de.bonn.limes.utils.Utility;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author peeyush
 */
public class AbstractSettings extends javax.swing.JFrame {

    /**
     * Creates new form AbstractSettings
     */
    public AbstractSettings() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        noOfAbstracts = new javax.swing.JTextField();
        human = new javax.swing.JRadioButton();
        mouse = new javax.swing.JRadioButton();
        synonym = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        abstractPerSec = new javax.swing.JTextField();
        set = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Maximum no. of abstracts:");

        noOfAbstracts.setForeground(new java.awt.Color(83, 83, 83));
        noOfAbstracts.setText("3000");

        human.setText("Human");
        human.setEnabled(false);
        human.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humanActionPerformed(evt);
            }
        });

        mouse.setText("Mouse");
        mouse.setEnabled(false);
        mouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mouseActionPerformed(evt);
            }
        });

        synonym.setText("Add Synonyms");
        synonym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                synonymActionPerformed(evt);
            }
        });

        jLabel3.setText("No. of Abstracts to download per second:");

        abstractPerSec.setForeground(new java.awt.Color(80, 80, 80));
        abstractPerSec.setText("3");

        set.setText("Set");
        set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(synonym)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(human)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mouse)
                        .addGap(18, 18, 18)
                        .addComponent(set, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noOfAbstracts, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(abstractPerSec, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(noOfAbstracts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(abstractPerSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(human)
                    .addComponent(mouse)
                    .addComponent(set)
                    .addComponent(synonym))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setActionPerformed

        if (!noOfAbstracts.getText().equals(null)) {
            maxAbstract = Integer.parseInt(noOfAbstracts.getText());
        }
        if (!abstractPerSec.getText().equals(null)) {
            abstractperSec = Integer.parseInt(abstractPerSec.getText());
        }
        if (synonym.isSelected()) {
            synonymCheck = 1;
            if (human.isSelected()) {
                humanSynonym = 1;
                mouse.setSelected(false);
            }
            if (mouse.isSelected()) {
                humanSynonym = 0;
                human.setSelected(false);
            }
        }
        this.dispose();

    }//GEN-LAST:event_setActionPerformed

    private void synonymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_synonymActionPerformed
        if (human.isEnabled() || mouse.isEnabled()) {
            human.setEnabled(false);
            mouse.setEnabled(false);
        } else {
            human.setEnabled(true);
            mouse.setEnabled(true);
        }
    }//GEN-LAST:event_synonymActionPerformed

    private void humanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humanActionPerformed
        mouse.setSelected(false);
    }//GEN-LAST:event_humanActionPerformed

    private void mouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mouseActionPerformed
        human.setSelected(false);
    }//GEN-LAST:event_mouseActionPerformed

    public void setabstract() {
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
                //new AbstractSettings().setVisible(true);
                AbstractSettings ui = new AbstractSettings();
                ui.setVisible(true);
                ui.setTitle("Abstract Fetch Settings");
                Utility.UI.adjustScreenPosition(ui);
                Utility.UI.addWindowClosingListener(ui);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abstractPerSec;
    private javax.swing.JRadioButton human;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton mouse;
    private javax.swing.JTextField noOfAbstracts;
    private javax.swing.JButton set;
    private javax.swing.JCheckBox synonym;
    // End of variables declaration//GEN-END:variables
}
