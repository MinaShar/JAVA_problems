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
public class MaximalSquare {
    
    public MaximalSquare(){
        
    }
    
    public int maximalSquare(char[][] matrix) {
        
        int max_area = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                
                if(matrix[i][j] == '1'){
                    int res = area_at_vertex(i,j,matrix);
                    max_area = Math.max( max_area , res );
                }
                
            }
        }
        return max_area;
    }
    
    public int area_at_vertex(int x,int y,char[][] matrix){
        
        int step=1;
        int max_area = 1;
        while( (x+step) < matrix.length && (y+step) < matrix[0].length
             && matrix[x+step][y+step] == '1'){
            
            
            int x_new = x+step;
            while(x_new > x && matrix[x_new][y+step] == '1' ){
                x_new--;
            }
            if(x_new > x || matrix[x_new][y+step] != '1' ){
                return max_area;
            }
            
            int y_new = y+step;
            while(y_new > y && matrix[x+step][y_new] == '1'){
                y_new--;
            }
            if(y_new > y || matrix[x+step][y_new] != '1'){
                return max_area;
            }
            
            max_area = (step+1) * (step+1);
            
            step++;
        }
        return max_area;
    }
}
