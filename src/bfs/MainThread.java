/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author misaac
 */
public class MainThread implements Runnable{

    CounterObj c;
    public MainThread(CounterObj c){
        this.c = c;
    }
    @Override
    public void run() {
        
        for(this.c.x=0;this.c.x<7;this.c.x++){
            try {
                Thread.sleep(5000);
                System.out.println("We are still working ...");
            } catch (Exception ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        this.c.flag = false;
        System.out.println("We finished working and aborting ....");
    }
    
}
