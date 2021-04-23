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
public class MergeKSortedLists {
    
    class CurrNode{
        public int element;
        public int ArrayIndex;
    }
    
    public MergeKSortedLists(ArrayList<ArrayList<Integer>> _array){
        System.out.println( Solve(_array) );
    }
    
    public ArrayList<Integer> Solve(ArrayList<ArrayList<Integer>> _array){
        
        ArrayList<Integer> MergedArray = new ArrayList<>();
        int totalValues = 0;
        for(int i=0;i<_array.size();i++)
            totalValues += _array.get(i).size();
        
        CurrNode[] subset = new CurrNode[_array.size()];
        
        for(int i=0;i<_array.size();i++){
            CurrNode buffer = new CurrNode();
            buffer.element = _array.get(i).get(0);
            _array.get(i).remove(0);
            buffer.ArrayIndex = i;
            
            subset[i] = buffer;
        }
        for(int j=subset.length/2;j>=0;j--){
            MinHeapify(subset, j);
        }
        
        for(int i=0;i<totalValues;i++){
            MergedArray.add(subset[0].element);
            subset[0].element = _array.get( subset[0].ArrayIndex ).size() > 0 ? _array.get( subset[0].ArrayIndex ).get(0) : Integer.MAX_VALUE;
            if(_array.get( subset[0].ArrayIndex ).size() > 0 ) _array.get( subset[0].ArrayIndex ).remove(0);
            MinHeapify(subset, 0);
        }
        
        return MergedArray;
    }
    
    public void Swap(Object[] subset,int i,int j){
        Object buffer = subset[i];
        subset[i] = subset[j];
        subset[j] = buffer;
    }
    
    public void MinHeapify(CurrNode[] subset, int index){
        
        int root = index;
        int left = root*2 + 1;
        int right = root*2 + 2;
        
        int index_smallest = root;
        if(left < subset.length && subset[left].element < subset[index_smallest].element){
            index_smallest = left;
        }
        if(right < subset.length && subset[right].element < subset[index_smallest].element){
            index_smallest = right;
        }
        
        if(index_smallest != root){
            Swap(subset, index_smallest, root);
            MinHeapify(subset, index_smallest);
        }
    }
    
}
