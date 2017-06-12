package com.jd.cloud.dice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiceSelect2Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetCandidates() {
		DiceSelect2 ds = new DiceSelect2();
		
		assertEquals(0, ds.setCandidates("张三",5));
		assertEquals(0, ds.setCandidates("李四",1));
		
		assertEquals(-1, ds.setCandidates(null, 1));
		assertEquals(-1, ds.setCandidates("", 2));
		assertEquals(-1, ds.setCandidates(" ", 1));
		assertEquals(-1, ds.setCandidates("123", 1));
		assertEquals(-1, ds.setCandidates("wang", 1));
		assertEquals(-1, ds.setCandidates("~!@#$", 1));
		assertEquals(-1, ds.setCandidates("张3", 1));
		
		assertEquals(-2, ds.setCandidates("张三", -1));
		assertEquals(-2, ds.setCandidates("张三", -0));
		assertEquals(-2, ds.setCandidates("张三", 6));
		assertEquals(-2, ds.setCandidates("张三", 2147483647));

		assertEquals(-1, ds.setCandidates(null, null));
		
		ds.getCandidates();
	}

}
