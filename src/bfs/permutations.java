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
public class permutations {
    
    public permutations(String s){
        
        char[] arr = s.toCharArray();
        ArrayList<String> results = new ArrayList<>();
        String subset = "";
        
        BackTrack(arr, results, subset);
        
        System.out.println(results);
        
    }
    
    public boolean StringContains(String s,char y){
        return s.matches(".*("+y+").*");
    }
    
    public void BackTrack(char[] arr,ArrayList<String> results,String Subset){
        
        if(Subset.length() == 3 && !Subset.matches(".*(AB).*")){
            results.add(Subset);
            return;
        }else if(Subset.length() == 3){
            return;
        }
        
        
        for(int i=0;i<arr.length;i++){
            if(!StringContains(Subset, arr[i]) && !(Subset+arr[i]).matches(".*(AB).*") ){
                Subset += arr[i];
                
                BackTrack(arr, results, Subset);
                
                Subset = Subset.substring(0,Subset.length()-1);
            }    
        }
    }
    
}
