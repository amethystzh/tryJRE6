package com.jd.cloud.testinput;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReverseSpaceStringsTest {
	
	ReverseSpaceStrings rs = new ReverseSpaceStrings();

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
	public void test() {
		String s = "abcdefg";
		String r = rs.reverseString(s);
		assertEquals(r, "gfedcba");
		
	}

}
