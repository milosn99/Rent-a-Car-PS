/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.automobil;

import domen.Automobil;
import domen.Model;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class KreirajAutomobilNoviSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Automobil)) {
            throw new Exception("Sistem nije dobio musteriju");
        }

        Automobil a = (Automobil) param;
        if (a.getCena() < 0 || a.getGodiste() < 0
                || a.getKilometraza() < 0 || a.getModel() == null || a.getGorivo() == null
                || a.getGorivo().equals("") || a.getRegistracija() == null
                || a.getRegistracija().equals("") || a.getJacinaMotora() < 0 || a.getKubikaza() < 0) {
            throw new Exception("Lose uneseni podaci");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Automobil automobil = (Automobil) param;
        broker.ubaci(automobil.getModel());
        List<Model> modeli = broker.vratiSve(automobil.getModel(), " JOIN marka on (model.markaid=marka.markaid)");
        Model model = automobil.getModel();
        for (Model m : modeli) {
            if(m.getModelId()>model.getModelId()){
                model = m;
            }
        }
        automobil.setModel(model);
        broker.ubaci(automobil);
    }
}
