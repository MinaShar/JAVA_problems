/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.*;

/**
 *
 * @author misaac
 */
public class SimpleCalc {
    
    class Node{
        boolean is_number;
        int number;
        char operation;
        public Node(int number){
            this.is_number = true;
            this.number = number;
            operation = ' ';
        }
        public Node(char operation){
            this.is_number = false;
            this.number = -1;
            this.operation = operation;
                
        }
    }
    
    public int solve(int x,int y,char z){
        switch(z){
            case '+':
                return x+y;
            case'-':
                return x-y;
            case '*':
                return x*y;
            case '/':
                return x/y;
        }
        return -1;
    }
    
    public int calculate(String s) {
        
        ArrayList<Node> stack = new ArrayList<Node>();
        
        for(int i=0;i<s.length();i++){
            if( s.charAt(i) == ' ' )
                continue;
            
            if( s.charAt(i) == '(' ){
                stack.add(new Node(s.charAt(i)));
                continue;
            }else if( s.charAt(i) == ')' ){
                while( stack.get( stack.size()-2 ).operation != '(' ){
                    int acc1 = stack.get( stack.size()-1 ).number;stack.remove(stack.size()-1);
                    char oper = stack.get( stack.size()-1 ).operation;stack.remove(stack.size()-1);
                    int acc2 = stack.get( stack.size()-1 ).number;stack.remove(stack.size()-1);
                    stack.add( new Node( solve(acc1,acc2,oper) ) );
                }
                stack.remove( stack.size()-2 );
                continue;
            }
            
            if( s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='*' || s.charAt(i)=='/' ){
                stack.add(new Node( s.charAt(i) ));
                continue;
            }
            
            String number = "";
            while( i<s.length() && Character.isDigit(s.charAt(i)) ){
                number += s.charAt(i);
                if(Character.isDigit(s.charAt(i+1)) ){
                    i++;
                }else
                    break;
            }
            int final_number = Integer.parseInt(number);
            stack.add( new Node(final_number) );
        }
        
        for(int i=0;i<stack.size();i++){
            if(stack.get(i).operation=='*' || stack.get(i).operation=='/'){
                int x = stack.get(i-1).number;
                char oper = stack.get(i).operation;
                int y = stack.get(i+1).number;
                
                stack.remove(i-1);stack.remove(i-1);stack.remove(i-1);
                stack.add(i-1, new Node(solve(x,y,oper)) );
            }
        }
        
        while(stack.size() > 1){
            int x = stack.get(0).number;
            stack.remove(0);
            
            char oper = stack.get(0).operation;
            stack.remove(0);
            
            int y = stack.get(0).number;
            stack.remove(0);
                
            stack.add(0, new Node(solve(x,y,oper)) );
        }
        
        return stack.get(0).number;
        
    }
    
}
