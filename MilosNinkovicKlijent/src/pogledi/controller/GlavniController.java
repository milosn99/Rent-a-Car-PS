/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pogledi.coordinator.Coordinator;
import pogledi.forme.FormaGlavna;

/**
 *
 * @author milos
 */
public class GlavniController {

    private final FormaGlavna frmGlavna;

    public GlavniController(FormaGlavna frmGlavna) {
        this.frmGlavna = frmGlavna;
        addActionListener();
    }

    private void addActionListener() {
        frmGlavna.addMiMusterijaUbaciActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstanca().otvoriMusterijaUbaciFormu();
            }

        });

        frmGlavna.addMiMusterijaPrikaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstanca().otvoriPrikaziMusterijeFormu();
            }
        });

        frmGlavna.addMiAutomobilDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstanca().otvoriAutomobilUbaciFormu();
            }
        });

        frmGlavna.addMiAutomobilPrikaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstanca().otvoriPrikaziAutomobileFormu();
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu();
        frmGlavna.setVisible(true);
    }

    private void pripremiFormu() {
        Korisnik korisnik = (Korisnik) Coordinator.getInstanca().vratiParam("korisnik");
        frmGlavna.getLblDobrodosli().setText(frmGlavna.getLblDobrodosli().getText() + " " + korisnik.getKorisnickoIme());
    }

    public FormaGlavna getFrmGlavna() {
        return frmGlavna;
    }

}
