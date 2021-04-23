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
public class VerticalOrderTraversal2 {
    
    public VerticalOrderTraversal2(TreeNode root){
        ArrayList<ArrayList<Integer>> test = MainFunc(root);
        
        for(int i=0;i<test.size();i++){
            ArrayList<Integer> x = test.get(i);
            if(test.get(i).size()==0){
                test.remove(i);
                i--;
                continue;
            }
            boolean is_empty = true;
            for(int j=0;j<x.size();j++){
                if(x.get(j) >= 1 ){
                    is_empty = false;
                    break;
                }
            }
            if(is_empty == true){
                test.remove(i);
                i--;
            }
        }
        
        System.out.println("Now printing ------------->");
        for(int i=0;i<test.size();i++){
            ArrayList<Integer> x = test.get(i);
            for(int j=0;j<x.size();j++){
                System.out.print(x.get(j) + " , ");
            }
            System.out.println();
        }
        System.out.println("Finished printing ------------->");
    }
    
    public ArrayList<ArrayList<Integer>> MainFunc(TreeNode root){
        
        ArrayList<TreeNode> Queue = new ArrayList<>();
        Queue.add(root);
        
        ////////////////////////////////////////////////////
        int NumberOfNodesInHoriontal = 1;
        ////////////////////////////////////////////////////
        
        ArrayList<TreeNode> NodesInHoriontal = new ArrayList<>();
        
        ArrayList<ArrayList<Integer>> FinalResult = new ArrayList<>();
        FinalResult.add(new ArrayList<>());
        
        ArrayList<Integer> NodesDistributionInHorizontal = new ArrayList<>();
        
        while( IsQueueFilled(Queue)){
            
            for(int j=0;j<NumberOfNodesInHoriontal;j++){
                NodesInHoriontal.add(Queue.remove(0));
            }
            
            NodesDistributionInHorizontal = VerticalOrderTraversal2.DecideNodesDevisionInVerticalLines(VerticalOrderTraversal2.CalculateHorizontal(NumberOfNodesInHoriontal));
            
            int NodeInWhichVericalLine = 0;
            for(int i=0;i<NodesInHoriontal.size();i++){
                
                if(NodesInHoriontal.get(i) != null){ 
                    if(NodesInHoriontal.get(i).val != -1){
                        FinalResult.get(NodeInWhichVericalLine).add(NodesInHoriontal.get(i).val);
                    }
                }
                
                
                if(NodesInHoriontal.get(i) != null){
                    if(NodesInHoriontal.get(i).left != null){
                        Queue.add(NodesInHoriontal.get(i).left);
                    }else{
                        Queue.add( Queue.size() , null);
                    }
                    
                    if(NodesInHoriontal.get(i).right != null){
                        Queue.add(NodesInHoriontal.get(i).right);
                    }else{
                        Queue.add( Queue.size() , null);
                    }
                }else{
                    Queue.add(Queue.size() ,null);Queue.add(Queue.size() ,null);
                }
                
                
                NodeInWhichVericalLine=VerticalOrderTraversal2.DecideCurrentNodeInWhichVerticalLine(i,NodesDistributionInHorizontal);
                
            }
            
            FinalResult.add(0, new ArrayList<Integer>());
            FinalResult.add(new ArrayList<Integer>());
            NodesInHoriontal = new ArrayList<>();
            
            
            NumberOfNodesInHoriontal = NumberOfNodesInHoriontal * 2;
            
        }
        
        return FinalResult;
    }
    
    public static int DecideCurrentNodeInWhichVerticalLine(int NodeIndex , ArrayList<Integer> NodesDistribution){
        NodeIndex++;
        int TotalNumberOfNodes = 0;
        
        for(int i=0;i<NodesDistribution.size();i++){
            TotalNumberOfNodes += NodesDistribution.get(i);
        }
        
        ArrayList<ArrayList<Integer>> Distribution = new ArrayList<>();
        
        for(int i=0;i<NodesDistribution.size();i++){
            Distribution.add(new ArrayList<>());
        }
        
        int CurrentNode=0;
        for(int i=0;i<NodesDistribution.size()-1;i++){
            while(true){
                Distribution.get(i).add(CurrentNode++);
                Distribution.get(i+1).add(CurrentNode++);
                if(Distribution.get(i).size() == NodesDistribution.get(i)){
                    break;
                }
            }
        }
        
        for(int i=0;i<Distribution.size();i++){
            for(int j=0;j<Distribution.get(i).size();j++){
                if(Distribution.get(i).get(j) == NodeIndex){
                    return i*2;
                }
            }
        }
        
        return -1;
    }
    
    public static ArrayList<Integer> DecideNodesDevisionInVerticalLines(int HriontalLevel){
        int VericalsInHorizzontal = HriontalLevel + 1;
        
        ArrayList<Integer> NumberOfNodesInEachVertical = new ArrayList<>();
        
        for(int i=0;i<VericalsInHorizzontal;i++){
            NumberOfNodesInEachVertical.add( CalculatePascalTriangle(HriontalLevel , i) );
        }
        
        return NumberOfNodesInEachVertical;
    }
    
    public static int CalculatePascalTriangle(int Horizontal , int Verical){
        if(Horizontal == Verical ||  Verical == 0){
            return 1;
        }else{
            return CalculatePascalTriangle(Horizontal-1 , Verical ) + CalculatePascalTriangle(Horizontal-1 , Verical-1);
        }
    }
    
    public static int CalculateHorizontal(int NumberOfNodesInHorizontal){
        return (int) (Math.log(NumberOfNodesInHorizontal) / Math.log(2));
    }
    
    
    public boolean IsQueueFilled(ArrayList<TreeNode> Queue){
        for(int i=0;i<Queue.size();i++){
            if(Queue.get(i) != null){
                return true;
            }
        }
        return false;
    }
    
}
