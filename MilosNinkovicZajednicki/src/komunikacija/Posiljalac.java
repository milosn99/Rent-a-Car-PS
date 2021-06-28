/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author FON
 */
public class Posiljalac implements Serializable{
    private Socket soket;

    public Posiljalac(Socket socket) {
        this.soket = socket;
    }
    
    
    public void posalji(Object object) throws Exception{
        try{
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(object);
    
        }catch(SocketException se){
            throw se;
        }
        catch(Exception e){
            throw new Exception("Greska pri slanju objekta\n"+e.getMessage());
        }
    }
    
    
}
