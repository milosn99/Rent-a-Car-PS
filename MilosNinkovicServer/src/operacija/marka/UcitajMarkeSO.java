/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.marka;

import domen.Marka;
import domen.Mesto;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajMarkeSO extends ApstraktnaGenerickaOperacija{

    List<Marka> marke;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        marke = broker.vratiSve(new Marka(), kljuc);
    }

    public List<Marka> getMarke() {
        return marke;
    }
}
