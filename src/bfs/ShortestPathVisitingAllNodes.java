/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class ShortestPathVisitingAllNodes {
    
    public ShortestPathVisitingAllNodes(){
        int[][] graph = new int[][]{{1,4,6,8,9},{0,6},{9},{5},{0},{7,3},{0,1},{9,5},{0},{0,2,7}};
        
        System.out.println("result is : "+ shortestPathLength(graph) );
    }
    
    class Node{
        int number_nodes_visited;
        HashMap<Integer,Integer> map;
        int last_visited;
        int previous_visited;
        
        public Node(int node_index){
            map = new HashMap<Integer,Integer>();
            map.put( node_index , 0 );
            number_nodes_visited = 1;
            last_visited = node_index;
            previous_visited = -1;
        }
        public Node(Node node){
            map = new HashMap<Integer,Integer>();
            map.putAll( node.map );
            number_nodes_visited = node.number_nodes_visited;
            last_visited = node.last_visited;
            previous_visited = node.previous_visited;
        }
        public void visit(int node_index){
            map.put( node_index , 0 );
            number_nodes_visited++;
            previous_visited = last_visited;
            last_visited = node_index;
        }
        public boolean is_all_visited(int nodes_count){
            if(nodes_count == map.size())
                return true;
            return false;
        }
    }
    
    public int shortestPathLength(int[][] graph) {
        
        if(graph.length == 1 && graph[0].length==0){
            return 0;
        }
        
        ArrayList<Node> queue = new ArrayList<Node>();
        
        for(int i=0;i<graph.length;i++){
            queue.add( new Node(i) );   
        }
        
        while(queue.size() > 0){
            Node n = queue.remove(0);
            
            for(int i=0;i<graph[n.last_visited].length;i++){
                
                Node another = new Node( n );
                another.visit( graph[n.last_visited][i] );
                if(!another.is_all_visited( graph.length )){
                    queue.add(another);
                }else{
                    return another.number_nodes_visited - 1;
                }
            }
        }
       return -1;
    }
}
