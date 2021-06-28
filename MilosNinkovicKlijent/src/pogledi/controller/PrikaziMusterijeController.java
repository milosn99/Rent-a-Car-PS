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
import pogledi.coordinator.Coordinator;
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
                try {
                    String uslov = "";
                    String ime = frmPM.getTxtIme().getText();
                    String prezime = frmPM.getTxtPrezime().getText();
                    Mesto mesto = (Mesto) frmPM.getCbMesto().getSelectedItem();
                    uslov += "musterija.mestoid=" + mesto.getMestoId();
                    if (!ime.equals("")) {
                        uslov += " AND musterija.ime= '" + ime + "'";
                    }
                    if (!prezime.equals("")) {
                        uslov += " AND musterija.prezime= '" + prezime + "'";
                    }
                    List<Musterija> musterije = Komunikacija.getInstanca().vratiMusterijePoUslovu(uslov);
                    MusterijeModelTabele mmt = new MusterijeModelTabele(musterije);
                    frmPM.getTabelaMusterije().setModel(mmt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPM, "Greska prilikom pokusaja pretrage", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPM.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = frmPM.getTabelaMusterije().getSelectedRow();
                if (red >= 0) {
                    MusterijeModelTabele mmt = (MusterijeModelTabele) frmPM.getTabelaMusterije().getModel();
                    Musterija m = mmt.getMusterijaAt(red);
                    Coordinator.getInstanca().dodajParam("PozicijaMusterije", mmt.getMusterije().indexOf(m));
                    Coordinator.getInstanca().dodajParam("Musterija", m);
                    Coordinator.getInstanca().otvoriMusterijaIzmeniFormu();
                }
            }
        });
    }

    private void pripremiFormu() throws Exception {
        List<Musterija> musterije = Komunikacija.getInstanca().ucitajMusterije();
        MusterijeModelTabele mmt = new MusterijeModelTabele(musterije);
        frmPM.getTabelaMusterije().setModel(mmt);
        popuniCbMesto();
    }

    private void popuniCbMesto() throws Exception {
        frmPM.getCbMesto().removeAllItems();
        List<Mesto> mesta = Komunikacija.getInstanca().ucitajMesta();
        for (Mesto mesto : mesta) {
            frmPM.getCbMesto().addItem(mesto);
        }
    }

    public void izmenaPodataka() {
        MusterijeModelTabele mmt = (MusterijeModelTabele) frmPM.getTabelaMusterije().getModel();
        mmt.izmeniElement((int) Coordinator.getInstanca().vratiParam("PozicijaMusterije"), (Musterija) Coordinator.getInstanca().vratiParam("Musterija"));
    }

}
