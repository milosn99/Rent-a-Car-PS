/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacija.mesto;

import domen.Mesto;
import domen.Musterija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author milos
 */
public class UcitajMestaSO extends ApstraktnaGenerickaOperacija{

    List<Mesto> mesta;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        mesta = broker.vratiSve(new Mesto(), kljuc);
    }

    public List<Mesto> getMesta() {
        return mesta;
    }

    
}
