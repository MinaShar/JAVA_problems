/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author misaac
 */
public class NumberCompination {
    
    public NumberCompination(ArrayList<Integer> t,int sum){
        
        Collections.sort(t);
        
        RemoveDuplicates(t);
        
        ArrayList<Integer> subset = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        Solve(t, subset, result, sum, 0);
        
        System.out.println(result);
    }
    
    public ArrayList<Integer> RemoveDuplicates(ArrayList<Integer> t){
        ArrayList<Integer> new_l = new ArrayList<>();
        for(int i=0;i<t.size();i++){
            if(!new_l.contains(t.get(i))){
                new_l.add(t.get(i));
            }
        }
        return new_l;
    }
    
    public boolean IsExists(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> subset){
        for(int i=0;i<result.size();i++){
            if(result.get(i).equals(subset)){
                return true;
            }
        }
        return false;
    }
    
    public void Solve(ArrayList<Integer> main , ArrayList<Integer> subset,ArrayList<ArrayList<Integer>> result , int r_sum , int index){
        
        if(r_sum < 0){
            return;
        }
        else if(r_sum == 0 && !IsExists(result, subset) ){
            ArrayList<Integer> t = (ArrayList<Integer>) subset.clone();
            result.add(t);
            return;
        }
        ArrayList<Integer> new_subset = (ArrayList<Integer>) subset.clone();
        while( index < main.size() && (( r_sum - main.get(index) ) >= 0 ) ){
            
            new_subset.add( main.get(index) );
            
            Solve(main, new_subset, result, r_sum - main.get(index) , index);
            
            new_subset.remove(new_subset.size()-1);
            
            index++;
            
        }
        
    }
    
}
