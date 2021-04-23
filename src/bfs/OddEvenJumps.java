/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.TreeMap;

/**
 *
 * @author misaac
 */
public class OddEvenJumps {
    
    public OddEvenJumps(){
        int[] arr = new int[]{1,2,3,2,1,4,4,5};
        
        System.out.println("result is : " + oddEvenJumps(arr) );
    }
    
    public int oddEvenJumps(int[] A) {
        if(A.length == 0)
            return 0;
        
        int n = A.length;
        boolean[] starting = new boolean[n];
        boolean[] even_j = new boolean[n];
        
        starting[n-1] = true;
        even_j[n-1] = true;
        
        int good_starts = 1;
        TreeMap map = new TreeMap<Integer,Integer>();
        map.put( A[n-1] , n-1 );
        for(int i=n-2;i>=0;i--){
            
            int o = odd_jump(i,A,map);
            int e = even_jump(i,A,map);
            
            map.put( A[i] , i );
            
            if( o!=-1 && even_j[o] ){
                starting[i] = true;
                good_starts++;
            }
            if(e != -1 && starting[e] ){
                even_j[i] = true;
            }
        }
        return good_starts;
    }
    
    public int odd_jump(int i,int[] arr,TreeMap map){
        
        Integer v = (Integer) map.ceilingKey(arr[i]);
        if(v == null ||  ((Integer)v) == i )
            return -1;
        else{
            return (Integer) map.get(v);
        }
    }
    
    public int even_jump(int i,int[] arr,TreeMap map){
        Integer v = (Integer) map.floorKey(arr[i]);
        if(v == null || ((Integer)v) == i )
            return -1;
        else{
            return (Integer) map.get(v);
        }
    }
}
