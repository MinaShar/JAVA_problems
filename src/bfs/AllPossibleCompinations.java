/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;

/**
 *
 * @author misaac
 */
public class AllPossibleCompinations {
    
    public AllPossibleCompinations(int n , int k){
        
        Solve(n, k);
        
    }
    
    public ArrayList<ArrayList<Integer>> Solve(int n,int k){
        
        ArrayList<Integer> series = new ArrayList<>();
        
        for(int i=1;i<=n;i++){
            series.add(i);
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> subset  = new ArrayList<>();
        BeckTrack(series, result, subset, k, 0);
        
//        System.out.println(result);
        
        return result;
    }
    
    public void BeckTrack(ArrayList<Integer> series , ArrayList<ArrayList<Integer>> result,ArrayList<Integer> subset,int subset_range,int index){
        
        if(subset.size() > subset_range){
            return;
        }else if(subset.size() == subset_range){
            result.add(new ArrayList<>(subset));
            return;
        }
        
        ArrayList<Integer> new_subset = new ArrayList<>(subset);
        
        for(int i=index;i<series.size();i++){
            new_subset.add(series.get(i));
            
            BeckTrack(series,result,new_subset,subset_range,i+1);
            
            new_subset.remove(new_subset.size()-1);    
        }
    }
    
}
