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
public class Model implements ApstraktniDomenskiObjekat {

    private Long modelId;
    private String oznaka;
    private String segment;
    private Marka marka;

    public Model() {
    }

    public Model(Long modelId, String oznaka, String segment, Marka marka) {
        this.modelId = modelId;
        this.oznaka = oznaka;
        this.segment = segment;
        this.marka = marka;
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
            String segment = rs.getString("model.segment");
            Model model = new Model(modelid, oznaka, segment, marka);
            lista.add(model);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "markaid,oznaka,segment";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "" + marka.getMarkaId() + ",'" + oznaka + "','" + segment + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "model.modelid = " + modelId + "AND model.markaid = " + marka.getMarkaId();
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "markaid=" + marka.getMarkaId() + ",oznaka='" + oznaka + "',segment='" + segment + "'";
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
            String segment = rs.getString("model.segment");
            model = new Model(modelid, oznaka, segment, marka);
        }
        return model;
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

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    @Override
    public String toString() {
        return marka.getNaziv() + " " + oznaka;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Model other = (Model) obj;
        if (!Objects.equals(this.modelId, other.modelId)) {
            return false;
        }
        if (!Objects.equals(this.marka, other.marka)) {
            return false;
        }
        return true;
    }

}
