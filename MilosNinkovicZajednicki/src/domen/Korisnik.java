/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author milos
 */
public class Korisnik implements ApstraktniDomenskiObjekat {

    private String korisnickoIme;
    private String sifra;

    public Korisnik() {
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        return true;
    }

    public Korisnik(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @Override
    public String vratiNazivTabele() {
        return "korisnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            String korisnickoIme = rs.getString("korisnik.korisnickoime");
            String sifra = rs.getString("korisnik.sifra");
            Korisnik korisnik = new Korisnik(korisnickoIme, sifra);
            lista.add(korisnik);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "korisnickoime,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "korisnik.korisnickoime = " + korisnickoIme;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "korisnickoime = '" + korisnickoIme + "',sifra='" + sifra + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat korisnik = null;
        while (rs.next()) {
            String korisnickoIme = rs.getString("korisnik.korisnickoime");
            String sifra = rs.getString("korisnik.sifra");
            korisnik = new Korisnik(korisnickoIme, sifra);
        }
        return korisnik;
    }

}
