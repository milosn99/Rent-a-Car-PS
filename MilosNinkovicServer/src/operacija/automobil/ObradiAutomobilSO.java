/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.automobil;

import domen.Automobil;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class ObradiAutomobilSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Automobil)) {
            throw new Exception("Sistem nije dobio automobil");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.izmeni((Automobil) param);
    }
}
