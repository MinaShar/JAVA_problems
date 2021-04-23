/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author misaac
 */
public class SumDistTree {
    
    class Node{
        int num_nodes_in_sub_tree;
        int total_cost_in_parnt;
        ArrayList<Node> sub_tree;
        int depth;
        ArrayList<Node> parent;
        int val;
        public Node(int val){
            this.val = val;
            num_nodes_in_sub_tree = 1;
            depth = 0;
            total_cost_in_parnt = 0;
            sub_tree = new ArrayList<>();
            parent = new ArrayList();
        }
        public void connect(Node n){
            sub_tree.add(n);
            n.parent.add(this);
            this.num_nodes_in_sub_tree++;
            for(int i=0;i<this.parent.size();i++){
                this.increase_nodes_in_subtree( this.parent.get(i) );
            }
        }
        
        public void increase_nodes_in_subtree(Node n){
            n.num_nodes_in_sub_tree++;
            for(int i=0;i<n.parent.size();i++){
                increase_nodes_in_subtree(n.parent.get(i));
            }
        }
        public int curr_tree_weight(){
            return this.num_nodes_in_sub_tree;
        }
    }
    
    public int[] Solve(int N, int[][] edges){
        
        if(N == 2 && edges.length > 0) return new int[]{1,1};
        
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0;i<N;i++){
            nodes.add(new Node(i));
        }
        for(int i=0;i<edges.length;i++){
            nodes.get( edges[i][0] ).connect( nodes.get( edges[i][1] ) );
        }
        
        cal_cost_in_par_for_parents(nodes);
        
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            if(nodes.get(i).parent.size()==0){
                continue;
            }
            nodes.get(i).total_cost_in_parnt = nodes.get(i).parent.get(0).total_cost_in_parnt
                - (nodes.get(i).num_nodes_in_sub_tree+nodes.get(i).parent.size()-1) + N - (nodes.get(i).num_nodes_in_sub_tree+nodes.get(i).parent.size()-1);
        }
        
        for(int i=0;i<N;i++){
            if(nodes.get(i).parent.size()==0){
                arr[i] = nodes.get(i).total_cost_in_parnt;
            }else arr[i] = nodes.get(i).parent.get(0).total_cost_in_parnt
                - (nodes.get(i).num_nodes_in_sub_tree+nodes.get(i).parent.size()-1) + N - (nodes.get(i).num_nodes_in_sub_tree+nodes.get(i).parent.size()-1);
        }
        
        return arr;
    }
    
    public void cal_cost_in_par_for_parents(ArrayList<Node> nodes){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).parent.size() == 0){
                ArrayList<Node> visited = new ArrayList();
                visited.add(nodes.get(i));
                int total = 0;
                for(int j=0;j<nodes.get(i).sub_tree.size();j++){
                    total += calc_cost_from_to_all( nodes.get(i).sub_tree.get(j),1,visited );
                }
                nodes.get(i).total_cost_in_parnt = total;
            }
        }
    }
    
    public int calc_cost_from_to_all(Node n,int curr_cost,ArrayList<Node> visited){
        int x=0;
        visited.add(n);
        for(int i=0;i<n.sub_tree.size();i++){
            if( !visited.contains( n.sub_tree.get(i) ) ){
                x += calc_cost_from_to_all(n.sub_tree.get(i),curr_cost+1,visited);   
            }
        }
        for(int i=0;i<n.parent.size();i++){
            if( !visited.contains( n.parent.get(i) ) ){
                x += calc_cost_from_to_all(n.parent.get(i),curr_cost+1,visited);   
            }
        }
        return curr_cost + x;
    }
    
    public SumDistTree(int N, int[][] edges){
        int[] x = Solve( N, edges);
        
        for(int i=0;i<x.length;i++){
            System.out.print(x[i]+",");
        }        
    }
}
