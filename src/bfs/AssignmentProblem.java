/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import sun.swing.BakedArrayList;

/**
 *
 * @author misaac
 */
public class AssignmentProblem {
    
    int[][] grid;
    Integer FinalCost;
    ArrayList<Integer> AllPossibleSol = new ArrayList<Integer>();
        
    public AssignmentProblem(int[][] grid){
        this.grid = grid;
        FinalCost = null;
        Solve();
    }
    
    public int[][] SubtractMinInRows(int[][] arr){
        
        int [][] new_array = new int[arr.length][arr.length];
        for(int row=0;row<arr.length;row++){
            
            int min_int = Integer.MAX_VALUE;
            for(int j=0;j<arr.length;j++){
                if(arr[row][j] < min_int) min_int = arr[row][j];
            }
            
            for(int j=0;j<arr.length;j++){
                new_array[row][j] = arr[row][j] - min_int;
            }
        }
        return new_array;
    }
    
    public int[][] SubtractMinInColumns(int[][] arr){
        
        int [][] new_array = new int[arr.length][arr.length];
        for(int column=0;column<arr.length;column++){
            
            int min_int = Integer.MAX_VALUE;
            for(int row=0;row<arr.length;row++){
                if(arr[row][column] < min_int) min_int = arr[row][column];
            }
            
            for(int row=0;row<arr.length;row++){
                new_array[row][column] = arr[row][column] - min_int;
            }
        }
        return new_array;
    }
    
    public int[][] CopyArray(int[][] source){
        int[][] arr = new int[source.length][source.length];
        
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                arr[i][j] = source[i][j];
            }
        }
        
        return arr;
    }
    
    public Object[] CoverZerosByMinLines(int[][] arr){
        HashMap<String,Integer> map;
        ArrayList<String> MaskedRowsOrColumns = new ArrayList<String>();
        boolean[][] is_covered_array = new boolean[arr.length][arr.length];
//        Arrays.fill(is_covered_array, -1);
        
        Map.Entry<String,Integer> maxEntry;
        int lines_counter = -1;
        do{
            lines_counter++;
            map = new HashMap<String,Integer>();
            //////count in rows
            for(int row=0;row<arr.length;row++){
                int zeros_counter = 0;
                for(int column=0;column<arr.length;column++){
                    if(arr[row][column] == 0 && is_covered_array[row][column]==false) zeros_counter++;
                }
                if(zeros_counter > 0) map.put("r"+row, zeros_counter);
            }
            
            //////count in columns
            for(int column=0;column<arr.length;column++){
                int zeros_counter = 0;
                for(int row=0;row<arr.length;row++){
                    if(arr[row][column] == 0 && is_covered_array[row][column]==false) zeros_counter++;
                }
                if(zeros_counter > 0) map.put("c"+column, zeros_counter);
            }
            
            maxEntry = null;
            for(Map.Entry<String,Integer> ent : map.entrySet()){
                if(maxEntry == null || ent.getValue() > maxEntry.getValue() ){
                    maxEntry = ent;
                }
            }
            if(maxEntry != null){
                MaskedRowsOrColumns.add(maxEntry.getKey());
                MaskMaxZeros(arr, is_covered_array, maxEntry);
            }
            
        }while(maxEntry != null);
        
        return new Object[] { is_covered_array , lines_counter , MaskedRowsOrColumns };
    }
    
    public void MaskMaxZeros(int[][] arr,boolean[][] is_covered_array,Map.Entry<String,Integer> ent){
        int row,column;
        switch(ent.getKey().charAt(0)){
            case 'r':
                row = Integer.parseInt( ent.getKey().substring(1) );
                for(column=0;column<arr.length;column++){
                    is_covered_array[row][column] = true;
                }
                break;
            case 'c':
                column = Integer.parseInt( ent.getKey().substring(1) );
                for(row=0;row<arr.length;row++){
                    is_covered_array[row][column] = true;
                }
                break;
        }
    }
    
    public void PrintArray(int[][] arr){
        System.out.println("Array : ");
        for(int x=0;x<arr.length;x++){
            for(int j=0;j<arr[x].length;j++){
                System.out.print("{ "+arr[x][j]+" }");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }
    
    public int GetMinInUnMaskedCells(int[][] arr,boolean[][] is_covered_array){
        int min_val = Integer.MAX_VALUE;
        
        for(int row=0;row<arr.length;row++){
            for(int column=0;column<arr[row].length;column++){
                if(arr[row][column] < min_val && is_covered_array[row][column]==false){
                    min_val = arr[row][column];
                }
            }
        }
        return min_val;
    }
    
    public void AddMinToCrossedAndSubtractFromUnMasked(int[][] arr,boolean [][] is_covered,int min_val,ArrayList<String> MaskedRowsOrColumns){
        ArrayList<Pair<Integer,Integer>> pairs = new ArrayList<Pair<Integer,Integer>>();
        for(int i=0;i<MaskedRowsOrColumns.size();i++){
            for(int j=0;j<MaskedRowsOrColumns.size();j++){
                if(i==j) continue;
                
                if( MaskedRowsOrColumns.get(i).charAt(0)=='r' && MaskedRowsOrColumns.get(j).charAt(0)=='c'){
                    pairs.add(new Pair<>( Integer.parseInt( MaskedRowsOrColumns.get(i).substring(1) )
                            , Integer.parseInt( MaskedRowsOrColumns.get(j).substring(1) ) ));
                }else if( MaskedRowsOrColumns.get(i).charAt(0)=='c' && MaskedRowsOrColumns.get(j).charAt(0)=='r' ){
                    pairs.add(new Pair<>( Integer.parseInt( MaskedRowsOrColumns.get(j).substring(1) )
                            , Integer.parseInt( MaskedRowsOrColumns.get(i).substring(1) ) ));
                }
            }
        }
        
        ///// add min val to crossed
        for(int i=0;i<pairs.size();i++){
            arr[pairs.get(i).getKey()][pairs.get(i).getValue()] = arr[pairs.get(i).getKey()][pairs.get(i).getValue()] + min_val;
        }
        
        ////// subtract min val from un covered
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(is_covered[i][j] == false){
                    arr[i][j] = arr[i][j] - min_val;
                }
            }
        }
    }
    
    public void GetOptimalSolution(int[][] final_array){       
        BackTrack(final_array, new boolean[final_array.length], 0, 0, 0,true,new ArrayList<String>());
    }
    
    public int GetColumnWithMinInRow(int[][] final_array ,int row){
        int min = -1;int column_index = -1;
        for(int column=0;column<this.grid[row].length;column++){
            if(final_array[row][column] == 12){
//                min = this.grid[row][column];
                column_index = column;
            }
        }
        return column_index;
    }
    
    int max_assigned_tasks=-1;
    int max_cost = -1;
    ArrayList<String> M_TRACKER;
    
    public boolean BackTrack(int[][] final_array,boolean[] is_assigned_task,int accumulated_cost,int x_co,int y_co,boolean can_I_use_smallest_jop,ArrayList<String> tracker){
        
        ////////////////for testing////////////////
        int assig_tasks = 0;
        for(int i=0;i<is_assigned_task.length;i++){
            if(is_assigned_task[i]==true) assig_tasks++;
        }
        if(assig_tasks > max_assigned_tasks) 
        { 
            max_assigned_tasks=assig_tasks;max_cost=accumulated_cost;M_TRACKER = tracker;
        }
        ///////////////////////////////////////////
        
        boolean is_all_tasks_assigned = true;
        for(int i=0;i<is_assigned_task.length;i++){
            if(is_assigned_task[i] == false){
                is_all_tasks_assigned = false;
                break;
            }
        }
        if(is_all_tasks_assigned == true){
            FinalCost = FinalCost == null ? accumulated_cost : accumulated_cost<FinalCost ? accumulated_cost : FinalCost;
            AllPossibleSol.add(accumulated_cost);
            return true;
        }
        
        int row = x_co , column = y_co;
        
        boolean zero_found = false;
        for(;column<final_array[row].length;column++){
            if(final_array[row][column] == 0 && is_assigned_task[column]==false){
                zero_found = true;
                break;
            }
        }
        if(zero_found != true) {
            if(can_I_use_smallest_jop){
                int col_min = GetColumnWithMinInRow(final_array,row);
                if(col_min!= -1 && is_assigned_task[col_min] == false){
                    is_assigned_task[col_min] = true;accumulated_cost += this.grid[row][col_min];
                    
                    tracker.add("{"+row+"}=>{"+col_min+"}");
                    if(BackTrack(final_array, is_assigned_task, accumulated_cost, row+1, 0, false,tracker)) return true;
                    
                    is_assigned_task[col_min] = false;accumulated_cost -= this.grid[row][col_min];
                    tracker.remove(tracker.size()-1);
                    return false;
                }
            }else return false;
        }
        
        if(row == 0 && column==15){
            int yy = 5;
        }
        
        
        if(row < final_array.length && column < final_array.length){
            is_assigned_task[column] = true;accumulated_cost += this.grid[row][column];
            tracker.add("{"+row+"}=>{"+column+"}");
            if(BackTrack(final_array, is_assigned_task, accumulated_cost,row+1,0,can_I_use_smallest_jop,tracker)) return true;
            
            is_assigned_task[column] = false;accumulated_cost -= this.grid[row][column];
            tracker.remove(tracker.size()-1);
            if(BackTrack(final_array, is_assigned_task, accumulated_cost, row, column+1,can_I_use_smallest_jop,tracker)) return true;
        }
        return false;
    }
    
    public void Solve(){
        
        int[][] new_arr = CopyArray(this.grid);
        
        do{
            new_arr = SubtractMinInRows(new_arr);
            new_arr = SubtractMinInColumns(new_arr);
            
            Object[] my_obj = CoverZerosByMinLines(new_arr);
            if( (Integer)my_obj[1] == new_arr.length ){
                System.out.println("Solution found :");
                PrintArray( new_arr );
                GetOptimalSolution(new_arr);
                System.out.println("Optimal assignment : "+FinalCost);                
                System.out.println("max assig tasks = "+max_assigned_tasks+" , with cost = "+max_cost);
                for(int i=0;i<M_TRACKER.size();i++){
                    System.out.println(M_TRACKER.get(i).toString());
                }
                
                for(int i=0;i<AllPossibleSol.size();i++){
                    System.out.print(AllPossibleSol.get(i).toString() + " , ");
                }
                
                break;
                
            }
            int min_val_in_curr_array = GetMinInUnMaskedCells(new_arr, (boolean[][])my_obj[0] );
            
            AddMinToCrossedAndSubtractFromUnMasked(new_arr, (boolean[][])my_obj[0] ,min_val_in_curr_array ,(ArrayList<String>)my_obj[2] );
            
        }while(true);
        
    }
}
