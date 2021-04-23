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
public class JudgePoint24 {
    
    enum oprtr{
        plus,minus,mult,division
    }
    
    class Node{
        public ArrayList<Float> arr;
        public Node(ArrayList<Float> arr){
            this.arr = new ArrayList<Float>(arr);
        }
    }
    
    public boolean judgePoint24(int[] nums) {
        ArrayList<Float> arr = new ArrayList<Float>();
        for(int i=0;i<nums.length;i++){
            arr.add(Float.parseFloat( String.valueOf( nums[i] ) ) );
        }
        return Solve(new Node(arr));
    }
    
    public boolean Solve(Node node){
        ArrayList<Node> Queue = new ArrayList<Node>();
        Queue.add(node);
        int counter = 0;int exception_counter = 0;
        while(Queue.size() > 0){
            counter++;
            Node n = Queue.get(Queue.size()-1);
            Queue.remove(Queue.size()-1);
            ArrayList<Float> arr = n.arr;
            
            if(arr.size()==1 && !Float.isNaN( arr.get(0) ) && (int)(arr.get(0)-24) == 0 ){
                return true;
            }
        
            for(int i=0;i<arr.size();i++){
                for(int j=0;j<arr.size();j++){
                    
                    if(i == j){
                        continue;
                    }

                    ArrayList<Float> arr2 = new ArrayList<Float>();
                    for(int k=0;k<arr.size();k++){
                        if(k!=i && k!=j){
                            arr2.add( arr.get(k) );
                        }
                    }

                    for(oprtr a : oprtr.values()){
                        float result;
                        try{
                            result = apply_opr(arr.get(i),arr.get(j),a);
                        }catch(Exception e){
                            exception_counter++;
                            continue;
                        }
                        ArrayList<Float> arr3 = new ArrayList<Float>(arr2);
                        arr3.add(result);
                        Queue.add(new Node(arr3));
                    }

                }
            }
        }
        System.out.println("counter : "+counter+", while exception_counter="+exception_counter);
        return false;        
    }
    
    public float apply_opr(float x,float y,oprtr o) throws Exception{
        switch(o){
            case plus :
                return x+y;
            case minus:
                return x-y;
            case mult:
                return x*y;
            case division:
                return x/y;
        }
        return -1;
    }
    
}
