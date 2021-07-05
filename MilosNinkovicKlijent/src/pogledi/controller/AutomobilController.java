/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Automobil;
import domen.Marka;
import domen.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import pogledi.coordinator.Coordinator;
import pogledi.forme.FormaAutomobil;
import pogledi.forme.util.FormaMod;

/**
 *
 * @author milos
 */
public class AutomobilController {

    private final FormaAutomobil frmAutomobil;

    public AutomobilController(FormaAutomobil frmAutomobil) {
        this.frmAutomobil = frmAutomobil;
        addActionListener();

    }

    public void otvoriFormu(FormaMod frmMod) {
        pripremiFormu(frmMod);
        frmAutomobil.setVisible(true);
    }

    private void addActionListener() {
        frmAutomobil.addRadioPostojeciActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmAutomobil.getPanelNovi().setEnabled(true);
                frmAutomobil.getCbModel().setEnabled(true);
                frmAutomobil.getCbMarka().setEnabled(false);
                frmAutomobil.getTxtOznaka().setEnabled(false);
                frmAutomobil.getCbSegment().setEnabled(false);
                frmAutomobil.getPanelPostojeci().setEnabled(false);
            }
        });

        frmAutomobil.addRadioNoviActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmAutomobil.getPanelNovi().setEnabled(false);
                frmAutomobil.getCbModel().setEnabled(false);
                frmAutomobil.getCbMarka().setEnabled(true);
                frmAutomobil.getTxtOznaka().setEnabled(true);
                frmAutomobil.getCbSegment().setEnabled(true);
                frmAutomobil.getPanelPostojeci().setEnabled(true);
            }
        });

        frmAutomobil.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validacija();
                    sacuvaj();
                    JOptionPane.showMessageDialog(frmAutomobil, "Sistem je uspesno zapamtio automobil", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    frmAutomobil.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAutomobil, "Sistem nije uspeo da zapamti automobil", "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }

            private void validacija() throws Exception {
                if (frmAutomobil.getRadioNovi().isSelected()) {
                    Marka marka = (Marka) frmAutomobil.getCbMarka().getSelectedItem();
                    String oznaka = frmAutomobil.getTxtOznaka().getText();
                    String segment = (String) frmAutomobil.getCbSegment().getSelectedItem();
                    if (oznaka == null || oznaka.equals("")) {
                        throw new Exception("Nisu uneseni podaci za model");
                    }
                }

                String registracija = frmAutomobil.getTxtRegistracija().getText().trim();
                int godiste = Integer.parseInt(frmAutomobil.getTxtGodiste().getText().trim());
                int kilometraza = Integer.parseInt(frmAutomobil.getTxtKilometraza().getText().trim());
                BigDecimal potrosnja = new BigDecimal(frmAutomobil.getTxtPotrosnja().getText().trim());
                int cena = Integer.parseInt(frmAutomobil.getTxtCena().getText().trim());
                String gorivo = (String) frmAutomobil.getCbGorivo().getSelectedItem();
                int kubikaza = Integer.parseInt(frmAutomobil.getTxtKubikaza().getText().trim());
                int jacinaMotora = Integer.parseInt(frmAutomobil.getTxtJacinaMotora().getText().trim());
                if (gorivo == null || gorivo.equals("") || registracija == null || registracija.equals("")) {
                    throw new Exception("Pogresno uneseni podaci za automobil");
                }
                if (godiste < 0 || kilometraza < 0 || cena < 0 || potrosnja.compareTo(new BigDecimal(0)) <= 0 || kubikaza < 0 || jacinaMotora < 0) {
                    throw new Exception("Nisu uneseni podaci za model");
                }
            }

            private void sacuvaj() throws Exception {
                Model model;
                if (frmAutomobil.getRadioNovi().isSelected()) {
                    Marka marka = (Marka) frmAutomobil.getCbMarka().getSelectedItem();
                    String oznaka = frmAutomobil.getTxtOznaka().getText();
                    String segment = (String) frmAutomobil.getCbSegment().getSelectedItem();
                    model = new Model(Long.MIN_VALUE, oznaka, segment, marka);
                } else {
                    model = (Model) frmAutomobil.getCbModel().getSelectedItem();
                }
                String registracija = frmAutomobil.getTxtRegistracija().getText().trim();
                int godiste = Integer.parseInt(frmAutomobil.getTxtGodiste().getText().trim());
                int kilometraza = Integer.parseInt(frmAutomobil.getTxtKilometraza().getText().trim());
                BigDecimal potrosnja = new BigDecimal(frmAutomobil.getTxtPotrosnja().getText().trim());
                int cena = Integer.parseInt(frmAutomobil.getTxtCena().getText().trim());
                String gorivo = (String) frmAutomobil.getCbGorivo().getSelectedItem();
                int kubikaza = Integer.parseInt(frmAutomobil.getTxtKubikaza().getText().trim());
                int jacinaMotora = Integer.parseInt(frmAutomobil.getTxtJacinaMotora().getText().trim());

                Automobil automobil = new Automobil(registracija, godiste, cena, kilometraza, potrosnja, gorivo, kubikaza, jacinaMotora, model);
                if (frmAutomobil.getRadioNovi().isSelected()) {
                    Komunikacija.getInstanca().ubaciAutomobilNovi(automobil);
                } else {
                    Komunikacija.getInstanca().ubaciAutomobilPostojeci(automobil);
                }

            }
        });

        frmAutomobil.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validacija();
                    izmeni();
                    JOptionPane.showMessageDialog(frmAutomobil, "Sistem je uspesno obradio automobil", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    frmAutomobil.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmAutomobil, "Sistem nije uspeo da obradi automobil", "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }

            private void validacija() throws Exception {

                String registracija = frmAutomobil.getTxtRegistracija().getText().trim();
                int godiste = Integer.parseInt(frmAutomobil.getTxtGodiste().getText().trim());
                int kilometraza = Integer.parseInt(frmAutomobil.getTxtKilometraza().getText().trim());
                BigDecimal potrosnja = new BigDecimal(frmAutomobil.getTxtPotrosnja().getText().trim());
                int cena = Integer.parseInt(frmAutomobil.getTxtCena().getText().trim());
                String gorivo = (String) frmAutomobil.getCbGorivo().getSelectedItem();
                int kubikaza = Integer.parseInt(frmAutomobil.getTxtKubikaza().getText().trim());
                int jacinaMotora = Integer.parseInt(frmAutomobil.getTxtJacinaMotora().getText().trim());
                if (gorivo == null || gorivo.equals("") || registracija == null || registracija.equals("")) {
                    throw new Exception("Pogresno uneseni podaci za automobil");
                }
                if (godiste < 0 || kilometraza < 0 || cena < 0 || potrosnja.compareTo(new BigDecimal(0)) <= 0 || kubikaza < 0 || jacinaMotora < 0) {
                    throw new Exception("Nisu uneseni podaci za model");
                }
            }

            private void izmeni() throws Exception {
                Model model = (Model) frmAutomobil.getCbModel().getSelectedItem();
                String registracija = frmAutomobil.getTxtRegistracija().getText().trim();
                int godiste = Integer.parseInt(frmAutomobil.getTxtGodiste().getText().trim());
                int kilometraza = Integer.parseInt(frmAutomobil.getTxtKilometraza().getText().trim());
                BigDecimal potrosnja = new BigDecimal(frmAutomobil.getTxtPotrosnja().getText().trim());
                int cena = Integer.parseInt(frmAutomobil.getTxtCena().getText().trim());
                String gorivo = (String) frmAutomobil.getCbGorivo().getSelectedItem();
                int kubikaza = Integer.parseInt(frmAutomobil.getTxtKubikaza().getText().trim());
                int jacinaMotora = Integer.parseInt(frmAutomobil.getTxtJacinaMotora().getText().trim());

                Automobil automobil = new Automobil(registracija, godiste, cena, kilometraza, potrosnja, gorivo, kubikaza, jacinaMotora, model);

                Komunikacija.getInstanca().izmeni(automobil);
                Coordinator.getInstanca().dodajParam("Automobil", automobil);

            }
        });
    }

    private void pripremiFormu(FormaMod formaMod) {
        try {

            popuniCbModel();
            popuniCbMarka();
            pripremiMod(formaMod);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmAutomobil, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void popuniCbModel() throws Exception {
        frmAutomobil.getCbModel().removeAllItems();
        List<Model> modeli = Komunikacija.getInstanca().ucitajModele();
        for (Model model : modeli) {
            frmAutomobil.getCbModel().addItem(model);
        }
    }

    private void popuniCbMarka() throws Exception {
        frmAutomobil.getCbMarka().removeAllItems();
        List<Marka> marke = Komunikacija.getInstanca().ucitajMarke();
        for (Marka marka : marke) {
            frmAutomobil.getCbMarka().addItem(marka);
        }
    }

    private void pripremiMod(FormaMod formaMod) {
        switch (formaMod) {
            case DODAJ:
                frmAutomobil.getRadioPostojeci().setSelected(true);
                frmAutomobil.getRadioNovi().setSelected(false);
                frmAutomobil.getBtnSacuvaj().setVisible(true);
                frmAutomobil.getBtnSacuvaj().setEnabled(true);
                frmAutomobil.getPanelNovi().setEnabled(false);
                frmAutomobil.getCbModel().setEnabled(true);
                frmAutomobil.getCbMarka().setEnabled(false);
                frmAutomobil.getTxtOznaka().setEnabled(false);
                frmAutomobil.getCbSegment().setEnabled(false);
                frmAutomobil.getPanelPostojeci().setEnabled(true);
                frmAutomobil.getBtnIzmeni().setVisible(false);
                break;
            case IZMENI:
                frmAutomobil.getPanelNovi().setEnabled(false);
                frmAutomobil.getCbModel().setEnabled(true);
                frmAutomobil.getCbMarka().setEnabled(false);
                frmAutomobil.getTxtOznaka().setEnabled(false);
                frmAutomobil.getCbSegment().setEnabled(false);
                frmAutomobil.getPanelPostojeci().setEnabled(true);
                frmAutomobil.getBtnSacuvaj().setVisible(false);
                frmAutomobil.getRadioNovi().setSelected(false);
                frmAutomobil.getRadioPostojeci().setSelected(true);
                frmAutomobil.getRadioPostojeci().setEnabled(false);
                frmAutomobil.getRadioNovi().setEnabled(false);
                frmAutomobil.getTxtGodiste().setEnabled(false);
                frmAutomobil.getTxtKubikaza().setEnabled(false);
                frmAutomobil.getTxtJacinaMotora().setEnabled(false);
                frmAutomobil.getCbGorivo().setEnabled(false);
                frmAutomobil.getTxtRegistracija().setEnabled(false);

                Automobil automobil = (Automobil) Coordinator.getInstanca().vratiParam("Automobil");

                frmAutomobil.getTxtRegistracija().setText(automobil.getRegistracija());
                frmAutomobil.getTxtGodiste().setText("" + automobil.getGodiste());
                frmAutomobil.getTxtKilometraza().setText("" + automobil.getKilometraza());
                frmAutomobil.getTxtCena().setText("" + automobil.getCena());
                frmAutomobil.getTxtPotrosnja().setText("" + automobil.getPotrosnja());
                frmAutomobil.getTxtKubikaza().setText("" + automobil.getKubikaza());
                frmAutomobil.getTxtJacinaMotora().setText("" + automobil.getJacinaMotora());
                frmAutomobil.getCbModel().setSelectedItem(automobil.getModel());
                frmAutomobil.getCbGorivo().setSelectedItem(automobil.getGorivo());
                break;
        }
    }
}
