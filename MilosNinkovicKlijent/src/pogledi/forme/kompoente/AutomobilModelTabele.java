/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pogledi.forme.kompoente;

import domen.Automobil;
import domen.Musterija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milos
 */
public class AutomobilModelTabele extends AbstractTableModel {

    List<Automobil> automobili;
    String[] kolone = {"Registracija", "Model", "Godiste", "Kilometraza", "Gorivo", "Potrosnja", "Cena", "Kubikaza", "Jacina motora"};

    public AutomobilModelTabele(List<Automobil> automobili) {
        this.automobili = automobili;
    }

    @Override
    public int getRowCount() {
        return automobili.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Automobil> getAutomobili() {
        return automobili;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return automobili.get(rowIndex).getRegistracija();
            case 1:
                return automobili.get(rowIndex).getModel();
            case 2:
                return automobili.get(rowIndex).getGodiste();
            case 3:
                return automobili.get(rowIndex).getKilometraza();
            case 4:
                return automobili.get(rowIndex).getGorivo();
            case 5:
                return automobili.get(rowIndex).getPotrosnja() + "l/100km";
            case 6:
                return automobili.get(rowIndex).getCena() + "EUR";
            case 7:
                return automobili.get(rowIndex).getKubikaza()+ "cm3";
            case 8:
                return automobili.get(rowIndex).getJacinaMotora()+ "kW";
            default:
                return "N/A";
        }
    }

    public Automobil getAutomobilAt(int index) {
        return automobili.get(index);
    }

    public void izmeniElement(int i, Automobil automobil) {
        automobili.set(i, automobil);
        fireTableRowsUpdated(i, i);
    }
}
