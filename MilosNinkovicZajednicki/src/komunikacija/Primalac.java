 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Primalac {
    private Socket soket;

    public Primalac(Socket soket) {
        this.soket = soket;
    }
    
    public Object primi() throws Exception{
        try {
            ObjectInputStream in=new ObjectInputStream(soket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new Exception("Greska prilikom primanja objekta!\n"+ex.getMessage());
        }
    }
}
