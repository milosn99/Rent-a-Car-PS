/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.automobil;

import domen.Automobil;
import domen.Mesto;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajAutomobileSO extends ApstraktnaGenerickaOperacija {

    List<Automobil> automobili;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        automobili = broker.vratiSve(new Automobil(), " JOIN model on (automobil.modelid=model.modelid) "
                + "JOIN marka on (model.markaid=marka.markaid)");
    }

    public List<Automobil> getAutomobili() {
        return automobili;
    }
}
