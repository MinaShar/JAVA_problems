/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class ColourfulNumber {
    
    public ColourfulNumber(int Number){
        Solve(Number);
    }
    
    public int getMultiblication(String s){
        
        int mult = 1;
        for(int i=0;i<s.length();i++){
            mult *= Character.getNumericValue( s.charAt(i) );
        }
        return mult;
    }
    
    public int Solve(int number){
        
        String curr_sub_string = (new Integer(number)).toString();
        
        HashMap<Integer,String> map = new HashMap<>();
        
        ArrayList<String> subsets = new ArrayList<String>();
        for(int length=1;length<=curr_sub_string.length();length++){
            for(int i=0;i<curr_sub_string.length();i++){
                if(i+length <= curr_sub_string.length()){
                    subsets.add(new String(curr_sub_string.substring(i,i+length)));
                }
            }
        }
        System.out.println(subsets);
        for(int i=0;i<subsets.size();i++){
            int mult = getMultiblication(subsets.get(i));
            if(map.containsKey(mult)){
                return 0;
            }else{
                map.put(mult, subsets.get(i));
            }
        }
        System.out.println(map);
        return 1;
    }
}
