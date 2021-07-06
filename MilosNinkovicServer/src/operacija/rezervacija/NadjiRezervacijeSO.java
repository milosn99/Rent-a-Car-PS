/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Rezervacija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class NadjiRezervacijeSO extends ApstraktnaGenerickaOperacija {

    List<Rezervacija> rezervacije;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        rezervacije = broker.vratiPoKriterijumu((Rezervacija)param, " JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid)", kljuc);
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
