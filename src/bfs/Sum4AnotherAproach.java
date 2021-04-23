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
public class Sum4AnotherAproach {
    
    public class Pair{
        int x,y;
        public Pair(int x,int y){
            this.x=x;this.y=y;
        }
        
        public int getKey(){
            return x;
        }
        
        public int getValue(){
            return y;
        }
    }
    
    public Sum4AnotherAproach(ArrayList<Integer> A , int B){
        System.out.println( Solve(A, B) );
    }
    
    public boolean exist_befor(ArrayList<Pair> result,Pair p){
        for(int i=0;i<result.size();i++){
            if(result.get(i).getKey() == p.getKey() && result.get(i).getValue()== p.getValue()){
                return true;
            }
        }
        return false;
    }
    
    public boolean exist_befor(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> p){
        for(int i=0;i<result.size();i++){
            if(result.get(i).get(0)== p.get(0)&& result.get(i).get(1)== p.get(1)){
                return true;
            }
        }
        return false;
    }
    
    public void two_pointers(ArrayList<Integer> A , ArrayList<Integer> subset , int from_index,int to_index, int target , ArrayList<Pair> result){
        int required = target - ( subset.get(0)+subset.get(1) );
        while(true){
            if(from_index == to_index || from_index > to_index) break;
            
            int t = A.get(from_index) + A.get(to_index);
            if( t > required){
                to_index--;
            }else if( t < required){
                from_index++;
            }else if(t == required){
                Pair p = new Pair(A.get(from_index),A.get(to_index));
                if(!exist_befor(result, p)) result.add(p);
                from_index++;to_index--;
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> Solve(ArrayList<Integer> A, int B){
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        Collections.sort(A);
        for(int i=0;i<A.size();i++){
            for(int j=i+1;j<A.size();j++){
                ArrayList<Pair> pairs = new ArrayList<>();
                ArrayList<Integer> subset= new ArrayList<>();
                subset.add(A.get(i));subset.add(A.get(j));
                if(exist_befor(solution, subset)) continue;
                two_pointers(A, subset, j+1, A.size()-1, B, pairs);
                
                for(int q=0;q<pairs.size();q++){
                    ArrayList<Integer> t = new ArrayList<>();
                    t.add(subset.get(0));t.add(subset.get(1));t.add((Integer)pairs.get(q).getKey());
                    t.add((Integer)pairs.get(q).getValue());
                    solution.add(t);
                }
            }
        }
        return solution;
    }
}
