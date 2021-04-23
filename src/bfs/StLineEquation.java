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
public class StLineEquation {

    public StLineEquation() {
        int[][] arr = new int[][]{{0,0},{2,1},{2,1}};
        
        System.out.println("result is : "+ isBoomerang(arr) );
    }
    
    
    
    public boolean isBoomerang(int[][] points) {
        
        float slope = (float)(points[1][1]-points[0][1]) / (float)(points[1][0]-points[0][0]); /////(points[1][0]-points[0][0]) ==0 ? Float.MAX_VALUE : 
           
        float intercepted = (float)points[0][1] - (float)(slope * (float)points[0][0]);
        
        // if(slope == Float.MAX_VALUE && points[2][1] == intercepted)
        //     return false;
        
        if(slope * points[2][0] + intercepted == points[2][1]){
            return false;
        }else {
            return true;
        }
    }
}
