/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domen.ApstraktniDomenskiObjekat;
import java.util.List;

/**
 *
 * @author milos
 */
public interface Repozitorijum<T> {
    public List<T> vratiSve(T param, String join) throws Exception;
    public List<T> vratiPoKriterijumu(T param, String join, String uslov) throws Exception;
    public T vratiPoPrimarnomKljucu(T param, String join) throws Exception;
    public void ubaci(T param) throws Exception;
    public void obrisi(T param) throws Exception;
    public void izmeni(T param) throws Exception;
}
