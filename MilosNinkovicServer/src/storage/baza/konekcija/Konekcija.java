/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.baza.konekcija;

import config.Konfiguracija;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author milos
 */
public class Konekcija {

    private static Konekcija instanca;
    private Connection konekcija;

    private Konekcija() throws SQLException {

    }

    public static Konekcija getInstanca() throws SQLException {
        if (instanca == null) {
            instanca = new Konekcija();
        }
        return instanca;
    }

    public Connection getKonekcija() throws SQLException {
        if (konekcija == null || konekcija.isClosed()) {
            String url = Konfiguracija.getInstanca().getProperty("url");
            String user = Konfiguracija.getInstanca().getProperty("user");
            String pass = Konfiguracija.getInstanca().getProperty("password");
            System.out.println(pass);
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentacar", "root", "newrootpassword");
            System.out.println("povezao se");
            konekcija.setAutoCommit(false);
        }
        return konekcija;
    }

}
