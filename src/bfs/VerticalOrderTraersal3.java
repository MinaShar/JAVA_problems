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
public class VerticalOrderTraersal3 {
    
    class Utility{
        int NodeValue;
        int CurrentHeight;
        
        public Utility(int NodeValue , int CurrentHeight){
            this.NodeValue = NodeValue;
            this.CurrentHeight = CurrentHeight;
        }
    }
    
    
    public ArrayList<ArrayList<Integer>> MainFunc(TreeNode root){
        HashMap<Integer,ArrayList<Utility>> result = new HashMap <Integer, ArrayList<Utility >>();
        
        RecursiveSolution(root , result , 0 , 0);
        
        ArrayList<ArrayList<Integer>> FinalResult ;
        
        FinalResult = CastMyResultToFinal(result);
        
        for(int i=0;i<FinalResult.size();i++){
            if(FinalResult.get(i).size() == 0){
                FinalResult.remove(i);
                i--;
            }
        }
        
        return FinalResult;
    }
    
    public void RecursiveSolution(TreeNode Node,HashMap<Integer,ArrayList<Utility>> result,int CurrentVertical,int CurrentHeight){
        
        if( Node == null){
            return;
        }else{
            ArrayList<Utility> t = result.get(CurrentVertical);
            if(t == null){
                t = new ArrayList<Utility>();
            }
            t.add(new Utility(Node.val, CurrentHeight));
            result.put(CurrentVertical, t);
        }
        
        RecursiveSolution(Node.left , result , CurrentVertical-1 , CurrentHeight+1);
        RecursiveSolution(Node.right , result , CurrentVertical+1 , CurrentHeight+1);
    }
    
    public ArrayList<ArrayList<Integer>> CastMyResultToFinal(HashMap<Integer,ArrayList<Utility>> result){
        
        ArrayList<ArrayList<Integer>> FinalResult = new ArrayList<>();
        
        int MinKey = 1000;int MaxKey = -1000;
        for(Integer key : result.keySet()){
            if(key < MinKey){
                MinKey = key;
            }
        }
        for(Integer key : result.keySet()){
            if(key > MaxKey){
                MaxKey = key;
            }
        }
        
        int Neutralizer = MaxKey > MinKey * -1 ? MaxKey : MinKey*-1;
        
        for(Integer key : result.keySet() ){
            ArrayList<Utility> _current = result.get(key);
            
            for(int i=0;i<_current.size();i++){
                for(int j=0;j<_current.size()-1;j++){
                    if(_current.get(j).CurrentHeight > _current.get(j+1).CurrentHeight){
                        Utility temp = _current.get(j);
                        _current.set(j, _current.get(j+1) );
                        _current.set(j+1, temp);
                    }
                }
            }
        }
        
        for(int i=MinKey;i<=Neutralizer;i++){
            ArrayList<Utility> k = result.get(i);
            
            if(k == null){
                k = new ArrayList<>();
            }
            ArrayList<Integer> _list = new ArrayList<>();
            
            for(int j=0;j<k.size();j++){
                _list.add(k.get(j).NodeValue);
            }
            FinalResult.add(_list);
        }
        
        
        
        return FinalResult;
    }
    
}
