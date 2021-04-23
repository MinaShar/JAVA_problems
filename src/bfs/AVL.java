/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author misaac
 */
public class AVL {
    
    class Node{
        int val;
        Node left;
        Node right;
        int height;
        public Node(int val,Node l,Node r){
            this.val = val;
            this.left = l;this.right = r;this.height = 1;
        }
    }
    
    Node root;
    
    public AVL(){
        root = null;
        
        root = insert(root, 0);
        print_tree_bf();
        root = insert(root, 1);
        print_tree_bf();
        root = insert(root, 2);
        print_tree_bf();
        root = insert(root, 3);
        print_tree_bf();
        root = insert(root, 4);
        print_tree_bf();
        root = insert(root, 5);
        print_tree_bf();
        root = insert(root, 6);
        print_tree_bf();
        root = insert(root, 7);
        
        print_tree_bf();
    }
    
    public int max(int x,int y){
        return x>y ? x : y;
    }
    
    public int updateNodeHeight(Node N){
        if(N == null)return 0;
        return max( updateNodeHeight(N.left) , updateNodeHeight(N.right) ) + 1;
    }
    
    public Node RightRotate(Node N){
        Node x = N.left;
        Node right_child = x.right;
        x.right = N;
        N.left = right_child;
        return x;
    }
    
    public Node LeftRotate(Node N){
        Node x = N.right;
        Node left_child = x.left;
        x.left = N;
        N.right =  left_child;
        return x;
    }
    
    public Node insert(Node N , int new_val){
        
        if(N == null){
            return new Node(new_val, null, null);
        }else if(new_val < N.val){
            N.left = insert(N.left, new_val);
        }else if(new_val > N.val){
            N.right = insert(N.right, new_val);
        }
                
        int left_h = N.left != null ? N.left.height : 0;
        int right_h = N.right != null ? N.right.height : 0;
        int weight = left_h - right_h ;
        
        if(weight > 1 && new_val < N.left.val){
            N = RightRotate(N);
        }else if(weight > 1 && new_val > N.left.val){
            LeftRotate(N.left);
            N = RightRotate(N);
        }else if(weight < -1 && new_val > N.right.val){
            N = LeftRotate(N);
        }else if(weight < -1 && new_val < N.right.val){
            RightRotate(N.right);
            N = LeftRotate(N);
        }
        
        N.height = updateNodeHeight(N);
        if(N.left != null)N.left.height = updateNodeHeight(N.left);
        if(N.right != null)N.right.height = updateNodeHeight(N.right);
        
        return N;
    }
    
    public void print_tree_bf(){
        
        Queue<Node> q = new LinkedList<>();
        
        q.add(root);
        
        Node n = null;
        System.out.println("the tree now :");
        int curr_height = 0;
        int nodes_in_curr_height =(int) Math.pow(2, curr_height);
        while( true ){
            n =(Node) q.peek();
            q.remove();
            
            if(n==null){System.out.print("[ ]");}
            else{System.out.print( "[" + n.val + "]" );}
            
            nodes_in_curr_height--;
            if(nodes_in_curr_height == 0){
                curr_height++;
                nodes_in_curr_height = (int) Math.pow(2, curr_height);
                System.out.println();
            }
            
            if(curr_height == 6)break;
            
            if(n != null){q.add(n.left);}else{q.add(null);}
            if(n != null){q.add(n.right);}else{q.add(null);}
            
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>");
        
        
        q = new LinkedList<Node>();
        q.add(root);
        n = null;
        while( (n = q.peek()) != null ){
            q.remove();
            System.out.println("Node : "+n.val + " have height "+ n.height);
            
            if(n.left != null) q.add(n.left);
            if(n.right != null)q.add(n.right);         
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>");

    }
    
}
