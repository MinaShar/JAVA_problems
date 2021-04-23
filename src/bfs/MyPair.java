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
class MyPair<X,Y>{
        
    X x;Y y;
        
        
    public MyPair(X x,Y y){     
        this.x = x;
        this.y = y;
    }
        
    
    public X getMyKey(){
        return this.x;
    }
        
    
    public Y getValue(){
    
        return this.y;
        
    }    
}

