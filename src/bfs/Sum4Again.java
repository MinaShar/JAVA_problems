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
public class Sum4Again {
    
    public Sum4Again(ArrayList<Integer> A , int B){
        System.out.println( Solve(A, B) );
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
    
    public ArrayList<ArrayList<Integer>> Solve(ArrayList<Integer> A , int B){
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        
        for(int i=0;i<A.size();i++){
            for(int j=i+1;j<A.size();j++){
                if( A.get(i)+A.get(j) > B ){
                    break;
                }
                for(int k=j+1;k<A.size();k++){
                    if( A.get(i)+A.get(j)+A.get(k) > B){
                        break;
                    }
                    for(int l=k+1;l<A.size();l++){
                        if(A.get(i)+A.get(j)+A.get(k)+A.get(l) == B){
                            ArrayList<Integer> subset = new ArrayList<>();
                            subset.add(A.get(i));subset.add(A.get(j));subset.add(A.get(k));subset.add(A.get(l));
                            //if(!IsExistBefore(solution, subset)){
                                solution.add(subset);
                            ///}
                        }
                    }
                }
            }
        }
        return solution;
    }
    
}
