/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author misaac
 */
public class MyScheduler extends TimerTask {
    
    
    String name;
    
    public MyScheduler(String _name){
        
        this.name = _name;
        
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" "+name+" the task has executed successfully "+ new Date());
        if("Task1".equalsIgnoreCase(name)){
          try {

          Thread.sleep(10000);

          } catch (InterruptedException e) {
            e.printStackTrace();

          }
        }
        
    }
    
}
