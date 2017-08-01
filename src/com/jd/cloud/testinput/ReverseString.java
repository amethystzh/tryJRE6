package com.jd.cloud.testinput;

import org.apache.log4j.Logger;

public class ReverseString {
	
	static public Logger log =  Logger.getLogger(ReverseString.class);
	
	public static void reverseString(String str) {
		String initString = str;
		
		if (initString == null || initString.isEmpty()) {
			log.info("the input string has nothing to format");
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbFinal = new StringBuffer();
		
		int initStringLength = initString.length();
		
		for (int i=0; i<initStringLength; i++) {
			if (initString.charAt(i) != ' ') {
				sb.insert(0, initString.charAt(i));
			}
			else {
				sb.append(' ');
				sbFinal.append(sb);
				sb.setLength(0);  // re-init sb
			}
		}
		sbFinal.append(sb);
		
		log.info("the init  string is: "+initString);
		log.info("the dealt string is: "+sbFinal);
		
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