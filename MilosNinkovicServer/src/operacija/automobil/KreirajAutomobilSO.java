/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.automobil;

import domen.Automobil;
import domen.Musterija;
import java.math.BigDecimal;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class KreirajAutomobilSO extends ApstraktnaGenerickaOperacija{
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Automobil)) {
            throw new Exception("Sistem nije dobio musteriju");
        }

        Automobil a = (Automobil) param;
        if(a.getCena()<0 || a.getGodiste()<0 || a.getKilometraza()<0 || a.getModel()==null){
            throw new Exception("Lose uneseni podaci");
        };
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.ubaci((Automobil) param);
    }

}
