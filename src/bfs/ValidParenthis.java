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
public class ValidParenthis {
    
    
    public ValidParenthis(String s){
        System.out.println("result is : "+ CheckIsValid(s));
    }
    
    public boolean CheckIsValid(String s){
        ArrayList<Character> stack = new ArrayList<>();
        
        for(int i=0;i<s.length();i++){
            if( s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i)=='{' ){
                stack.add(s.charAt(i));
            }else{
                if( s.charAt(i) == ')' && stack.get( stack.size()-1 ) == '(' ){
                    stack.remove( stack.size()-1 );
                }else if ( s.charAt(i) == '}' && stack.get( stack.size()-1 ) == '{' ){
                    stack.remove( stack.size()-1 );
                }else if( s.charAt(i) == ']' && stack.get( stack.size()-1 ) == '[' ){
                    stack.remove( stack.size()-1 );
                }else return false;
            }
        }
        
        if( stack.size() == 0 ){
            return true;
        }else return false;
    }
}
