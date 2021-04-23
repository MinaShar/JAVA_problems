/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.*;

/**
 *
 * @author misaac
 */
public class CriticalConnTarjan {
    
    int counter = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        
        for(int i=0;i<connections.size();i++){
            if(!map.containsKey(connections.get(i).get(0))){
                map.put( connections.get(i).get(0) ,new ArrayList<Integer>() );
            }
            if(!map.containsKey(connections.get(i).get(1))){
                map.put( connections.get(i).get(1) ,new ArrayList<Integer>() );
            }
            
            map.get( connections.get(i).get(0) ).add(  connections.get(i).get(1) );
            map.get( connections.get(i).get(1) ).add(  connections.get(i).get(0) );
        }
        int[] ids = new int[map.size()];
        int[] low = new int[map.size()];
        
        Arrays.fill(ids, -1); 
        Arrays.fill(low, -1); 
        
        List<List<Integer>> solu = new ArrayList<List<Integer>>();
        dfs(0,0,map,ids,low,solu);
        return solu;
    }
    
    public void dfs(int curr_node,int parent,HashMap<Integer,ArrayList<Integer>> map,int[] ids,int[] low,List<List<Integer>> solu){
        
        ids[curr_node] = counter;
        low[curr_node] = counter++;
        
        for(Integer i : map.get(curr_node) ){     
            if(i == parent){
                continue;
            }
            if( ids[i] == -1 ){
                dfs(i,curr_node,map,ids,low,solu);   
                
                if(low[curr_node] < low[i]){
                    ArrayList<Integer> y = new ArrayList<Integer>();
                    y.add(curr_node);y.add(i);
                    solu.add(y);
                }
            }
            
            low[curr_node] = Math.min(low[i],low[curr_node]);
        }
    }
}
