/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.baza.impl;

import domen.ApstraktniDomenskiObjekat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import storage.baza.DBRepozitorijum;
import storage.baza.konekcija.Konekcija;

/**
 *
 * @author milos
 */
public class RepozitorijumDBGenericki implements DBRepozitorijum<ApstraktniDomenskiObjekat>{

    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat ado, String join) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = null;
        String upit = "SELECT * FROM " + ado.vratiNazivTabele();
        if (join != null) {
            upit += join;
        }
        System.out.println(upit);
        Connection konekcija = Konekcija.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = ado.vratiListu(rs);

        rs.close();
        st.close();

        return lista;
    }

    public List<ApstraktniDomenskiObjekat> vratiPoKriterijumu(ApstraktniDomenskiObjekat ado, String join, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = null;
        String upit = "SELECT * FROM " + ado.vratiNazivTabele();
        if (join != null) {
            upit += join;
        }
        upit += " WHERE " + uslov;
        System.out.println(upit);
        Connection konekcija = Konekcija.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = ado.vratiListu(rs);

        rs.close();
        st.close();

        return lista;
    }

    public ApstraktniDomenskiObjekat vratiPoPrimarnomKljucu(ApstraktniDomenskiObjekat ado, String join) throws Exception {
        ApstraktniDomenskiObjekat abs = null;
        String upit = "SELECT * FROM " + ado.vratiNazivTabele();
        if (join != null) {
            upit += join;
        }
        upit += " WHERE " + ado.vratiPrimarniKljuc();
        System.out.println(upit);
        Connection konekcija = Konekcija.getInstanca().getKonekcija();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);
        abs = ado.vratiObjekatIzRS(rs);

        rs.close();
        st.close();

        return abs;
    }

    public void ubaci(ApstraktniDomenskiObjekat ado) throws Exception {
        String upit = "INSERT INTO " + ado.vratiNazivTabele() + "(" + ado.vratiKoloneZaUbacivanje() + ") VALUES(" + ado.vratiVrednostiZaUbacivanje() + ")";
        System.out.println(upit);
        Connection konekcija = Konekcija.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    public void obrisi(ApstraktniDomenskiObjekat ado) throws Exception {
        String upit = "DELETE FROM " + ado.vratiNazivTabele() + " WHERE " + ado.vratiPrimarniKljuc();
        System.out.println(upit);
        Connection konekcija = Konekcija.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    public void izmeni(ApstraktniDomenskiObjekat ado) throws Exception {
        String upit = "UPDATE " + ado.vratiNazivTabele() + " SET " + ado.vratiVrednostiZaIzmenu() + " WHERE " + ado.vratiPrimarniKljuc();
        System.out.println(upit);
        Connection konekcija = Konekcija.getInstanca().getKonekcija();
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);
        st.close();
    }
}
