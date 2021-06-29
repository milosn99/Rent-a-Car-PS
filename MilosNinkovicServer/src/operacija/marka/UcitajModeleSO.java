/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.marka;

import domen.Mesto;
import domen.Model;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajModeleSO extends ApstraktnaGenerickaOperacija {

    List<Model> modeli;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        modeli = broker.vratiSve(new Model(), " JOIN marka on (model.markaid=marka.markaid)");
    }

    public List<Model> getModeli() {
        return modeli;
    }
}
