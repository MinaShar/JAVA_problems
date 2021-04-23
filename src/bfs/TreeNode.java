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
public class TreeNode {
    
    public int val;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode(int x){
        this.val = x;
        this.left = null;
        this.right = null;
    }
    
    
    public static TreeNode CreateBST(TreeNode root , int numToAdd){
        
        if(root == null){
            return new TreeNode(numToAdd);
        }else if(numToAdd < root.val){
            root.left = CreateBST(root.left, numToAdd);
        }else if(numToAdd > root.val){
            root.right = CreateBST(root.right, numToAdd);
        }
        
        return root;
    }
    
    public static void PrintDF(TreeNode root){
        ArrayList<TreeNode> iter = new ArrayList<>();
        iter.add(root);
        System.out.println("NOW PRINTING TREE IN DFS ....---------------");
        while (iter.size() > 0) {
            TreeNode _curr = iter.remove(0);
            if(_curr != null){
                System.out.println(_curr.val);
                iter.add(_curr.left);iter.add(_curr.right);
            }
        }
        System.out.println("FINISHED PRINTING TREE IN DFS ....--------------");
    }
    
}
