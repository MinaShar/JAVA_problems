/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class RemoveBoxesDF {
    
    int[][] db;
    int[] boxesp;
    
    public RemoveBoxesDF(){
        int[] boxes = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println("result is : "+ removeBoxes(boxes) );
    }
    
    public int removeBoxes(int[] boxes) {
        db = new int[boxes.length][boxes.length];
        this.boxesp = new int[boxes.length];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int l=this.boxesp.length-1;l>=0;l--){
            this.boxesp[l] = map.getOrDefault( boxes[l] , this.boxesp.length );
            map.put( boxes[l] , l );
        }
        
        return solve(0,this.boxesp.length-1);
    }
    
    public int solve(int start,int end){
        if(start == end)
            return 1;
        else if(start > end)
            return 0;
        else if(db[start][end] != 0)
            return db[start][end];
        else return db[start][end] = df(start,end,boxesp[start],1,0);
    }
    
    public int df(int start,int end,int next,int counter,int acc){
        if(next > end)
            return counter*counter + acc + solve(start+1,end);
        else if(start+1 == next)
            return df(next,end,boxesp[next],counter+1,acc);
        else return Math.max( df(start,end,boxesp[next],counter,acc),
                            df(next,end,boxesp[next],counter+1,acc+solve(start+1,next-1)));
    }
}
