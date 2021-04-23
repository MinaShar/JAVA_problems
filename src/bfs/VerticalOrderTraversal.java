/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author misaac
 */
class MinMaxWidth{
    int MinWidth;
    int MaxWidth;
    
    public MinMaxWidth(){
        MinWidth = 0;
        MaxWidth = 0;
    }
}

//class HorizontalDist{
//    TreeNode _node;
//    int HoriDist;
//    
//    public HorizontalDist(TreeNode _node, int Dist){
//        this._node = _node;
//        this.HoriDist = Dist;
//    }
//}

public class VerticalOrderTraversal {
    
    MinMaxWidth container;
    
    HashMap<String, Integer> Mapping = new HashMap<String, Integer>(10000,10000); 
    
    public VerticalOrderTraversal(TreeNode root){
        container = new MinMaxWidth();
        
        ArrayList<ArrayList<Integer>> test = MainFunc(root);
        
        for(int i=0;i<test.size();i++){
            ArrayList<Integer> x = test.get(i);
            System.out.print("[");
            for(int j=0;j<x.size();j++){
                System.out.print(  x.get(j) );
                System.out.print(" , ");
            }
            System.out.print("]");
        }
    }
    
    public ArrayList<ArrayList<Integer>> MainFunc(TreeNode root){
        
        this.CalculateMinMaxWidth(root, 0);
        
        ArrayList<ArrayList<Integer>> Total = new ArrayList<>();
        
        for(int i=container.MinWidth;i<=container.MaxWidth;i++){
            ArrayList<Integer> _curr = new ArrayList<>();
            ArrayList<TreeNode> BFSearch = new ArrayList<>();
            BFSearch.add(root);
            this.CurrentVerticalLine(BFSearch, i, _curr);
            Total.add(_curr);
        }
        
        return Total;
        
    }
    
    public void CurrentVerticalLine(ArrayList<TreeNode> BFSearch , int PrintWhichLine , ArrayList<Integer> Container){
       
        if(BFSearch.size() == 0){
            return;
        }
        
        while(BFSearch.size() > 0){
            TreeNode _currNode = BFSearch.remove(0);
            
            if(PrintWhichLine == Mapping.get( String.valueOf(_currNode.val) ) ){
                Container.add(_currNode.val);
            }
            
            if(_currNode.left != null){
                BFSearch.add(_currNode.left);
            }
            if(_currNode.right != null){
                BFSearch.add(_currNode.right);
            }
        }
//        CurrentVerticalLine(BFSearch,PrintWhichLine,Container);
    }
    
    
    public void CalculateMinMaxWidth(TreeNode _node , int HD){
        
        if(_node == null){
            return;
        }
        
        Mapping.put(String.valueOf( _node.val) , HD);
        
        if(HD < container.MinWidth){
            container.MinWidth = HD;
        }
        if(HD > container.MaxWidth){
            container.MaxWidth = HD;
        }
        CalculateMinMaxWidth(_node.left , HD-1);
        CalculateMinMaxWidth(_node.right, HD+1);
        
    }
    
}
