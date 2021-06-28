/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Model;
import domen.Rezervacija;
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
        
        if(r.getAutomobil()==null || r.getMusterija()==null || r.getDatumDo()==null || r.getDatumOd()==null || r.getDatumOd().after(r.getDatumDo())){
            throw new Exception("Losi podaci");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.ubaci((Rezervacija) param);
    }
}
