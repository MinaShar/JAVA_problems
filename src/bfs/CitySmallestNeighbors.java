/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author misaac
 */
public class CitySmallestNeighbors {
    
    class Node{
        int to;
        int weight;
        public Node(int to,int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    
    class StackNode{
        int curr_node;
        int remaining_cost;
        
        public StackNode(int curr_node,int remaining_cost){
            this.curr_node = curr_node;
            this.remaining_cost = remaining_cost;
        }
    }
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
        HashMap<Integer,ArrayList<Node>> map = new HashMap<Integer,ArrayList<Node>>();
        
        for(int i=0;i<edges.length;i++){
            ArrayList<Node> arr = map.getOrDefault( edges[i][0] , new ArrayList<Node>() );
            arr.add(new Node(edges[i][1],edges[i][2]));
            map.put( edges[i][0] , arr );
            
            arr = map.getOrDefault( edges[i][1] , new ArrayList<Node>() );
            arr.add(new Node(edges[i][0],edges[i][2]));
            map.put( edges[i][1] , arr );
        }
        
        int min_city = Integer.MAX_VALUE;
        int city_number = 0;
        for(int i=0;i<n;i++){
            int c = get_cities_df( map , i , distanceThreshold );
            if( c == min_city ){
                min_city = c;
                city_number = i;
            }else if( c < min_city ){
                min_city = c;
                city_number = i;
            }
        }
        return city_number;
    }
    
    public int get_cities_df(HashMap<Integer,ArrayList<Node>> map,int start,int distanceThreshold){
        ArrayList<StackNode> stack = new ArrayList<StackNode>();
        
        HashSet<String> _visited = new HashSet<String>();
        HashSet<Integer> Visited = new HashSet<Integer>();
        
        int destinations_counter = 0;
        ArrayList<Node> destinations = map.getOrDefault(start,new ArrayList<Node>());
        Visited.add(start);
        for(int i=0;i<destinations.size();i++){
            if( (distanceThreshold-destinations.get(i).weight) >= 0 ){
                stack.add( new StackNode( destinations.get(i).to
                                        , distanceThreshold-destinations.get(i).weight ) );
//                _visited.add(start+":"+destinations.get(i).to);
//                _visited.add(destinations.get(i).to+":"+start);
                
                if(!Visited.contains(destinations.get(i).to)){
                    Visited.add( destinations.get(i).to );
                    destinations_counter++;
                }
            }
        }
        while( stack.size() > 0 ){
            StackNode n = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            
            destinations = map.getOrDefault(n.curr_node,new ArrayList<Node>());
            for(int i=0;i<destinations.size();i++){
                if( (n.remaining_cost - destinations.get(i).weight) >= 0  ){
                    stack.add( new StackNode( destinations.get(i).to
                                            , n.remaining_cost-destinations.get(i).weight ) );
//                    _visited.add(n.curr_node+":"+destinations.get(i).to);
//                    _visited.add(destinations.get(i).to+":"+n.curr_node);
                    
                    if(!Visited.contains(destinations.get(i).to)){
                        Visited.add( destinations.get(i).to );
                        destinations_counter++;
                    }
                }
            }
        }
        return destinations_counter;
    }
    
}
