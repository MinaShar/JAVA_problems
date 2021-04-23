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
public class IntersectionOfSortedArrays {
    
    
    public IntersectionOfSortedArrays(){
        int[] arr1 = new int[]{1,2,3,4,5};
        int[] arr2 = new int[]{1,4,5,6,7};
        
        int[] final_array = Solve(arr1,arr2);
        
        printArray(final_array);
    }
    
    public int[] Solve(int[] arr1,int[] arr2){
        
        int[] intersection = new int[arr1.length<=arr2.length ? arr1.length : arr2.length];
        int intersection_index = 0,arr1_index=0,arr2_index=0;
        while(arr1_index<arr1.length && arr2_index<arr2.length){
            if(arr1[arr1_index] == arr2[arr2_index]){
                intersection[intersection_index++] = arr1[arr1_index];
                arr1_index++;arr2_index++;
            }else if(arr1[arr1_index] < arr2[arr2_index]){
                arr1_index++;
            }else if(arr1[arr1_index] > arr2[arr2_index]){
                arr2_index++;
            }
        }
        return getFinalArray(intersection, intersection_index);
    }
    
    public int[] getFinalArray(int[] intersection ,int index){
        int[] new_Array = new int[index];
        
        for(int i=0;i<index;i++){
            new_Array[i] = intersection[i];
        }
        return new_Array;
    }
    
    public void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + ",");
        }
    }
}
