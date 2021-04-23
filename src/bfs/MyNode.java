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
public class MyNode {
    
    public int value;
    public MyNode left;
    public MyNode right;
    
    public MyNode(int x){
        this.value = x;
        this.left = null;
        this.right = null;
    }
    
    public static MyNode CreateBST(MyNode root , int valueToAdd){
        
        if(root == null){
            return new MyNode(valueToAdd);
        }
        
        if(valueToAdd < root.value ){
            
            root.left = CreateBST(root.left, valueToAdd);
            
        }else if(valueToAdd > root.value){
           
            root.right = CreateBST(root.right, valueToAdd);
           
        }
        return root;
    }
    
    public static MyNode MyMain(){
        MyNode root = null;
        root = CreateBST(root ,85);
        root = CreateBST(root, 1);
        root = CreateBST(root, 100);
        root = CreateBST(root, 22);
        root = CreateBST(root, 88);  
        root = CreateBST(root, 103);
        root = CreateBST(root, 16);
        root = CreateBST(root, 73);
        root = CreateBST(root, 94);
        root = CreateBST(root, 106);
        root = CreateBST(root, 7);
        root = CreateBST(root, 19);
        root = CreateBST(root, 49);
        root = CreateBST(root, 76);
        root = CreateBST(root, 91);
        root = CreateBST(root, 97);
        root = CreateBST(root, 109);
        root = CreateBST(root, 4);
        root = CreateBST(root, 13);
        root = CreateBST(root, 37);
        root = CreateBST(root, 61);
        root = CreateBST(root, 82);
        root = CreateBST(root, 10);
        root = CreateBST(root, 34);
        root = CreateBST(root, 46);
        root = CreateBST(root, 58);
        root = CreateBST(root, 64);
        root = CreateBST(root, 79);
        root = CreateBST(root, 28);
        root = CreateBST(root, 40);
        root = CreateBST(root, 52);
        root = CreateBST(root, 67);
        root = CreateBST(root, 25);
        root = CreateBST(root, 31);
        root = CreateBST(root, 43);
        root = CreateBST(root, 55);
        root = CreateBST(root, 70);
        return root;
        
//        MyNode.PrintBF(f_st);
    }
    
    public static void PrintBF(MyNode A){
        String R = "the List = ";
        ArrayList<MyNode> _list = new ArrayList<>();
        
        _list.add(A);
        
        MyNode _currentNode;
        while(_list.isEmpty() ==false){
            _currentNode = _list.get(0);
            reFormArray(_list);
            
            R = _currentNode ==null ? R + " null , " : R + _currentNode.value + " , "; 
            if(_currentNode != null){
                _list.add(_currentNode.left);
                _list.add(_currentNode.right);
            }
        }
        System.out.println(R);
    }
    
    public static void reFormArray(ArrayList T){
        for(int i=1;i<T.size();i++){
            T.set(i-1, T.get(i));
        }
        T.remove(T.size()-1);
    }
    
}
