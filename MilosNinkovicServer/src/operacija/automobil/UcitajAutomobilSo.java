/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.automobil;

import domen.Automobil;
import domen.Musterija;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajAutomobilSo extends ApstraktnaGenerickaOperacija {

    Automobil automobil;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Automobil)) {
            throw new Exception("Pogresan unos");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        automobil = (Automobil) broker.vratiPoPrimarnomKljucu((Automobil) param, "JOIN model JOIN marka");
    }

    public Automobil getMusterija() {
        return automobil;
    }
}
