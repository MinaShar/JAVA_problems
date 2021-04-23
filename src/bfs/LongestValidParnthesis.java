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
public class LongestValidParnthesis {
    
    
    public LongestValidParnthesis(String s){
        System.out.println("string-length : "+s.length());
        int x = getLongestLenByStack(s);
        
        System.out.println("max-length : "+ x);
    }
    
    private int getLongestLenByStack(String s) {
        //use last to store the last matched index
        int len = s.length(), maxLen = 0, last = -1;
        if (len == 0 || len == 1)
            return 0;
        
        //use this stack to store the index of '('
        ArrayList<Integer> stack = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(')
                stack.add(i);
            else {
                //if stack is empty, it means that we already found a complete valid combo
                //update the last index.
                if (stack.isEmpty()) {
                    last = i;
                } else {
                    stack.remove(stack.size()-1);
                    //found a complete valid combo and calculate max length
                    if (stack.isEmpty())
                        maxLen = Math.max(maxLen, i - last);
                    else
                        //calculate current max length
                        maxLen = Math.max(maxLen, i - stack.get(stack.size()-1));
                }
            }
        }
        
        return maxLen;
    }
    
    public int SolveLinearly(String s){
        class Node{
            char ch;
            boolean IsClosed;
            int sub_length;
            public Node(char ch,boolean IsClosed){
                this.ch = ch;this.IsClosed = IsClosed;sub_length=0;
            }
        }
        int max_current = 0;
        ArrayList<Node> arr = new ArrayList<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                arr.add( new Node( s.charAt(i) , false) );
            }else if(s.charAt(i) == ')'){
                arr.add(new Node(s.charAt(i),true));
                boolean solution_found = false;
                int length = 1;int j=i-1;
                for( ;j>=0;j--){
                    length++;
                    if(arr.get(j).ch == '(' && arr.get(j).IsClosed == false){
                        arr.get(j).IsClosed = true;arr.get(j).sub_length=length;
                        solution_found = true;
                        break;
                    }
                }
                
                int longest_possible = length;
                if(solution_found){                   
                    for(int k=j-1;k>=0;k--){
                        if(arr.get(k).ch=='(' && arr.get(k).IsClosed==true){
                            longest_possible += arr.get(k).sub_length;
                        }else if(arr.get(k).ch=='(' && arr.get(k).IsClosed==false){
                            break;
                        }
                    }
                }
                
                
                if(solution_found==true && longest_possible > max_current){
                    max_current = longest_possible;
                }
            }
        }
        
        
        return max_current;
    }
    
    public int SolveDF(String s){
        
        int max = -1;
        for(int start=0;start<s.length();start++){
            for(int end=s.length()-1;end>start;end--){
                int x = CheckAvailability(s, start, end);
                max = x > max ? x : max;
            }
        }
        return max;
    }
    
    public int CheckAvailability(String s,int start,int end){
        
        ArrayList<Character> _stack = new ArrayList<Character>();
        int counter =0;
        
        for(int i=start;i<=end;i++){
            if( s.charAt(i) == '(' ){
                _stack.add( s.charAt(i) );
                counter++;
            }else if( s.charAt(i) == ')' && _stack.size() > 0 ){
                _stack.remove( _stack.size() -1 );
                counter++;
                
            }else {
                break;
            }
        }
        if(_stack.size() == 0){
            return counter;
        }
        return -1;
    }
    
    public int calculate(String s){
        int max_length = 0;
        ArrayList<Character> _stack = new ArrayList<Character>();
        int counter =0;
        for(int i=0;i<s.length();i++){
            counter = 0;
            _stack = new ArrayList<>();
            for(int j=i;j<s.length();j++){
                if( s.charAt(j) == '(' ){
                    _stack.add( s.charAt(j) );
                    counter++;
                }else if( s.charAt(j) == ')' && _stack.size() > 0 ){
                    _stack.remove( _stack.size() -1 );
                    counter++;
                    
                }else {
                    break;
                }
            }
            
            if(_stack.size() == 0){
                max_length = counter > max_length ? counter : max_length;
            }else{
                while(_stack.size() > 0 && counter > 0){
                    counter--;
                    _stack.remove(_stack.size()-1);
                }
                if(_stack.size() == 0){
                    max_length = counter > max_length ? counter : max_length;
                }
            }
            counter =0 ;
        }
        
        return max_length;
    }
}
