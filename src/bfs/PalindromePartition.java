/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author misaac
 */
public class PalindromePartition {
    
    public PalindromePartition(){
        String s = "ababbbabbababaaaabbbbaaaaaaaababababababaaabbb";
        
        System.out.println( minCut(s) );
    }
    
    class The_Comparator implements Comparator<String> { 
        @Override
        public int compare(String str1, String str2) 
        { 
            int first = Integer.parseInt(str1.split(",")[2] ); 
            int second = Integer.parseInt(str2.split(",")[2] ); 
            return second - first ; 
        } 
    } 
    
    public int minCut(String s) {
        
        int min_cost = Integer.MAX_VALUE;
        PriorityQueue<String> memory = new PriorityQueue<String>(new The_Comparator());
        for(int i=0;i<s.length();i++){
            for(int j=s.length();j>i;j--){
                if( !memory.contains(i+","+j) && is_palindrome(s.substring(i, j)) ){
                    memory.add(i+","+(j-1)+","+(j-i) );
                }
            }
        }
        int min_cuts = 0;
        HashMap<Integer,Integer> used = new HashMap<Integer,Integer>();
        while(used.size() < s.length()){
            String b = memory.poll();
            boolean flag_used = false;
            for(int q = Integer.parseInt( b.split(",")[0] );q<=Integer.parseInt( b.split(",")[1] );q++){
                if( used.containsKey(q) ){
                    flag_used = true;
                    break;
                }
            }
            if(flag_used == false){
                for(int q = Integer.parseInt( b.split(",")[0] );q<=Integer.parseInt( b.split(",")[1] );q++){
                    used.put(q, 0); 
                }
                min_cuts++;
            }
        }
        return min_cuts-1;
    }
    
    public boolean is_palindrome(String s){
        
        int x,y;
        for(x=0,y=s.length()-1;x<=y;x++,y--){
            if( s.charAt(x) != s.charAt(y) ){
                return false;
            }
        }
        if(x > y){
            return true;
        }
        return false;
    }
}
