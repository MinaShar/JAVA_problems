/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author misaac
 */
public class IncreasingSubsequences {
    
    
    public IncreasingSubsequences(){
        
        int[] arr = new int[]{4,6,7,7};
        
        List<List<Integer>> resu = findSubsequences(arr);
        
        int x = 4;
    }
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        
        Arrays.sort(nums);
        
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        HashMap<String,Boolean> map = new HashMap<String,Boolean>();////length , sum
        
        
        dfs(nums,-1,results,new ArrayList<Integer>(),0,map);
        
        return results;
    }
    
    public void dfs(int[] nums,int index,List<List<Integer>> results,ArrayList<Integer> curr
                    ,int acc_sum ,HashMap<String,Boolean> map ){
        
        if(curr.size() >= 2){
            if( !map.containsKey(curr.size()+","+acc_sum) ){
                results.add( new ArrayList<Integer>(curr) );
                map.put( curr.size()+","+acc_sum , true );
            }
        }
        
        for(int i=index+1;i<nums.length;i++){
            curr.add(nums[i]);
            
            dfs(nums,i,results,curr,acc_sum+nums[i],map);
            
            curr.remove(curr.size()-1);
            acc_sum -= nums[i];
        }
        
    }
}
