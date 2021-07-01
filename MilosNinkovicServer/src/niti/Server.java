/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import konfiguracija.Konfiguracija;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Server extends Thread {

    List<KlijentNit> klijenti;
    ServerSocket serverSoket;

    public Server() {
        klijenti = new ArrayList<>();
    }

    
    
    @Override
    public void run() {
        try {
            int port = Integer.parseInt(Konfiguracija.getInstanca().getProperty("port"));
            System.out.println(port);
            serverSoket = new ServerSocket(port);
            while (!serverSoket.isClosed()) {
                System.out.println("Server je ukljucen, ceka se konekcija...");
                Socket soket = serverSoket.accept();
                System.out.println("Povezan klijent");
                KlijentNit nit = new KlijentNit(soket);
                klijenti.add(nit);
                nit.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustavi() {
        try {
            for (KlijentNit klijentNit : klijenti) {
                klijentNit.prekini();
            }
            serverSoket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
