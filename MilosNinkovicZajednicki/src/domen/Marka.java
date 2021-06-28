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

}
