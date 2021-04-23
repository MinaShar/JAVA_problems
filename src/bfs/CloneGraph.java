/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author misaac
 */
public class CloneGraph {
    
    class Node {
        public int val;
        public List<Node> neighbors;
    }
    
    public CloneGraph(Node node){
        
        Node result = cloneGraph(node);
    }
    
    public Node cloneGraph(Node node) {
        
        if(node == null)
            return null;
        
        HashMap<Node,Node> map = new HashMap<Node,Node>();
        clone_df(node,map);
        
        for (Map.Entry<Node, Node> entry : map.entrySet()){
            Node n = entry.getKey();
            for(int i=0;i<n.neighbors.size();i++){
                entry.getValue().neighbors.add( map.get( n.neighbors.get(i) ) );
            }
        }
        
        return map.get( node );
    }
    /// map : key => main , value => copy
    public void clone_df(Node n,HashMap<Node,Node> map){
        
        Node x= new Node();
        x.val = n.val;
        x.neighbors = new ArrayList<Node>();
        
        map.put(n,x);
        for(int i=0;i<n.neighbors.size();i++){
            if( !map.containsKey( n.neighbors.get(i) ) ){
                clone_df( n.neighbors.get(i) , map );
            }
        }
    }
    
}
