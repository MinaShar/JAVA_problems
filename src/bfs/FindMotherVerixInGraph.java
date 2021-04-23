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
public class FindMotherVerixInGraph {
    
    class Node{
        int val;
        Node left;
        Node right;
        
        public Node(int val,Node l,Node r){
            this.val= val;
            this.left = l;
            this.right = r;
        }
    }
    
    class Graph{
        ArrayList<Node> g;
        public Graph(){
            g = new ArrayList<Node>();
        }
        
        public int FindMotherVerix(){
            
            for(int i=0;i<g.size();i++){
                ArrayList<Node> visitedNodes = new ArrayList<>();
                Proceedforward(g.get(i), visitedNodes);
                if(visitedNodes.size() == g.size()){
                    return g.get(i).val;
                }
            }
            return -1;
        }
        
        public void Proceedforward(Node N ,ArrayList<Node> VNodes){
            if(N == null){
                return;
            }
            boolean flag_isvisited = false;
            for(int i=0;i<VNodes.size();i++){
                if(N.val == VNodes.get(i).val){
                    flag_isvisited = true;
                    break;
                }
            }
            
            if(flag_isvisited == false){
                VNodes.add(N);
            }
            Proceedforward(N.left, VNodes);
            Proceedforward(N.right, VNodes);
        }
    }
    
    public FindMotherVerixInGraph(){
        Graph g = new Graph();
        Node n0 = new Node(0, null, null);
        Node n1 = new Node(1, null, null);
        Node n2 = new Node(2, null, null);
        Node n3 = new Node(3, null, null);
        Node n4 = new Node(4, null, null);
        Node n5 = new Node(5, null, null);
        Node n6 = new Node(6, null, null);
        
        n0.left = n1;n0.right = n2;
        n1.left = n3;
        n4.left = n1;
        n5.left = n2;n5.right = n6;
        n6.left = n4;n6.right = n0;
        
        g.g.add(n0);g.g.add(n1);g.g.add(n2);g.g.add(n3);g.g.add(n4);g.g.add(n5);g.g.add(n6);
        
        System.out.println("Mother verix is : " + g.FindMotherVerix());
    }
    
}
