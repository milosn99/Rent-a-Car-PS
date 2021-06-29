/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Automobil;
import domen.Korisnik;
import domen.Marka;
import domen.Mesto;
import domen.Model;
import domen.Musterija;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Controller;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.util.Operacije;

/**
 *
 * @author milos
 */
public class KlijentNit extends Thread {

    private Socket soket;
    private boolean kraj = false;

    public KlijentNit(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        try {
            Primalac primalac = new Primalac(soket);
            Posiljalac posiljalac = new Posiljalac(soket);
            while (!soket.isClosed() && soket.isConnected()) {
                Odgovor odgovor = new Odgovor();
                Zahtev zahtev = (Zahtev) primalac.primi();
                try {
                    switch (zahtev.getOperacija()) {
                        case LOGIN:
                            Korisnik k = (Korisnik) zahtev.getPodatak();
                            k = Controller.getInstanca().login(k.getKorisnickoIme(), k.getSifra(), soket);
                            odgovor.setPodatak(k);
                            odgovor.setOperacija(Operacije.LOGIN);
                            odgovor.setGreska(null);
                            break;
                        case UCITAJ_MESTA:
                            List<Mesto> mesta = Controller.getInstanca().ucitajMesta();
                            odgovor.setPodatak(mesta);
                            odgovor.setOperacija(Operacije.UCITAJ_MESTA);
                            odgovor.setGreska(null);
                            break;
                        case ZAPAMTI_MUSTERIJU:
                            Controller.getInstanca().kreiraj((Musterija) zahtev.getPodatak());
                            odgovor.setOperacija(Operacije.ZAPAMTI_MUSTERIJU);
                            odgovor.setGreska(null);
                            odgovor.setPodatak(null);
                            break;
                        case UCITAJ_MUSTERIJE:
                            List<Musterija> musterije = Controller.getInstanca().ucitajMusterije();
                            odgovor.setPodatak(musterije);
                            odgovor.setOperacija(Operacije.UCITAJ_MUSTERIJE);
                            odgovor.setGreska(null);
                            break;
                        case OBRADI_MUSTERIJU:
                            Controller.getInstanca().izmeni((Musterija) zahtev.getPodatak());
                            odgovor.setOperacija(Operacije.OBRADI_MUSTERIJU);
                            odgovor.setGreska(null);
                            odgovor.setPodatak(null);
                            break;
                        case NADJI_MUSTERIJE:
                            List<Musterija> musterijePoKriterijumu = Controller.getInstanca().ucitajMusterije((String) zahtev.getPodatak());
                            odgovor.setPodatak(musterijePoKriterijumu);
                            odgovor.setOperacija(Operacije.UCITAJ_MUSTERIJE);
                            odgovor.setGreska(null);
                            break;
                        case UCITAJ_MARKE:
                            List<Marka> marke = Controller.getInstanca().ucitajMarke();
                            odgovor.setPodatak(marke);
                            odgovor.setOperacija(Operacije.UCITAJ_MARKE);
                            odgovor.setGreska(null);
                            break;
                        case UCITAJ_MODELE:
                            List<Model> modeli = Controller.getInstanca().ucitajModele();
                            odgovor.setPodatak(modeli);
                            odgovor.setOperacija(Operacije.UCITAJ_MARKE);
                            odgovor.setGreska(null);
                            break;
                        case ZAPAMTI_AUTOMOBIL_POSTOJECI:
                            Controller.getInstanca().kreiraj((Automobil) zahtev.getPodatak());
                            odgovor.setOperacija(Operacije.ZAPAMTI_AUTOMOBIL_POSTOJECI);
                            odgovor.setGreska(null);
                            odgovor.setPodatak(null);
                            break;
                        case ZAPAMTI_AUTOMOBIL_NOVI:
                            Controller.getInstanca().kreirajNovi((Automobil) zahtev.getPodatak());
                            odgovor.setOperacija(Operacije.ZAPAMTI_AUTOMOBIL_POSTOJECI);
                            odgovor.setGreska(null);
                            odgovor.setPodatak(null);
                            break;
                        case NADJI_AUTOMOBILE:
                            List<Automobil> automobiliPoKriterijumu = Controller.getInstanca().ucitajAutomobile((String) zahtev.getPodatak());
                            odgovor.setPodatak(automobiliPoKriterijumu);
                            odgovor.setOperacija(Operacije.UCITAJ_MUSTERIJE);
                            odgovor.setGreska(null);
                            break;
                        case UCITAJ_AUTOMOBILE:
                            List<Automobil> automobili = Controller.getInstanca().ucitajAutomobile();
                            odgovor.setPodatak(automobili);
                            odgovor.setOperacija(Operacije.UCITAJ_MARKE);
                            odgovor.setGreska(null);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Desila se greska");
                    odgovor.setGreska(e);
                }
                posiljalac.posalji(odgovor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void prekini() {
        try {
            soket.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
