/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import static bfs.GFG.get_first;

/**
 *
 * @author misaac
 */

class GFG{
    
    public static Node get_first(Node pivot){
        Node curr = pivot;
        while( curr.prev != null ){
            curr = curr.prev;
        }
        return curr;
    }
    
    public static Node get_last(Node pivot){
        Node curr = pivot;
        while( curr.next != null ){
            curr = curr.next;
        }
        return curr;
    }
    
    public static Node partition(Node l, Node h)
    {
        System.out.println("low is :" + l.data +" , high :" + h.data );
           
        if( l == null && h != null ){
            return h;
        }
        else if( h == null && l != null ){
            return l;
        }
        
        Node pivot = h;
        
        System.out.println("pivot is : "+ pivot.data );
        
        Node begin = l , end = h.prev;
        
        if( begin == end ){
            return begin;
        }
        else if( begin == end.prev ){
            if( begin.data < end.data ){
                return begin;
            }else{
                Node prev = begin.prev;
                Node next = end.next;
                
                if( prev != null )
                    prev.next = end;
                end.prev = prev;
                
                end.next = begin;
                begin.prev = end;
                
                begin.next = next;
                if( next != null )
                    next.prev = begin;
                
                return end;
            }
        }
        
        
        if( end == null )
            System.out.println("watch out end is null ...");
        
        while( begin != end && begin.prev != end ){
            
            while( begin.data < pivot.data && begin != end && begin.next != null ){
                System.out.println("begin is :"+begin.data+" so advance ...");
                begin = begin.next;
            }
            
            while( end.data > pivot.data && end != begin && end.prev != null ){
                System.out.println("end is :"+end.data+" so back ...");
                end = end.prev;
            }
            
            if( begin == end ){
                break;
            }else{
                
                if( end.prev == begin || begin.next == end ){
                    
                    Node begin_prev = begin.prev;
                    Node end_next = end.next;
                    
                    begin_prev.next = end;
                    end.prev = begin_prev;
                    
                    end.next = begin;
                    begin.prev = end;
                    
                    begin.next = end_next;
                    end_next.prev = begin;
                    
                    break;
                    
                }
                else{
                    
                    Node end_prev = end.prev;
                    Node end_next = end.next;

                    Node begin_prev = begin.prev;
                    Node begin_next = begin.next;

                    if( end_prev != null )
                        end_prev.next = begin;
                    begin.prev = end_prev;
                    begin.next = end_next;
                    if( end_next != null )
                        end_next.prev = begin;


                    if( begin_prev != null )
                        begin_prev.next = end;
                    end.prev = begin_prev;
                    end.next = begin_next;
                    if( begin_next != null )
                        begin_next.prev = end;

                    Node temp = begin;
                    begin = end.next;
                    end = temp.prev;
                }
                
            }
            
        }
        
        Node prev_pivot = pivot.prev;
        Node next_pivot = pivot.next;
        
        prev_pivot.next = pivot.next;
        if( next_pivot != null )
            next_pivot.prev = prev_pivot;
        
        if( pivot.data < begin.data ){
            
            Node begin_prev = begin.prev;
            
            if( begin_prev != null )
                begin_prev.next = pivot;
            pivot.prev = begin_prev;
            pivot.next = begin;
            begin.prev = pivot;
            
        }else{
            Node begin_next = begin.next;
            
            begin.next = pivot;
            pivot.prev = begin;
            pivot.next = begin_next;
            if( begin_next != null )
                begin_next.prev = pivot;
        }
        return pivot;
    }
}

class Node{
    int data;
    Node next;    
    Node prev;
    public Node(int data){
        this.data = data;
        this.next = this.prev = null;   
    }    
}

public class DouplyLL {
    Node head = null, tail = null;
    
    public void add_node(int data){
        if( head == null ){
            head = tail = new Node( data );
        }else{
            Node temp = new Node( data );
            tail.next = temp;
            temp.prev = tail;
            
            tail = temp;
        }
    }
    
    public boolean check_sorted(Node l ,Node h){
        Node curr = l;
        while( curr != h ){
            if( curr.data > curr.next.data ){
                return false;
            }
            curr = curr.next;
        }
        
        return true;
    }
    
    public void quick_sort(Node l ,Node h ){
        GFG gfg = new GFG();
        
        if( h!= null && l!=h && l!= h.next ){
            
            if( check_sorted(l, h) == true )
                    return;
            

            Node p = gfg.partition(l, h);
            head = l = gfg.get_first( p );
            tail = h = gfg.get_last( p );
            
            print_list();
            
            if( p.prev != null ){
                System.out.println("now the callbacks will be :"+l.data+" to "+p.prev.data);
            }
            if( p.next != null ){
                System.out.println("now the callbacks will be :"+p.next.data+" to "+h.data);
            }
            quick_sort(l, p.prev);
            quick_sort(p.next, h);
        }
    }
    
    void print_list(){
        
        System.out.println("Forword List now is :");
        Node curr = head;
        while( curr != null ){
            System.out.print( curr.data +" ");
            curr = curr.next;
        }
        System.out.println();
        
        System.out.println("Backword List now is :");
        curr = tail;
        while( curr != null ){
            System.out.print( curr.data +" ");
            curr = curr.prev;
        }
        System.out.println();
    }
    
    public DouplyLL(String input){
        String[] splited = input.split("\\s+");
        
        for( String s : splited ){
            add_node( Integer.parseInt(s) );
        }
        print_list();
        
        quick_sort(head, tail);
        
        print_list();
    }
    
}
