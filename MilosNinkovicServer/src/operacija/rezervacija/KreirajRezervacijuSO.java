/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Model;
import domen.Rezervacija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class KreirajRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Sistem nije dobio rezervaciju");
        }

        Rezervacija r = (Rezervacija) param;

        if (r.getAutomobil() == null || r.getMusterija() == null || r.getDatumDo() == null || r.getDatumOd() == null || r.getDatumOd().after(r.getDatumDo())) {
            throw new Exception("Losi podaci");
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
                + new java.sql.Date(r.getDatumDo().getTime()) +"' BETWEEN datumOd AND datumDo)";
        
        List<Rezervacija > rezervacije = broker.vratiPoKriterijumu((Rezervacija) param, " JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid) "
                + "JOIN automobil on (rezervacija.registracija=automobil.registracija) "
                + "JOIN model on (automobil.modelid=model.modelid) "
                + "JOIN marka on (model.markaid=marka.markaid)", uslov);
        
        for (Rezervacija rezervacija : rezervacije) {
            if(rezervacija.getAutomobil().equals(r.getAutomobil()))
                throw new Exception("Izabrani automobil nije slobodan u tom periodu");
        }

        broker.ubaci((Rezervacija) param);
    }
}
