/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Automobil;
import domen.Model;
import domen.Musterija;
import domen.Rezervacija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class KreirajRezervacijuNoviSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Sistem nije dobio musteriju");
        }

        Rezervacija rezervacija = (Rezervacija) param;
        if (rezervacija.getAutomobil() == null || rezervacija.getMusterija() == null
                || rezervacija.getDatumDo() == null || rezervacija.getDatumOd() == null
                || rezervacija.getDatumOd().after(rezervacija.getDatumDo())) {
            throw new Exception("Lose uneseni podaci");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        Rezervacija r = (Rezervacija) param;
        String uslov = " (datumOd BETWEEN '" + new java.sql.Date(r.getDatumOd().getTime())
                + "' AND '" + new java.sql.Date(r.getDatumDo().getTime()) + "') OR (datumDo BETWEEN '"
                + new java.sql.Date(r.getDatumDo().getTime())
                + "' AND '" + new java.sql.Date(r.getDatumOd().getTime()) + "') OR ('"
                + new java.sql.Date(r.getDatumOd().getTime()) + "' BETWEEN datumOd AND datumDo) OR ('"
                + new java.sql.Date(r.getDatumDo().getTime()) + "' BETWEEN datumOd AND datumDo)";

        List<Rezervacija> rezervacije = broker.vratiPoKriterijumu((Rezervacija) param, " JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid) "
                + "JOIN automobil on (rezervacija.registracija=automobil.registracija) "
                + "JOIN model on (automobil.modelid=model.modelid) "
                + "JOIN marka on (model.markaid=marka.markaid)", uslov);

        for (Rezervacija rez : rezervacije) {
            if (rez.getAutomobil().equals(r.getAutomobil())) {
                throw new Exception("Izabrani automobil nije slobodan u tom periodu");
            }
        }
        
        Rezervacija rezervacija = (Rezervacija) param;
        broker.ubaci(rezervacija.getMusterija());
        List<Musterija> musterije = broker.vratiSve(rezervacija.getMusterija(), " JOIN mesto on (musterija.mestoid=mesto.mestoid)");
        Musterija musterija = rezervacija.getMusterija();
        for (Musterija m : musterije) {
            if (m.getMusterijaId() > musterija.getMusterijaId()) {
                musterija = m;
            }
        }
        rezervacija.setMusterija(musterija);
        broker.ubaci(rezervacija);
    }
}
