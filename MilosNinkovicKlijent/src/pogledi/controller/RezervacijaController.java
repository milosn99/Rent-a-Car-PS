/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Automobil;
import domen.Mesto;
import domen.Model;
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
import pogledi.forme.FormaRezervacija;
import pogledi.forme.util.FormaMod;

/**
 *
 * @author milos
 */
public class RezervacijaController {

    private final FormaRezervacija frmRezervacija;

    public RezervacijaController(FormaRezervacija frmRezervacija) {
        this.frmRezervacija = frmRezervacija;
        addActionListeners();
    }

    private void addActionListeners() {
        frmRezervacija.addRadioNoviActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRezervacija.getCbMusterija().setEnabled(false);
                frmRezervacija.getCbMesto().setEnabled(true);
                frmRezervacija.getTxtIme().setEnabled(true);
                frmRezervacija.getTxtPrezime().setEnabled(true);
                frmRezervacija.getTxtAdresa().setEnabled(true);
            }
        });

        frmRezervacija.addRadioPostojeciActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRezervacija.getCbMusterija().setEnabled(true);
                frmRezervacija.getCbMesto().setEnabled(false);
                frmRezervacija.getTxtIme().setEnabled(false);
                frmRezervacija.getTxtPrezime().setEnabled(false);
                frmRezervacija.getTxtAdresa().setEnabled(false);
            }
        });

        frmRezervacija.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Rezervacija rezervacija = validacija();
                    if (frmRezervacija.getRadioNovi().isSelected()) {
                        Komunikacija.getInstanca().ubaciRezervacijuNovi(rezervacija);
                    } else {
                        Komunikacija.getInstanca().ubaci(rezervacija);
                    }
                    JOptionPane.showMessageDialog(frmRezervacija, "Uspesno uneseno", "Greska", JOptionPane.INFORMATION_MESSAGE);
                    frmRezervacija.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRezervacija, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }

            private Rezervacija validacija() throws Exception {
                Musterija musterija;
                if (frmRezervacija.getRadioPostojeci().isSelected()) {
                    musterija = (Musterija) frmRezervacija.getCbMusterija().getSelectedItem();
                } else {
                    String ime = frmRezervacija.getTxtIme().getText().trim();
                    String prezime = frmRezervacija.getTxtPrezime().getText().trim();
                    String adresa = frmRezervacija.getTxtAdresa().getText().trim();
                    Mesto mesto = (Mesto) frmRezervacija.getCbMesto().getSelectedItem();

                    if (ime == null || ime.equals("")
                            || prezime == null || prezime.equals("")
                            || adresa == null || adresa.equals("")) {
                        throw new Exception("Nisu uneseni svi podaci za musteriju");
                    }

                    musterija = new Musterija(Long.MIN_VALUE, ime, prezime, adresa, mesto);
                }

                Date datumOd = frmRezervacija.getDcDatumOd().getDate();
                Date datumDo = frmRezervacija.getDcDatumDo().getDate();
                Automobil automobil = (Automobil) frmRezervacija.getCbAutomobil().getSelectedItem();

                if (musterija == null || datumDo == null || datumOd == null || datumDo.before(datumOd)) {
                    throw new Exception("Losi podaci za rezervaciju");
                }

                Rezervacija rezervacija = new Rezervacija(datumDo, datumOd, musterija, automobil);
                return rezervacija;
            }
        });
    }

    public void otvoriFormu() {
        try {
            pripremiFormu();
            frmRezervacija.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(RezervacijaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pripremiFormu() throws Exception {
        popuniCbMesto();
        popuniCbMusterija();
        popuniCbAutomobil();
        srediPolja();
    }

    private void srediPolja() {
        frmRezervacija.getRadioPostojeci().setSelected(true);
        frmRezervacija.getRadioNovi().setSelected(false);
        frmRezervacija.getCbMusterija().setEnabled(true);
        frmRezervacija.getCbMesto().setEnabled(false);
        frmRezervacija.getTxtIme().setEnabled(false);
        frmRezervacija.getTxtPrezime().setEnabled(false);
        frmRezervacija.getTxtAdresa().setEnabled(false);
    }

    private void popuniCbAutomobil() throws Exception {
        frmRezervacija.getCbAutomobil().removeAllItems();
        List<Automobil> automobili = Komunikacija.getInstanca().ucitajAutomobile();
        for (Automobil automobil : automobili) {
            frmRezervacija.getCbAutomobil().addItem(automobil);
        }
    }

    private void popuniCbMusterija() throws Exception {
        frmRezervacija.getCbMusterija().removeAllItems();
        List<Musterija> musterije = Komunikacija.getInstanca().ucitajMusterije();
        for (Musterija musterija : musterije) {
            frmRezervacija.getCbMusterija().addItem(musterija);
        }
    }

    private void popuniCbMesto() throws Exception {
        frmRezervacija.getCbMesto().removeAllItems();
        List<Mesto> mesta = Komunikacija.getInstanca().ucitajMesta();
        for (Mesto mesto : mesta) {
            frmRezervacija.getCbMesto().addItem(mesto);
        }
    }
}
