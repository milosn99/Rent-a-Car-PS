/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.Automobil;
import domen.Korisnik;
import domen.Marka;
import domen.Mesto;
import domen.Model;
import domen.Musterija;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.util.Operacije;

/**
 *
 * @author milos
 */
public class Komunikacija {

    private static Komunikacija instanca;
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public void konekcija() throws Exception {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            throw new Exception("Server is not connected!");
        }
    }

    public Korisnik logIn(String korisnickoIme, String sifra) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.LOGIN, new Korisnik(korisnickoIme, sifra));
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (Korisnik) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Mesto> ucitajMesta() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_MESTA, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Mesto>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }

    }

    public void ubaci(Musterija musterija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_MUSTERIJU, musterija);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Musterija> ucitajMusterije() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_MUSTERIJE, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Musterija>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public void izmeni(Musterija musterija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.OBRADI_MUSTERIJU, musterija);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Musterija> vratiMusterijePoUslovu(String uslov) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.NADJI_MUSTERIJE, uslov);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Musterija>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Model> ucitajModele() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_MODELE, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Model>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Marka> ucitajMarke() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_MARKE, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Marka>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public void ubaciAutomobilNovi(Automobil automobil) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_AUTOMOBIL_NOVI, automobil);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public void ubaciAutomobilPostojeci(Automobil automobil) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_AUTOMOBIL_POSTOJECI, automobil);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Automobil> vratiAutomobilePoUslovu(String uslov) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.NADJI_AUTOMOBILE, uslov);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Automobil>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Automobil> ucitajAutomobile() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_AUTOMOBILE, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Automobil>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public void izmeni(Automobil automobil) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.OBRADI_AUTOMOBIL, automobil);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public void ubaci(Rezervacija rezervacija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_REZERVACIJU_POSTOJECI, rezervacija);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public void ubaciRezervacijuNovi(Rezervacija rezervacija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.ZAPAMTI_REZERVACIJU_NOVI, rezervacija);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Rezervacija> vratiRezervacijePoUslovu(String uslov) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.NADJI_REZERVACIJE, uslov);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Rezervacija>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<Rezervacija> ucitajRezervacije() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_REZERVACIJE, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<Rezervacija>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

    public void obrisiRezervaciju(Rezervacija rezervacija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_REZERVACIJU, rezervacija);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return;
        } else {
            throw odgovor.getGreska();
        }
    }

    public List<StavkaRezervacije> ucitajStavke() throws Exception {
        Zahtev zahtev = new Zahtev(Operacije.UCITAJ_STAVKE, null);

        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getGreska() == null) {
            return (List<StavkaRezervacije>) odgovor.getPodatak();
        } else {
            throw odgovor.getGreska();
        }
    }

}
