/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.*;

/**
 *
 * @author misaac
 */
public class UniquPaths {
    class pair<T>{
        T x;
        T y;
        public pair(T x,T y){
            this.x = x;
            this.y = y;
        }
    }
    int counter = 0;
    public int uniquePathsIII(int[][] grid) {
        
        int x=-1,y=-1;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    x=j;y=i;
                    break;
                }
            }
            if(x != -1)
                break;
        }
        ArrayList<pair<Integer>> path = new ArrayList<pair<Integer>>();
        path.add(new pair(x,y));
        solve_df(grid,path);
        return counter;
    }
    
    public boolean is_exist(ArrayList<pair<Integer>> path,pair<Integer> p){
        for(int i=0;i<path.size();i++){
            if(p.x == path.get(i).x && p.y==path.get(i).y){
                return true;
            }
        }
        return false;
    } 
    
    public void solve_df(int[][] grid,ArrayList<pair<Integer>> path){
        
        pair<Integer> last_step = path.get(path.size()-1);
        if(grid[last_step.y][last_step.x] == 2){
            counter++;
            return;
        }
        
        pair<Integer> next_up=null,next_down=null,next_left=null,next_right=null;
        if(last_step.y > 0 && grid[last_step.y-1][last_step.x]!=-1 && !is_exist(path,new pair<Integer>(last_step.x,last_step.y-1)))
            next_up = new pair<Integer>(last_step.x,last_step.y-1);
        if(last_step.y < grid.length-1 && grid[last_step.y+1][last_step.x]!=-1 
           && !is_exist(path,new pair<Integer>(last_step.x,last_step.y+1)))
            next_down = new pair<Integer>(last_step.x,last_step.y+1);
        if(last_step.x>0 && grid[last_step.y][last_step.x-1]!=-1 
           && !is_exist(path,new pair<Integer>(last_step.x-1,last_step.y)))
            next_left = new pair<Integer>(last_step.x-1,last_step.y);
        if(last_step.x<grid[0].length-1 && grid[last_step.y][last_step.x+1]!=-1 
           && !is_exist(path,new pair<Integer>(last_step.x+1,last_step.y)))
            next_right = new pair<Integer>(last_step.x+1,last_step.y);
        
        if(next_up != null){
            path.add(next_up);
            solve_df(grid,path);
            path.remove(path.size()-1);
        }
        if(next_down != null){
            path.add(next_down);
            solve_df(grid,path);
            path.remove(path.size()-1);
        }
        if(next_left != null){
            path.add(next_left);
            solve_df(grid,path);
            path.remove(path.size()-1);
        }
        if(next_right != null){
            path.add(next_right);
            solve_df(grid,path);
            path.remove(path.size()-1);
        }
    }
}
