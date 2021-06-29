/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Automobil;
import domen.Korisnik;
import domen.Marka;
import domen.Mesto;
import domen.Model;
import domen.Musterija;
import java.net.Socket;
import java.util.List;
import niti.Server;
import operacija.automobil.KreirajAutomobilNoviSO;
import operacija.automobil.KreirajAutomobilSO;
import operacija.automobil.NadjiAutomobileSO;
import operacija.automobil.UcitajAutomobileSO;
import operacija.login.PrijaviKorisnikaSO;
import operacija.marka.UcitajMarkeSO;
import operacija.marka.UcitajModeleSO;
import operacija.mesto.UcitajMestaSO;
import operacija.musterija.KreirajMusterijuSO;
import operacija.musterija.NadjiMusterijeSO;
import operacija.musterija.ObradiMusterijuSO;
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

    public void kreiraj(Automobil automobil) throws Exception {
        KreirajAutomobilSO operacija = new KreirajAutomobilSO();
        operacija.izvrsi(automobil, null);

    }

    public List<Musterija> ucitajMusterije() throws Exception {
        UcitajMusterijeSO operacija = new UcitajMusterijeSO();
        operacija.izvrsi(null, null);
        return operacija.getMusterije();
    }

    public void izmeni(Musterija musterija) throws Exception {
        ObradiMusterijuSO operacija = new ObradiMusterijuSO();
        operacija.izvrsi(musterija, null);
    }

    public List<Musterija> ucitajMusterije(String uslov) throws Exception {
        NadjiMusterijeSO operacija = new NadjiMusterijeSO();
        operacija.izvrsi(new Musterija(), uslov);
        return operacija.getMusterije();
    }

    public List<Marka> ucitajMarke() throws Exception {
        UcitajMarkeSO operacija = new UcitajMarkeSO();
        operacija.izvrsi(null, null);
        return operacija.getMarke();
    }

    public List<Model> ucitajModele() throws Exception {
        UcitajModeleSO operacija = new UcitajModeleSO();
        operacija.izvrsi(null, null);
        return operacija.getModeli();
    }

    public void kreirajNovi(Automobil automobil) throws Exception {
        KreirajAutomobilNoviSO operacija = new KreirajAutomobilNoviSO();
        operacija.izvrsi(automobil, null);
    }

    public List<Automobil> ucitajAutomobile() throws Exception {
        UcitajAutomobileSO operacija = new UcitajAutomobileSO();
        operacija.izvrsi(null, null);
        return operacija.getAutomobili();
    }

    public List<Automobil> ucitajAutomobile(String uslov) throws Exception {
        NadjiAutomobileSO operacija = new NadjiAutomobileSO();
        operacija.izvrsi(new Automobil(), uslov);
        return operacija.getAutomobili();
    }

}
