/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author misaac
 */
public class test {
    
    class Node{
        ArrayList<Integer> visited;
        HashMap<Integer,Integer> map;
        int curr_node;
        public Node(int node){
            map = new HashMap<Integer,Integer>();
            visited = new ArrayList<Integer>();
            visited.add( node );
            map.put(node,0);
            curr_node = node;
        }
        public Node(Node n){
            map = new HashMap<Integer,Integer>();
            visited = new ArrayList<Integer>(n.visited);
            map.putAll(n.map);
            curr_node = n.curr_node;
        }
        public void visit(int node){
            visited.add(node);
            map.put(node,0);
            curr_node = node;
        }
        public boolean is_all_visited(int nodes_length){
            return nodes_length == map.size();
        }
        public int get_cost(){
            return visited.size()-1;
        }
    }
    
    public int shortestPathLength(int[][] graph) {
        
        ArrayList<Node> queue = new ArrayList<Node>();
        for(int i=0;i<graph.length;i++){
            queue.add( new Node( i ) );
        }
        
        return brute_force( queue , graph );
    }
    
    public int brute_force(ArrayList<Node> queue,int[][] graph){
        int min_cost = Integer.MAX_VALUE;
        //// key=cost , value=arraylist of visisted nodes
        while(queue.size() > 0){
            Node n = queue.remove(0);
            int curr_node = n.curr_node;
            
            for(int i=0;i<graph[curr_node].length;i++){
                Node an_node = new Node(n);
                an_node.visit( graph[curr_node][i] );
                
                if( an_node.is_all_visited(graph.length) && an_node.get_cost() <= min_cost){
                    min_cost = an_node.get_cost();
                }else if( !an_node.is_all_visited(graph.length) && an_node.get_cost() <= min_cost ){
                    queue.add(an_node);
                }
            }
        }
        return min_cost;
    }
    
    
    public test(){
        HashMap<Integer,String> map = new HashMap<Integer,String>();
        map.put(123, "hello");
        System.out.println("result from map is : " +map.get(123));
//        int[][] graph = new int[][]{{1,2,3},{0},{0},{0}};
//        
//        System.out.println( shortestPathLength(graph) );
    }
    
    public static void ttt(int x){
        x = 3;
    }
}
