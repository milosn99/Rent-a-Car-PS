/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Mesto;
import domen.Musterija;
import domen.Rezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import pogledi.coordinator.Coordinator;
import pogledi.forme.FormaPrikaziMusterije;
import pogledi.forme.FormaPrikaziRezervacije;
import pogledi.forme.kompoente.MusterijeModelTabele;
import pogledi.forme.kompoente.RezervacijeModelTabele;

/**
 *
 * @author milos
 */
public class PrikaziRezervacijeController {

    private final FormaPrikaziRezervacije frmPR;

    public PrikaziRezervacijeController(FormaPrikaziRezervacije frmPR) {
        this.frmPR = frmPR;
        addActionListener();
    }

    public void otvoriFormu() {
        try {
            pripremiFormu();
            frmPR.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPR, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        frmPR.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String uslov = "";
                    Date datumOd = frmPR.getDcDatumOd().getDate();
                    Date datumDo = frmPR.getDcDatumDo().getDate();
                    if (datumOd != null && datumDo != null) {
                        uslov += "datumOd > '" + new java.sql.Date(datumOd.getTime()) + "' AND datumDo < '" + new java.sql.Date(datumDo.getTime()) + "'";
                    } else if (datumOd != null && datumDo == null) {
                        uslov += "datumOd>'" + new java.sql.Date(datumOd.getTime()) + "'";
                    } else if (datumOd == null && datumDo != null) {
                        uslov += "datumDo<'" + new java.sql.Date(datumDo.getTime()) + "'";
                    }
                    List<Rezervacija> rezervacija = Komunikacija.getInstanca().vratiRezervacijePoUslovu(uslov);
                    RezervacijeModelTabele rmt = new RezervacijeModelTabele(rezervacija);
                    frmPR.getTabelaRezervacije().setModel(rmt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPR, "Sistem nije uspeo da nađe rezervacije po zadatim kriterijumima", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPR.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = frmPR.getTabelaRezervacije().getSelectedRow();
                if (red >= 0) {
                    RezervacijeModelTabele rmt = (RezervacijeModelTabele) frmPR.getTabelaRezervacije().getModel();
                    Rezervacija r = rmt.getRezervacijaAt(red);
                    Coordinator.getInstanca().dodajParam("PozicijaRezervacije", rmt.getRezervacije().indexOf(r));
                    Coordinator.getInstanca().dodajParam("Rezervacija", r);
                    try {
                        int siguran = JOptionPane.showConfirmDialog(frmPR, "Da li ste sigurni da zelite da obrisete rezervaciju?");
                        if (siguran > 0) {
                            Komunikacija.getInstanca().obrisiRezervaciju(r);
                            izmenaPodataka();
                            JOptionPane.showConfirmDialog(frmPR, "Sistem je uspesno obrisao rezervaciju", "Greska", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmPR, "Sistem nije uspeo da obriše rezervaciju", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void pripremiFormu() throws Exception {
        List<Rezervacija> rezervacije = Komunikacija.getInstanca().ucitajRezervacije();
        RezervacijeModelTabele rmt = new RezervacijeModelTabele(rezervacije);
        frmPR.getTabelaRezervacije().setModel(rmt);
    }

    public void izmenaPodataka() {
        RezervacijeModelTabele rmt = (RezervacijeModelTabele) frmPR.getTabelaRezervacije().getModel();
        rmt.izmeniElement((int) Coordinator.getInstanca().vratiParam("PozicijaRezervacije"), (Rezervacija) Coordinator.getInstanca().vratiParam("Rezervacija"));
    }

}
