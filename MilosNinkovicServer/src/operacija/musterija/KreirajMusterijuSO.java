/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.musterija;

import domen.Musterija;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class KreirajMusterijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Musterija)) {
            throw new Exception("Sistem nije dobio musteriju");
        }

        Musterija m = (Musterija) param;
        if (m.getAdresa() == null || m.getAdresa().equals("")) {
            throw new Exception("Prazna adresa");
        }

        if (m.getIme() == null || m.getIme().equals("")) {
            throw new Exception("Prazno ime");
        }

        if (m.getPrezime() == null || m.getPrezime().equals("")) {
            throw new Exception("Prazno prezime");
        }

        if (m.getMesto() == null) {
            throw new Exception("Prazno mesto");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.ubaci((Musterija) param);
    }

}
