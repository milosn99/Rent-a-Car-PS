/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.baza;

import storage.Repozitorijum;
import storage.baza.konekcija.Konekcija;

/**
 *
 * @author milos
 */
public interface DBRepozitorijum<T> extends Repozitorijum<T> {

    default void potvrdiTransakciju() throws Exception {
        Konekcija.getInstanca().getKonekcija().commit();

    }

    default void ugasiKonekciju() throws Exception {
        Konekcija.getInstanca().getKonekcija().close();

    }

    default void ponistiTransakciju() throws Exception {
        Konekcija.getInstanca().getKonekcija().rollback();

    }

    default void povezi() throws Exception {
        Konekcija.getInstanca().getKonekcija();

    }
}
