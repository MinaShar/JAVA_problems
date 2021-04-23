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
public class ReverseVowelsofString {
    
    public ReverseVowelsofString(){
        
        System.out.println( reverseVowels("hello") );
    }
    public char[] swap(String s,int i, int j){
        
        char[] ch = s.toCharArray(); 
        char temp = ch[i]; 
        ch[i] = ch[j]; 
        ch[j] = temp; 
        return ch; 
    }
    
    public String reverseVowels(String s) {
        
        int last=-1,curr=-1;
        for(int i=0;i<s.length();i++){
            
            if( (s.charAt(i)=='a' || s.charAt(i)=='e' || s.charAt(i)=='i' || s.charAt(i)=='o' || s.charAt(i)=='u')
              && (last==-1 && curr==-1) ){
                last = curr = i;
            }
            
            else if( s.charAt(i)=='a' || s.charAt(i)=='e' || s.charAt(i)=='i' || s.charAt(i)=='o' || s.charAt(i)=='u' ){
                curr = i;
                char[] arr = swap(s,curr,last);
                s = new String(arr);
                last = curr;
            }
        }
        return s;
    }
}
