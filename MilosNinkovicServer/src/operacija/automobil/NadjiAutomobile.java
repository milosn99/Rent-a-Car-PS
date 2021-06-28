/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.automobil;

import domen.Automobil;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class NadjiAutomobile extends ApstraktnaGenerickaOperacija {

    List<Automobil> automobili;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        automobili = broker.vratiPoKriterijumu((Automobil)param, "JOIN model JOIN marka",kljuc);
    }

    public List<Automobil> getMesta() {
        return automobili;
    }
}
