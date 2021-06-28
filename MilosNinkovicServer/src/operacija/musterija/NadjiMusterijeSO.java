/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.musterija;

import domen.Musterija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class NadjiMusterijeSO extends ApstraktnaGenerickaOperacija{

    List<Musterija> musterije;

    @Override
    protected void preduslovi(Object param) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        musterije = broker.vratiPoKriterijumu((Musterija)param, " JOIN mesto on (musterija.mestoid=mesto.mestoid)",kljuc);
    }

    public List<Musterija> getMesta() {
        return musterije;
    }
}
