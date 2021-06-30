/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.coordinator;

import java.util.HashMap;
import java.util.Map;
import pogledi.controller.AutomobilController;
import pogledi.controller.GlavniController;
import pogledi.controller.LogInController;
import pogledi.controller.MusterijaController;
import pogledi.controller.PrikaziAutomobileController;
import pogledi.controller.PrikaziMusterijeController;
import pogledi.forme.FormaAutomobil;
import pogledi.forme.FormaGlavna;
import pogledi.forme.FormaLogIn;
import pogledi.forme.FormaMusterija;
import pogledi.forme.FormaPrikaziAutomobile;
import pogledi.forme.FormaPrikaziMusterije;
import pogledi.forme.util.FormaMod;

/**
 *
 * @author milos
 */
public class Coordinator {

    public static Coordinator instanca;
    private Map<String, Object> parametri;
    private GlavniController glavniController;
    private MusterijaController musterijaController;
    private PrikaziMusterijeController pmController;
    private AutomobilController automobilController;
    private PrikaziAutomobileController paController;

    private Coordinator() {
        parametri = new HashMap<>();
    }

    public static Coordinator getInstanca() {
        if (instanca == null) {
            instanca = new Coordinator();
        }
        return instanca;
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriLoginFormu() {
        LogInController frmLogin = new LogInController(new FormaLogIn());
        frmLogin.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavniController = new GlavniController(new FormaGlavna());
        glavniController.otvoriFormu();
    }

    public void otvoriMusterijaUbaciFormu() {
        musterijaController = new MusterijaController(new FormaMusterija(glavniController.getFrmGlavna(), true));
        musterijaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikaziMusterijeFormu() {
        pmController = new PrikaziMusterijeController(new FormaPrikaziMusterije(glavniController.getFrmGlavna(), true));
        pmController.otvoriFormu();
    }

    public void otvoriMusterijaIzmeniFormu() {
        musterijaController = new MusterijaController(new FormaMusterija(glavniController.getFrmGlavna(), true));
        musterijaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void srediFormuPrikazMusterija() {
        pmController.izmenaPodataka();
    }

    public void otvoriAutomobilUbaciFormu() {
        automobilController = new AutomobilController(new FormaAutomobil(glavniController.getFrmGlavna(), true));
        automobilController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikaziAutomobileFormu() {
        paController = new PrikaziAutomobileController(new FormaPrikaziAutomobile(glavniController.getFrmGlavna(), true));
        paController.otvoriFormu();
    }

    public void otvoriAutomobilIzmeniFormu() {
        automobilController = new AutomobilController(new FormaAutomobil(glavniController.getFrmGlavna(), true));
        automobilController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikaziRezervacijeFormu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void otvoriRezervacijaUbaciFormu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
