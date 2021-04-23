/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author misaac
 */
public class NQueens {
    
    class Node{
        int[][] curr_grid;
        int NumberOfQueens;
        int last_x,last_y;
        public Node(int[][] grid,int queens,int last_x,int last_y){
            this.curr_grid = grid;
            this.NumberOfQueens = queens;
            this.last_x = last_x;
            this.last_y = last_y;
        }
    }
    
    public NQueens(int N){
        
        int[][] grid = new int[N][N];
        for(int[] e : grid){
            Arrays.fill(e, 0);
        }
        
        int[][] arr;
        
        if( (arr = SolveBackTrack(grid) ) != null ){
            System.out.println("Finally the Solution is :");
            print_array(arr);
        }
    }
    
    public void print_array(int[][] arr){
        for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    System.out.print(arr[i][j] + " , ");
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
    
    public int[][] SolveBackTrack(int[][] grid){
        
        Stack<Node> s = new Stack();
        s.add(new Node(copy_array(grid), 0 , 0, 0));
        
        Node n;
        
        while( ( n= s.pop() ) != null ){
            if(n.NumberOfQueens == grid.length){
                return n.curr_grid;
            }
            
            System.out.println("currnt array : >>>>>>>>>>");
            print_array(n.curr_grid);
            
            int x=-1,y=-1;
            int curr_row_process;
            for(curr_row_process=n.last_x;curr_row_process<n.curr_grid.length;curr_row_process++){
                for(int j=n.last_y;j<n.curr_grid[curr_row_process].length;j++){
                    if(n.curr_grid[curr_row_process][j] == 0 && !is_cell_threatend(n.curr_grid,curr_row_process, j)){
                        x = curr_row_process;y = j;
                        break;
                    }
                }
                if( x != -1 && y != -1){
                    break;
                }
            }
            
            if(x == -1 || y== -1){
                continue;
            }
            
            if(!is_grid_correct(n.curr_grid, curr_row_process )){
                continue;
            }
                
            s.push(new Node(copy_array(n.curr_grid) , n.NumberOfQueens , x , y+1 ));
            
            int[][] arr1 = copy_array(n.curr_grid);
            
            arr1[x][y] = 1;
            if(n.NumberOfQueens + 1 == grid.length){
                return arr1;
            }
            
            s.push(new Node(arr1, n.NumberOfQueens+1 , 0, 0));
        }     
        return null;
    }
    
}
