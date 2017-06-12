package com.jd.cloud.testinput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.log4j.*;

public class TestCSVInput {
	
	static public Logger log =  Logger.getLogger(TestCSVInput.class);
	
	String sPath = System.getProperty("user.dir");
	
	private static TestCSVInput mInstance;

	private ArrayList<String[]> mTestCSVInput;

	private TestCSVInput() {
		mTestCSVInput = new ArrayList< String[] >();
	}

	public static TestCSVInput getInstance() {
		if (mInstance == null) {
			mInstance = new TestCSVInput();
		}
		return mInstance;
	}
	
	public String[] getAL(int i) {
		return mTestCSVInput.get(i);
	}
	
	public void printInputFile(){
		StringBuffer sbt = new StringBuffer();
		log.info("========================================");
		for (int i = 0; i<mTestCSVInput.size(); i++){
			sbt.setLength(0);
			String[] array = mTestCSVInput.get(i);
			int arraySize = array.length;

			for ( int j = 0; j<arraySize; j++) {
				sbt = sbt.append(array[j]+" ");
			}
			log.info(sbt);
		}
	}
	
	public boolean loadTestInput() {
		boolean isSuccess = true;
		String filePath = sPath + "\\Data\\test_input.csv";
		try {
			FileReader filereader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(filereader);
			String temp = null;
			int argNum = 0;
			temp = br.readLine();
			while (temp != null) {
				if (temp.contains(",")) {
					String[] sInfo = temp.split(",");
					int arraySize = sInfo.length;
					
					if ( argNum != 0 && arraySize != argNum) {
						isSuccess = false;
						log.error("the argument number is not correct");
						break;
					}
					
					for ( int i = 0; i<arraySize; i++) {
						sInfo[i]=sInfo[i].trim();
					}
					
					mTestCSVInput.add(sInfo);
					
					if (mTestCSVInput.get(0)!=null) {
						argNum = mTestCSVInput.get(0).length;
					}

				}
				temp = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public void testFunc(String name, char gender, int age, String mobile, String address){
		log.info("Name: "+name+", Gender: "+gender+", Age: "+age+", Mobile: "+mobile+", Address: "+address);
	}
	
	public int exampleTestFunc(ArrayList<String[]> args) {
		int returnCode = 0;
		
		log.info("===============================================");
		
		for ( int i = 1; i < args.size(); i++ ){
			
			String[] ins = args.get(i);
			
			String name = ins[0];
			char gender = ins[1].charAt(0);
			int age = Integer.parseInt(ins[2]);
			String mobile = ins[3];
			String address = ins[4];
			
			
			testFunc(name, gender, age, mobile, address);
			
		}
		
		
		return returnCode;
	}
	

	public static void main(String[] args) {
		
		TestCSVInput oi = new TestCSVInput();
		log.info("initialize an instance");
		log.info("sPath="+oi.sPath);
		if (oi.loadTestInput()) {
			log.info("load file succeed");
			oi.printInputFile();
			}
		else {
			log.error("load file failed");
		}
		
		oi.exampleTestFunc(oi.mTestCSVInput);

	}

}
