/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Automobil;
import domen.Korisnik;
import domen.Mesto;
import domen.Model;
import domen.Musterija;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import pogledi.coordinator.Coordinator;
import pogledi.forme.FormaRezervacija;
import pogledi.forme.kompoente.AutomobilModelTabele;

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

        frmRezervacija.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Rezervacija rezervacija = validacija();
                    System.out.println(rezervacija);
                    Komunikacija.getInstanca().ubaci(rezervacija);
                    JOptionPane.showMessageDialog(frmRezervacija, "Sistem je uspesno zapamtio rezervaciju", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    frmRezervacija.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRezervacija, "Sistem nije uspesno zapamtio rezervaciju", "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }

            private Rezervacija validacija() throws Exception {

                Musterija musterija = (Musterija) frmRezervacija.getCbMusterija().getSelectedItem();
                Date datumOd = frmRezervacija.getDcDatumOd().getDate();
                Date datumDo = frmRezervacija.getDcDatumDo().getDate();

                if (musterija == null || datumDo == null || datumOd
                        == null || datumOd.before(new Date()) || datumDo.before(datumOd)) {
                    throw new Exception("Losi podaci za rezervaciju");
                }

                Rezervacija rezervacija = new Rezervacija(datumDo, datumOd, musterija, (Korisnik) Coordinator.getInstanca().vratiParam("korisnik"));

                AutomobilModelTabele amt = (AutomobilModelTabele) frmRezervacija.getTabelaAutomobili().getModel();
                List<StavkaRezervacije> stavke = new ArrayList<>();
                for (Automobil automobil : amt.getAutomobili()) {
                    stavke.add(new StavkaRezervacije(rezervacija, automobil));
                }
                if (stavke.isEmpty()) {
                    throw new Exception("Prazna lista auta");
                }
                rezervacija.setAutomobili(stavke);
                return rezervacija;
            }

        }
        );

        frmRezervacija.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AutomobilModelTabele amt = (AutomobilModelTabele) frmRezervacija.getTabelaAutomobili().getModel();
                if (amt.getAutomobili().contains((Automobil) frmRezervacija.getCbAutomobil().getSelectedItem())) {
                    JOptionPane.showMessageDialog(frmRezervacija, "Automobil je vec u listi", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                amt.dodajAutomobil((Automobil) frmRezervacija.getCbAutomobil().getSelectedItem());
                frmRezervacija.getTabelaAutomobili().setModel(amt);
            }
        });
    }

    public void otvoriFormu() {
        try {
            pripremiFormu();
            frmRezervacija.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(RezervacijaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pripremiFormu() throws Exception {
        popuniCbMusterija();
        popuniCbAutomobil();
        srediPolja();
    }

    private void srediPolja() {
        frmRezervacija.getCbMusterija().setEnabled(true);
        AutomobilModelTabele amt = new AutomobilModelTabele(null);
        frmRezervacija.getTabelaAutomobili().setModel(amt);
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

}
