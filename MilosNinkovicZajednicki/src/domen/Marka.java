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
public class Marka implements ApstraktniDomenskiObjekat {

    private Long markaId;
    private String naziv;

    public Marka() {
    }

    public Marka(Long markaId, String naziv) {
        this.markaId = markaId;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getMarkaId() {
        return markaId;
    }

    public void setMarkaId(Long markaId) {
        this.markaId = markaId;
    }

    @Override
    public String vratiNazivTabele() {
        return "marka";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long markaid = rs.getLong("marka.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            Marka marka = new Marka(markaid, markaNaziv);
            lista.add(marka);
        }
        return lista;
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
        final Marka other = (Marka) obj;
        if (!Objects.equals(this.markaId, other.markaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "marka.markaid=" + markaId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat marka = null;
        while (rs.next()) {
            Long markaid = rs.getLong("marka.markaid");
            String markaNaziv = rs.getString("marka.naziv");
            marka = new Marka(markaid, markaNaziv);
        }
        return marka;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
}
