/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author misaac
 */
public class Suduku {
    
    public Suduku(List<String> board){
//        System.out.println( Solve(board) );
        
        JOptionPane.showConfirmDialog(null, Solve(board) );
    }
    
    public boolean validate_rows(int [][] board){
        
        for(int j=0;j<board.length;j++){
            HashMap<Integer,Boolean> map = new HashMap<>();
            for(int i=0;i<board[j].length;i++){
                if(board[j][i] == -1)continue;
                if(map.containsKey(board[j][i])){
                    return false;
                }
                map.put(board[j][i], Boolean.TRUE);
            }
        }
        return true;
        
    }
    
    public boolean validate_columns(int [][] board){
        
        
        for(int column=0;column<board[0].length;column++){
            HashMap<Integer,Boolean> map = new HashMap<>();
            for(int row=0;row<board.length;row++){
                if(board[row][column] == -1)continue;
                if(map.containsKey(board[row][column])){
                    return false;
                }
                map.put(board[row][column], Boolean.TRUE);
            }
        }
        return true;
        
    }
    
    public boolean validate_boxes(int [][] board){
        
        HashMap<Integer,Boolean>[][] maps = new HashMap[3][3];
        
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length;j++){
                maps[i][j] = new HashMap<>();
            }
        }

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == -1)continue;
                
                if(maps[i/3][j/3].containsKey(board[i][j])){
                    return false;
                }
                
                maps[i/3][j/3].put(board[i][j], Boolean.TRUE);
            }
        }
        
        return true;
    }
    
    public boolean Solve(List<String> board){
        
        int[][] opt_board = new int[9][9];
        
        for(int i=0;i<board.size();i++){
            String curr_row = board.get(i);
            
            for(int j=0;j<curr_row.length();j++){
                opt_board[i][j] = curr_row.charAt(j)=='.' ? -1: Integer.parseInt( Character.toString( curr_row.charAt(j) ) );
            }
        }
        if(validate_rows(opt_board) && validate_columns(opt_board) && validate_boxes(opt_board)) return true;     
        return false;
        
    }
    
}
