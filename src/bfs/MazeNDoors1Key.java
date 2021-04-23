/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author misaac
 */
public class MazeNDoors1Key {
    
    public MazeNDoors1Key(int [][] maze){
        
        Pair start = new Pair(0, 0);
        ArrayList<Pair> subset = new ArrayList<>();
        subset.add(start);
        ArrayList<ArrayList<Pair>> results = new ArrayList<>();
        
        BackTrack(maze, results, subset, start, 1);
        
        System.out.println(results);
        
    }
    
    public boolean checkIfPairExist(ArrayList<Pair> subset,Pair p){
        for(int i=0;i<subset.size();i++){
            if(subset.get(i).equals(p)){
                return true;
            }
        }
        return false;
    }
    
    public void BackTrack(int[][] main,ArrayList<ArrayList<Pair>> results,ArrayList<Pair> subset,Pair fin_index,int NumberOfKeys){
        
        if( ((Integer)fin_index.getKey())== main.length-1  && ((Integer)fin_index.getValue())== main[main.length-1].length -1 ){
            results.add(new ArrayList<>(subset));
            return;
        }
        
        ArrayList<Pair<Integer,Integer>> poss_pairs = new ArrayList<>();
        poss_pairs.add(new Pair<>( ((Integer)fin_index.getKey())-1,(Integer)fin_index.getValue()));
        poss_pairs.add(new Pair<>( ((Integer)fin_index.getKey())+1,(Integer)fin_index.getValue()));
        poss_pairs.add(new Pair<>( (Integer)fin_index.getKey(),(Integer)fin_index.getValue() -1));
        poss_pairs.add(new Pair<>( (Integer)fin_index.getKey(),(Integer)fin_index.getValue() +1));
        
        
        for(int i=0;i<poss_pairs.size();i++){
            if( (Integer)poss_pairs.get(i).getKey() >= 0 &&
                (Integer)poss_pairs.get(i).getKey() < main.length &&
                (Integer)poss_pairs.get(i).getValue() >= 0 &&
                (Integer)poss_pairs.get(i).getValue() < main[main.length-1].length ){
                
                if(NumberOfKeys >= 0 && !checkIfPairExist(subset, poss_pairs.get(i))){
                    subset.add(poss_pairs.get(i));
                    
                    BackTrack(main, results, subset, poss_pairs.get(i),main[(Integer)poss_pairs.get(i).getKey()][(Integer)poss_pairs.get(i).getValue()] == 1 ? NumberOfKeys-1 : NumberOfKeys);
                    
                    subset.remove(subset.size()-1);
                }
                
            }
        }
    }
    
}
