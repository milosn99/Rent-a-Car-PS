/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Konfiguracija {

    private static Konfiguracija instanca;
    private Properties properti;

    private Konfiguracija() {
        try {
            properti = new Properties();
            properti.load(new FileInputStream("../MilosNinkovicZajednicki/konfiguracija.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konfiguracija getInstanca() {
        if (instanca == null) {
            instanca = new Konfiguracija();
        }
        return instanca;
    }

    public Properties getProperti() {
        return properti;
    }
    
    
    public String getProperty(String key) {
        return properti.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value) {
        properti.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try {
            properti.store(new FileOutputStream("../MilosNinkovicZajednicki/konfiguracija.properties"), null);

        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
