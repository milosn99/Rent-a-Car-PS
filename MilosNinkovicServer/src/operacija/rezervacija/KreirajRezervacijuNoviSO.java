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
public class KreirajRezervacijuNoviSO extends ApstraktnaGenerickaOperacija{
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Sistem nije dobio musteriju");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija rezervacija = (Rezervacija) param;
        broker.ubaci(rezervacija.getMusterija());
        List<Musterija> musterije = broker.vratiSve(rezervacija.getMusterija(), " JOIN mesto on (musterija.mestoid=mesto.mestoid)");
        Musterija musterija = rezervacija.getMusterija();
        for (Musterija m : musterije) {
            if(m.getMusterijaId()>musterija.getMusterijaId()){
                musterija = m;
            }
        }
        rezervacija.setMusterija(musterija);
        broker.ubaci(rezervacija);
    }
}
