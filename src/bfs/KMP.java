/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;

/**
 *
 * @author misaac
 */
public class KMP {

    class Node{
        public int index;
        Node(int index){
            this.index = index;
        }
    }
    
    public KMP() {
        String s = "ABABDABACDABABCABABAAAABAAABABBBAABABCABABAAAABBABAA";
        String pat = "ABABCABAB";
        
        ArrayList<Integer> res = Solve(s, pat);
        
        System.out.println("results are : ");
        for(int i=0;i<res.size();i++){
            System.out.println("result index : "+res.get(i) );
        }
    }
    
    public ArrayList<Integer> Solve(String s,String pattern){
        
        ArrayList<Integer> results = new ArrayList<Integer>();
        ArrayList<Node> queue = new ArrayList<Node>();
        for(int i=0;i<=s.length();i++){
            for(int j=0;j<queue.size();j++){
                Node n = queue.get(j);
                int length = i - n.index + 1;
                if(s.substring(n.index, i).equals( pattern.substring(0, length-1) ) && length-1 == pattern.length()){
                    results.add( n.index );
                    queue.remove(j);
                }else if( s.substring(n.index, i).equals( pattern.substring(0, length-1) ) ){
                    continue;
                }else if( !s.substring(n.index, i).equals( pattern.substring(0, length-1) ) ){
                    queue.remove(j);
                }
            }          
            queue.add(new Node(i));
        }
        return results;
    }
    
}
