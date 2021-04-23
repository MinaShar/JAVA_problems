/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.LinkedList;
import java.util.Queue;
import javafx.util.Pair;

/**
 *
 * @author misaac
 */
public class Buzzle8ProblemBF {
    
    enum direction{
        up,down,left,right
    };
    
    class Node{
        int[][] curr_grid;
        Node parent;
        public Node(int[][] grid,Node parent){
            this.curr_grid = grid;
            this.parent = parent;
        }
    }
    
    int[][] grid;
    int[][] solution;
    
    public Buzzle8ProblemBF(int[][] grid,int[][] solution){
        this.grid = grid;
        this.solution = solution;
        SolveBF(grid);
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
    
    public void Swap(int[][] source,int x1, int y1,int x2,int y2){
        int x = source[x1][y1];
        source[x1][y1] = source[x2][y2];
        source[x2][y2] = x;
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
    
    public void SolveBF(int[][] grid){
        
        Queue q = new LinkedList<Node>();
        
        q.add(new Node(copy_arr(grid), null));
        
        Node n = null;
        
        while(!q.isEmpty()){
            n = (Node) q.remove();
            
            if(Resultreached(n.curr_grid)){
                break;
            }
            
            int[][] n_up = Move(copy_arr(n.curr_grid), direction.up);
            int[][] n_down = Move(copy_arr(n.curr_grid), direction.down);
            int[][] n_left = Move(copy_arr(n.curr_grid), direction.left);
            int[][] n_right = Move(copy_arr(n.curr_grid), direction.right);
            
            if(n_up != null) q.add(new Node(n_up, n));
            if(n_down != null) q.add(new Node(n_down, n));
            if(n_left != null) q.add(new Node(n_left, n));
            if(n_right != null) q.add(new Node(n_right, n));
        }
        
        print_result(n);
    }
    
    public void print_result(Node n){
        
        do{
            System.out.println("----------------------------");
            int[][] x = n.curr_grid;
            
            for(int i=0;i<x.length;i++){
                for(int j=0;j<x[i].length;j++){
                    System.out.print(x[i][j] + " , ");
                }
                System.out.println();
            }
            n = n.parent;
        }while(n != null);
    }
    
    public int[][] copy_arr(int[][] arr){
        int[][] new_arr = new int[arr.length][arr[0].length];
        
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                new_arr[i][j] = arr[i][j];
            }
        }
        return new_arr;
    }
}
