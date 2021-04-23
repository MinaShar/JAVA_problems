/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;
import java.util.*;

/**
 *
 * @author misaac
 */
public class CriticalConn {
    
    public CriticalConn(){
        
        List<List<Integer>> t = new ArrayList<List<Integer>>();
        
        ArrayList<Integer> a = new ArrayList<>();
        a.add(0);a.add(1);
        t.add(a);
        
        ArrayList<Integer> b = new ArrayList<>();
        b.add(1);b.add(2);
        t.add(b);
        
        ArrayList<Integer> c = new ArrayList<>();
        c.add(2);c.add(0);
        t.add(c);
        
        ArrayList<Integer> d = new ArrayList<>();
        d.add(1);d.add(3);
        t.add(d);
        
        ArrayList<Integer> e = new ArrayList<>();
        e.add(3);e.add(4);
        t.add(e);
        
        ArrayList<Integer> f = new ArrayList<>();
        f.add(4);f.add(5);
        t.add(f);
        
        ArrayList<Integer> g = new ArrayList<>();
        g.add(5);g.add(3);
        t.add(g);
        
        List<List<Integer>> resy = criticalConnections(6,t);
        
        System.out.println("result is :");
        for(int i=0;i<resy.size();i++){
            System.out.println(resy.get(i).get(0)+","+resy.get(i).get(1));
        }
    }
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<List<Integer>> cititcal = new ArrayList<List<Integer>>();
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        for(int i=0;i<connections.size();i++){
            if(!nodes.contains(connections.get(i).get(0))){
                nodes.add( connections.get(i).get(0) );
            }
            if(!nodes.contains(connections.get(i).get(1))){
                nodes.add( connections.get(i).get(1) );
            }
        }
        
        for(int i=0;i<connections.size();i++){
            ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>();
            for(int j=0;j<connections.size();j++){
                if(i==j){
                    continue;
                }
                graph.add(connections.get(j));
            }
            if(check_valid_graph(connections.get(i).get(0),connections.get(i).get(1),graph,nodes) == false){
                cititcal.add(connections.get(i));
            }
        }
        return cititcal;
    }
    
    public boolean check_valid_graph(int from ,int to,ArrayList<List<Integer>> graph,ArrayList<Integer> nodes){
        int total_number_nodes= nodes.size();
        ArrayList<Integer> curr_node_list = new ArrayList<Integer>();
        curr_node_list.add( from );
        
        while(curr_node_list.size() > 0 && curr_node_list.get(curr_node_list.size()-1) != to){
            int curr_node = curr_node_list.get(curr_node_list.size()-1);
            curr_node_list.remove(curr_node_list.size()-1);
            for(int i=0;i<graph.size();i++){           
                if( graph.get(i).get(0) == curr_node ){
                    curr_node_list.add( graph.get(i).get(1) );
                    graph.remove(i);
                    i--;
                }else if(graph.get(i).get(1) == curr_node){
                    curr_node_list.add( graph.get(i).get(0) );
                    graph.remove(i);
                    i--;
                }
            }
        }
        if(curr_node_list.size() > 0 && curr_node_list.get(curr_node_list.size()-1) == to){
            return true;
        }else return false;
    }
    
}
