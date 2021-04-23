/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author misaac
 */
public class NQueensBF {
    
    class Node{
        int[][] grid;
        int NumberOfQueens;
        int last_x,last_y;
        public Node(int[][] grid,int NumberOfQueens,int last_x,int last_y){
            this.grid = grid;
            this.NumberOfQueens = NumberOfQueens;
            this.last_x = last_x;
            this.last_y = last_y;
        }
    }
    
    public NQueensBF(int N){
        int[][] grid = new int[N][N];
        for(int[] e : grid){
            Arrays.fill(e, 0);
        }
        
        SolveBF(grid);
    }
    
    public void print_array(int[][] arr){
        for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    System.out.print("["+arr[i][j] + "]");
                }
                System.out.println();
            }
    }
    
    public boolean is_cell_threatend(int[][] grid ,int x,int y){
        /////rows
        for(int i=0;i<grid.length;i++){
            if(grid[i][y] == 1){
                return true;
            }
        }
        ////columns
        for(int i=0;i<grid[0].length;i++){
            if(grid[x][i] == 1){
                return true;
            }
        }
        ///diagonals fron left
        for(int i=x+1,j=y+1;i<grid.length && j<grid[0].length;i++,j++){
            if(grid[i][j] == 1){
                return true;
            }
        }
        ///diagonals back left
        for(int i=x-1,j=y-1;i>=0 && j>=0;i--,j--){
            if(grid[i][j] == 1){
                return true;
            }
        }
        ///diagonals front right
        for(int i=x-1,j=y+1;i>=0 && j< grid[0].length;i--,j++){
            if(grid[i][j] == 1){
                return true;
            }
        }
        ///diagonals back right
        for(int i=x+1,j=y-1;i<grid.length && j>=0 ;i++,j--){
            if(grid[i][j] == 1){
                return true;
            }
        }
        return false;
    }
    
    public int[][] copy_array(int[][] arr){
        int[][] arr_new = new int[arr.length][arr[0].length];
        
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                arr_new[i][j] = arr[i][j];
            }             
        }
        return arr_new;
    }
    
    public boolean is_grid_correct(int[][] grid , int row){
        
        for(int i=0;i<row;i++){
            boolean is_all_empty = true;
            for(int j=0;j<grid.length;j++){
                if(grid[i][j] == 1){
                    is_all_empty = false;
                    break;
                }
            }
            if(is_all_empty == true){
                return false;
            }
        }
        return true;
    }
    
    public void SolveBF(int [][] grid){
        
        Node root = new Node(grid, 0, 0, -1);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node n = null;
        while( ( n = q.remove()) != null ){
            
            System.out.println("So far : ");
            print_array(n.grid);
            
            for(int i=n.last_x;i<grid.length;i++){
                boolean break_flag = false;
                for(int j=n.last_y+1;j<grid[i].length;j++){
                    
                    break_flag = false;
                    if( !is_grid_correct(n.grid, i) ){
                        break_flag = true;
                        break;
                    }
                    
                    if(is_cell_threatend( n.grid , i , j) ){
                        continue;
                    }else{
                        int[][] arr = copy_array(n.grid);
                        arr[i][j] = 1;
                        
                        if(n.NumberOfQueens +1 == grid.length){System.out.println("Correct solution: ");print_array(arr);return;}
                        
                        Node a = new Node(arr, n.NumberOfQueens+1, i, -1);
                        q.add(a);
                    }
                }
                if(break_flag == true)break;
            }
        }
    }
    
}
