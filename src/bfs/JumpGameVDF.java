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
public class JumpGameVDF {

    public JumpGameVDF() {
        int[] arr = new int[]{6,4,14,6,8,13,9,7,10,6,12};
        int d = 2;
        System.out.println( "result is : "+ maxJumps(arr, d) );
    }
    
    public int maxJumps(int[] arr, int d) {
        
        int[] memory = new int[arr.length];
        
        for(int i=0;i<memory.length;i++)
            memory[i] = -1;
        
        int max_val = 0;
        for(int i=0;i<arr.length;i++){
            max_val = Math.max( max_val , solve_df(arr,memory,i,d,1) );
        }
        return max_val;
    }
    
    public int solve_df(int[] arr,int[] memory,int curr,int d,int cost){
        
        if(memory[curr] != -1)
            return memory[curr];
        
        int max_val = 1;
        for(int i=curr-d;i<=curr+d;i++){
            if(i==curr)
                continue;
            if( i>=0 && i <arr.length ){
                
                int max_length = arr[curr];
                boolean can;
                if(i < curr){
                    can = true;
                    for(int j=i;j<curr;j++){
                        if(arr[j] >= max_length){
                            can = false;
                            break;
                        }
                    }
                    if(can == true){
                        max_val = Math.max( max_val , 1+solve_df(arr,memory,i,d,max_val+1) );         
                    }
                }else if(i > curr){
                    can = true;
                    for(int j=curr+1;j<=i;j++){
                        if(arr[j] >= max_length){
                            can = false;
                            break;
                        }
                    }
                    if(can == true){
                        max_val = Math.max( max_val , 1+solve_df(arr,memory,i,d,max_val+1) );         
                    }
                }
            }
        }
        memory[curr] = max_val;
        return max_val;
    }
}
