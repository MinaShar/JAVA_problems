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
public class InorderT {
    
    
    
    public InorderT(TreeNode A){
        ArrayList<Integer> _f = this.MainF(A);
        
        System.out.println(_f.toString());
    }
    
    public ArrayList<Integer> MainF(TreeNode A){
        ArrayList<Integer> _list = new ArrayList<>();
        
        ArrayList<TreeNode> _stack = new ArrayList<>();
        
        if(A == null){
            return _list;
        }
        
        TreeNode _current = A;
        TreeNode _poped;
        
        boolean Escape_left = false;
        
        _stack.add(_current);
        
        while (_stack.isEmpty() == false) {
            
            if(Escape_left == false){
                while (true) { 
                    if(_current.left != null){
                        _current = _current.left;
                        _stack.add(_current);
                    }else{
                        break;
                    }
                }
            }
            Escape_left = false;
            
            _poped = _stack.get(_stack.size()-1);
            _stack.remove(_stack.size()-1);

            _list.add(_poped.val);
            _current = _poped;

            if(_current.right != null){
                _current = _current.right;
                _stack.add(_current);
            }else{
                Escape_left = true;
            }  
           
        }
        return _list;
    }
    
}
