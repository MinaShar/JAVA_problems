/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bfs;
import bfs.SocialNetworkConnections.my_comp;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.geometry.VerticalDirection;
import javafx.util.Pair;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author misaac
 */
public class BFS {
    
    public static void getCost(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
    // Print your answer within the function and return nothing
        // System.out.println("this executing java ... ");
        HashMap<Integer,ArrayList<Pair<Integer,Integer>>> _list
            = new HashMap<Integer,ArrayList<Pair<Integer,Integer>>>();

        for(int i=0;i<gFrom.size();i++){
            ArrayList<Pair<Integer,Integer>> pp =_list.getOrDefault( gFrom.get(i) , new ArrayList<Pair<Integer,Integer>>() );

            pp.add( new Pair<Integer,Integer>(gTo.get(i),gWeight.get(i)) );
            _list.put( gFrom.get(i) , pp );


             ArrayList<Pair<Integer,Integer>> pp2 =_list.getOrDefault( gTo.get(i) , new ArrayList<Pair<Integer,Integer>>() );

            pp2.add( new Pair<Integer,Integer>(gFrom.get(i),gWeight.get(i)) );
            _list.put( gTo.get(i) , pp2 );

        }

        int left = 1 , right = 10000002;

        while( left <= right ){

            int mid = (left + right) / 2;
            System.out.println("now using cost : "+ mid);

            HashSet<Integer> visited = new HashSet<Integer>();
            visited.add( 1 );

            Queue<Integer> _queue = new LinkedList<Integer>();
            _queue.add( 1 );

            boolean flag_found = false;

            while( _queue.size() > 0 ){
                
                flag_found = false;

                int curr_node = _queue.poll();
                System.out.println("Standing at node:"+curr_node);
                System.out.println("visited_nodes:" + visited.toString() );
                for(int i=0;i<_list.get(curr_node).size();i++){
                
                    if(!visited.contains( _list.get(curr_node).get(i).getKey()) 
                            && _list.get(curr_node).get(i).getValue() <= mid ){
                        
                        _queue.add( _list.get(curr_node).get(i).getKey() );
                        visited.add( _list.get(curr_node).get(i).getKey() );

                        System.out.println("goin to node : " +_list.get(curr_node).get(i).getKey() );

                        if( _list.get(curr_node).get(i).getKey() == gNodes ){
                            flag_found = true;
                            break;
                        }   
                    }
                }

                if( flag_found ){
                    break;
                }
            }

            if( flag_found == true ){
                // System.out.println("found and decreasing the window");
                right = mid-1;
            }else{
                // System.out.println("not found and increasing the window");
                left = mid+1;
            }

        }

        if( left < 10000000 ){
            System.out.println( left );
        }else{
            System.out.println( "NO PATH EXISTS" );
        }
        
    }
    
    private static int nested_integer_back_track(NestedInteger item,int depth){
        
        if( item.isInteger() ){
            return item.getInteger() * depth;
        }
        else{
            List<NestedInteger> _list = item.getList();
            int solution = 0;
            for(int i=0;i<_list.size();i++){
                solution += nested_integer_back_track(_list.get(i), depth+1);
            }
            return solution;
        }
    }
    
    private static int NestedIntegerSolver(List<NestedInteger> _list){
        
        int solution = 0; 
        for(int i=0;i<_list.size();i++){
            solution += nested_integer_back_track(_list.get(i), 1);
        }
        return solution;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long d1 = new Date().getTime();
        
//        SolutionOnThread test = new SolutionOnThread();
//        Thread th = new Thread( test , "my thread" );
//        
//        try{
//            th.start();
//            
//            while( new Date().getTime() - d1 < 100 ){
//                if( !th.isAlive() ){
//                    break;
//                }
//            }
//
//            if( th.isAlive() ){
//                th.stop();
//                System.out.println("Time Limit Exceeded ...");
//            }
//            else{
//                System.out.println("Program finished successfully ...");
//            }
//        }catch(Exception e){
//            System.out.println("Error occured ...");
//        }
        
        
//        WebChecker[] arr = new WebChecker[1000];
//        for(int i=0;i<50;i++){
//            WebChecker we = new WebChecker();
//            arr[i] = we;
//        }
//        
//        for(int i=0;i<50;i++){
//            try{
//                arr[i].th.join();
//                System.out.println("Thread : "+i+" joined .");
//            }
//            catch(Exception e){
//                System.out.println("thread exception occured : "+e.toString());
//            }
//        }
        
        long d2 = new Date().getTime();
        System.out.println("time taken = " + (d2-d1) );
        
    }
    
}
