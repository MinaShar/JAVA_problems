/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bfs;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author misaac
 */
public class RemoveBoxesUpperBound {
    
    public RemoveBoxesUpperBound(){
        int[] boxes = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
            System.out.println("result is : "+removeBoxes(boxes));
    }
    
    public int removeBoxes(int[] boxes) {
        
        int max = 0;
        for(int i=0;i<boxes.length;i++){
            max = Math.max(max , solve_df(boxes, 1<<i , i , 0 , max ) );
        }
        return max;
    }
    
    public int getupper_bound(int[] boxes,int used){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<boxes.length;i++){
            if(  ((1<<i) & used) == 0)
                map.put( boxes[i] , map.getOrDefault(boxes[i],0) + 1 );
        }
        int upperbound = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            upperbound += (entry.getValue()*entry.getValue());
        }
        return upperbound;
    }
    
    public int solve_df(int[] boxes,int used , int current , int cost_sofar , int max_solution){
        
        used = used | (1<<current);
        int left = current-1,right = current+1, size=1;
        while( ( left>=0 && ( ((1<<left)&used) > 0 || boxes[left] == boxes[current] ) )
                || ( right<boxes.length &&  ( ((1<<right)&used) > 0 || boxes[right] == boxes[current] ) ) ){
            if(left >= 0){
                if( ((1<<left)&used)>0 ) {
                    left--;
                }else if( boxes[left] == boxes[current] ){
                    used = used | (1<<left);
                    left--;
                    size++;
                }
            }
            
            if(right < boxes.length){
                if( ((1<<right)&used)>0 ){
                    right++;
                }else if(boxes[right]==boxes[current]){
                    used = used | (1<<right);
                    right++;
                    size++;
                }
            }
            
        }
        cost_sofar += (size * size);
        
        if(getupper_bound(boxes,used)+cost_sofar <= max_solution ){
            return 0;
        }
        
        int max_val = cost_sofar;
        for(int i=0;i<boxes.length;i++){
            if( ( (1<<i) & used) > 0 ){
                continue;
            }
            else max_val = Math.max(max_val , solve_df(boxes,used,i,cost_sofar,max_solution));
        }
        return max_val;
    }
}
