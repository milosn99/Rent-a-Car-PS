/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Automobil;
import domen.Rezervacija;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajRezervacijuSO extends ApstraktnaGenerickaOperacija{

    Rezervacija rezervacija;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Pogresan unos");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        rezervacija = (Rezervacija) broker.vratiPoPrimarnomKljucu((Rezervacija) param, " JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid) "
                + "JOIN automobil on (rezervacija.registracija=automobil.registracija) "
                + "JOIN model on (automobil.modelid=model.modelid) "
                + "JOIN marka on (model.markaid=marka.markaid)");
    }

    public Rezervacija getMusterija() {
        return rezervacija;
    }
}
