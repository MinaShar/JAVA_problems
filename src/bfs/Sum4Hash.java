/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class Sum4Hash {
    
    public Sum4Hash(ArrayList<Integer> A , int B){
        ArrayList<ArrayList<Integer>> s = Solve(A, B);
        System.out.println(s);
    }
    
    class Pair<T,Y>{
        public T x;
        public Y y;
        public Pair(T t , Y y){
            this.x = t;
            this.y = y;
        }
    }
    
    class HashCell<X,Y>{
        int key;
        ArrayList<Pair<X,Y>> values; 
        
        public HashCell(int key,Pair p){
            this.key = key;
            this.values = new ArrayList<>();
            this.values.add(p);
        }
    }
    
    class MyHashMap<X,Y>{
        public ArrayList<HashCell<X,Y>> AllCells;
        public MyHashMap(){
            AllCells = new ArrayList<>();
        }
        public void Insert(int Key,Pair p){
            for(int i=0;i<AllCells.size();i++){
                if(AllCells.get(i).key == Key){
                    AllCells.get(i).values.add(p);
                    return;
                }
            }
            AllCells.add(new HashCell(Key,p));
        }
        public ArrayList<Pair<X,Y>> get(int key){
            for(int i=0;i<this.AllCells.size();i++){
                if(this.AllCells.get(i).key == key){
                    return this.AllCells.get(i).values;
                }
            }
            return null;
        }
        public boolean containsKey(int key){
            for(int i=0;i<this.AllCells.size();i++){
                if(this.AllCells.get(i).key == key){
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean InAscOrder(ArrayList<Integer> t){
        for(int i=0;i<t.size();i++){
            int targ = t.get(i);
            for(int j=i+1;j<t.size();j++){
                if(t.get(j) < targ){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean IsExistBefore(ArrayList<ArrayList<Integer>> solution, ArrayList<Integer> subset){
        for(int i=0;i<solution.size();i++){
            if(subset.get(0) == solution.get(i).get(0) && 
                    subset.get(1) == solution.get(i).get(1) && 
                    subset.get(2) == solution.get(i).get(2) && 
                    subset.get(3) == solution.get(i).get(3) ){
                return true;
            }
        }
        return false;
    }
    
    public void InsertLogarith(ArrayList<ArrayList<Integer>> solution,ArrayList<Integer> subset){
        
        int curr_index;
        for(int i=0;i<solution.size();i++){
            curr_index = 0;
            for(int j=0;j<solution.get(i).size();j++){
                if( solution.get(i).get(curr_index) == subset.get(curr_index) ){
                    curr_index++;
                }else if(solution.get(i).get(curr_index) > subset.get(curr_index)){
                    solution.add(i, subset);
                    return;
                }else if(solution.get(i).get(curr_index) < subset.get(curr_index)){
                    break;
                }
            }
        }
        solution.add(subset);
    }
    
    public ArrayList<ArrayList<Integer>> Solve(ArrayList<Integer> A, int B){
        
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        
        Collections.sort(A);
        
        MyHashMap<Integer,Integer> map = new MyHashMap<Integer,Integer>();
        for(int i=0;i<A.size();i++){
            for(int j=i+1;j<A.size();j++){
                map.Insert(A.get(i)+A.get(j), new Pair<Integer,Integer>(i,j));
            }
        }
        
        for(int i=0;i<map.AllCells.size();i++){
            int curr_key = map.AllCells.get(i).key;
            ArrayList<Pair<Integer,Integer>> curr_pairs = map.get(curr_key);
            int complementary = B - curr_key;
            
            if(map.containsKey(complementary)){
                ArrayList<Pair<Integer,Integer>> all_complementary_pairs = map.get(complementary);
                
                for(int a=0;a<curr_pairs.size();a++){
                    for(int b=0;b<all_complementary_pairs.size();b++){
                        if(curr_pairs.get(a).x != all_complementary_pairs.get(b).x && 
                                curr_pairs.get(a).x != all_complementary_pairs.get(b).y &&
                                curr_pairs.get(a).y != all_complementary_pairs.get(b).x &&
                                curr_pairs.get(a).y != all_complementary_pairs.get(b).y ){
                            ArrayList<Integer> subset = new ArrayList<>();
                            subset.add(A.get( curr_pairs.get(a).x) );subset.add( A.get( curr_pairs.get(a).y) );
                            subset.add(A.get( all_complementary_pairs.get(b).x) );
                            subset.add(A.get( all_complementary_pairs.get(b).y) );
                            Collections.sort(subset);
                            
                            if( !IsExistBefore(solution, subset)){
                                InsertLogarith(solution, subset);
                            }
                            
                        }
                    }
                }
            }
        }
        
        
        /////// TO BE SUBSTITUTED  ////////
//        for(int i=0;i<A.size();i++){
//            for(int j=i+1;j<A.size();j++){
//                int sum = A.get(i) + A.get(j);
//                
//                if(map.containsKey(B - sum)){
//                    ArrayList<Pair<Integer,Integer>> p = map.get(B - sum);
//                    
//                    for(int k=0;k<p.size();k++){
//                        ArrayList<Integer> subset = new ArrayList<>();
//                        if(i != p.get(k).x && i != p.get(k).y && j != p.get(k).x && j != p.get(k).y){
//                            subset.add(A.get(i));subset.add(A.get(j) );subset.add(A.get( p.get(k).x) );subset.add(A.get( p.get(k).y) );
//                            Collections.sort(subset);
//
//                            if(InAscOrder(subset) && !IsExistBefore(solution, subset)){
//                                InsertLogarith(solution, subset);
//                            }
//                        }
//                        
//                    }
//                }
//            }
//        }
        ////////////////////////////////////
        return solution;
    }
    
}
