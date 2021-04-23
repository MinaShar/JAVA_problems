/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class StreamChecker {
    
    class Node{
        Character ch;
        HashMap<Character,Node> children;
        boolean is_leaf;
        public Node(Character ch){
            this.ch = ch;
            children = new HashMap<Character,Node>();
            is_leaf = false;
        }
    }
    
    class Tree{
        Node root;
        
        ArrayList<Node> queries;
        
        public Tree(){
            root = new Node(null);
            queries = new ArrayList<Node>();
        }
        
        public void insert(String s){
            
            Node n = root;
            
            for(int i=0;i<s.length();i++){
                if(n.children.containsKey(s.charAt(i))){
                   n = n.children.get( s.charAt(i) ); 
                }else{
                    n.children.put( s.charAt(i) , new Node( s.charAt(i) ) );
                    n = n.children.get( s.charAt(i) );
                }
            }
            n.is_leaf = true;
        }
        
        public boolean query(char c){
            boolean flag = false;
            
            for(int i=0;i<queries.size();i++){
                if( queries.get(i).children.containsKey(c) ){
                    Node to_be_added = queries.get(i).children.get(c);
                    queries.remove(i);
                    queries.add(i,to_be_added);
                    if( queries.get(i).is_leaf ){
                        flag = true;
                    }
                    
                }else{
                    queries.remove(i);
                    i--;
                }
            }
            
            if(this.root.children.containsKey(c)){
                this.queries.add(this.root.children.get(c));
                if( this.root.children.get(c).is_leaf ){
                    flag = true;
                }
            }
            
            return flag;
        }
    }
    
    Tree t;

    public StreamChecker(String[] words) {
        t = new Tree();
        
        for(int i=0;i<words.length;i++){
            t.insert(words[i]);
        }
    }
    
    public boolean query(char letter) {
        return t.query(letter);
    }
    
}
