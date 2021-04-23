/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class PalindromePartitioningUsingMap {
    
    String _string;

    public PalindromePartitioningUsingMap(String s){
        this._string = s;
        System.out.println("Min cuts : " + Solve(0, this._string.length()-1 , new HashMap<String,Integer>() ) );
    }
    
    public boolean is_palindrome(int from , int to){
        
        while(this._string.charAt(from) == this._string.charAt(to) && from < to){
            from++;
            to--;
        }
        
        if(from >= to){
            return true;
        }else{
            return false;
        }
    }
    
    public int Solve(int from, int to,HashMap<String,Integer> memo){
        
        if(from >= to){
            return 0;
        }
        
        if(memo.containsKey( Integer.toString(from)+","+Integer.toString(to) ) ){
            return memo.get(Integer.toString(from)+","+Integer.toString(to));
        }else if(from == to){
            return 0;
        }else if(is_palindrome(from, to)){
            return 0;
        }
        
        int minimum = Integer.MAX_VALUE;
        
        for(int i=from;i<to;i++){
            int left_min = Integer.MAX_VALUE;int right_min = Integer.MAX_VALUE;
            
            if( memo.containsKey(Integer.toString(from)+","+Integer.toString(i) ) ){
                left_min = memo.get( Integer.toString(from)+","+Integer.toString(i) );
            }else{
                left_min = Solve(from, i, memo);
                memo.put(Integer.toString(from)+","+Integer.toString(i) , left_min );
            }
            
            if( memo.containsKey( Integer.toString(i+1)+","+Integer.toString(to) ) ){
                right_min = memo.get(Integer.toString(i+1)+","+Integer.toString(to) );
            }else{
                right_min = Solve(i+1, to, memo);
                memo.put(Integer.toString(i+1)+","+Integer.toString(to) , right_min );
            }
            
            if(left_min + right_min + 1 < minimum ){
                minimum = left_min + right_min + 1;
            }         
        }
        
        memo.put(Integer.toString(from)+","+Integer.toString(to) , minimum );
        
        return minimum;
    }
    
}
