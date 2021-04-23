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
public class AllPossibleIPs {
    
    public AllPossibleIPs(String s){
        ArrayList<String> results = new ArrayList<>();
        ArrayList<String> subset = new ArrayList<>();
        BackTrack(s, results, subset);
        
        System.out.println(results);  
    }
    
    public void BackTrack(String remain_str,ArrayList<String> results,ArrayList<String> subset){
        
        if(subset.size() == 4 && remain_str.length()==0){
            boolean flag = true;
            for(int i=0;i<subset.size();i++){
                if(new Integer( subset.get(i) ) < 0 || new Integer( subset.get(i) ) > 255
                        || subset.get(i).charAt(0) == '0'){
                    flag = false;
                    break;
                }
            }
            if(flag == true){
                results.add(subset.get(0)+"."+subset.get(1)+"."+subset.get(2)+"."+subset.get(3));
            }
            return;
        }
        else if(subset.size() > 4) return;
        
        for(int i=1;i<=remain_str.length();i++){
            if(remain_str.substring(0, i).length() <= 3){
                subset.add( remain_str.substring(0, i)  );
            
                BackTrack(remain_str.substring(i,remain_str.length()), results, subset);

                subset.remove(subset.size()-1);
            }
        }
    }
    
}
