/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author misaac
 */
public class PathExistInMaze {
    
    class my_com implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            return (int)( ((TestCase)o1).heuristics - ((TestCase)o2).heuristics);
        }
    }
    
    class TestCase{
        ArrayList<Pair<Integer,Integer>> path;
        double heuristics;
        
        public TestCase(TestCase parent){
            path = new ArrayList<>(parent.path);
            heuristics = -1;
        }
        
        public TestCase(Pair Start){
            path = new ArrayList<>();
            path.add(Start);
            heuristics = -1;
        }
        
        public void AddStep(Pair<Integer,Integer> step,Pair<Integer,Integer> destination){
            this.path.add(step);
            this.heuristics = Math.sqrt( Math.pow( Math.abs(destination.getKey()-step.getKey() ),2) + Math.pow( Math.abs(destination.getValue()-step.getValue()),2) );
        }
        
        public boolean ExistBefore(int x,int y){
            for(int i=0;i<this.path.size();i++){
                if(this.path.get(i).getKey()==x && this.path.get(i).getValue()==y){
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<path.size();i++){
                sb.append(path.get(i).getKey().toString()+","+path.get(i).getValue().toString()+"\n");
            }
            return sb.toString();
        }
    }
    ArrayList<Pair<Integer,Integer>> UDLR;
        
    public PathExistInMaze(){
        
        UDLR = new ArrayList<>();
        UDLR.add(new Pair(-1,0));UDLR.add(new Pair(1,0));UDLR.add(new Pair(0,-1));UDLR.add(new Pair(0,1));
        try{
            getInput();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public TestCase Solve(int[][] maze){
        Pair<Integer,Integer> source = null;
        Pair<Integer,Integer> destination = null;
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                if(maze[i][j] == 1) {
                    source = new Pair<>(i,j);
                    break;
                }
            }
            if(source != null)break;
        }
        
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                if(maze[i][j] == 2) {
                    destination = new Pair<>(i,j);
                    break;
                }
            }
            if(destination != null)break;
        }
        
        return SolveHerustics(maze, source, destination, new PriorityQueue<>(new my_com()));
    }
    
    public TestCase SolveHerustics(int[][] maze,Pair from,Pair to,PriorityQueue<TestCase> TestCases){
        
        TestCases.add(new TestCase(from));
        
        while(TestCases.peek() !=  null){
            TestCase current = TestCases.poll();
            
            Pair<Integer,Integer> LastStep = current.path.get(current.path.size()-1);
            for(int i=0;i<UDLR.size();i++){
                if( LastStep.getKey()+UDLR.get(i).getKey() >= 0 && LastStep.getKey()+UDLR.get(i).getKey() < maze.length
                        && LastStep.getValue()+UDLR.get(i).getValue() >= 0 && LastStep.getValue()+UDLR.get(i).getValue() < maze.length
                        && !current.ExistBefore(LastStep.getKey()+UDLR.get(i).getKey(),LastStep.getValue()+UDLR.get(i).getValue())
                        && ( maze[LastStep.getKey()+UDLR.get(i).getKey()][LastStep.getValue()+UDLR.get(i).getValue()] == 3
                        || maze[LastStep.getKey()+UDLR.get(i).getKey()][LastStep.getValue()+UDLR.get(i).getValue()] == 2 ) ){
                    TestCase T = new TestCase(current);
                    T.AddStep(new Pair<Integer,Integer>(LastStep.getKey()+UDLR.get(i).getKey()
                            ,LastStep.getValue()+UDLR.get(i).getValue()), to);
                    
                    if(T.path.get(T.path.size()-1).getKey()==to.getKey() &&
                            T.path.get(T.path.size()-1).getValue()==to.getValue()){
                        return T;
                    }                  
                    TestCases.add(T);
                }
            }
        }
        return null;
    }
    
    public void printarray(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+"|");
            }
            System.out.println();
        }
    }
    
    public void getInput() throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TestCases = Integer.parseInt(br.readLine());
        
        while(TestCases-- > 0){
            int MatrixSideLength = Integer.parseInt(br.readLine());
            int[] _arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] final_array = new int[MatrixSideLength][MatrixSideLength];
            int _arr_index = 0;
            for(int i=0;i<final_array.length;i++){
                for(int j=0;j<final_array[i].length;j++){
                    final_array[i][j] = _arr[_arr_index++];
                }
            }
            TestCase T = null;
            if( ( T = Solve(final_array) ) != null){
                System.out.println("Solution found :");
                printarray(final_array);
                System.out.println(T.toString() );
            }else{
                System.out.println("No solution exists ...");
            }
        }
    }
    
}
