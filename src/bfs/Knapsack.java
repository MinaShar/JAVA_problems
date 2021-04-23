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


public class Knapsack {
    
    class bag{
        ArrayList<MyPair<Double,Double>> m_bag;
        double curr_bag_weight; 
        double curr_bag_profit;
        double max_possible_weight;
        
        public bag(bag g){
            m_bag = new ArrayList<>( g.m_bag );
            curr_bag_weight = g.curr_bag_weight;
            curr_bag_profit = g.curr_bag_profit;
            max_possible_weight = g.max_possible_weight;
        }
        
        public bag(Double max_weight){
            m_bag = new ArrayList<>();
            curr_bag_weight = 0;
            curr_bag_profit = 0;
            this.max_possible_weight = max_weight;
        }
        
        public void add_item(MyPair p){
            m_bag.add(p);
            curr_bag_weight += (Double)p.getMyKey();
            curr_bag_profit += (Double)p.getValue();
        }
        
        public void remove_item(int index){
            MyPair<Double,Double> p = m_bag.remove(index);
            curr_bag_weight -= (Double)p.getMyKey();
            curr_bag_profit -= (Double)p.getValue();
        }
        
        public boolean able_to_take_more(MyPair<Double,Double> p){
            if(max_possible_weight >= curr_bag_weight + (Double)p.getMyKey() ){
                return true;
            }else{
                return false;
            }
        }
        
        @Override
        public String toString(){
            
            String s = "";
            for(int i=0;i<m_bag.size();i++){
                s += m_bag.get(i).getMyKey() +" , "+m_bag.get(i).getValue() + "\n" ;
            }
            return s;
        }
    }
    
    bag best_bag;
    double best_profit;
    
    public Knapsack(ArrayList<MyPair<Double,Double>> p){
        best_bag = null;
        best_profit = 0;
        
        BackTrack(p, null, 0);
        
        System.out.println("best bag have profit = "+best_bag.curr_bag_profit);
        System.out.println(best_bag);
    }
    
    public void BackTrack(ArrayList<MyPair<Double,Double>> p,bag b,int index){
        
        if(b == null){
            b = new bag(10.0);
        }
        
        if(b.curr_bag_profit > best_profit){
            best_bag = new bag(b);
            best_profit = best_bag.curr_bag_profit;
        }
        
        for(int i=index;i<p.size();i++){
            
            if(b.able_to_take_more(p.get(i))){
                b.add_item(p.get(i));
            
                BackTrack(p, b, i+1);

                b.remove_item(b.m_bag.size()-1);
            }
        }
    }
    
}
