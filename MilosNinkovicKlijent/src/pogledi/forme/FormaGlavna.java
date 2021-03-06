/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.forme;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author milos
 */
public class FormaGlavna extends javax.swing.JFrame {

    /**
     * Creates new form FormaGlavna
     */
    public FormaGlavna() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        lblDobrodosli = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        menuMusterija = new javax.swing.JMenu();
        miMusterijaUbaci = new javax.swing.JMenuItem();
        miMusterijaPrikazi = new javax.swing.JMenuItem();
        menuAutomobil = new javax.swing.JMenu();
        miAutomobilDodaj = new javax.swing.JMenuItem();
        miAutomobilPrikazi = new javax.swing.JMenuItem();
        menuRezervacija = new javax.swing.JMenu();
        miRezervacijaDodaj = new javax.swing.JMenuItem();
        miRezervacijaPretrazi = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDobrodosli.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDobrodosli.setText("Dobrodo??ao:");

        menuMusterija.setText("Musterija");

        miMusterijaUbaci.setText("Dodaj");
        menuMusterija.add(miMusterijaUbaci);

        miMusterijaPrikazi.setText("Prikazi");
        menuMusterija.add(miMusterijaPrikazi);

        jMenuBar2.add(menuMusterija);

        menuAutomobil.setText("Automobil");

        miAutomobilDodaj.setText("Dodaj");
        menuAutomobil.add(miAutomobilDodaj);

        miAutomobilPrikazi.setText("Prikazi");
        menuAutomobil.add(miAutomobilPrikazi);

        jMenuBar2.add(menuAutomobil);

        menuRezervacija.setText("Rezervacija");

        miRezervacijaDodaj.setText("Dodaj");
        menuRezervacija.add(miRezervacijaDodaj);

        miRezervacijaPretrazi.setText("Pretrazi");
        miRezervacijaPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRezervacijaPretraziActionPerformed(evt);
            }
        });
        menuRezervacija.add(miRezervacijaPretrazi);

        jMenuBar2.add(menuRezervacija);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDobrodosli, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
                .addComponent(lblDobrodosli, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miRezervacijaPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRezervacijaPretraziActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miRezervacijaPretraziActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JLabel lblDobrodosli;
    private javax.swing.JMenu menuAutomobil;
    private javax.swing.JMenu menuMusterija;
    private javax.swing.JMenu menuRezervacija;
    private javax.swing.JMenuItem miAutomobilDodaj;
    private javax.swing.JMenuItem miAutomobilPrikazi;
    private javax.swing.JMenuItem miMusterijaPrikazi;
    private javax.swing.JMenuItem miMusterijaUbaci;
    private javax.swing.JMenuItem miRezervacijaDodaj;
    private javax.swing.JMenuItem miRezervacijaPretrazi;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JLabel getLblDobrodosli() {
        return lblDobrodosli;
    }

    public javax.swing.JMenu getMenuAutomobil() {
        return menuAutomobil;
    }

    public javax.swing.JMenu getMenuMusterija() {
        return menuMusterija;
    }

    public javax.swing.JMenu getMenuRezervacija() {
        return menuRezervacija;
    }

    public void addMiMusterijaUbaciActionListener(ActionListener actionListener) {
        miMusterijaUbaci.addActionListener(actionListener);
    }

    public void addMiMusterijaPrikaziActionListener(ActionListener actionListener) {
        miMusterijaPrikazi.addActionListener(actionListener);
    }

    public void addMiAutomobilDodajActionListener(ActionListener actionListener) {
        miAutomobilDodaj.addActionListener(actionListener);
    }

    public void addMiAutomobilPrikaziActionListener(ActionListener actionListener) {
        miAutomobilPrikazi.addActionListener(actionListener);
    }

    public void addMiRezervacijaDodajActionListener(ActionListener actionListener) {
        miRezervacijaDodaj.addActionListener(actionListener);
    }

    public void addMiRezervacijaPrikaziActionListener(ActionListener actionListener) {
        miRezervacijaPretrazi.addActionListener(actionListener);
    }

}
