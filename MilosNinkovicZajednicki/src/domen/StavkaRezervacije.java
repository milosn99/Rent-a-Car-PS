/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author milos
 */
public class StavkaRezervacije implements ApstraktniDomenskiObjekat {

    private Rezervacija rezervacija;
    private Automobil automobil;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(Rezervacija rezervacija, Automobil automobil) {
        this.rezervacija = rezervacija;
        this.automobil = automobil;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            String registracija = rs.getString("stavkarezervacije.registracija");
            Long modelid = rs.getLong("automobil.modelid");

            Long markaid = rs.getLong("model.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            Marka marka = new Marka(markaid, markaNaziv);

            String oznaka = rs.getString("model.oznaka");
            String segment = rs.getString("model.segment");

            Model model = new Model(modelid, oznaka, segment, marka);

            int kubikaza = rs.getInt("automobil.kubikaza");
            int jacinaMotora = rs.getInt("automobil.jacinamotora");
            int godiste = rs.getInt("automobil.godiste");
            String gorivo = rs.getString("automobil.gorivo");
            int kilometraza = rs.getInt("automobil.kilometraza");
            BigDecimal potrosnja = rs.getBigDecimal("automobil.potrosnja");
            int cena = rs.getInt("automobil.cena");

            Automobil automobil = new Automobil(registracija, godiste, cena, kilometraza, potrosnja, gorivo, kubikaza, jacinaMotora, model, null);

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

            Long rezervacijaId = rs.getLong("stavkarezervacije.rezervacijaid");

            Rezervacija rezervacija = new Rezervacija(rezervacijaId, datumDo, datumOd, musterija, korisnik);

            StavkaRezervacije stavka = new StavkaRezervacije(rezervacija, automobil);

            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "registracija,rezervacijaid";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + automobil.getRegistracija() + "'," + rezervacija.getRezervacijaId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkarezervacije.registracija = '" + automobil.getRegistracija() + "' AND stavkarezervacije.rezervacijaid = " + rezervacija.getRezervacijaId();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

}
