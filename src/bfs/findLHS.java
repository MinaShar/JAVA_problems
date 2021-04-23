/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author misaac
 */
public class findLHS {
    
    
    public int findLHS_func(int[] nums) {
        
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        HashSet<String> rejected_pair = new HashSet<String>();
        HashSet<Integer> nums_hash = new HashSet<Integer>();
        
        int max_val = 0;
        String max_pair = "";
        
        boolean flag;
        do{
            
            flag = true;
            max_val = 0;
            
            for(int i=0;i<nums.length;i++){
                nums_hash.add( nums[i] );

                int min = nums[i]-1;
                int pos = nums[i]+1;        

                
                if( !rejected_pair.contains( min+","+nums[i] )){
                    map.put(min+","+nums[i] , map.getOrDefault(min+","+nums[i],0)+1);
                    if( map.get(min+","+nums[i]) > max_val ){
                        max_val = map.get(min+","+nums[i]);
                        max_pair = min+","+nums[i];
                    }
                }
                    

                if( !rejected_pair.contains( nums[i]+","+pos )){
                    map.put(nums[i]+","+pos , map.getOrDefault(nums[i]+","+pos,0)+1); 
                    if( map.get(nums[i]+","+pos) > max_val ){
                        max_val = map.get(nums[i]+","+pos);
                        max_pair = nums[i]+","+pos;
                    }
                }
                    
            }
            
            if(max_val == 0 ){
                return 0;
            }

           
            String[] parts = max_pair.split(",");
            if( !nums_hash.contains( Integer.parseInt(parts[0]) ) || !nums_hash.contains( Integer.parseInt(parts[1]) ) ){
                flag = false;
                rejected_pair.add( max_pair );
            }
        }while(flag == false);
        
        return max_val;
    }
}
