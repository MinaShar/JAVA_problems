/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

/**
 *
 * @author misaac
 */
public class t extends Thread{
        
        String _name;
        public void func(){
            
                try{
                    for(int i=0;i<10;i++){
                        System.out.println("thread "+ this._name + " gives "+i);
                        int x = (int) (Math.random()*1000);
                        System.out.println("Now thread "+this._name+" will sleep "+x);
                        Thread.currentThread().sleep(x);
                    }  
                }catch(Exception e){
                    System.out.println("Error: "+e.toString());
                }          
        }
        
        public t(String s){
            this._name = s;
        }

        @Override
        public void run() {
            System.out.println("thread "+this._name+" launched successfully...");
            func();
        }
        
    }
