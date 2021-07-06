/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Model;
import domen.Rezervacija;
import domen.StavkaRezervacije;
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

        if (r.getMusterija() == null || r.getDatumDo() == null || r.getDatumOd() == null || r.getDatumOd().after(r.getDatumDo())) {
            throw new Exception("Losi podaci");
        }
        if (r.getAutomobili().isEmpty()) {
            throw new Exception("Prazna lista auta");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) param;

        broker.ubaci((Rezervacija) param);

        String uslov = " (rezervacija.datumOd BETWEEN '" + new java.sql.Date(r.getDatumOd().getTime())
                + "' AND '" + new java.sql.Date(r.getDatumDo().getTime()) + "') OR (rezervacija.datumDo BETWEEN '"
                + new java.sql.Date(r.getDatumDo().getTime())
                + "' AND '" + new java.sql.Date(r.getDatumOd().getTime()) + "') OR ('"
                + new java.sql.Date(r.getDatumOd().getTime()) + "' BETWEEN rezervacija.datumOd AND rezervacija.datumDo) OR ('"
                + new java.sql.Date(r.getDatumDo().getTime()) + "' BETWEEN rezervacija.datumOd AND rezervacija.datumDo)";

        List<StavkaRezervacije> stavke = broker.vratiPoKriterijumu(new StavkaRezervacije(), ""
                + " JOIN rezervacija ON (stavkarezervacije.rezervacijaid = rezervacija.rezervacijaid) "
                + "JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid) "
                + "JOIN automobil on (stavkarezervacije.registracija=automobil.registracija) "
                + "JOIN model on (automobil.modelid=model.modelid) "
                + "JOIN marka on (model.markaid=marka.markaid) ", uslov);
        
        for (StavkaRezervacije stavka : stavke) {
            for (StavkaRezervacije stavkaRezervacije : r.getAutomobili()) {
                if(stavka.getAutomobil().equals(stavkaRezervacije.getAutomobil())){
                    throw new Exception("Zauzet auto");
                }
            }
        }

        List<Rezervacija> rezervacije = broker.vratiSve(new Rezervacija(), " JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid) ");
        Rezervacija r2 = rezervacije.get(0);
        for (Rezervacija r1 : rezervacije) {
            if (r1.getRezervacijaId() > r2.getRezervacijaId()) {
                r2 = r1;
            }
        }

        List<StavkaRezervacije> stavkeRez = ((Rezervacija) param).getAutomobili();
        for (StavkaRezervacije stavkaRezervacije : stavkeRez) {
            stavkaRezervacije.setRezervacija(r2);
            broker.ubaci(stavkaRezervacije);
        }

    }
}
