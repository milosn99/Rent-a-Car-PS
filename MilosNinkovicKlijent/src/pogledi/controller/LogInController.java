/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.controller;

import domen.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import pogledi.coordinator.Coordinator;
import pogledi.forme.FormaLogIn;

/**
 *
 * @author milos
 */
public class LogInController {

    private final FormaLogIn frmLogIn;

    public LogInController(FormaLogIn frmLogIn) {
        this.frmLogIn = frmLogIn;
        addActionListeners();
    }

    public void otvoriFormu() {
        frmLogIn.setVisible(true);
    }

    private void addActionListeners() {
        frmLogIn.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            public void prijava(ActionEvent e) {
                try {
                    String korisnickoIme = frmLogIn.getTxtKorisnickoIme().getText().trim();
                    String sifra = String.copyValueOf(frmLogIn.getTxtSifra().getPassword()).trim();
                    frmLogIn.getLblKorisnickoImeValidacija().setText("");
                    frmLogIn.getLblSifraValidacija().setText("");
                    validirajKorisnika(korisnickoIme, sifra);
                    Komunikacija.getInstanca().konekcija();
                    Korisnik k = Komunikacija.getInstanca().logIn(korisnickoIme,sifra);
                    Coordinator.getInstanca().dodajParam("korisnik", k);
//                    JOptionPane.showMessageDialog(frmLogIn, "Dobrodosao "+ k.getKorisnickoIme(),"Uspeh",JOptionPane.INFORMATION_MESSAGE);
                    Coordinator.getInstanca().otvoriGlavnuFormu();
                    frmLogIn.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmLogIn, "Korisnik ne postoji", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void validirajKorisnika(String korisnickoIme, String sifra) throws Exception {
                if (korisnickoIme.equals("") || sifra.equals("")) {
                    if (korisnickoIme.equals("")) {
                        frmLogIn.getLblKorisnickoImeValidacija().setText("Unesite korisnicko ime");
                    }
                    if (sifra.equals("")) {
                        frmLogIn.getLblSifraValidacija().setText("Unesite sifru");
                    }

                    frmLogIn.getTxtSifra().setText("");
                    throw new Exception("Korisnicko ime i sifra moraju biti uneti");
                }
                frmLogIn.getLblKorisnickoImeValidacija().setText("");
                frmLogIn.getLblSifraValidacija().setText("");
            }
        });
    }

}
