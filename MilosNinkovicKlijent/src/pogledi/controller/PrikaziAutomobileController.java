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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import pogledi.coordinator.Coordinator;
import pogledi.forme.FormaPrikaziAutomobile;
import pogledi.forme.FormaPrikaziMusterije;
import pogledi.forme.kompoente.AutomobilModelTabele;
import pogledi.forme.kompoente.MusterijeModelTabele;

/**
 *
 * @author milos
 */
public class PrikaziAutomobileController {

    private final FormaPrikaziAutomobile frmPA;

    public PrikaziAutomobileController(FormaPrikaziAutomobile frmPA) {
        this.frmPA = frmPA;
        addActionListener();
    }

    public void otvoriFormu() {
        try {
            pripremiFormu();
            frmPA.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPA, "Greska prilikom ucitavanja. Molimo Vas da probate ponovo", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        frmPA.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String uslov = "";
                    String registracija = frmPA.getTxtRegistracija().getText().trim();
                    Model model = (Model) frmPA.getCbModel().getSelectedItem();
                    String cenaOd = frmPA.getTxtCenaOd().getText().trim();
                    String cenaDo = frmPA.getTxtCenaDo().getText().trim();
                    String godisteOd = frmPA.getTxtGodisteOd().getText().trim();
                    String godisteDo = frmPA.getTxtGodisteDo().getText().trim();
                    String kilometrazaOd = frmPA.getTxtKmOd().getText().trim();
                    String kilometrazaDo = frmPA.getTxtKmDo().getText().trim();

                    uslov += "automobil.modelid=" + model.getModelId();
                    if (registracija != null && !registracija.equals("")) {
                        uslov += " AND automobil.registracija='" + registracija + "'";
                    }
                    if (cenaOd != null && !cenaOd.equals("")) {
                        uslov += " AND automobil.cena>" + Integer.parseInt(cenaOd);
                    }
                    if (cenaDo != null && !cenaDo.equals("")) {
                        uslov += " AND automobil.cena<" + Integer.parseInt(cenaDo);
                    }
                    if (kilometrazaOd != null && !kilometrazaOd.equals("")) {
                        uslov += " AND automobil.kilometraza>" + Integer.parseInt(kilometrazaOd);
                    }
                    if (kilometrazaDo != null && !kilometrazaDo.equals("")) {
                        uslov += " AND automobil.kilometraza<" + Integer.parseInt(kilometrazaDo);
                    }
                    if (godisteOd != null && !godisteOd.equals("")) {
                        uslov += " AND automobil.godiste>" + Integer.parseInt(godisteOd);
                    }
                    if (godisteDo != null && !godisteDo.equals("")) {
                        uslov += " AND automobil.godiste<" + Integer.parseInt(godisteDo);
                    }
                    List<Automobil> automobili = Komunikacija.getInstanca().vratiAutomobilePoUslovu(uslov);
                    AutomobilModelTabele amt = new AutomobilModelTabele(automobili);
                    frmPA.getTabelaAutomobili().setModel(amt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPA, "Sistem nije uspeo da nađe automobile po zadatim kriterijumima", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmPA.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = frmPA.getTabelaAutomobili().getSelectedRow();
                if (red >= 0) {
                    AutomobilModelTabele amt = (AutomobilModelTabele) frmPA.getTabelaAutomobili().getModel();
                    Automobil a = amt.getAutomobilAt(red);
                    Coordinator.getInstanca().dodajParam("PozicijaAutomobila", amt.getAutomobili().indexOf(a));
                    Coordinator.getInstanca().dodajParam("Automobil", a);
                    Coordinator.getInstanca().otvoriAutomobilIzmeniFormu();
                }
            }
        });
    }

    private void pripremiFormu() throws Exception {
        List<Automobil> automobili = Komunikacija.getInstanca().ucitajAutomobile();
        AutomobilModelTabele amt = new AutomobilModelTabele(automobili);
        frmPA.getTabelaAutomobili().setModel(amt);
        popuniCbModel();
    }

    public void izmenaPodataka() {
        MusterijeModelTabele mmt = (MusterijeModelTabele) frmPA.getTabelaAutomobili().getModel();
        mmt.izmeniElement((int) Coordinator.getInstanca().vratiParam("PozicijaAutomobila"), (Musterija) Coordinator.getInstanca().vratiParam("Automobil"));
    }

    private void popuniCbModel() throws Exception {
        frmPA.getCbModel().removeAllItems();
        List<Model> modeli = Komunikacija.getInstanca().ucitajModele();
        for (Model model : modeli) {
            frmPA.getCbModel().addItem(model);
        }
    }

}
