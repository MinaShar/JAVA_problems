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
public class SolveSuduku {
    
    public SolveSuduku(int[][] board){
        
        if(BackTrack(board) == true){
            PrintBoard(board);
        }
        
    }
    
    public boolean validate_rows(int [][] board){
        
        for(int j=0;j<board.length;j++){
            HashMap<Integer,Boolean> map = new HashMap<>();
            for(int i=0;i<board[j].length;i++){
                if(board[j][i] == 0)continue;
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
                if(board[row][column] == 0)continue;
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
                if(board[i][j] == 0)continue;
                
                if(maps[i/3][j/3].containsKey(board[i][j])){
                    return false;
                }
                
                maps[i/3][j/3].put(board[i][j], Boolean.TRUE);
            }
        }
        
        return true;
    }
    
    public void PrintBoard(int[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print("{"+board[i][j]+"} , ");
            }
            System.out.println();
        }
    }
    
    public boolean BackTrack(int[][] board){
        
        System.out.println("============================================");
        PrintBoard(board);
        System.out.println("============================================");
        
        int row=-1,col=-1;
        
        boolean flag_board_ok = true;
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == 0){
                    row = i;col = j;
                    flag_board_ok = false;
                    break;
                }
            }
            if(flag_board_ok == false){
                break;
            }
        }
        
        if(flag_board_ok == true){
            return true;
        }
        
        if(board[row][col] == 0){               
            for(int k=1;k<=9;k++){     
                board[row][col] = k;         
                if( validate_rows(board) && validate_columns(board) && validate_boxes(board) ){       
                    if(BackTrack(board) == true){      
                        return true; 
                    }
                }        
                board[row][col] = 0;
            }
        }
        return false;
    }
}
