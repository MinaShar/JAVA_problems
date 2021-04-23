/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class RemoveBoxesPQ {
    
    class The_Comparator implements Comparator<String> {
        public int compare(String str1, String str2)
        {
            String first_Str;
            String second_Str;
            first_Str = str1;
            second_Str = str2;
            return second_Str.compareTo(first_Str);
        }
    } 
    
    
    public RemoveBoxesPQ(){
        
        int[] arr = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        
        removeBoxes(arr);
    }
    
    public int removeBoxes(int[] boxes) {
        int used = 0;
        int len = boxes.length;
        int[] boxes_pointer = new int[len];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int l = len-1;l>=0;l--){
            boxes_pointer[l] = map.getOrDefault( boxes[l] , len );
            map.put( boxes[l] , l );
        }
        return 0;
    }
    
    public int solve(int start,int end){
        HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
        for(int i=start;i<end;i++){
            
        }
        return 0;
    }
}
