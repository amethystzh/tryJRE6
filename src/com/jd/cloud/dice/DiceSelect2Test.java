package com.jd.cloud.dice;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DiceSelect2Test {
	
	private static DiceSelect2 ds = new DiceSelect2();
	
	private static Logger log = Logger.getLogger(DiceSelect2Test.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("TestSet Begins========");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("TestSet Ends==========");
	}

	@Before
	public void setUp() throws Exception {
		log.info("Test Begins-----------");
	}

	@After
	public void tearDown() throws Exception {
		log.info("Test Ends-------------");
	}
	
	@Test
	public void testSetCandidates1() {
		assertEquals(0, ds.setCandidates("张三",5));
		log.info("1st TC");
	}
	
//	@Ignore
	@Test
	public void testSetCandidates2() {
		assertEquals(0, ds.setCandidates("李四",3));
		log.info("2nd TC");
	}
	
	@Test
	public void testSetCandidates3() {
		assertEquals(-1, ds.setCandidates(null, 1));
		log.info("3rd TC");
	}
	
	@Test
	public void testSetCandidates4() {
		assertEquals(-2, ds.setCandidates("张三", -1));
		log.info("4th TC");
	}


	@Test
	public void testSetCandidates() {
		
		assertEquals(0, ds.setCandidates("张三",5));
		assertEquals(0, ds.setCandidates("李四",1));
		
		assertEquals(-1, ds.setCandidates(null, 1));
		assertEquals(-1, ds.setCandidates("", 2));
		assertEquals(-1, ds.setCandidates(" ", 5));
		assertEquals(-1, ds.setCandidates("123", 1));
		assertEquals(-1, ds.setCandidates("wang", 1));
		assertEquals(-1, ds.setCandidates("~!@#$", 1));
		assertEquals(-1, ds.setCandidates("张3", 1));
		
		assertEquals(-2, ds.setCandidates("张三", -1));
		assertEquals(-2, ds.setCandidates("张三", -0));
		assertEquals(-2, ds.setCandidates("张三", 6));
		assertEquals(-2, ds.setCandidates("张三", 2147483647));

		assertEquals(-1, ds.setCandidates(null, null));
		
	}

}
