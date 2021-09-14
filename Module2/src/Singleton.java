
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joann
 */
public class Singleton {
    private static Singleton firstInstance = null;
    private static Boolean firstThread = true;
    
    private Singleton(){}
    
    public Singleton getInstance(){
        if(firstInstance==null){
            if(firstThread){
                firstThread=false;
                Thread.currentThread();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Singleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            firstInstance = new Singleton();
        }
        return firstInstance;
    }
}
