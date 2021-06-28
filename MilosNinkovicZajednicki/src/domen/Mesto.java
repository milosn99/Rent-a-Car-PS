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
public class Mesto implements ApstraktniDomenskiObjekat {

    private Long mestoId;
    private String naziv;

    public Mesto() {
    }

    public Mesto(Long mestoId, String naziv) {
        this.mestoId = mestoId;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getMestoId() {
        return mestoId;
    }

    public void setMestoId(Long mestoId) {
        this.mestoId = mestoId;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Long mestoid = rs.getLong("mesto.mestoid");
            String naziv = rs.getString("mesto.naziv");
            Mesto mesto = new Mesto(mestoid, naziv);
            lista.add(mesto);
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
        return "mesto.mestoid=" + mestoId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ApstraktniDomenskiObjekat mesto = null;
        while (rs.next()) {
            Long mestoid = rs.getLong("mesto.mestoid");
            String naziv = rs.getString("mesto.naziv");
            mesto = new Mesto(mestoid, naziv);
        }
        return mesto;
    }

}
