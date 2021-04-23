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
public class HeightGenericTree {
    
    static int TreeHeight=0;
    
    class Node<T>{
        T val;
        ArrayList<Node> childs;
        boolean is_root;
        int n_height;
                
        public Node(T val,Node _p){
            this.val = val;
            this.childs = new ArrayList<Node>();
            is_root = false;
            n_height = _p == null ? 0 : _p.n_height+1;
            HeightGenericTree.TreeHeight = n_height >HeightGenericTree.TreeHeight ? n_height : HeightGenericTree.TreeHeight;
        }
        public void make_root(){
            this.is_root = true;
        }
    }
        
    public HeightGenericTree(){
        
        int[] arr = new int[]{ -1, 0, 0, 0, 3, 1, 1, 2 };
        
        BuildTree(arr);
        
        System.out.println("The tree height : "+ HeightGenericTree.TreeHeight);
    }
    
    public void BuildTree(int[] arr){
        
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            
            Node n = new Node<Integer>(i, arr[i]==-1? null : nodes.get(arr[i]) );
            if(arr[i] == -1) n.make_root();
            nodes.add(n);
            
            if(arr[i] != -1)nodes.get(arr[i]).childs.add(n);
        }
    }
}
