/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author milos
 */
public class Rezervacija implements ApstraktniDomenskiObjekat {

    private Long rezervacijaId;
    private Date datumDo;
    private Date datumOd;
    private Musterija musterija;
    private Korisnik korisnik;
    private List<StavkaRezervacije> automobili;

    public Rezervacija() {
        automobili = new ArrayList<>();

    }

    public Rezervacija(Long rezervacijaId, Date datumDo, Date datumOd, Musterija musterija, Korisnik korisnik) {
        this.rezervacijaId = rezervacijaId;
        this.datumDo = datumDo;
        this.datumOd = datumOd;
        this.musterija = musterija;
        this.korisnik = korisnik;
        automobili = new ArrayList<>();
    }

    public Rezervacija(Date datumDo, Date datumOd, Musterija musterija, Korisnik korisnik) {
        this.datumDo = datumDo;
        this.datumOd = datumOd;
        this.musterija = musterija;
        this.korisnik = korisnik;
        automobili = new ArrayList<>();

    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {

            Long mestoid = rs.getLong("musterija.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);

            Long musterijaId = rs.getLong("rezervacija.musterijaid");
            String ime = rs.getString("musterija.ime");
            String prezime = rs.getString("musterija.prezime");
            String adresa = rs.getString("musterija.adresa");

            String korisnickoIme = rs.getString("rezervacija.korisnik");
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);
            Musterija musterija = new Musterija(musterijaId, ime, prezime, adresa, mesto, korisnik);

            Date datumOd = rs.getDate("rezervacija.datumod");
            Date datumDo = rs.getDate("rezervacija.datumdo");

            Long rezervacijaId = rs.getLong("rezervacija.rezervacijaid");

            Rezervacija rezervacija = new Rezervacija(rezervacijaId, datumDo, datumOd, musterija, korisnik);

            lista.add(rezervacija);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "musterijaid,datumod,datumdo,korisnik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return musterija.getMusterijaId() + ",'" + new java.sql.Date(datumOd.getTime()) + "','" + new java.sql.Date(datumDo.getTime()) + "','" + korisnik.getKorisnickoIme() + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacija.datumod= '" + new java.sql.Date(datumOd.getTime()) + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat rezervacija = null;
        while (rs.next()) {
            Long mestoid = rs.getLong("musterija.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);

            Long musterijaId = rs.getLong("rezervacija.musterijaid");
            String ime = rs.getString("musterija.ime");
            String prezime = rs.getString("musterija.prezime");
            String adresa = rs.getString("musterija.adresa");
            Musterija musterija = new Musterija(musterijaId, ime, prezime, adresa, mesto, null);

            Date datumOd = rs.getDate("rezervacija.datumod");
            Date datumDo = rs.getDate("rezervacija.datumdo");

            String korisnickoIme = rs.getString("rezervacija.korisnik");
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);

            Long rezervacijaId = rs.getLong("rezervacija.rezervacijaid");

            rezervacija = new Rezervacija(rezervacijaId, datumDo, datumOd, musterija, korisnik);

        }
        return rezervacija;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    public void setRezervacijaId(Long rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<StavkaRezervacije> getAutomobili() {
        return automobili;
    }

    public void setAutomobili(List<StavkaRezervacije> automobili) {
        this.automobili = automobili;
    }

}
