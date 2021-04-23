/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author misaac
 */
public class RefuelStops {
    
    public class my_comp implements Comparator<int[]>{
        
        @Override
        public int compare(int[] o1,int[] o2) {
            return o2[1]-o1[1];
        }
    }
    
    int min_val;
    
    public RefuelStops(int target, int startFuel, int[][] stations){
        
        PriorityQueue<int[]> arr = new PriorityQueue<>(new my_comp());
        
        for(int i=0;i<stations.length;i++){
            arr.add(stations[i]);
        }
        
        while(arr.size()>0){
            System.out.println(arr.remove()[0]+","+arr.remove()[1]);
        }
        
//        System.out.println( "result is : "+ minRefuelStops(target, startFuel, stations));
    }
    
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        
        int max_distance_covered = startFuel, stops_count = 0,last_station_processed = -1;
        PriorityQueue<int[]> pq_stations = new PriorityQueue<>(new my_comp());
        
        while(max_distance_covered < target){
            
            for(int i=last_station_processed+1;i<stations.length;i++){
                if(stations[i][0] <= max_distance_covered){
                    pq_stations.add(stations[i]);
                }else{
                    break;
                }
            }
            if(pq_stations.size() > 0){
                max_distance_covered += pq_stations.remove()[1];
                stops_count++;
            }else{
                return -1;
            }
        }
        return stops_count;
    }
    
    public int solve_df(int target,int curr_position,int rem_fuel,int[][] stations,int stops,int last_reful_at,HashMap<String,Integer> map,ArrayList<String> result){
        
        
        if(last_reful_at == 17){
            int y=0;
        }
        
        if(stops == 15 && curr_position >= target){
            System.out.println("15 found>>>>>>>>>>>>>now with results :");
            for(int i=0;i<result.size();i++){
                System.out.println(result.get(i));
            }
        }
        
        if(curr_position >= target && this.min_val > stops){
            this.min_val = stops;
            
            System.out.println(">>>>>>>>>>>>>now with results :");
            for(int i=0;i<result.size();i++){
                System.out.println(result.get(i));
            }
            
            return stops;
        }
        
        if( curr_position < target ){
            
            for(int i=stations.length-1;i>last_reful_at;i--){
                
                if( stations[i][0] <= curr_position ){
                    
                    // if( map.containsKey( curr_position+","+i+","+rem_fuel ) ){
                    //     continue;
                    // }
                    
                    result.add("will refule at station : "+i+" distance:"+stations[i][0] + " , amount:"+stations[i][1]
                              +" and rem.fule:"+rem_fuel);
                    
                    int x = solve_df( target,stations[i][0]+stations[i][1]+rem_fuel 
                                     ,curr_position-stations[i][0] , stations , stops+1 , i , map , result);
                    
                    result.remove(result.size()-1);
                    // map.put( curr_position+","+i+","+rem_fuel , x );
                    
                }
            }
            
        }
        
        return -1;
    }
    
}
