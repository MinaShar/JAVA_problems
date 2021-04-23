/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.awt.Desktop;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author misaac
 */
public class WebChecker implements Runnable {
    
    static Lock l = new ReentrantLock();
    public Thread th;
    public WebChecker(){
        th = new Thread(this);
        th.start();
    }
    
    public void log_to_file(String s){
        
        Logger logger = Logger.getLogger("MyLog"); 
        FileHandler fh;  
     
        Timestamp tme = new Timestamp( System.currentTimeMillis() );
        try {  
            
//            byte[] array = new byte[7]; // length is bounded by 7
//            new Random().nextBytes(array);
//            String generatedString = new String(array, Charset.forName("UTF-8"));
            
//            l.lock();
            // This block configure the logger with handler and formatter time 
            fh = new FileHandler("C:/NetBeansProjects/BFS/my_logs"+tme.getTime()+".txt");  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  

            // the following statement is used to log any messages  
            logger.info(s);  

        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        finally{
//            l.unlock();
        }
        
    }
    
    @Override
    public void run(){
        
        try {
            
            Desktop.getDesktop().browse(new URI("https://ry1swvdwhweb01.mof.gov.sa/Attachment/#/fc/1") );
            
        } catch (Exception ex) {
            Logger.getLogger(WebChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
