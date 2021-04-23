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
public class ConvertLinkedListToBST {
    
    MyNode root;
    
    public ConvertLinkedListToBST(ArrayList<Integer> _list){
        
        root = convert(_list,0,_list.size()-1);
        
        MyNode.PrintBF(root);
        
    }
    
    public MyNode convert(ArrayList<Integer> _list,int from_index,int to_index){
        
        if(from_index == to_index){
            System.out.println("basecase ith node = "+ _list.get( to_index ) );
            return new MyNode( _list.get( to_index ) );
        }else if(to_index < from_index){
            return null;
        }
        
        int indexElem = GetMiddleElement(from_index, to_index);
        MyNode newNode = new MyNode( _list.get(indexElem) );
        System.out.println("new node created = "+newNode.value);
        
        newNode.left = convert(_list, from_index, indexElem-1);
        newNode.right = convert(_list, indexElem + 1, to_index );
        
        return newNode;
    }
    
    public int GetMiddleElement(int from_index,int to_index){
        System.out.println("from = "+from_index+" & to = "+to_index +" && the devision result = "+ Math.ceil( ( ( to_index - from_index ) / 2.0)  ));
        return (int)Math.ceil(  (( to_index - from_index ) / 2.0 ) ) + from_index ;
    }
    
}