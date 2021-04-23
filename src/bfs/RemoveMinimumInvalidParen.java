/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;

/**
 *
 * @author misaac
 */
public class RemoveMinimumInvalidParen {
    
    Set<String> validExpressions = new HashSet<String>();
    
    public RemoveMinimumInvalidParen(String s){
//        List<String> resutlt = removeInvalidParentheses(s);
//        
//        for(int i=0;i<resutlt.size();i++){
//            System.out.println("result"+i+resutlt.get(i));
//        }
        Solve(s);
    }
    
    /////////////////////////FOR TESTING///////////////////////////
    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            int leftRem,
            int rightRem,
            StringBuilder expression) {
        
        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                this.validExpressions.add(expression.toString());
            }
            
        } else {
            char character = s.charAt(index);
            int length = expression.length();
            
            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                this.recurse(
                        s,
                        index + 1,
                        leftCount,
                        rightCount,
                        leftRem - (character == '(' ? 1 : 0),
                        rightRem - (character == ')' ? 1 : 0),
                        expression);
            }
            
            expression.append(character);
            
            // Simply recurse one step further if the current character is not a parenthesis.
            if (character != '(' && character != ')') {
                
                this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);
                
            } else if (character == '(') {
                
                // Consider an opening bracket.
                this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);
                
            } else if (rightCount < leftCount) {
                
                // Consider a closing bracket.
                this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }
            
            // Delete for backtracking.
            expression.deleteCharAt(length);
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        
        int left = 0, right = 0;
        
        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {
            
            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;
                
                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }
        
        this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(this.validExpressions);
    }
    ///////////////////////////////////////////////////////////////
    
    public void Solve(String s){
        int left_inv = 0,right_inv = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                left_inv++;
            }else if(s.charAt(i)==')'){
                if(left_inv > 0) left_inv--;
                else right_inv++;
            }
        }
        
        ArrayList<String> all_possibles = new ArrayList<String>();
        BackTracking(0,s,new String(), all_possibles,0,left_inv,right_inv);
        PrintRestult(all_possibles);
    }
    
    public void PrintRestult(ArrayList<String> all_possibles){
        for(int i=0;i<all_possibles.size();i++){
            System.out.println("string"+(i+1)+"="+all_possibles.get(i) );
        }
    }
    
    public void BackTracking(int index,String s,String curr,ArrayList<String> all_possible,int number_of_removed_chars,int left_inv,int right_inv){
        
        if(index == s.length()){
            if(CheckStringValid(curr,all_possible)){
                all_possible.add( new String(curr) );
            }
            return;
        }
        
        if(all_possible.size() > 0 && (s.length()-all_possible.get(0).length() < number_of_removed_chars) ){
            return;
        }
        
        if( s.charAt(index) == ')' || s.charAt(index) == '(' ){
            curr += s.charAt(index);
            BackTracking(index+1 , s, curr, all_possible,number_of_removed_chars,left_inv,right_inv);
            
            curr = curr.substring(0, curr.length()-1);
            if( (s.charAt(index)=='(' && left_inv>0) || (s.charAt(index)==')' && right_inv>0) ){                
                BackTracking(index+1 ,s, curr, all_possible,number_of_removed_chars+1,s.charAt(index)=='('?left_inv-1:left_inv,s.charAt(index)==')'?right_inv-1:right_inv);  
            }         
        }else{
            curr += s.charAt(index);
            BackTracking(index+1, s, curr, all_possible,number_of_removed_chars,left_inv,right_inv);
        }
        
    }
    
    public boolean CheckStringValid(String s,ArrayList<String> all_possibles){
        
        ArrayList<Integer> stack = new ArrayList<Integer>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.add(i);
            }else if( s.charAt(i) == ')' && stack.size()>0){
                stack.remove( stack.size()-1 );
            }else if( s.charAt(i) == ')' && stack.size()==0 ){
                return false;
            }
        }
        
        for(int i=0;i<all_possibles.size();i++){
            if( all_possibles.get(i).compareTo(s) == 0 ){
                return false;
            }
        }
        
        if(stack.size()==0){
            return true;
        }
        return false;
    }
}
