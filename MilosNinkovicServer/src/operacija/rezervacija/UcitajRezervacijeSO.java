/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.rezervacija;

import domen.Mesto;
import domen.Rezervacija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajRezervacijeSO extends ApstraktnaGenerickaOperacija{
    List<Rezervacija> rezervacije;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        rezervacije = broker.vratiSve(new Rezervacija(), "JOIN musterija JOIN mesto JOIN automobil JOIN model JOIN marka");
    }

    public List<Rezervacija> getMesta() {
        return rezervacije;
    }
}
