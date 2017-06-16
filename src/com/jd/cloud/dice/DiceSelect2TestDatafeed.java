package com.jd.cloud.dice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.databene.feed4junit.Feeder;
import org.databene.benerator.anno.Source;

import org.apache.log4j.*;

@RunWith(Feeder.class) 
public class DiceSelect2TestDatafeed {
	
	public Logger log = Logger.getLogger(DiceSelect2TestDatafeed.class);

	DiceSelect2 ds = new DiceSelect2();	
	
	public boolean checkResult(int returnCode) {
		boolean checkResult = true;
        if (returnCode==0){
        	checkResult = true;
        	log.info("pass");
        }
        else{
        	checkResult = false;
            log.info("fail");
        }
		return checkResult;
	}


	@Test
	@Source("./data/test.xls")
	public void testSetCandidates(int expect, String name, Integer star) {
		

		
//		log.setLevel(Level.INFO);
		log.info("expect: "+expect+", name: "+name+", star: "+star);
		
		int returnCode = ds.setCandidates(name, star);
		
		checkResult(returnCode);
		
		assertEquals(expect, returnCode);

	}
}