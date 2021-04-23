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
public class JumpGameV {
    
    public JumpGameV(){
        int[] arr = new int[]{6,4,14,6,8,13,9,7,10,6,12};
        int d = 2;
        System.out.println( "result is : "+ maxJumps(arr, d) );
    }
    
    class Node{
        int visited,current,visited_count;
        public Node(int visited,int current){
            this.visited = visited;
            this.current = current;
            this.visited_count = 1;
        }
        
        public Node(int visited,int current,int visited_count){
            this.visited = visited;
            this.current = current;
            this.visited_count = visited_count+1;
        }
    }
    public int maxJumps(int[] arr, int d) {
        
        ArrayList<Node> queue = new ArrayList<Node>(); 
        for(int s=0;s<arr.length;s++){
            queue.add( new Node( 1<<s , s ) );
        }
        
        int max_jumbs = 1;
        while(queue.size() > 0){
            Node n = queue.remove(0);
            
            int max_length = arr[n.current];
            boolean can = true; 
            for(int i=n.current-d;i<=n.current+d;i++){
                if(i<0 || i>=arr.length)
                    continue;
                if(i==n.current)
                    continue;
                if(i < n.current){
                    can = true;
                    for(int j=i;j<n.current;j++){
                        if(arr[j] >= max_length){
                            can = false;
                            break;
                        }
                    }
                    if(can == true){
                        Node another = new Node(n.visited | (1<<i),i,n.visited_count);
                        if(another.visited_count > max_jumbs){
                            max_jumbs = another.visited_count;
                        }
                        queue.add(another);
                    }
                }else if(i > n.current){
                    can = true;
                    for(int j=n.current+1;j<=i;j++){
                        if(arr[j] >= max_length){
                            can = false;
                            break;
                        }
                    }
                    if(can == true){
                        Node another = new Node(n.visited | (1<<i),i,n.visited_count);
                        if(another.visited_count > max_jumbs){
                            max_jumbs = another.visited_count;
                        }
                        queue.add(another);
                    }
                }           
            }
        }
        return max_jumbs;
    }
}
