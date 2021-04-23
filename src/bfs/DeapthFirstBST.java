/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author misaac
 */
public class DeapthFirstBST {
    
    ArrayList<Integer> queue;
    int queueIndexer;
    
    public DeapthFirstBST(TreeNode root){
        queue = new ArrayList();
        queueIndexer = 0;
        DFS(root, queue);
    }
    
    private void DFS(TreeNode node , ArrayList container){
        if(node.left != null){
            DFS(node.left , container);
        }
        
        container.add(node.val);
        
        if(node.right != null){
            DFS(node.right , container);
        }
    }
    
    public boolean hasNext(){
        if(queueIndexer < queue.size()){
            return true;
        }else{
            return false;
        }
    }
    
    public int next(){
        queueIndexer++;
        return queue.get(queueIndexer-1);
    }
    
}
