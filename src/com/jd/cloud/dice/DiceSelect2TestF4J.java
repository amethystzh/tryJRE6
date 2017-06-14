package com.jd.cloud.dice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.databene.feed4junit.Feeder;
import org.databene.benerator.anno.Source;

import org.apache.log4j.*;

@RunWith(Feeder.class) 
public class DiceSelect2TestF4J {
	
	public Logger log = Logger.getLogger(DiceSelect2TestF4J.class);

	DiceSelect2 ds = new DiceSelect2();	


	@Test
	@Source("./data/test.xls")
	public void testSetCandidates(int errorCode, String name, Integer star) {
		

		
		log.setLevel(Level.INFO);
		log.info("errorCode: "+errorCode+", name: "+name+", star: "+star);
		
		int returnCode = ds.setCandidates(name, star);
		
		log.info("returnCode: "+returnCode);
		
		assertEquals(errorCode, returnCode);
		

	}

}