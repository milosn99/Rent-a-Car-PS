/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.awt.Color;
import kontroler.Controller;
import niti.Server;

/**
 *
 * @author milos
 */
public class GlavnaForma extends javax.swing.JFrame {

    Server serverNit;

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() {
        initComponents();
        setLocationRelativeTo(null);
        btnZaustavi.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        btnPokreni = new javax.swing.JButton();
        btnZaustavi = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuKonfiguracija = new javax.swing.JMenu();
        miBaza = new javax.swing.JMenuItem();
        miPort = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Status servera:");

        btnPokreni.setText("Pokreni server");
        btnPokreni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniActionPerformed(evt);
            }
        });

        btnZaustavi.setText("Zaustavi server");
        btnZaustavi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviActionPerformed(evt);
            }
        });

        menuKonfiguracija.setText("Konfiguracija");

        miBaza.setText("Baza");
        miBaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBazaActionPerformed(evt);
            }
        });
        menuKonfiguracija.add(miBaza);

        miPort.setText("Port");
        miPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPortActionPerformed(evt);
            }
        });
        menuKonfiguracija.add(miPort);

        jMenuBar1.add(menuKonfiguracija);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPokreni, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnZaustavi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreni)
                    .addComponent(btnZaustavi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPokreniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniActionPerformed
        Controller.getInstanca().startServer();

        lblStatus.setText("Server je pokrenut");
        lblStatus.setForeground(Color.green.darker());
        btnPokreni.setEnabled(false);
        btnZaustavi.setEnabled(true);
        menuKonfiguracija.setEnabled(false);
    }//GEN-LAST:event_btnPokreniActionPerformed

    private void btnZaustaviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZaustaviActionPerformed
        Controller.getInstanca().stopServer();

        lblStatus.setText("Server je zaustavljen");
        lblStatus.setForeground(Color.red.darker());
        btnPokreni.setEnabled(true);
        btnZaustavi.setEnabled(false);
        menuKonfiguracija.setEnabled(true);
    }//GEN-LAST:event_btnZaustaviActionPerformed

    private void miBazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBazaActionPerformed
        FormaBaza fb = new FormaBaza(this, true);
        fb.setVisible(true);
    }//GEN-LAST:event_miBazaActionPerformed

    private void miPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPortActionPerformed
        FormaPort fp = new FormaPort(this, true);
        fp.setVisible(true);
    }//GEN-LAST:event_miPortActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPokreni;
    private javax.swing.JButton btnZaustavi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenu menuKonfiguracija;
    private javax.swing.JMenuItem miBaza;
    private javax.swing.JMenuItem miPort;
    // End of variables declaration//GEN-END:variables
}
