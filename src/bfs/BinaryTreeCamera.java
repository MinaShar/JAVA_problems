/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author misaac
 */
public class BinaryTreeCamera {  
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;left=null;right=null; }
    }
    
    class Container{
        int counter;
    }
    
    public TreeNode FormTree(){
        TreeNode root = new TreeNode(1);
        TreeNode x = root.left = new TreeNode(2);
        TreeNode y = x.right = new TreeNode(3);
        TreeNode z = y.left = new TreeNode(4);
        TreeNode q = z.right = new TreeNode(5);
        q.left = new TreeNode(6);
        return root;
    }
    
    public BinaryTreeCamera(){
        ArrayList<TreeNode> x = new ArrayList<>();
        x.add(null);
        
        TreeNode root = FormTree();
        Container counter = new Container();
        SolvRecur(root, null , x, counter);
        
        System.out.println("Solution is :"+counter.counter);
    }
    
    public void SolvRecur(TreeNode root,TreeNode parent,ArrayList<TreeNode> covered,Container counter){
        if(root.left != null)
            SolvRecur(root.left, root , covered, counter);
        if(root.right != null)
            SolvRecur(root.right, root, covered, counter);
        
        
        if( !covered.contains(root.left) || !covered.contains(root.right) || (parent==null && !covered.contains(root)) ){
            counter.counter++;
            covered.add(root);covered.add(parent);covered.add(root.left);covered.add(root.right);
        }
    }
}
