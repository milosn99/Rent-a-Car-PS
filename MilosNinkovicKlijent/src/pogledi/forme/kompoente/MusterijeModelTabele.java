/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.forme.kompoente;

import domen.Musterija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class MusterijeModelTabele extends AbstractTableModel {

    List<Musterija> musterije;
    String[] kolone = {"Ime", "Prezime", "Mesto", "Adresa"};

    public MusterijeModelTabele(List<Musterija> musterije) {
        this.musterije = musterije;
    }

    @Override
    public int getRowCount() {
        return musterije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Musterija> getMusterije() {
        return musterije;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return musterije.get(rowIndex).getIme();
            case 1:
                return musterije.get(rowIndex).getPrezime();
            case 2:
                return musterije.get(rowIndex).getMesto().getNaziv();
            case 3:
                return musterije.get(rowIndex).getAdresa();
            default:
                return "N/A";
        }
    }

    public Musterija getMusterijaAt(int index) {
        return musterije.get(index);
    }


    public void izmeniElement(int i, Musterija musterija) {
        musterije.set(i, musterija);
        fireTableRowsUpdated(i, i);
    }
}
