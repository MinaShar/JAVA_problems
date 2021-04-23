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
public class PrintingThread implements Runnable{
    
    CounterObj o;
    public PrintingThread(CounterObj o){
        this.o = o;
    }
    @Override
    public void run() {
        while(o.flag){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Counter now is : " + o.x);
        }
        System.out.println("Now counter thread is aborting ... ");
    }
    
}
