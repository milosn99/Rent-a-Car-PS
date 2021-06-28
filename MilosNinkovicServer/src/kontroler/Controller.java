/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Korisnik;
import domen.Mesto;
import domen.Musterija;
import java.net.Socket;
import java.util.List;
import niti.Server;
import operacija.login.PrijaviKorisnikaSO;
import operacija.mesto.UcitajMestaSO;
import operacija.musterija.KreirajMusterijuSO;
import operacija.musterija.UcitajMusterijeSO;

/**
 *
 * @author milos
 */
public class Controller {

    private static Controller instanca;
    private Server server;

    private Controller() {
    }

    public static Controller getInstanca() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public Korisnik login(String korisnickoIme, String sifra, Socket soket) throws Exception {
        PrijaviKorisnikaSO operacija = new PrijaviKorisnikaSO();
        Korisnik korisnik = new Korisnik(korisnickoIme, sifra);

        operacija.izvrsi(korisnik, null);
//        System.out.println("IZVRSI u LOGINu ");

        korisnik = operacija.getKorisnik();
        return korisnik;
    }

    public void startServer() {
        server = new Server();
        server.start();
    }

    public void stopServer() {
        server.zaustavi();
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }

    public void kreiraj(Musterija musterija) throws Exception {
        KreirajMusterijuSO operacija = new KreirajMusterijuSO();
        operacija.izvrsi(musterija, null);

    }

    public List<Musterija> ucitajMusterije() throws Exception {
        UcitajMusterijeSO operacija = new UcitajMusterijeSO();
        operacija.izvrsi(null, null);
        return operacija.getMusterije();
    }

}
