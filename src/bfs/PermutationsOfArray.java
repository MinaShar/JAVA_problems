/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.*;

/**
 *
 * @author misaac
 */
public class PermutationsOfArray {
    
    public PermutationsOfArray(){
        String[] words = new String[]{"word","good","best","word"};
        ArrayList<String> permutations = new ArrayList<String>(); 
        ArrayList<String> rest_words = new ArrayList<String>();
        for(int i=0;i<words.length;i++){
            rest_words.add(words[i]);
        }
        back_track(words,rest_words, "", permutations);
        
        System.out.println("result is :");
        for(int i=0;i<permutations.size();i++){
            System.out.println(permutations.get(i));
        }
    } 
    
    
    public void back_track(String[] words,ArrayList<String> rest_words,String curr,ArrayList<String> permutaions){
        
        if(rest_words.size() == 0){
            permutaions.add(curr);
        }
        
        for(int i=0;i<rest_words.size();i++){        
            curr += rest_words.get(i);
            String removed_str = rest_words.remove(i);
            
            back_track(words,rest_words,curr,permutaions);
            
            rest_words.add(i,removed_str);
            curr = curr.substring(0,curr.length()-words[0].length());
        }
    }
    
}
