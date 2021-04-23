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
public class GridIllumination {
    
    public GridIllumination(){
        int[][] arr = new int[][]{{0,0},{0,4}};
        int[][] queries = new int[][] {{0,4},{0,1},{1,4}};
        gridIllumination(5,arr,queries);
    }
    
    public boolean is_xaxis(int N,ArrayList<Integer> lamp,int[] query){

            if(query[0]==lamp.get(0) )
                return true;
            return false;
        }
        public boolean is_yaxis(int N,ArrayList<Integer> lamp,int[] query){

            if(query[1]==lamp.get(1) )
                return true;
            return false;
        }
        public boolean is_diagonal(int N,ArrayList<Integer> lamp,int[] query){
            int x,y;

            x=lamp.get(0);
            y=lamp.get(1);
            while(x<N && x>=0 && y<N && y>=0){
                if(query[0]==x && query[1]==y){
                    return true;
                }
                x++;
                y++;
            }

            x=lamp.get(0);
            y=lamp.get(1);
            while(x<N && x>=0 && y<N && y>=0){
                if(query[0]==x && query[1]==y){
                    return true;
                }
                x--;
                y--;
            }

            x=lamp.get(0);
            y=lamp.get(1);
            while(x<N && x>=0 && y<N && y>=0){
                if(query[0]==x && query[1]==y){
                    return true;
                }
                x++;
                y--;
            }

            x=lamp.get(0);
            y=lamp.get(1);
            while(x<N && x>=0 && y<N && y>=0){
                if(query[0]==x && query[1]==y){
                    return true;
                }
                x--;
                y++;
            }

            return false;
        }

        public boolean is_lit(int N,ArrayList<ArrayList<Integer>> lamps,int[] query){

            for(int i=0;i<lamps.size();i++){
                if( is_xaxis(N,lamps.get(i),query) ){
                    return true;
                }
                if( is_yaxis(N,lamps.get(i),query) ){
                    return true;
                }
                if( is_diagonal(N,lamps.get(i),query) ){
                    return true;
                }
            }
            return false;
        }

        public void close_adjacent_cells(ArrayList<ArrayList<Integer>> _lamps,int[] query){

            for(int i=0;i<_lamps.size();i++){
                ////equale the cell
                if(query[0]==_lamps.get(i).get(0) && query[1]==_lamps.get(i).get(1)){
                    _lamps.remove(i);
                    i--;
                }

                ///x-axis
                if(query[0]==_lamps.get(i).get(0)+1 && query[1]==_lamps.get(i).get(1)){
                    _lamps.remove(i);
                    i--;
                }
                if(query[0]==_lamps.get(i).get(0)-1 && query[1]==_lamps.get(i).get(1)){
                    _lamps.remove(i);
                    i--;
                }
                ///y-axis
                if(query[0]==_lamps.get(i).get(0) && query[1]==_lamps.get(i).get(1)+1 ){
                    _lamps.remove(i);
                    i--;
                }
                if(query[0]==_lamps.get(i).get(0) && query[1]==_lamps.get(i).get(1)-1 ){
                    _lamps.remove(i);
                    i--;
                }

                ///diagonals
                if(query[0]==_lamps.get(i).get(0)+1 && query[1]==_lamps.get(i).get(1)+1 ){
                    _lamps.remove(i);
                    i--;
                }
                if(query[0]==_lamps.get(i).get(0)-1 && query[1]==_lamps.get(i).get(1)-1 ){
                    _lamps.remove(i);
                    i--;
                }
                if(query[0]==_lamps.get(i).get(0)+1 && query[1]==_lamps.get(i).get(1)-1 ){
                    _lamps.remove(i);
                    i--;
                }
                if(query[0]==_lamps.get(i).get(0)-1 && query[1]==_lamps.get(i).get(1)+1 ){
                    _lamps.remove(i);
                    i--;
                }
            }
        }

        public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
            ArrayList<ArrayList<Integer>> _lamps = new ArrayList<ArrayList<Integer>>();
            for(int i=0;i<lamps.length;i++){
                ArrayList<Integer> arr = new ArrayList<Integer>();
                arr.add(lamps[i][0]);arr.add(lamps[i][1]);
                _lamps.add(arr);
            }
            int[] res = new int[queries.length];

            for(int i=0;i<queries.length;i++){
                res[i] = is_lit(N,_lamps,queries[i])==true ? 1 : 0;
                close_adjacent_cells(_lamps,queries[i]);
            }
            return res;
        }
}
