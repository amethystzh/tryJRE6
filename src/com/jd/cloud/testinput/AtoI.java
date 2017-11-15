package com.jd.cloud.testinput;

import java.math.*;

public class AtoI {
	
	private String inputStr;
	private int outputInt;
	private boolean invalidSign;
	
	public void setStr(String str){
		inputStr = str;
	}
	
	public String getStr(){
		return inputStr;
	}
	
	public void setInt(int result) {
		outputInt = result;
	}
	
	public int getInt(){
		return outputInt;
	}
	
	public void setInvalidSign(boolean sign) {
		invalidSign = sign;
	}
	
	public boolean getInvalidSign(){
		return invalidSign;
	}
	
	public void atoi(){

		String str = getStr();
		setInvalidSign(false);

		
		int result=0;
		int sign=1;    // + or -, default as +
		
		if ( str == null || str.length() == 0 ) {
			setInvalidSign(true);
			return;
		}
		
		str = str.replace(" ", "");
		
		if (str.charAt(0)=='+') {
			sign = 1;
			str = str.substring(1);
		}
			
		if (str.charAt(0)=='-') {
			sign = -1;
			str = str.substring(1);
		}
		
		if ( str.length() > String.valueOf(Integer.MAX_VALUE).length() ) {
			setInvalidSign(true);
			return;
		}
		
		for (int i=0; i<str.length(); i++){
	
			if ( ctoi(str.charAt(i)) == -1 ) {
				setInvalidSign(true);
				return;
			}
			else {
				
				// tell if input string overflow Max_Integer
				if ( 
						( i == String.valueOf(Integer.MAX_VALUE).length()-1 && result > Integer.MAX_VALUE/10) 
					||
					    ( i == String.valueOf(Integer.MAX_VALUE).length()-1 
					    && result == Integer.MAX_VALUE/10	
					    && str.charAt(i) > String.valueOf(Integer.MAX_VALUE).charAt(String.valueOf(Integer.MAX_VALUE).length()-1) 
					     ) 
					) {
					setInvalidSign(true);
					return;
				}
				
				//result = 10*result + Integer.parseInt(str.charAt(i)+"");
				result = 10*result + ctoi(str.charAt(i));
			}
			
		}

		setInt(sign*result);
		
	}
	
	public static int ctoi(char ch){
		int result = -1;
		switch(ch){
		case 48:
			result = 0;
			break;
		case 49: 
			result = 1;
			break;
		case 50:
			result = 2;
			break;
		case 51:
			result = 3;
			break;
		case 52:
			result = 4;
			break;
		case 53:
			result = 5;
			break;
		case 54:
			result = 6;
			break;
		case 55:
			result = 7;
			break;
		case 56:
			result = 8;
			break;
		case 57:
			result = 9;
			break;
		case 58:
			result = 0;
			break;
		default:
			result = -1;
		
		}
		
			
		return result;
	}

	public static void main(String[] args) {
		
		AtoI object = new AtoI();
		String str = "123";
		object.setStr(str);
		object.atoi();
		
		System.out.println("max int is:"+Integer.MAX_VALUE);
		
		if (object.getInvalidSign()) {
			System.out.println("the input string is not a vaild numeric string");
		}
		else{
			System.out.println("convert string as "+str+" to: "+object.getInt());
		}

	}

}
