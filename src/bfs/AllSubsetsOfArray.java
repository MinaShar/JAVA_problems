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
public class AllSubsetsOfArray {
    
    public AllSubsetsOfArray(ArrayList<Integer> t){
        ArrayList<Integer> subset = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        Recursive(t, subset, result, 0);
        
//        System.out.println(result);
//        System.out.println(result.size());
    }
    
    
    public void Recursive(ArrayList<Integer> main , ArrayList<Integer> subset,ArrayList<ArrayList<Integer>> result,int index){
        
        ArrayList<Integer> t = (ArrayList<Integer>) subset.clone();
        result.add(t);
        
        ArrayList<Integer> new_subset = new ArrayList<>();
        new_subset = (ArrayList<Integer>)subset.clone();
        
        for(int i=index;i<main.size();i++){
            
            new_subset.add(main.get(i));
            
            Recursive(main, new_subset, result, i+1);
            
            new_subset.remove(new_subset.size()-1);
            new_subset = (ArrayList<Integer>) new_subset.clone();
        }
    }
    
}
