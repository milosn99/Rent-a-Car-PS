/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;
import komunikacija.util.Operacije;

/**
 *
 * @author milos
 */
public class Zahtev implements Serializable{
    private Operacije operacija;
    private Object podatak;

    public Zahtev() {
    }

    public Zahtev(Operacije operacija, Object podatak) {
        this.operacija = operacija;
        this.podatak = podatak;
    }

    public Operacije getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacije operacija) {
        this.operacija = operacija;
    }

    public Object getPodatak() {
        return podatak;
    }

    public void setPodatak(Object podatak) {
        this.podatak = podatak;
    }


    
}
