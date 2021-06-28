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
public class ObradiMusterijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Musterija)) {
            throw new Exception("Sistem nije dobio musteriju");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.izmeni((Musterija) param);
    }
}
