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
public class UcitajMusterijuSO extends ApstraktnaGenerickaOperacija{
    
    Musterija musterija;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Musterija)){
            throw new Exception("Pogresan unos");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        musterija = (Musterija) broker.vratiPoPrimarnomKljucu((Musterija)param, "JOIN mesto");
    }

    public Musterija getMusterija() {
        return musterija;
    }
    
}
