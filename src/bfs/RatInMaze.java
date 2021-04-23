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
public class RatInMaze {
    
    public RatInMaze(int[][] maze){
        
        Pair start = new Pair(0, 0);
        
        ArrayList<Pair> subset = new ArrayList<>();
        subset.add(start);
        
        ArrayList<ArrayList<Pair>> result = new ArrayList<>();
        
        BackTrack(maze, result, subset);
        
        System.out.println(result);
    }
    
    public boolean IsExist(ArrayList<Pair> subset,Pair _curr){
        for(int i=0;i<subset.size();i++){
            if(subset.get(i).equals(_curr)){
                return true;
            }
        }
        return false;
    }
    
    public void BackTrack(int[][] maze,ArrayList<ArrayList<Pair>> result,ArrayList<Pair> subset){
        
        if((Integer)subset.get(subset.size()-1).getKey() == maze.length-1 && (Integer)subset.get(subset.size()-1).getValue()== maze[maze.length-1].length-1){
            result.add(new ArrayList<Pair>(subset));
            return;
        }
        Pair last_index = subset.get(subset.size()-1);
        
        ArrayList<Pair> next_step = new ArrayList<Pair>();
        next_step.add(new Pair((Integer)last_index.getKey() + 1,(Integer)last_index.getValue() ));
        next_step.add(new Pair((Integer)last_index.getKey() - 1,(Integer)last_index.getValue() ));
        next_step.add(new Pair((Integer)last_index.getKey() ,(Integer)last_index.getValue() + 1 ));
        next_step.add(new Pair((Integer)last_index.getKey() ,(Integer)last_index.getValue() - 1));
        
        for(int i=0;i<next_step.size();i++){
            if( (Integer)next_step.get(i).getKey() >= 0 &&
                (Integer)next_step.get(i).getKey() < maze.length &&
                (Integer)next_step.get(i).getValue()>= 0 &&
                (Integer)next_step.get(i).getValue()< maze[maze.length-1].length &&
                !IsExist(subset, next_step.get(i)) &&
                maze[(Integer)next_step.get(i).getKey()][(Integer)next_step.get(i).getValue()] == 1 ){
                
                subset.add(next_step.get(i));
                
                BackTrack(maze, result, subset);
                
                subset.remove(subset.size()-1);
            }
        }
    }
    
}
