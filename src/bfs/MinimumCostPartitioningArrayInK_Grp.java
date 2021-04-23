/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;

/**
 *
 * @author misaac
 */
public class MinimumCostPartitioningArrayInK_Grp {
    
    int[] c_arr;int K;
    public MinimumCostPartitioningArrayInK_Grp(int[] arr,int K){
        this.c_arr = arr;this.K = K;
        
        System.out.println("cost : "+ SolveRecur(0, this.c_arr.length-1, K, new HashMap<String,Integer>()) );
    }
    
    public int FrequentNum(int from,int to){
        
        class Node{
            int num;int frequency;
            public Node(int num,int freq){
                this.num = num;this.frequency = freq;
            }
        }
        Node max_node = new Node(-1,Integer.MIN_VALUE);
        HashMap map = new HashMap<Integer,Integer>();
        
        for(int i=from;i<=to;i++){
            if(map.containsKey(this.c_arr[i])) map.put( this.c_arr[i] ,Integer.parseInt( map.get(this.c_arr[i]).toString() )+1);
            else map.put( this.c_arr[i] , 1);
            
            if( Integer.parseInt(map.get(this.c_arr[i]).toString()) > max_node.frequency){
                max_node = new Node(this.c_arr[i], Integer.parseInt(map.get(this.c_arr[i]).toString()) );
            }
        }
        
        return max_node.num;
    }
    
    public int cost_of_array(int from ,int to,int max_freq_num){
        int cost = 0;
        for(int i=from;i<=to;i++){
            if(this.c_arr[i] != max_freq_num){
                cost++;
            }
        }
        return cost;
    }
    
    public Integer get_prev_calcu_cost(HashMap<String,Integer> memo,int from,int to,int K){
        if(memo.containsKey( String.valueOf(from)+","+String.valueOf(to)+","+String.valueOf(K) ) ){
            return memo.get( String.valueOf(from)+","+String.valueOf(to)+","+String.valueOf(K) );
        }else return null;
    }
    
    public void add_cost(HashMap<String,Integer> memo,int from,int to,int K,int cost){
        memo.put(String.valueOf(from)+","+String.valueOf(to)+","+String.valueOf(K) , cost );
    }
    
    public int SolveRecur(int from, int to , int k, HashMap<String,Integer> memo){
        
        if(from == to){
            return 0;
        }
//        }else if(to < from){
//            return Integer.MAX_VALUE;
//        }
        
        Integer x;
        if( (x = get_prev_calcu_cost(memo,from,to,k)) != null){
            return x;
        }
        
        if(k == 1){
            int max_freq_num = FrequentNum(from, to);
            int cost = cost_of_array(from, to, max_freq_num);
            return cost;
        }
        
        int min_cost = Integer.MAX_VALUE;
        for(int i=to;i>from;i--){
            int right_cost = SolveRecur(i, to, 1, memo);
            add_cost(memo, i, to, 1, right_cost);
            int left_cost = SolveRecur(from, i-1, k-1, memo);
            add_cost(memo, from, i-1, k-1, left_cost);
            
            if(left_cost+right_cost < min_cost){
                min_cost = left_cost + right_cost;
            }
            
            if(min_cost < -20000){
                int y = 7;
            }
        }
        add_cost(memo, from, to, k, min_cost);
        
        return min_cost;
    }
}
