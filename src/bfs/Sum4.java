/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 *
 * @author misaac
 */
public class Sum4 {
    
    public Sum4(ArrayList<Integer> A , int B){
        Solve(A, B);
    }
    
    public ArrayList<ArrayList<Integer>> Solve(ArrayList<Integer> A, int B){  
        Collections.sort(A);
        
//        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(A);
//        ArrayList<Integer> A_without_duplicates = new ArrayList<>(hashSet);
        
        HashMap<String,Boolean> map = new HashMap<>();
        ArrayList<Integer> subset = new ArrayList<>();
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        BackTrack(A, subset, solution, 0,B , map);
        System.out.println(solution);
        return solution;
    }
    
    public boolean sumIsCorrect(ArrayList<Integer> t, int target){
        int sum = 0;
        for(int i=0;i<t.size();i++){
            sum+= t.get(i);
        }
        if(sum == target)
            return true;
        else{
            return false;
        }
    }
    
    public boolean InAscOrder(ArrayList<Integer> t){
        for(int i=0;i<t.size();i++){
            int targ = t.get(i);
            for(int j=i+1;j<t.size();j++){
                if(t.get(j) < targ){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean IsExistBefore(ArrayList<ArrayList<Integer>> solution, ArrayList<Integer> subset){
        for(int i=0;i<solution.size();i++){
            if(subset.get(0) == solution.get(i).get(0) && 
                    subset.get(1) == solution.get(i).get(1) && 
                    subset.get(2) == solution.get(i).get(2) && 
                    subset.get(3) == solution.get(i).get(3) ){
                return true;
            }
        }
        return false;
    }
    
    public boolean MakeSense(ArrayList<Integer> T,int target){
        int sum = 0;
        for(int i=0;i<T.size();i++){
            sum += T.get(i);
        }
        if(sum > target)
            return false;
        else
            return true;
    }
    
    public void BackTrack(ArrayList<Integer> A,ArrayList<Integer> subset,ArrayList<ArrayList<Integer>> solution,int index,int target ,HashMap map){
        
        if(subset.size() == 4 && sumIsCorrect(subset,target) ){
            if((Boolean)map.containsKey(subset.get(0)+";"+subset.get(1)+";"+subset.get(2)) == true){
                return ;
            }
            solution.add(new ArrayList<>(subset));
            map.put( subset.get(0)+";"+subset.get(1)+";"+subset.get(2), true );
            return ;
        }else if (subset.size() == 4){
            return ;
        }
        
        
        for(int i=index;i<A.size();i++){
            subset.add(A.get(i));
            
            if(subset.size() == 3 &&
                    (Boolean)map.containsKey(subset.get(0)+";"+subset.get(1)+";"+subset.get(2)) == true){
                
            }else{
                BackTrack(A, subset, solution, i+1, target , map);
            }
            
            subset.remove(subset.size()-1);
        }
        return;
    }
}
