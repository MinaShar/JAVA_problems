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
public class ShortestPathVisitingAllNodes_Array {

    public ShortestPathVisitingAllNodes_Array() {
        int[][] arr = new int[][]{{1,2,3},{0},{0},{0}};
        System.out.println( "result : " + shortestPathLength(arr) );
    }
    
    
    class Node{
        public int covered,current,cost;
        public Node(int covered,int current){
            this.covered = covered;
            this.current = current;
            this.cost = 1;
        }
        public Node(int covered,int current,int cost){
            this.covered = covered;
            this.current = current;
            this.cost = cost+1;
        }
    }
    public int shortestPathLength(int[][] graph) {
        
        ArrayList<Node> queue = new ArrayList<Node>();
        for(int i=0;i<graph.length;i++){
            queue.add(new Node( 1<<i , i ));
        }
        
        while( queue.size() > 0 ){
            Node n = queue.remove(0);
            
            for(int j=0;j<graph[n.current].length;j++){
                Node another = new Node( n.covered | (1<<graph[n.current][j]) , graph[n.current][j] , n.cost );
                if(another.covered == ((1<<graph.length)-1) )
                    return another.cost-1;
                queue.add(another);
            }
        }
        return -1;
    }
}
