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
public class Fibbonachi {
    
    public Fibbonachi(int n){
        int[] arr = new int[n];
        arr[0] = 0;
        arr[1] = 1;
        for(int i=2;i<n;i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+",");
        }
    }
    
}
