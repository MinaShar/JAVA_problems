/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class CopyListWithRandomPointer {
    
    class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
    
    public Node copyRandomList(Node head) {
        
        Node head2 = head;
        Node copy_head = null;
        Node returned_copy_head = null;
        HashMap<Node,Node> linker = new HashMap<Node,Node>();
        linker.put(null,null);
        
        while(head != null){
            if(copy_head == null){
                copy_head = new Node(head.val);
                linker.put(head,copy_head);
                returned_copy_head = copy_head;
            }else{
                copy_head.next = new Node(head.val);
                linker.put(head,copy_head.next);
                copy_head = copy_head.next;
            }
            head = head.next;
        }
        
        copy_head = returned_copy_head;
        
        while(head2 != null){
            copy_head.random = linker.get(head2.random)!= null?linker.get( head2.random ) : null;
            
            head2 = head2.next;
            copy_head = copy_head.next;
        }
        return returned_copy_head;
    }
}
