/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.login;

import domen.Korisnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class PrijaviKorisnikaSO extends ApstraktnaGenerickaOperacija {

    Korisnik korisnik;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
//        System.out.println("dosao do izvrsiOperaciju");
        List<Korisnik> korisnici = broker.vratiSve((Korisnik) param, null);
        Korisnik k = (Korisnik) param;
        if (korisnici.contains(k)) {
            korisnik = k;
        } else {
            throw new Exception("Korisnik ne postoji");
        }

    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

}
