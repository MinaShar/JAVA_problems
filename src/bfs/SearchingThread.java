/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

/**
 *
 * @author misaac
 */
public class SearchingThread extends Thread{
    
    int start , end, required_to_find;String name;int[] arr;
    public SearchingThread(int[] arr , String name , int start,int end,int required_to_find){
        this.arr = arr;
        System.out.println("Thread "+name+" constructed from : "+start+" to : "+end);
        this.name = name;
        this.start = start;this.end = end;
        this.required_to_find = required_to_find;
    }
    
    @Override
    public void run(){
        find_element();
    }
    
    public void find_element(){
        
        for(int i=this.start;i<= this.end;i++){
            if(arr[i] == required_to_find){
                System.out.println("Thread "+this.name+" found the element at index "+i );
                System.out.println("Thread "+this.name+" now exiting successfully ...");
                return;
            }
        }
        System.out.println("Thread "+this.name+" exiting and didn't find any thing ...");
    }
}
