/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.forme.kompoente;

import domen.Automobil;
import domen.Musterija;
import domen.Rezervacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class RezervacijeModelTabele extends AbstractTableModel {

    List<Rezervacija> rezervacije;
    String[] kolone = {"Datum od", "Datum do", "Musterija", "Automobil"};

    public RezervacijeModelTabele(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    @Override
    public int getRowCount() {
        return rezervacije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rezervacije.get(rowIndex).getDatumOd();
            case 1:
                return rezervacije.get(rowIndex).getDatumDo();
            case 2:
                return rezervacije.get(rowIndex).getMusterija();
            case 3:
                return rezervacije.get(rowIndex).getAutomobil();
            default:
                return "N/A";
        }
    }

    public Rezervacija getRezervacijaAt(int index) {
        return rezervacije.get(index);
    }

    public void izmeniElement(int i, Rezervacija rezervacija) {
        rezervacije.remove(i);
        fireTableRowsDeleted(i, i);
    }
}
