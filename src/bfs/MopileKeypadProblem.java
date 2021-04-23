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
public class MopileKeypadProblem {

    public MopileKeypadProblem() {
        
        char[][] arr = new char[][]{{'1','2','3'},{'4','5','6'},{'7','8','9'},{'*','0','#'}};
        
        System.out.println("result for {5} is : " + Solve(arr, 5) );
    }
    
    public int Solve(char[][] arr,int n){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        int[] x_dir = new int[]{0,0,1,0,-1};
        int[] y_dir = new int[]{0,1,0,-1,0};
        for(int i=1;i<=n;i++){
            
            for(int x=0;x<arr.length;x++){
                for(int y=0;y<arr[x].length;y++){
                    
                    if(arr[x][y] == '*' || arr[x][y] == '#')
                        continue;
                    
                    if(i==1){
                        map.put(x+","+y+","+i, 1);
                    }else{
                        int sum_x = 0;
                        for(int qq=0;qq<5;qq++){
                            sum_x += map.getOrDefault((x+x_dir[qq])+","+(y+y_dir[qq])+","+(i-1) ,0);
                        }
                        map.put(x+","+y+","+i,sum_x );
                    }
                }
            }
        }
        
        int result = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j] != '*' && arr[i][j] != '#'){
                    result += map.getOrDefault(i+","+j+","+n,0);
                }
            }
        }
        
        return result;
    }
    
}
