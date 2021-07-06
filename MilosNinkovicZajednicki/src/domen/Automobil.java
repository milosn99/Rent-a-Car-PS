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
import java.util.List;
import java.util.Objects;

/**
 *
 * @author milos
 */
public class Automobil implements ApstraktniDomenskiObjekat {

    private String registracija;
    private int godiste;
    private int cena;
    private int kilometraza;
    private BigDecimal potrosnja;
    private String gorivo;
    private int kubikaza;
    private int jacinaMotora;
    private Model model;
    private Korisnik korisnik;

    public Automobil() {
    }

    @Override
    public String toString() {
        return model + " (" + godiste+". god, "+cena+" EUR "+ gorivo + ", tablice: " + registracija+")";
    }

    public Automobil(String registracija, int godiste, int cena, int kilometraza, BigDecimal potrosnja, String gorivo, int kubikaza, int jacinaMotora, Model model, Korisnik korisnik) {
        this.registracija = registracija;
        this.godiste = godiste;
        this.cena = cena;
        this.kilometraza = kilometraza;
        this.potrosnja = potrosnja;
        this.gorivo = gorivo;
        this.kubikaza = kubikaza;
        this.jacinaMotora = jacinaMotora;
        this.model = model;
        this.korisnik = korisnik;
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
        final Automobil other = (Automobil) obj;
        if (!Objects.equals(this.registracija, other.registracija)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "automobil";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            String registracija = rs.getString("automobil.registracija");
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
            
            String korisnickoIme = rs.getString("automobil.korisnik");
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);
            Automobil automobil = new Automobil(registracija, godiste, cena, kilometraza, potrosnja, gorivo, kubikaza, jacinaMotora, model, korisnik);
            lista.add(automobil);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "registracija,modelid,godiste,kilometraza,potrosnja,cena,gorivo,kubikaza,jacinamotora,korisnik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+registracija+"', " + model.getModelId() + ", " + godiste + ", " + kilometraza + "," + potrosnja + ", " + cena + ", '"+gorivo+"', "+kubikaza+", "+jacinaMotora+",'"+korisnik.getKorisnickoIme()+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "automobil.registracija = '" + registracija+"'";
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "registracija='"+registracija+"', modelid = " + model.getModelId() + ", godiste= " + godiste + ", kilometraza= " + kilometraza + ",potrosnja= " + potrosnja + ", cena=" + cena + ", gorivo='"+gorivo+"', kubikaza="+kubikaza+", jacinamotora="+jacinaMotora;

    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat automobil = null;
        while (rs.next()) {
            String registracija = rs.getString("automobil.registracija");
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

            
            String korisnickoIme = rs.getString("automobil.korisnik");
            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnickoIme(korisnickoIme);
            automobil = new Automobil(registracija, godiste, cena, kilometraza, potrosnja, gorivo, kubikaza, jacinaMotora, model, korisnik);

        }
        return automobil;
    }

    public String getGorivo() {
        return gorivo;
    }

    public void setGorivo(String gorivo) {
        this.gorivo = gorivo;
    }

    public int getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public int getJacinaMotora() {
        return jacinaMotora;
    }

    public void setJacinaMotora(int jacinaMotora) {
        this.jacinaMotora = jacinaMotora;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKilometraza() {
        return kilometraza;
    }

    public void setKilometraza(int kilometraza) {
        this.kilometraza = kilometraza;
    }

    public BigDecimal getPotrosnja() {
        return potrosnja;
    }

    public void setPotrosnja(BigDecimal potrosnja) {
        this.potrosnja = potrosnja;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

}
