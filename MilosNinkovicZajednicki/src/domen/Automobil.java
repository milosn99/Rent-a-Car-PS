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

    private Long automobilId;
    private int godiste;
    private int cena;
    private int kilometraza;
    private BigDecimal potrosnja;
    private Model model;

    public Automobil() {
    }

    public Automobil(Long automobilId, int godiste, int cena, int kilometraza, BigDecimal potrosnja, Model model) {
        this.automobilId = automobilId;
        this.godiste = godiste;
        this.cena = cena;
        this.kilometraza = kilometraza;
        this.potrosnja = potrosnja;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Long getAutomobilId() {
        return automobilId;
    }

    public void setAutomobilId(Long automobilId) {
        this.automobilId = automobilId;
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
        if (!Objects.equals(this.automobilId, other.automobilId)) {
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
            Long automobilid = rs.getLong("automobil.automobilid");
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
            lista.add(automobil);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "modelid,godiste,kilometraza,potrosnja,cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "" + model.getModelId() + "," + godiste + "," + kilometraza + "," + potrosnja + ", " + cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "automobil.automobilid =" + automobilId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "modelid = " + model.getModelId() + ", godiste= " + godiste + ", kilometraza= " + kilometraza + ",potrosnja= " + potrosnja + ", cena=" + cena;

    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat automobil = null;
        while (rs.next()) {
            Long automobilid = rs.getLong("automobil.automobilid");
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

            automobil = new Automobil(automobilid, godiste, cena, kilometraza, potrosnja, model);
        }
        return automobil;
    }

}
