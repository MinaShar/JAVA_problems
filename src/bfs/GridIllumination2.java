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
public class GridIllumination2 {
    int[] x12 = new int[]{0 ,  0, 0  ,1 ,-1 ,1 ,-1 , 1 ,-1};
    int[] y12 = new int[]{0 , -1, 1  ,0 , 0 ,1 ,-1 , -1, 1};
    
    public int is_lit(int[] query,int[][] lamps,HashMap<String,Boolean> lamp_active
                     ,HashMap<String,Integer> _lamps_index
                     ,HashMap<Integer,ArrayList<Integer>> _lamps_x
                     ,HashMap<Integer,ArrayList<Integer>> _lamps_y
                     ,HashMap<Integer,ArrayList<Integer>> _lamps_diff){
        
        if(_lamps_x.containsKey(query[0]) && _lamps_x.get(query[0]).size()>0){
            return 1;
        }
        if(_lamps_y.containsKey(query[1]) && _lamps_y.get(query[1]).size()>0){
            return 1;
        }
        if(_lamps_diff.containsKey(Math.abs(query[1]-query[0]))){
            return 1;
        }
        return 0;
    }
    
    public void close(int[] query,int[][] lamps
                     ,HashMap<String,Integer> _lamps_index
                     ,HashMap<String,Boolean> _lamps_active
                     ,HashMap<Integer,ArrayList<Integer>> _lamps_x
                     ,HashMap<Integer,ArrayList<Integer>> _lamps_y
                     ,HashMap<Integer,ArrayList<Integer>> _lamps_diff){
        
        for(int i=0;i<9;i++){
            if(_lamps_index.containsKey( (query[0]+x12[i]) +"," + (query[1]+y12[i]) ) ){
                int index = _lamps_index.get( (query[0]+x12[i]) +"," + (query[1]+y12[i]) );
                
                _lamps_active.put(lamps[index][0]+","+lamps[index][1],false);
                
                ArrayList<Integer> xxxx = _lamps_x.get(lamps[index][0]);
                for(int g=0;g<xxxx.size();g++){
                    if(xxxx.get(g)==index){
                        xxxx.remove(g);
                        break;
                    }
                }
                
                ArrayList<Integer> yyyy = _lamps_y.get(lamps[index][1]);
                for(int g=0;g<yyyy.size();g++){
                    if(yyyy.get(g)==index){
                        yyyy.remove(g);
                        break;
                    }
                }
                
                ArrayList<Integer> diffff = _lamps_diff.get(Math.abs(lamps[index][1]-lamps[index][0]));
                for(int g=0;g<diffff.size();g++){
                    if(diffff.get(g)==index){
                        diffff.remove(g);
                        break;
                    }
                }
            }
        }
        
    }
    
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        
        HashMap<String,Integer> _lamps_index = new HashMap<String,Integer>();
        HashMap<String,Boolean> _lamps_active = new HashMap<String,Boolean>();
        
        HashMap<Integer,ArrayList<Integer>> _lamps_x = new HashMap<Integer,ArrayList<Integer>>();
        HashMap<Integer,ArrayList<Integer>> _lamps_y = new HashMap<Integer,ArrayList<Integer>>();
        HashMap<Integer,ArrayList<Integer>> _lamps_diff = new HashMap<Integer,ArrayList<Integer>>();
        for(int i=0;i<lamps.length;i++){
            _lamps_index.put(lamps[i][0]+","+lamps[i][1],i);
            _lamps_active.put(lamps[i][0]+","+lamps[i][1],true);
            
            ArrayList<Integer> xx = _lamps_x.getOrDefault(lamps[i][0],new ArrayList<Integer>());
            xx.add(i);
            _lamps_x.put(lamps[i][0],xx);
            
            ArrayList<Integer> yy = _lamps_y.getOrDefault(lamps[i][1],new ArrayList<Integer>());
            yy.add(i);
            _lamps_y.put(lamps[i][1],yy);
            
            ArrayList<Integer> diff = _lamps_diff.getOrDefault(Math.abs(lamps[i][1]-lamps[i][0]),new ArrayList<Integer>());
            diff.add(i);
            _lamps_diff.put(Math.abs(lamps[i][1]-lamps[i][0]),diff);
        }
        
        int[] solution = new int[queries.length];
        int q_counter = 0;
        do{
            solution[q_counter] = is_lit(queries[q_counter],lamps,_lamps_active,_lamps_index,_lamps_x,_lamps_y,_lamps_diff);
            close(queries[q_counter],lamps,_lamps_index,_lamps_active,_lamps_x,_lamps_y,_lamps_diff);
            q_counter++;
        }while(q_counter < queries.length);
        
        return solution;
    }
}
