/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author misaac
 */
public class Solution {
    
    ArrayList<MyNode> _list;
    int _listIndex;
    
    ArrayList<Integer> SortedArray;
    int SortedArrayIndex;
    
    public Solution(MyNode root){
        _list = new ArrayList<MyNode>();
        _list.add(root);
        
        _listIndex = 0;
        SortedArrayIndex = 0;
        
        SortedArray = new ArrayList<Integer>();
        
        do{
            
            MyNode _currentNode = _list.get(_listIndex);
            
            if(_listIndex != 0 && _currentNode != null){
                int valueToAdd = _currentNode.value;
                
                if(SortedArray.size() == 0){
                    SortedArray.add(valueToAdd);
                }else{
                    int buffer;
                    boolean isValueAdded = false;
                    
                    for(int i=0;i<SortedArray.size();i++){
                        if(valueToAdd < SortedArray.get(i)){
                            buffer = SortedArray.get(i);
                            SortedArray.set(i, valueToAdd);
                            valueToAdd = buffer;
                        }
                    }
                    
                    SortedArray.add(valueToAdd);
                }
            }
            
            if(_currentNode != null){
                _list.add(_currentNode.left);
                _list.add(_currentNode.right);
            }
            
            _listIndex++;
        
        }while(_listIndex < _list.size());
        
        for (int j=0;j< SortedArray.size();j++) {
            System.out.print(SortedArray.get(j) + " , ");
        }
        System.out.println();
        System.out.println();       
        
    }
    
    public boolean hasNext(){
        return SortedArrayIndex < SortedArray.size();
    }
    
    public int getNext(){
        return SortedArray.get(SortedArrayIndex++);
    }
    
}
