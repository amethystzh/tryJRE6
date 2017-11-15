package com.jd.cloud.testinput;

public class StringIPv4 {
	
	public boolean stringIsIPv4 (String str) {
		boolean result = false;
		
		if ( str==null || str.length()==0 ) {
			result = false;
			return result;
		}
		
		String[] parts = str.split(".");
		
		if ( parts.length != 4 ) {
			result = false;
			return result;
		} 
		
		
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
