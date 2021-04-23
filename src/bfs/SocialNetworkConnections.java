/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author misaac
 */
public class SocialNetworkConnections {
    
    class my_comp implements Comparator<MyPair<Integer>>{
        
        @Override
        public int compare(MyPair<Integer> o1, MyPair<Integer> o2) {
            return (o1.ConnectedTo - o2.ConnectedTo);
        }
        
    }
    
    class MyPair<T>{
        T ConnectedTo;T Link;
        public MyPair(T conn,T link){
            this.ConnectedTo = conn;this.Link = link;
        }
    }
    
    class Node{
        int UserID;
        ArrayList<Node> ConnectedUsers;
        public Node(int UserID){
            this.UserID = UserID;
            this.ConnectedUsers = new ArrayList<>();
        }
    }
    Node[] Graph;
    public SocialNetworkConnections(int[] array){
        Graph = new Node[array.length+1];
        
        for(int i=0;i<Graph.length;i++){
            Graph[i] = new Node(i+1);
        }
        
        for(int i=0;i<array.length;i++){
            Graph[i+1].ConnectedUsers.add( Graph[ array[i]-1 ] );
        }
        
        SolveGraph();
    }
    
    public void SolveGraph(){
        ArrayList<Pair<Integer,PriorityQueue<MyPair<Integer>>>> FinalArray = new ArrayList <>();
        for(int i=0;i<this.Graph.length;i++){
//            System.out.println("user "+this.Graph[i].UserID+" connected to :");
            
            PriorityQueue<MyPair<Integer>> pairs = new PriorityQueue<MyPair<Integer>>(new my_comp());
            
            int link = 1;
            for(int j=0;j<this.Graph[i].ConnectedUsers.size();j++){
                pairs.add(new MyPair<Integer>(this.Graph[i].ConnectedUsers.get(j).UserID,link));
//                System.out.print( this.Graph[i].ConnectedUsers.get(j).UserID + " with "+ link + " link , " );
                SolveDFS(this.Graph[i].ConnectedUsers.get(j).UserID, link+1,pairs);
            }
            FinalArray.add(new Pair(this.Graph[i].UserID,pairs));
//            System.out.println();
        }
        
        print(FinalArray);
    }
    
    public void print(ArrayList<Pair<Integer,PriorityQueue<MyPair<Integer>>>> FinalArray){
        
//        for(int i=0;i<FinalArray.size();i++){
//            System.out.println("user "+FinalArray.get(i).getKey()+" connected to :");
//            int s = FinalArray.get(i).getValue().size();
//            while(  FinalArray.get(i).getValue().peek() != null ){
//                MyPair<Integer> rr = FinalArray.get(i).getValue().poll();
//                System.out.println( rr.ConnectedTo +" with "+rr.Link+" link(s)" );
//            }
//        }
        
        for(int i=0;i<FinalArray.size();i++){
            int s = FinalArray.get(i).getValue().size();
            while(  FinalArray.get(i).getValue().peek() != null ){
                MyPair<Integer> rr = FinalArray.get(i).getValue().poll();
                System.out.print( FinalArray.get(i).getKey() +" "+ rr.ConnectedTo +" "+rr.Link+" " );
            }
        }
    }
    
    public void SolveDFS(int UserID , int Link,PriorityQueue<MyPair<Integer>> pairs){
        
        for(int i=0;i<this.Graph[UserID-1].ConnectedUsers.size();i++){
//            System.out.print( this.Graph[UserID-1].ConnectedUsers.get(i).UserID + " with "+ Link + " link , " );
            pairs.add(new MyPair<Integer>( this.Graph[UserID-1].ConnectedUsers.get(i).UserID ,Link));
            SolveDFS(this.Graph[UserID-1].ConnectedUsers.get(i).UserID, Link+1,pairs);
        }
    }
}
