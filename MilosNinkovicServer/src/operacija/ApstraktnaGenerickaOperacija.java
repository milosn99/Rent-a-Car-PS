/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija;

import storage.Repozitorijum;
import storage.baza.DBRepozitorijum;
import storage.baza.impl.RepozitorijumDBGenericki;
import storage.baza.konekcija.Konekcija;

/**
 *
 * @author milos
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        broker = new RepozitorijumDBGenericki();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally{
            ugasi();
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DBRepozitorijum)broker).povezi();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum)broker).potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum)broker).ponistiTransakciju();
    }

    private void ugasi() throws Exception {
        ((DBRepozitorijum)broker).ugasi();
    }

}
