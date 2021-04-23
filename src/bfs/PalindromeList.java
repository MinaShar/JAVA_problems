/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 *
 * @author misaac
 */
public class PalindromeList {
    
    class List<T>{
        
        Node head;
        
        class Node<T>{
            T val;
            Node n;
            public Node(T v){
                this.val = v;
                this.n = n;
            }
        }
        
        public List(){
            this.head = null;
        }
        
        public void add(T v){
            if(this.head == null) this.head = new Node<T>(v); else add_in_tail(v); 
        }
        
        public void add_in_tail(T val){
            Node curr = this.head;
            while(curr.n != null){
                curr = curr.n;
            }
            curr.n = new Node<T>(val);
        }
    }
    
    public PalindromeList(){
        
        List<Character> t = new List<>();
        t.add('r');t.add('a');t.add('d');t.add('a');t.add('r');t.add('t');
        
        try{
            System.out.println(check_palindrome_list(t));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public boolean check_palindrome_list(List T) throws Exception{
//        Field integerListField = PalindromeList.class.getDeclaredField("T");
//        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
//        Class<?> ListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
//        System.out.println("Node typ : "+ListClass);
        
        ArrayList<Object> _stack = new ArrayList<>();
        
        List.Node _n = T.head;
        while(_n != null){
            _stack.add(_n.val);
            _n = _n.n;
        }
        _n = T.head;
        while(_n!= null && _stack.size()>0 && _n.val == _stack.get(_stack.size()-1) ){
            _n = _n.n;
            _stack.remove(_stack.size()-1);
        }
        if(_n == null && _stack.size()==0){
            return true;
        }else return false;
    }
    
}
