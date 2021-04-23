/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

/**
 *
 * @author misaac
 */
public class Merge {
    
    class LNode{
        int data;
        LNode next;
        LNode(int val){
            this.data = val;
            this.next = null;
        }
    }
    
    
    public LNode mergeLists(LNode head1 , LNode head2)
	{
		// WRITE YOUR CODE HERE
		
			
		LNode head = null;
		LNode curr = null;
		
		while(head1 != null || head2 != null){
		    if(head1 == null){
		        if (curr == null)
		        {
		            curr = new LNode(head2.data);
		        }
		        else {
		            curr.next = new LNode(head2.data);
                            curr = curr.next;
		        }
		    }
		    else if (head2 == null){
		         if (curr == null)
		        {
		            curr = new LNode(head1.data);
		        }
		        else {
		            curr.next = new LNode(head1.data);
                            curr = curr.next;
		        }
		    }
		    else if(head1.data < head2.data){
		         if (curr == null)
		        {
		            curr = new LNode(head1.data);
		        }
		        else {
		            curr.next = new LNode(head1.data);
                            curr = curr.next;
		        }
		    }else{
		         if (curr == null)
		        {
		            curr = new LNode(head2.data);
		        }
		        else {
		            curr.next = new LNode(head2.data);
                            curr = curr.next;
		        }
		    }
		    if(head == null){
		        head = curr;
		    }
		    head1 = head1.next;
		    head2 = head2.next;
		    
		}
		return head;
	}
    
    public Merge(){
        LNode r = new LNode(1);
        r.next = new LNode(2);
        r.next.next = new LNode(3);
        
        LNode l = new LNode(1);
        l.next = new LNode(2);
        l.next.next = new LNode(3);
        
        LNode x = mergeLists(r,l);
    }
    
}
