/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.coordinator;

import java.util.HashMap;
import java.util.Map;
import pogledi.controller.GlavniController;
import pogledi.controller.LogInController;
import pogledi.controller.MusterijaController;
import pogledi.controller.PrikaziMusterijeController;
import pogledi.forme.FormaGlavna;
import pogledi.forme.FormaLogIn;
import pogledi.forme.FormaMusterija;
import pogledi.forme.FormaPrikaziMusterije;
import pogledi.forme.util.FormaMod;

/**
 *
 * @author milos
 */
public class Coordinator {

    public static Coordinator instanca;
    private Map<String, Object> params;
    private GlavniController glavniController;
    private MusterijaController musterijaController;
    private PrikaziMusterijeController pmController;

    private Coordinator() {
        params = new HashMap<>();
    }

    public static Coordinator getInstanca() {
        if (instanca == null) {
            instanca = new Coordinator();
        }
        return instanca;
    }

    public void dodajParam(String s, Object o) {
        params.put(s, o);
    }

    public Object getParam(String s) {
        return params.get(s);
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

}
