/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;
import javax.security.sasl.Sasl;

/**
 *
 * @author misaac
 */
public class Equale {
    
    public Equale(ArrayList<Integer> A){
        ArrayList<Integer> t = Solve(A);
        System.out.println(t);
        
    }
    
    public int[] toggleIndex(int toggle_at,int[] curr_index){
        int indexer = 1;
        while (true) {      
            if(curr_index.length-indexer < 0 ){
                return null;
            }
            int x = curr_index[ curr_index.length-indexer ];
            x++;
            if(x == toggle_at){
                x = 0;
                curr_index[ curr_index.length-indexer ] = x ;
                indexer++;
            }else{
                curr_index[ curr_index.length-indexer ] = x ;
                break;
            }
        }
        return curr_index;
    }
    
    public ArrayList<Integer> Solve(ArrayList<Integer> A){
        HashMap<Integer,Pair<Integer,Integer>> buffer = new HashMap<Integer,Pair<Integer,Integer>>();
        ArrayList<Integer> solution = new ArrayList<>();
        
        int toggle_at = A.size();int[] curr_index = {0,0,0,0}; 
        
        for(;(curr_index = toggleIndex(toggle_at,curr_index)) != null;){
            int a = A.get(  curr_index[0]  );
            int b = A.get(  curr_index[1]  );
            int c = A.get(  curr_index[2]  );
            int d = A.get(  curr_index[3]  );
            if( a + b == c + d  &&
                    curr_index[0]  < curr_index[1]  &&
                    curr_index[2]  < curr_index[3]  &&
                    curr_index[0]  <  curr_index[2] &&
                    curr_index[1]  != curr_index[2] && 
                    curr_index[1]  != curr_index[3] ){
                solution.add(  curr_index[0] ) ;
                solution.add(  curr_index[1] ) ;
                solution.add(  curr_index[2] ) ;
                solution.add(  curr_index[3] ) ;
                break;
            }
        }
        
        return solution;
    }
}
