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
public class CryptarithmeticBuzzle {
    
    class Node{
        char c;
        int val;
        public Node (char c ,int val){
            this.c = c;this.val = val;
        }
    }
    
    String s1;String s2;String s3;
    ArrayList<Character> distinct_chars;
    char[] string_1_reversed;
    char[] string_2_reversed;
    char[] string_3_reversed;
    int max;
    
    public CryptarithmeticBuzzle(String s1, String s2,String s3){
        this.s1 = s1;this.s2=s2;this.s3=s3;
        
        ArrayList<String> ss = new ArrayList<>();
        ss.add(this.s1);ss.add(this.s2);ss.add(this.s3);
        
        distinct_chars = new ArrayList<Character>();
        
        for(String s : ss){
            for(char i : s.toCharArray()){
                boolean is_exist = false;
                for(int j=0;j<distinct_chars.size();j++){
                    if(i == distinct_chars.get(j)){
                        is_exist = true;
                        break;
                    }
                }
                if(is_exist == false){
                    distinct_chars.add(i);
                }
            }
        }
        
        SetupRevrsedStrings();
        
        this.max = this.s1.length()>=this.s2.length()&&this.s1.length()>=this.s3.length()?this.s1.length()
                :this.s2.length()>=this.s1.length()&&this.s2.length()>=this.s3.length()?this.s2.length()
                :this.s3.length()>=this.s1.length()&&this.s3.length()>=this.s2.length()?this.s3.length():0;
        
        ArrayList<Node> all_cases = new ArrayList<>();
        if(Solve(all_cases,0)){
            System.out.println("Solution found :");
            printSolution(all_cases);
        }else System.out.println("Cannot find solution ...");
        
    }
    
    public void printSolution(ArrayList<Node> cases){
        for(int i=0;i<cases.size();i++){
            System.out.println("Char "+cases.get(i).c+ " have val "+cases.get(i).val);
        }
    }
    
    public void SetupRevrsedStrings(){
        string_1_reversed = new char[this.s1.length()];
        string_2_reversed = new char[this.s2.length()];
        string_3_reversed = new char[this.s3.length()];
        
        int index = 0;
        for(int i=this.s1.length()-1;i>=0;i--){
            string_1_reversed[index++] = this.s1.charAt(i);
        }
        index=0;
        for(int i=this.s2.length()-1;i>=0;i--){
            string_2_reversed[index++] = this.s2.charAt(i);
        }
        index=0;
        for(int i=this.s3.length()-1;i>=0;i--){
            string_3_reversed[index++] = this.s3.charAt(i);
        }
    }
    
    public boolean is_val_used_before(ArrayList<Node> cases,int val){
        for(int i=0;i<cases.size();i++){
            if(cases.get(i).val == val){
                return true;
            }
        }
        return false;
    }
    
    public boolean Solve(ArrayList<Node> cases, int index){
        
//        if(cases.size()==8 && cases.get(0).val==7
//                && cases.get(1).val==5 &&cases.get(2).val==3 && cases.get(3).val==1&& cases.get(4).val==0
//                && cases.get(5).val==8 && cases.get(6).val==2 && cases.get(7).val==6){
//            int eee = 5;
//        }
        
        if(cases.size() == distinct_chars.size()){
            try{
                if(CheckCases(cases))return true;
                else return false;
            }catch(Exception e){
                System.err.println("We catch inhandeled case ...");
                printSolution(cases);
                return false;
            }
        }
        
        char curr_char = distinct_chars.get(index);
        
        for(int j=0;j<=9;j++){
            if(is_val_used_before(cases, j))continue;
            cases.add(new Node(curr_char,j));
            if(Solve(cases, index+1))return true;
            cases.remove(cases.size()-1);
        }
        
        return false;
    }
    
    public boolean CheckCases(ArrayList<Node> cases) throws Exception{
        
        String num_1="",num_2="",num_3="";
        
        for(int j=0;j<max;j++){
            num_1 = ( j>=string_1_reversed.length ? "0" : Integer.toString( get_equivalent(cases, string_1_reversed[j]) ) ) + num_1;
            num_2 = ( j>=string_2_reversed.length ? "0" : Integer.toString( get_equivalent(cases, string_2_reversed[j]) ) ) + num_2;
            num_3 = ( j>=string_3_reversed.length ? "0" : Integer.toString( get_equivalent(cases, string_3_reversed[j]) ) ) + num_3;
        }
        
        int N1 = Integer.parseInt(num_1),N2 = Integer.parseInt(num_2),N3 = Integer.parseInt(num_3);
        
        if(N1+N2 == N3)return true;
        else return false;
    }
    
    public int get_equivalent(ArrayList<Node> cases ,char ch)throws Exception{
        for(int i=0;i<cases.size();i++){
            if(cases.get(i).c == ch){
                return cases.get(i).val;
            }
        }
        throw new Exception("Case unhandeled ...");
    }
    
}
