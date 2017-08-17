package leetcode;


// 344. Reverse String
//Write a function that takes a string as input and returns the string reversed.
//
//Example:
//Given s = "hello", return "olleh".
public class ReverseString {
    public static String reverseString(String s) {
        
        // if the string is null or empty																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													q	
		if ( s == null || s.isEmpty()) {
			return  "the input string has nothing to reverse";
		}

//		generic solution		
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
        
		// another solution, directly use the reverse() method
//		StringBuilder sbb = new StringBuilder(s);
//		return sbb.reverse().toString();
		
        // or use StringBuffer
//		StringBuffer sbf = new StringBuffer(s);
//		return sbf.reverse().toString();

       
    }
    
    public static void main(String[] args) {
        // test
        String s = "abcdefg 12345 ~!@#$";
        long beginTime = System.nanoTime();
        String result = reverseString(s);
        long endTime = System.nanoTime();
        long diff = endTime - beginTime;
        System.out.println(result);
        System.out.println(diff);
        
//        short sn=(short)50000;
//        System.out.println("sn="+sn);
//        System.out.println(Integer.MAX_VALUE);
        
    }
}