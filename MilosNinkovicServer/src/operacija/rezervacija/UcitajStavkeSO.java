/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Mesto;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija{
    List<StavkaRezervacije> stavke;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        stavke = broker.vratiSve(new StavkaRezervacije(), ""
                + " JOIN rezervacija ON (stavkarezervacije.rezervacijaid = rezervacija.rezervacijaid) "
                + "JOIN musterija on (rezervacija.musterijaid=musterija.musterijaid) "
                + "JOIN mesto on (musterija.mestoid=mesto.mestoid) "
                + "JOIN automobil on (stavkarezervacije.registracija=automobil.registracija) "
                + "JOIN model on (automobil.modelid=model.modelid) "
                + "JOIN marka on (model.markaid=marka.markaid) ");
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }
}
