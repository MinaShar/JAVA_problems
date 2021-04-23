/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

/**
 *
 * @author misaac
 */
public class GetNextBST {
    
    public MyNode NextNode;
    
    public GetNextBST(MyNode root, int x){
        
        MyNode _curr = root;
        MyNode _pointer = null;
        while(_curr.value != x){
            if(_curr.value > x){
                _pointer = _curr;
                _curr = _curr.left;///smaller
            }else if(_curr.value < x){
                _curr = _curr.right;
            }else if(_curr.value == x){
                break;
            }
        }
        
        MyNode MinimuminRightSubTree = null;
        
        if(_curr.right != null){
            MinimuminRightSubTree = GetMinimumInSubTree(_curr.right);
        }
        
        if(MinimuminRightSubTree != null && _pointer != null){
            NextNode = MinimuminRightSubTree.value < _pointer.value ? MinimuminRightSubTree : _pointer;
        }else if(MinimuminRightSubTree != null && _pointer == null){
            NextNode = MinimuminRightSubTree;
        }else if(MinimuminRightSubTree == null && _pointer != null){
            NextNode = _pointer;
        }else{
            NextNode = null;
        }
        
    }
    
    public MyNode GetMinimumInSubTree(MyNode N){
        while (N.left != null) {            
            N = N.left;
        }
        return N;
    }
    
}
