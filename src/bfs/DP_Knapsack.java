/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

/**
 *
 * @author misaac
 */
public class DP_Knapsack {

    public DP_Knapsack() {
        int[] val = {60, 100, 120}; 
        int[] wt = {10, 20, 30};
        int weight = 50;
        System.out.println("Knap sack problem = "+SolveRecur(val, wt,0,weight));
    }
    
    public int SolveRecur(int[] val,int[] wt,int index,int rest_wt){
        
        if(rest_wt == 0 || index== wt.length)
            return 0;
        
        if(wt[index] > rest_wt){
            return SolveRecur(val, wt, index+1, rest_wt);
        }
        
        return Math.max( val[index] + SolveRecur(val, wt, index+1, rest_wt - wt[index]) 
                , SolveRecur(val, wt, index+1, rest_wt));
    }
    
    public void Solve(int[] val,int[] wt){
        
        int[][] K = new int[val.length+1][wt.length+1];
        for(int i=0;i<=val.length;i++){
            for(int j=0;j<=wt.length;j++){
                
            }
        }
    }
    
}
