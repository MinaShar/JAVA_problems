/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class MinNumberOfChairs {
    
    HashMap<Integer,Integer> _arr_map;
    HashMap<Integer,Integer> _dep_map;
    
    public MinNumberOfChairs(int[] arrival,int[] departure){
        
        _arr_map = new HashMap<Integer,Integer>();
        _dep_map = new HashMap<Integer,Integer>();
        
        int min_arrive = Integer.MAX_VALUE;
        for(int i=0;i<arrival.length;i++){
            int x = _arr_map.getOrDefault(arrival[i], 0);
            _arr_map.put( arrival[i] , x+1 );
            min_arrive = Math.min(min_arrive, arrival[i]);
        }
        
        int max_deparure = Integer.MIN_VALUE;
        for(int i=0;i<departure.length;i++){
            int x = _dep_map.getOrDefault(departure[i], 0);
            _dep_map.put( departure[i] , x+1 );
            max_deparure = Math.max(max_deparure, departure[i]);
        }
        
        int current_persons = 0;
        int max_chairs = 0;
        for(int i=min_arrive;i<=max_deparure;i++){
            current_persons += ( _arr_map.getOrDefault(i,0) - _dep_map.getOrDefault(i,0) );
            max_chairs = Math.max(current_persons, max_chairs);
        }
        System.out.println("max number of chairs : " + max_chairs);
    }
    
}
