/** @file
 * @brief this is file description
 * @author AmethystZh
 * @date 2019-10-22
 * @version 1.1
 * @note this is JAVADOC format comments for doxygen usage
 */
package leetcode;

import java.util.HashSet;

/**
 * get the longest sub-string without repeating characters.
 * given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * 1. Given "abcabcbb", the answer is "abc", which the length is 3.
 * 2. Given "bbbbb", the answer is "b", with the length of 1.
 * 3. Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubString {

	/**
	 * this is the original implement, now is abandoned
	 * @param s the input string
	 * @return the location of the result sub-string
	 */
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

	/**
	 * this is the method now been used
	 * @param s the input string
	 * @return the location of sub-string where it is find
	 */
	public static int lengthOfLongestSubstring(String s) {
		
		if ( s==null || s.isEmpty() ) {
			return 0;
		}
		
		if ( s.length() < 2 ) {
			return s.length();
		}
		
		int result=1;
		
		HashSet<Character> hs = new HashSet<Character>();
		
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

	/**
	 * the main function, we put the test here
	 * @param args this is the java main function default arguments
	 */
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
