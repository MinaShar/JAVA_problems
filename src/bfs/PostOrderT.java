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
public class PostOrderT {
    
    public class TrickyThing{
        TreeNode A;
        boolean is_leftProcessed;
        boolean is_rightProcessed;
        
        public TrickyThing(TreeNode _a){
            this.A = _a;
            is_leftProcessed = false;
            is_rightProcessed = false;
        }
    }
    
    
    
    public ArrayList<Integer> MainF(TreeNode A){        
        ArrayList<Integer> _list = new ArrayList<>();
        
        ArrayList<TrickyThing> _stack = new ArrayList<>();
        
        if(A == null){
            return _list;
        }
        
        TreeNode _current = A;
        
        _stack.add(new TrickyThing(_current));
        
        TrickyThing _currentTricky = null;
        boolean repeat;
        
        while (_stack.isEmpty() == false) {  
            
            do{
            repeat = false;
            
            if(_currentTricky == null || (_currentTricky != null && _currentTricky.is_leftProcessed==false) ){
                while(_current.left != null){
                    _stack.get(_stack.size()-1).is_leftProcessed=true;
                    _current = _current.left;
                    _stack.add(new TrickyThing(_current));
                }
                _stack.get(_stack.size()-1).is_leftProcessed=true;
            }
            
            if(_current.right != null){
                if(_currentTricky != null && _currentTricky.is_rightProcessed == true && _currentTricky.is_leftProcessed == true){
                    
                }else{
                    _stack.get(_stack.size()-1).is_rightProcessed=true;
                    _current = _current.right;
                    _stack.add(new TrickyThing(_current));
                    repeat = true;
                    _currentTricky = null;
                }
                
            }else{
                _stack.get(_stack.size()-1).is_rightProcessed=true;
            }
        }while(repeat == true);
        
            _currentTricky = _stack.get(_stack.size() -1);
            if(_currentTricky.is_leftProcessed == true && _currentTricky.is_rightProcessed == true){
                _current = _currentTricky.A;
                _list.add(_current.val);
                _stack.remove(_stack.size()-1);
            }
            if(_stack.isEmpty() == false){
                _currentTricky = _stack.get(_stack.size()-1);
                _current = _currentTricky.A;
            }else{
                break;
            }
           
        }
        
        return _list;
    }
    
    public PostOrderT(TreeNode A){
        
        ArrayList<Integer> T = MainF(A);
        
        System.out.println(T);
    }
    
}
