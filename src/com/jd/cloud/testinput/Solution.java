package com.jd.cloud.testinput;

public class Solution {
    public static String reverseString(String s) {
        
        // if the string is null or empty
		if ( s == null || s.isEmpty()) {
			return  "the input string has nothing to reverse";
		}
		
		StringBuilder sb = new StringBuilder();
				
		int sLength = s.length();
        
        // if the string is too long
        if (sLength < 0) {
            return "the string is too long";
        }
		
		for (int i=(sLength-1); i>=0; i--) {
			sb.append(s.charAt(i));
		}
        return sb.toString();
       
    }
    
    public static void main(String[] args) {
        // test
        String s = "hello";
        String result = reverseString(s);
        System.out.println(result);
        
        short sn=(short)50000;
        System.out.println("sn="+sn);
        System.out.println(Integer.MAX_VALUE);
        
    }
}