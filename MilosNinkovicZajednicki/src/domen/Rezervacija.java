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

    private Date datumDo;
    private Date datumOd;
    private Musterija musterija;
    private Automobil automobil;

    public Rezervacija() {
    }

    public Rezervacija(Date datumDo, Date datumOd, Musterija musterija, Automobil automobil) {
        this.datumDo = datumDo;
        this.datumOd = datumOd;
        this.musterija = musterija;
        this.automobil = automobil;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
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
            Long automobilid = rs.getLong("rezervacija.automobilid");
            Long modelid = rs.getLong("automobil.modelid");

            Long markaid = rs.getLong("model.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            Marka marka = new Marka(markaid, markaNaziv);

            String oznaka = rs.getString("model.oznaka");
            int kubikaza = rs.getInt("model.kubikaza");
            BigDecimal jacinaMotora = rs.getBigDecimal("model.jacinamotora");
            Model model = new Model(modelid, oznaka, kubikaza, jacinaMotora, marka);

            int godiste = rs.getInt("automobil.godiste");
            int kilometraza = rs.getInt("automobil.kilometraza");
            BigDecimal potrosnja = rs.getBigDecimal("automobil.potrosnja");
            int cena = rs.getInt("automobil.cena");

            Automobil automobil = new Automobil(automobilid, godiste, cena, kilometraza, potrosnja, model);

            Long mestoid = rs.getLong("musterija.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);

            Long musterijaId = rs.getLong("rezervacija.musterijaid");
            String ime = rs.getString("musterija.ime");
            String prezime = rs.getString("musterija.prezime");
            String adresa = rs.getString("musterija.adresa");
            Musterija musterija = new Musterija(musterijaId, ime, prezime, adresa, mesto);

            Date datumOd = rs.getDate("rezervacija.datumod");
            Date datumDo = rs.getDate("rezervacija.datumdo");

            Rezervacija rezervacija = new Rezervacija(datumDo, datumOd, musterija, automobil);

            lista.add(rezervacija);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "automobilid,musterijaid,datumod,datumdo";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "" + automobil.getAutomobilId() + "," + musterija.getMusterijaId() + ",'" + new java.sql.Date(datumOd.getTime()) + "','" + new java.sql.Date(datumDo.getTime()) + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacija.datumod= '" + new java.sql.Date(datumOd.getTime()) + "' and rezervacija.automobilid=" + automobil.getAutomobilId();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat rezervacija = null;
        while (rs.next()) {
            Long automobilid = rs.getLong("rezervacija.automobilid");
            Long modelid = rs.getLong("automobil.modelid");

            Long markaid = rs.getLong("model.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            Marka marka = new Marka(markaid, markaNaziv);

            String oznaka = rs.getString("model.oznaka");
            int kubikaza = rs.getInt("model.kubikaza");
            BigDecimal jacinaMotora = rs.getBigDecimal("model.jacinamotora");
            Model model = new Model(modelid, oznaka, kubikaza, jacinaMotora, marka);

            int godiste = rs.getInt("automobil.godiste");
            int kilometraza = rs.getInt("automobil.kilometraza");
            BigDecimal potrosnja = rs.getBigDecimal("automobil.potrosnja");
            int cena = rs.getInt("automobil.cena");

            Automobil automobil = new Automobil(automobilid, godiste, cena, kilometraza, potrosnja, model);

            Long mestoid = rs.getLong("musterija.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);

            Long musterijaId = rs.getLong("rezervacija.musterijaid");
            String ime = rs.getString("musterija.ime");
            String prezime = rs.getString("musterija.prezime");
            String adresa = rs.getString("musterija.adresa");
            Musterija musterija = new Musterija(musterijaId, ime, prezime, adresa, mesto);

            Date datumOd = rs.getDate("rezervacija.datumod");
            Date datumDo = rs.getDate("rezervacija.datumdo");

            rezervacija = new Rezervacija(datumDo, datumOd, musterija, automobil);

        }
        return rezervacija;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
