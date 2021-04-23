/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author misaac
 */
public class OptimalAsignment {
    
    class Node{
        Node parent;
        boolean[] assigned;
        int worker_id,jop_id,over_all_cost;
        
        public Node(int worker_id,int jop_id,boolean[] assigned,Node parent,int[][] grid) throws Exception{
            this.parent = parent;
            this.assigned = new boolean[assigned.length];
            for(int i=0;i<assigned.length;i++){
                this.assigned[i] = assigned[i];
            }
            if(jop_id >= 0) if(this.assigned[jop_id] == true) throw new Exception("Error occured assigning jops"); else this.assigned[jop_id] = true;
            this.worker_id = worker_id;
            this.jop_id = jop_id;
            this.over_all_cost = jop_id >=0 ? parent.over_all_cost + grid[worker_id][jop_id] : 0;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node curr_node = this;
            int optimal_cost = this.over_all_cost;
            if(curr_node == null){
                s.append("solution set is empty");
            }
            while(curr_node != null){
                if(curr_node.worker_id == -1)break;
                s.append("Worker "+curr_node.worker_id + " have jop "+ curr_node.jop_id+"\n");
                curr_node = curr_node.parent;
            }
            s.append("with optimal cost = "+optimal_cost+" \n");
            return s.toString();
        }
        
        
    }
    
    public OptimalAsignment(int[][] grid){
        SolveBF(grid);
    }
    
    public boolean AllJopsAssigned(Node n){
        for(int i=0;i<n.assigned.length;i++){
            if(n.assigned[i] == false) return false;
        }
        return true;
    }
    
    public void SolveBF(int[][] grid){
        
        boolean[] assigned = new boolean[grid[0].length];
        Arrays.fill(assigned, false);
        Queue q = new LinkedList<Node>();
        
        Node optimal_node = null;
        Node n = null;
        try{
        
            Node root = new Node(-1,-1,assigned,null,grid);

            q.add(root);

            while(!q.isEmpty()){
                n =(Node) q.remove();
                int worker_id = n.worker_id+1;
                
                if(AllJopsAssigned(n)){
                    q.add(n);
                    break;
                }
                

                for(int i=0;i<grid[worker_id].length;i++){
                    if(n.assigned[i] == true) continue;
                    Node x = new Node(worker_id, i, n.assigned, n, grid);
                    q.add(x);
                }
            }
            
            int optimal_cost = Integer.MAX_VALUE;
            Node iter;
            while(!q.isEmpty()){
                iter =(Node) q.remove();
                if(iter.over_all_cost < optimal_cost){
                    optimal_node = iter;
                    optimal_cost = optimal_node.over_all_cost;
                }
            }
            
            System.out.println( optimal_node.toString() );
            
        }catch(Exception e){
            System.out.println( n.toString() );
            System.out.println(e.toString());
        }
    }
    
}
