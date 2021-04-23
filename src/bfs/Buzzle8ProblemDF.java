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
public class Buzzle8ProblemDF {
    
    enum direction{
        up,down,left,right
    };
    
    int[][] buzzle,solution;
    
    public Buzzle8ProblemDF(int[][] buzzle,int[][] solution){
        this.buzzle = buzzle;
        this.solution = solution;
        
        ArrayList<int[][]> subset = new ArrayList<>();
        subset.add( CopyArray(buzzle) );
        
        if(SolveDF(subset,get_cost(subset.get(subset.size()-1)))){
            
            System.out.println("Now printing result :");
            for(int i=0;i<subset.size();i++){
                
                int[][] x = subset.get(i);
                
                System.out.println("arr["+i+"] :");
                
                for(int j=0;j<x.length;j++){
                    for(int k=0;k<x[j].length;k++){
                        System.out.print(x[j][k] + " , ");
                    }
                    System.out.println();
                }
            }
        }
        
    }
    
    public Pair<Integer,Integer> LocationEmptyCell(int[][] source){
        
        for(int i=0;i<source.length;i++){
            for(int j=0;j<source[i].length;j++){
                if(source[i][j] == 0 ){
                    return new Pair<Integer,Integer>(i,j);
                }
            }
        }
        return null;
    }
    
    public void Swap(int[][] source,int x1, int y1,int x2,int y2){
        int x = source[x1][y1];
        source[x1][y1] = source[x2][y2];
        source[x2][y2] = x;
    }     
    
    public int[][] Move(int[][] source,direction d){
        try{
        int[][] result =new int[source.length][source[0].length];
        Pair p = LocationEmptyCell(source);
        switch(d){
            case up :
                if(((Integer)p.getKey() ) -1 >= 0){
                    Swap(source, (Integer)p.getKey(), (Integer)p.getValue(), ((Integer)p.getKey() ) -1,(Integer) p.getValue());
                    return source;
                }else{
                    return null;
                }
            case down:
                if(((Integer)p.getKey() ) +1 < source.length){
                    Swap(source, (Integer)p.getKey(), (Integer)p.getValue(), ((Integer)p.getKey() ) +1,(Integer) p.getValue());
                    return source;
                }else{
                    return null;
                }
            case left:
                if(((Integer)p.getValue()) -1 >= 0){
                    Swap(source, (Integer)p.getKey(), (Integer)p.getValue(),(Integer)p.getKey() ,((Integer) p.getValue()) -1);
                    return source;
                }else{
                    return null;
                }
            case right:
                if(((Integer)p.getValue()) +1 < source[(Integer)p.getKey()].length){
                    Swap(source, (Integer)p.getKey(), (Integer)p.getValue(),(Integer)p.getKey() ,((Integer) p.getValue()) +1);
                    return source;
                }else{
                    return null;
                }
        }
        
        }catch(Exception e){
            System.out.println("you give me array like this to move : ");
            int[][] x = source;
            for(int j=0;j<x.length;j++){
                    for(int k=0;k<x[j].length;k++){
                        System.out.print(x[j][k] + " , ");
                    }
                    System.out.println();
                }
        }
        
        return null;
    }
    
    public int[][] CopyArray(int[][] arr){
        int[][] new_arr = new int[arr.length][arr[0].length];
        
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                new_arr[i][j] = arr[i][j];
            }
        }
        return new_arr;
    }
    
    public boolean Resultreached(int [][] grid){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] != this.solution[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean exist_before(ArrayList<int[][]> subset){
        int[][] arr = subset.get(subset.size()-1);
        
        for(int i=0;i<subset.size()-1;i++){
            int[][] x = subset.get(i);
            boolean is_equale = true;
            
            for(int j=0;j<x.length;j++){
                for(int k=0;k<x[j].length;k++){
                    if(x[j][k] != arr[j][k]){
                        is_equale = false;
                        break;
                    }
                }
                if(is_equale == false){
                    break;
                }
            }
            
            if(is_equale == true){
                return true;
            }
        }
        return false;
    }
    
    public int get_cost(int[][] grid){
        int cost = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == this.solution[i][j]){
                    cost++;
                }
            }
        }
        return cost;
    }
    
    public boolean SolveDF(ArrayList<int[][]> subset,int last_cost){
        
        if(exist_before(subset))return false;
        
        int[][] curr = CopyArray( subset.get(subset.size()-1) );
        
        if(Resultreached(curr)){
            return true;
        }
        
        for(int i=0;i<4;i++){
            switch(i){
                case 0:
                    int[][] x = CopyArray(curr);
                    int[][] curr_up = Move(x, direction.up);
                    if(curr_up == null)continue;
                    if(get_cost(curr_up) < last_cost)continue;
                    subset.add(curr_up);
                    if(SolveDF(subset,get_cost(subset.get(subset.size()-1))))return true;
                    subset.remove(subset.size()-1);
                    break;
                case 1:
                    int[][] y = CopyArray(curr);
                    int[][] curr_down = Move(y, direction.down);
                    if(curr_down == null)continue;
                    if(get_cost(curr_down) < last_cost)continue;
                    subset.add(curr_down);
                    if(SolveDF(subset,get_cost(subset.get(subset.size()-1))))return true;
                    subset.remove(subset.size()-1);
                    break;
                case 2:
                    int[][] z = CopyArray(curr);
                    int[][] curr_left = Move(z, direction.left);
                    if(curr_left == null)continue;
                    if(get_cost(curr_left) < last_cost)continue;
                    subset.add(curr_left);
                    if(SolveDF(subset,get_cost(subset.get(subset.size()-1))))return true;
                    subset.remove(subset.size()-1);
                    break;
                case 3:
                    int[][] l = CopyArray(curr);
                    int[][] curr_right = Move(l, direction.right);
                    if(curr_right == null)continue;
                    if(get_cost(curr_right) < last_cost)continue;
                    subset.add(curr_right);
                    if(SolveDF(subset,get_cost(subset.get(subset.size()-1))))return true;
                    subset.remove(subset.size()-1);
                    break;
            }
        } 
        return false;
    }
    
}
