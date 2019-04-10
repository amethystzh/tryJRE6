package com.jd.cloud.testinput;

import org.apache.log4j.Logger;

public class ReverseSpaceStrings {
	
	private static Logger log =  Logger.getLogger(ReverseSpaceStrings.class);
	
	static String reverseString(String str) {

		if (str == null || str.isEmpty()) {
			log.info("the input string has nothing to format");
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFinal = new StringBuffer();
		
		int initStringLength = str.length();
		
		for (int i=0; i<initStringLength; i++) {
			if (str.charAt(i) != ' ') {
				sb.insert(0, str.charAt(i));
			}
			else {
				sb.append(' ');
				sbFinal.append(sb);
				sb.setLength(0);  // re-init sb
			}
		}
		sbFinal.append(sb);
		
		log.info("the init  string is: "+ str);
		log.info("the dealt string is: "+sbFinal);
		
		return sbFinal.toString();
		
	}
	
	public static void main(String[] args) {
		
		String str1 = "abcdefg hijk lmn opq rst 12345 !#%^ u v   wxyz";
		reverseString(str1);
		
		String str2 = null;
		reverseString(str2);
		
		String str3 = "";
		reverseString(str3);
		
		String str4 = " ";
		reverseString(str4);
		
		String str5 = "#";
		reverseString(str5);
		
		String str6 = "Google Developer Days are global events showcasing the latest developer products and platforms from Google to help you quickly develop high quality apps, grow and retain an active user base, and tap into tools to earn more.";
		reverseString(str6);
		
	}

}