package leetcode;

import java.util.HashSet;

// 3. Longest Substring Without Repeating Characters
//
//Given a string, find the length of the longest substring without repeating characters.
//
//Examples:
//
//Given "abcabcbb", the answer is "abc", which the length is 3.
//
//Given "bbbbb", the answer is "b", with the length of 1.
//
//Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

public class LongestSubString {
	
	public static int lengthOfLongestSubstring1(String s) {
		
		if ( s==null || s.isEmpty() ) {
			return 0;
		}
		
		if ( s.length() < 2 ) {
			return s.length();
		}
		
		// convert to char[]
		char[] ch = new char[s.length()];
		ch = s.toCharArray();
		
		int result=1;
		
		for ( int i=0; i<s.length()-1; i++ ) {
			for ( int j=i+1; j<s.length(); j++ ) {
				
				if ( s.substring(i, j).contains(ch[j]+"") ) {
					break;
				}
				
				if ( result < j-i+1 ) {
					result = j-i+1;
				}
			}
		}
        return result;
    }
	
	
	private static int lengthOfLongestSubstring(String s) {
		
		if ( s==null || s.isEmpty() ) {
			return 0;
		}
		
		if ( s.length() < 2 ) {
			return s.length();
		}
		
		int result=1;
		
		HashSet<Character> hs = new HashSet<>();
		
		for (int i = 0; i < s.length()-1; i++ ) {
			hs.add(s.charAt(i));
			for (int j=i+1; j < s.length(); j++ ) {
				int length = hs.size();
				hs.add(s.charAt(j));
				if ( hs.size() == length ) {
					break;
				}
				
				if ( hs.size() > result ) {
					result = hs.size();
				}

			}
			hs.clear();
		}
		return result;
	}

	public static void main(String[] args) {
		
		String s1 = "abcabcbb";
		System.out.println(s1+": "+lengthOfLongestSubstring(s1));
		
		String s2 = "bbbbb";
		System.out.println(s2+": "+lengthOfLongestSubstring(s2));
		
		String s3 = "pwwkew";
		System.out.println(s3+": "+lengthOfLongestSubstring(s3));

		String s4 = "aababcac";
		System.out.println(s4+": "+lengthOfLongestSubstring(s4));	
		
		String s5 = null;
		System.out.println(s5+": "+lengthOfLongestSubstring(s5));
		
		String s6 = "";
		System.out.println(s6+": "+lengthOfLongestSubstring(s6));
		
		String s7 = "a";
		System.out.println(s7+": "+lengthOfLongestSubstring(s7));

	}

}
