/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;
import java.util.ArrayList;

/**
 *
 * @author misaac
 */
public class MergingSortedArrays {
    
    public MergingSortedArrays(){
        
    }
    
    public void Merge(ArrayList<Integer> arr1 , ArrayList<Integer> arr2){
        
        int arr1_index = 0, arr2_index = 0;
        
        while(arr1_index < arr1.size() && arr2_index < arr2.size()){
            if(arr2.get(arr2_index) < arr1.get(arr1_index)){
                arr1.add(arr1_index, arr2.get(arr2_index++));
            }
            arr1_index++;
        }
        
        while(arr2_index < arr2.size()){
            arr1.add(arr2.get(arr2_index++));
        }
    }
    
}
