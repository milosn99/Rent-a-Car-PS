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

/**
 *
 * @author milos
 */
public class Musterija implements ApstraktniDomenskiObjekat {

    private Long musterijaId;
    private String ime;
    private String prezime;
    private String adresa;
    private Mesto mesto;
    private Korisnik korisnik;

    public Musterija() {
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " " + mesto;
    }

    public Musterija(Long musterijaId, String ime, String prezime, String adresa, Mesto mesto, Korisnik korisnik) {
        this.musterijaId = musterijaId;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.mesto = mesto;
        this.korisnik = korisnik;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Long getMusterijaId() {
        return musterijaId;
    }

    public void setMusterijaId(Long musterijaId) {
        this.musterijaId = musterijaId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String vratiNazivTabele() {
        return "musterija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long mestoid = rs.getLong("musterija.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);

            Long musterijaId = rs.getLong("musterija.musterijaid");
            String ime = rs.getString("musterija.ime");
            String prezime = rs.getString("musterija.prezime");
            String adresa = rs.getString("musterija.adresa");

            String korisnickoIme = rs.getString("musterija.korisnik");
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);

            Musterija musterija = new Musterija(musterijaId, ime, prezime, adresa, mesto, korisnik);
            lista.add(musterija);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,adresa,mestoid,korisnik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + adresa + "'," + mesto.getMestoId()+",'" + korisnik.getKorisnickoIme()+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "musterija.musterijaid = " + musterijaId;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat musterija = null;
        while (rs.next()) {
            Long mestoid = rs.getLong("musterija.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);

            Long musterijaId = rs.getLong("musterija.musterijaid");
            String ime = rs.getString("musterija.ime");
            String prezime = rs.getString("musterija.prezime");
            String adresa = rs.getString("musterija.adresa");
            String korisnickoIme = rs.getString("musterija.korisnik");
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);

            musterija = new Musterija(musterijaId, ime, prezime, adresa, mesto, korisnik);
        }
        return musterija;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "',prezime='" + prezime + "',adresa='" + adresa + "',mestoid=" + mesto.getMestoId();
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

}
