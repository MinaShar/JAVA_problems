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
public class ConstructTreeFromCartisean {
    
    TreeNode root ;

    public ConstructTreeFromCartisean(ArrayList<Integer> _list) {
        
        root = ConstructBST(_list);
        
    }
    
    public TreeNode ConstructBST(ArrayList<Integer> _list ){
        if(_list.size() == 0){
            return null;
        }
        if(_list.size() == 1){
            return new TreeNode(_list.get(0));
        }
        
        int IndexMax = GetIndexMaxValue(_list);
        ArrayList<Integer> _leftSubTree = new ArrayList<Integer>();
        ArrayList<Integer> _rightSubTree = new ArrayList<Integer>();
        
        ConstructLeftRightSubTree(_list , IndexMax , _leftSubTree , _rightSubTree);
        
        TreeNode _root = new TreeNode(_list.get(IndexMax));
        
        _root.left = ConstructBST(_leftSubTree);
        _root.right = ConstructBST(_rightSubTree);
        
        return _root;
    }
    
    public void ConstructLeftRightSubTree(ArrayList<Integer> _list , int RootIndex , ArrayList<Integer> Left ,ArrayList<Integer> Right ){
        
        for(int i=0;i<RootIndex;i++){
            Left.add(_list.get(i));
        }
        
        for(int i=RootIndex+1;i<_list.size();i++){
            Right.add(_list.get(i));
        }
    }
    
    public int GetIndexMaxValue(ArrayList<Integer> _list){
        int maxVal = -1;int maxIndex = -1; 
        for(int i=0;i<_list.size();i++){
            if(_list.get(i) > maxVal){
                maxVal = _list.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public TreeNode ConstructBSTCallBack( ArrayList<Integer> _list , int ListIndex ){
        
        TreeNode _current = new TreeNode(_list.get(ListIndex));
        if(root == null){
            root = _current;
        }
        return root;
    }
    
    public TreeNode construct(ArrayList<Integer> _list , int index ){
        
        if(index == _list.size()){
            return null;
        }
        
        TreeNode _temp =  new TreeNode(_list.get(index));
        
        index++;
        
        TreeNode _temp2 = construct(_list, index );
        
        if(_temp2 != null){
            if(_temp2.val > _temp.val){
                _temp2.left = _temp;
                if(root == null || root.val < _temp2.val){
                    this.root = _temp2;
                }
                return _temp;
            }else{
                _temp.right = _temp2;
                if(this.root == null || this.root.val < _temp.val ){
                    this.root = _temp;
                }
                return _temp;
            }
        }else {
            if(this.root == null || this.root.val < _temp.val ){
                this.root = _temp;
            }
            return _temp;
        }
        
    }
    
    
    public void printDFS(){
        TreeNode _temp = root;
        
        System.out.println("begining of DFS ");
        
        Iterate(_temp);
        System.out.println();
        System.out.println("Ending of DFS ");
    }
    
    public void Iterate(TreeNode _node){
        if(_node == null){
            return;
        }
        
        if(_node.left != null){
            Iterate(_node.left);
        }
        
        System.out.print(_node.val + " , ");
        
        if(_node.right != null){
            Iterate(_node.right);
        }
    }
    
}
