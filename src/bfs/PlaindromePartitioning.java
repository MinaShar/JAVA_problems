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
public class PlaindromePartitioning {
    
    class Node{
        ArrayList<String> un_processed;
        ArrayList<String> processed;
        
        public Node(){
            this.un_processed = new ArrayList<>();
            this.processed = new ArrayList<>();
        }
        
        public Node Copy(){
            Node n = new Node();
            n.un_processed = new ArrayList<>(this.un_processed);
            n.processed = new ArrayList<>(this.processed);
            return n;
        }
        
        public void print_partitions(){
            System.out.println("Partitions so far : ");
            for(int i=0;i<processed.size();i++){
                System.out.println(processed.get(i));
            }
        }
    }
    
    String _string;
    int min_stirngs = Integer.MAX_VALUE;
    ArrayList<String> solution;
    Node Solution_node;
    
    public PlaindromePartitioning(String s){
        this._string = s;
        
        SolveIteratively();
    }
    
    public void print_solution(){
        System.out.println("partitions so far is : ");
        for(int i=0;i<this.solution.size();i++){
            System.out.println(this.solution.get(i).toString());
        }
    }
    
    /////CORRECT SOLUTION
    public void SolveIteratively(){
        ArrayList<Node> _stack = new ArrayList<>();
        Node n = new Node();
        n.un_processed.add(this._string);
        _stack.add(n);
        
        while(!_stack.isEmpty()){
            Node x = _stack.get(_stack.size()-1);
            _stack.remove(_stack.size()-1);
            Node new_n = x.Copy();
            
            new_n.print_partitions();
            
            if(new_n.un_processed.size() == 0){
                Solution_node = (Solution_node == null) ? new_n : new_n.processed.size() < Solution_node.processed.size() ? new_n : Solution_node;
            }else if(Solution_node != null && new_n.processed.size() >= Solution_node.processed.size()){
                continue;
            }
                        
            for(int j=0;j<new_n.un_processed.size();j++){
                String curr_proc = new_n.un_processed.get(j);
                new_n.un_processed.remove(j);
                for(int y = curr_proc.length();y>=0;y--){
                    if( check_palindrome( curr_proc.substring(0, y) ) ){
                        
//                        if( new_n.un_processed.get(j).substring(0, y).compareTo("bbaba") == 0 ){
//                            int xqq = 3;
//                            check_palindrome( new_n.un_processed.get(j).substring(0, y) );
//                        }                       
                        Node qq = new_n.Copy();
                        qq.processed.add( curr_proc.substring(0, y) );if(curr_proc.substring(y, curr_proc.length()).length() > 0 ) qq.un_processed.add( curr_proc.substring(y,curr_proc.length() ) );
                        _stack.add(qq);
                    }
                }
                
            }
        }
        
        Solution_node.print_partitions();
    }
    
    
    ///Solve palindrome recursively ...(very noisy)
    public ArrayList<String> Solve(ArrayList<String> subsets,int from,int to){
        
        if(from == to){
            subsets.add(Character.toString( _string.charAt(from) ) );
            return subsets;
        }else if( from > to){
            return subsets;
        }
        
        if(this.solution != null && subsets.size() >= this.solution.size()){
            return subsets;
        }
        if(from <= to && check_palindrome(from, to)){
            subsets.add(this._string.substring(from, to+1));
            subsets = Solve(subsets, 0, from-1);
            subsets = Solve(subsets, to+1, this._string.length()-1);
        }else if(from <= to){
            subsets = Solve(new ArrayList<String>(subsets), from, to-1);
            subsets = Solve(new ArrayList<String>(subsets),from+1, to);
        }
        
        int _total = 0;
        for(int i=0;i<subsets.size();i++)
            _total += subsets.get(i).length();
        
        if(_total == this._string.length()){
            if(this.solution == null ){
                min_stirngs = subsets.size();
                solution = subsets;
            }else if(subsets.size() < this.solution.size()){
                min_stirngs = subsets.size();
                solution = subsets;
            }
        }
        return subsets;
    }
    
    public boolean check_palindrome(String s){
        
        if(s.length() == 1){
            return true;
        }
        if(s.length() == 0 ){
            return false;
        }
        
        ArrayList<Character> _stack = new ArrayList();
        
        for(int i=0 ;i<s.length();i++){
            _stack.add(s.charAt(i));
        }
        
        int k = 0;
        while(!_stack.isEmpty() && k<s.length() && _stack.get(_stack.size()-1) == s.charAt(k)){
            k++;_stack.remove(_stack.size()-1);
        }
        if(_stack.isEmpty() && k==s.length()){
            return true;
        }else return false;
    }
    
    public boolean check_palindrome(int from ,int to){
        ArrayList<Character> _stack = new ArrayList();
        
        for(int i=from ;i<=to;i++){
            _stack.add(this._string.charAt(i));
        }
        
        int k = from;
        while(!_stack.isEmpty() && k<=to && _stack.get(_stack.size()-1) == this._string.charAt(k)){
            k++;_stack.remove(_stack.size()-1);
        }
        if(_stack.isEmpty() && k==to+1){
            return true;
        }else return false;
    }
    
}
