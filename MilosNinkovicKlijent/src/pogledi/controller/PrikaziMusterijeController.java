/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Mesto;
import domen.Musterija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import pogledi.forme.FormaPrikaziMusterije;
import pogledi.forme.kompoente.MusterijeModelTabele;

/**
 *
 * @author milos
 */
public class PrikaziMusterijeController {

    private final FormaPrikaziMusterije frmPM;

    public PrikaziMusterijeController(FormaPrikaziMusterije frmPM) {
        this.frmPM = frmPM;
        addActionListener();
    }

    public void otvoriFormu() {
        try {
            pripremiFormu();
            frmPM.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPM, "Greska prilikom ucitavanja. Molimo Vas da probate ponovo", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        frmPM.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frmPM.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void pripremiFormu() throws Exception {
        frmPM.getBtnIzmeni().setVisible(true);
        frmPM.getBtnPretrazi().setVisible(true);
        frmPM.getCbMesto().setVisible(true);
        frmPM.getTxtIme().setVisible(true);
        frmPM.getTxtPrezime().setVisible(true);
        List<Musterija> musterije = Komunikacija.getInstanca().ucitajMusterije();
        MusterijeModelTabele mmt = new MusterijeModelTabele(musterije);
        frmPM.getTabelaMusterije().setModel(mmt);
        frmPM.getTabelaMusterije().setVisible(true);
        popuniCbMesto();
    }

    private void popuniCbMesto() throws Exception {
        frmPM.getCbMesto().removeAllItems();
        List<Mesto> mesta = Komunikacija.getInstanca().ucitajMesta();
        for (Mesto mesto : mesta) {
            frmPM.getCbMesto().addItem(mesto);
        }
    }

}
