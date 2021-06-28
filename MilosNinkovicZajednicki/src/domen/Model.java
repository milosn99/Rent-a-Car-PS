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

/**
 *
 * @author milos
 */
public class Model implements ApstraktniDomenskiObjekat {

    private Long modelId;
    private String oznaka;
    private int kubikaza;
    private BigDecimal jacinaMotora;
    private Marka marka;

    public Model() {
    }

    public Model(Long modelId, String oznaka, int kubikaza, BigDecimal jacinaMotora, Marka marka) {
        this.modelId = modelId;
        this.oznaka = oznaka;
        this.kubikaza = kubikaza;
        this.jacinaMotora = jacinaMotora;
        this.marka = marka;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public BigDecimal getJacinaMotora() {
        return jacinaMotora;
    }

    public void setJacinaMotora(BigDecimal jacinaMotora) {
        this.jacinaMotora = jacinaMotora;
    }

    @Override
    public String vratiNazivTabele() {
        return "model";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long modelid = rs.getLong("model.modelid");

            Long markaid = rs.getLong("model.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            Marka marka = new Marka(markaid, markaNaziv);

            String oznaka = rs.getString("model.oznaka");
            int kubikaza = rs.getInt("model.kubikaza");
            BigDecimal jacinaMotora = rs.getBigDecimal("model.jacinamotora");
            Model model = new Model(modelid, oznaka, kubikaza, jacinaMotora, marka);
            lista.add(model);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "markaid,oznaka,kubikaza,jacinaMotora";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "" + marka.getMarkaId() + ",'" + oznaka + "'," + kubikaza + "," + jacinaMotora;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "model.modelid = " + modelId + "AND model.markaid = " + marka.getMarkaId();
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "markaid=" + marka.getMarkaId() + ",oznaka='" + oznaka + "',kubikaza=" + kubikaza + ",jacinaMotora=" + jacinaMotora;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat model = null;
        while (rs.next()) {
            Long modelid = rs.getLong("model.modelid");

            Long markaid = rs.getLong("model.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            Marka marka = new Marka(markaid, markaNaziv);

            String oznaka = rs.getString("model.oznaka");
            int kubikaza = rs.getInt("model.kubikaza");
            BigDecimal jacinaMotora = rs.getBigDecimal("model.jacinamotora");
            model = new Model(modelid, oznaka, kubikaza, jacinaMotora, marka);
        }
        return model;
    }

}
