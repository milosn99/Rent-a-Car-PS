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
import pogledi.forme.FormaMusterija;
import pogledi.forme.util.FormaMod;

/**
 *
 * @author milos
 */
public class MusterijaController {

    private final FormaMusterija frmMusterija;

    public MusterijaController(FormaMusterija frmMusterija) {
        this.frmMusterija = frmMusterija;
        addActionListener();

    }

    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        frmMusterija.setVisible(true);

    }

    private void addActionListener() {
        frmMusterija.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validacija();
                    sacuvaj();
                    JOptionPane.showMessageDialog(frmMusterija, "Uspesno ste uneli musteriju", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    frmMusterija.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmMusterija, "Greska prilikom ubacivanja", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void validacija() throws Exception {
                if (frmMusterija.getTxtAdresa() == null || frmMusterija.getTxtAdresa().equals("")) {
                    throw new Exception("Nije unesena adresa");
                }
                if (frmMusterija.getTxtIme() == null || frmMusterija.getTxtIme().equals("")) {
                    throw new Exception("Nije uneseno ime");
                }
                if (frmMusterija.getTxtPrezime() == null || frmMusterija.getTxtPrezime().equals("")) {
                    throw new Exception("Nije uneseno prezime");
                }
            }

            private void sacuvaj() throws Exception {
                Musterija musterija = new Musterija(Long.MIN_VALUE, frmMusterija.getTxtIme().getText(), frmMusterija.getTxtPrezime().getText(), frmMusterija.getTxtAdresa().getText(), (Mesto) frmMusterija.getCbMesto().getSelectedItem());
                Komunikacija.getInstanca().ubaci(musterija);
            }
        });

        frmMusterija.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void pripremiFormu(FormaMod formaMod) {
        try {
            pripremiMod(formaMod);
            popuniCbMesto();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmMusterija, "Greska prilikom ucitavanja mesta", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void popuniCbMesto() throws Exception {
        frmMusterija.getCbMesto().removeAllItems();
        List<Mesto> mesta = Komunikacija.getInstanca().ucitajMesta();
        for (Mesto mesto : mesta) {
            frmMusterija.getCbMesto().addItem(mesto);
        }
    }

    private void pripremiMod(FormaMod formaMod) {
        switch (formaMod) {
            case DODAJ:
                frmMusterija.getBtnSacuvaj().setEnabled(true);
                frmMusterija.getBtnIzmeni().setVisible(false);
                frmMusterija.getTxtAdresa().setText("");
                frmMusterija.getTxtIme().setText("");
                frmMusterija.getTxtPrezime().setText("");
                break;
            case IZMENI:

        }
    }
}
