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
public class Solution2 {
    
    ArrayList<Integer> _list;
    int _indexer;
    
    public Solution2(MyNode root){
        _list = new ArrayList<Integer>();
        _indexer = 0;
        DFS(root);
    }
    
    public void DFS(MyNode _node){
        
        if(_node == null){
            return;
        }
        DFS(_node.left);
        _list.add(_node.value);
        DFS(_node.right);
    }
    
    public boolean hasNext(){
        return _indexer < _list.size();
    }
    
    public int getNext(){
        return _list.get(_indexer++);
    }
    
}
