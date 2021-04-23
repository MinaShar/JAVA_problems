/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author misaac
 */
public class NestedIntegerExtender implements NestedInteger{
    private int integer;
    private boolean is_integer;
    private List<NestedInteger> _list;
    
    private NestedIntegerExtender(int integer){
        is_integer = true;
        this.integer = integer;
    }
    private NestedIntegerExtender(){
        is_integer = false;
        _list = new ArrayList<NestedInteger>();
    }
    private void add_item(NestedInteger item){
        this._list.add(item);
    }
    
    public boolean isInteger(){
        return is_integer;
    }
    public Integer getInteger(){
        return integer;
    }
    public List<NestedInteger> getList(){
        return _list;
    }
    
    public static class StackItem{
        public boolean isNestedInteger;
        public NestedInteger item;
        public char ch;
        public StackItem(NestedInteger item){
            isNestedInteger = true;
            this.item = item;
        }
        public StackItem(Character ch){
            isNestedInteger = false;
            this.ch = ch; 
        }
    }
    
    public static List<NestedInteger> parser(String s){
        
        List<NestedInteger> _list = new ArrayList<NestedInteger>();
        
        Stack<StackItem> st = new Stack<StackItem>();
        
        for(int i=1;i<s.length()-1;i++){
            if( s.charAt(i) == ',' ) continue;
            else if( s.charAt(i) != '[' && s.charAt(i) != ']' && !st.empty() ){
                st.add(new StackItem( new NestedIntegerExtender(s.charAt(i)-'0') ) );
            }
            else if( s.charAt(i) == ']' ){
                
                NestedIntegerExtender k = new NestedIntegerExtender();
                
                while( st.peek().isNestedInteger ){
                    k.add_item( st.pop().item );
                }
                st.pop();
                
                st.add(new StackItem(k));
            }
            else if( s.charAt(i) == '[' ){
                st.add(new StackItem( s.charAt(i) ) );
            }
            else{
                st.add( new StackItem( new NestedIntegerExtender( s.charAt(i) - '0' ) ) );
            }
        }
        
        while( !st.empty() ){
            _list.add(st.pop().item);
        }
        int x = 4;
        return _list;
    }
}
