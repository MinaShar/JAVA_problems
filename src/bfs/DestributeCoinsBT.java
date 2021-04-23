/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import com.sun.java.accessibility.util.EventID;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author misaac
 */
public class DestributeCoinsBT {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;left=null;right=null; }
    }
    
    public TreeNode FormTree(){
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(0);
//        root.left.right = new TreeNode(3);
//        root.right = new TreeNode(0);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        return root;
    }
    
    public DestributeCoinsBT(){
        TreeNode root = FormTree();
        
        MoveDF(root,null);
        
        System.out.println("number of moves : "+MovesCounter);
    }
    
    int MovesCounter;
    
    public void MoveDF(TreeNode current,TreeNode parent){
        
        if(current.left != null)
            MoveDF(current.left,current);
        if(current.right != null)
            MoveDF(current.right,current);
        
        if(parent == null)
            return;
        
        if(current.val != 1){
            MovesCounter += Math.abs( current.val - 1 );
        }
    }
}
