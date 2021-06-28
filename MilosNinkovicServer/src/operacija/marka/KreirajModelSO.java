/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.marka;

import domen.Automobil;
import domen.Model;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class KreirajModelSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Model)) {
            throw new Exception("Sistem nije dobio model");
        }
        
        Model m = (Model) param;
        
        if (m.getSegment() == null || m.getSegment().equals("") || m.getMarka() == null || m.getOznaka() == null || m.getOznaka().equals("")) {
            throw new Exception("Losi podaci");
        };
    }
    
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.ubaci((Model) param);
    }
    
}
