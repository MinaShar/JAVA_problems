/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author misaac
 */
public class BackTracking {
    
    public BackTracking(ArrayList<Integer> _list){
        
        System.out.println(Solve(_list));
    }
    
    public void SortLinkedList(ArrayList<ArrayList<Integer>> t){
        for(int i=0;i<t.size();i++){
            for(int j=0;j<t.size()-1;j++){
                int length = (t.get(j).size() <= t.get(j+1).size() ) ? t.get(j).size() : t.get(j+1).size();
                for(int k=0;k<length;k++){
                    if(t.get(j).get(k) > t.get(j+1).get(k)){
                        ArrayList<Integer> buff = t.get(j);
                        t.set(j, t.get(j+1));
                        t.set(j+1, buff);
                        break;
                    }
                }
            }
        }
    }
    
    public boolean CheckLinkedListSorted(ArrayList<ArrayList<Integer>> t){
        for(int i=0;i<t.size();i++){
            for(int j=0;j<t.size()-1;j++){
                int length = (t.get(j).size() <= t.get(j+1).size() ) ? t.get(j).size() : t.get(j+1).size();
                
                for(int k=0;k<length;k++){
                    if(t.get(j).get(k) > t.get(j+1).get(k)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void Sort(ArrayList<Integer> t){
        
        for(int i=0;i<t.size();i++){
            for(int j=0;j<t.size()-1;j++){
                if(t.get(j) > t.get(j+1)){
                    int buff = t.get(j);
                    t.set(j, t.get(j+1));
                    t.set(j+1, buff);
                }
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> Solve(ArrayList<Integer> t){
        
        Sort(t);
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

                for(int i=0;i< Math.pow(2, t.size()) ;i++){

                    ArrayList<Integer> y = new ArrayList<>();
                    for(int j=0;j<t.size();j++){

                        if( ( i & (1 << j) ) > 0  ){
                            y.add(t.get(j));
                        }
                    }
                    result.add(y);
                }
        while(!CheckLinkedListSorted(result)){
            SortLinkedList(result);
        }
        System.out.println("result size is : " + result.size());
        return result;
    }
}
