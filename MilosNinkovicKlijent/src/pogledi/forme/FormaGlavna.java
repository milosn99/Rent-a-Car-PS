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
        menuAutomobil = new javax.swing.JMenu();
        menuRezervacija = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDobrodosli.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDobrodosli.setText("Dobrodošao:");

        menuMusterija.setText("Musterija");

        miMusterijaUbaci.setText("Dodaj");
        menuMusterija.add(miMusterijaUbaci);

        jMenuBar2.add(menuMusterija);

        menuAutomobil.setText("Automobil");
        jMenuBar2.add(menuAutomobil);

        menuRezervacija.setText("Rezervacija");
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
    private javax.swing.JMenuItem miMusterijaUbaci;
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
    
    public void addMiMusterijaUbaciActionListener(ActionListener actionListener){
        miMusterijaUbaci.addActionListener(actionListener);
    }
    
}
