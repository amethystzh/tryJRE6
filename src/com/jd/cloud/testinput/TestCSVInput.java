package com.jd.cloud.testinput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.*;

public class TestCSVInput {
	
	private static Logger log =  Logger.getLogger(TestCSVInput.class);
	
	private String sPath = System.getProperty("user.dir");
	
	private static TestCSVInput mInstance;

	private ArrayList<String[]> mTestCSVInput;

	private TestCSVInput() {
		mTestCSVInput = new ArrayList<>();
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
	
	private void printInputFile(){
		StringBuffer sbt = new StringBuffer();
		log.info("========================================");
        for (String[] strings : mTestCSVInput) {
            sbt.setLength(0);

            for (String s : strings) {
                sbt.append(s).append(" ");
            }
            log.info(sbt);
        }
	}
	
	private boolean loadTestInput() {
		boolean isSuccess = true;
		String filePath = sPath + "\\Data\\test_input.csv";
		try {
			FileReader filereader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(filereader);
			String temp;
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
	
	private void testFunc(String name, char gender, int age, String mobile, String address, int expect_code, Boolean expect_msg){
		log.info("Name: "+name+", Gender: "+gender+", Age: "+age+", Mobile: "+mobile+", Address: "+address);
		if (expect_code == 0) {
			log.info("test passed: " + expect_msg);
		}
		else {
			log.error("test failed: " + expect_msg);
		}
	}
	
	private void exampleTestFunc(ArrayList<String[]> args) {

		log.info("===============================================");
		
		for ( int i = 1; i < args.size(); i++ ){
			
			String[] ins = args.get(i);
			
			String name = ins[0];
			char gender = ins[1].charAt(0);
			int age = Integer.parseInt(ins[2]);
			String mobile = ins[3];
			String address = ins[4];
			int expect_code = Integer.parseInt(ins[5]);
			Boolean expect_msg = Boolean.parseBoolean(ins[6]);

			testFunc(name, gender, age, mobile, address, expect_code, expect_msg);
			
		}

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
