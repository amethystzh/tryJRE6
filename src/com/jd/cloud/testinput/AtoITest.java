package com.jd.cloud.testinput;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AtoITest {
	
	public static int result;
	
	public static void printResult() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		printResult();
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
	public void testAtoi1() {
		AtoI o = new AtoI();
		o.setStr("123");
		o.atoi();
		result = o.getInt();
		assertEquals(123, result);
	}
	
	@Test
	public void testAtoi2() {
		AtoI o = new AtoI();
		o.setStr("012");
		o.atoi();
		result = o.getInt();
		assertEquals(12, result);
	}
	
	@Test
	public void testAtoi3() {
		AtoI o = new AtoI();
		o.setStr("+123");
		o.atoi();
		result = o.getInt();
		assertEquals(123, result);
	}
	
	@Test
	public void testAtoi4() {
		AtoI o = new AtoI();
		o.setStr("-123");
		o.atoi();
		result = o.getInt();
		assertEquals(-123, result);
	}
	
	@Test
	public void testAtoi5() {
		AtoI o = new AtoI();
		o.setStr("999999999");
		o.atoi();
		result = o.getInt();
		assertEquals(999999999, result);
	}
	
	@Test
	public void testAtoi6() {
		AtoI o = new AtoI();
		o.setStr("  123 45 6  ");
		o.atoi();
		result = o.getInt();
		assertEquals(123456, result);
	}
	
	@Test
	public void testAtoi7() {
		AtoI o = new AtoI();
		o.setStr("str");
		o.atoi();
		assertEquals(o.getInvalidSign(), true);
	}
	
	@Test
	public void testAtoi8() {
		AtoI o = new AtoI();
		o.setStr(null);
		o.atoi();
		assertEquals(o.getInvalidSign(), true);
	}
	
	@Test
	public void testAtoi9() {
		AtoI o = new AtoI();
		o.setStr("");
		o.atoi();
		assertEquals(o.getInvalidSign(), true);
	}
	
	@Test
	public void testAtoi10() {
		AtoI o = new AtoI();
		o.setStr("123a56");
		o.atoi();
		assertEquals(o.getInvalidSign(), true);
	}
	
	@Test
	public void testAtoi11() {
		AtoI o = new AtoI();
		o.setStr("0000 0000");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), false);
		assertEquals(0, result);
	}
	
	@Test
	public void testAtoi12() {
		AtoI o = new AtoI();
		o.setStr("2147483647");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), false);
		assertEquals(2147483647, result);
	}
	
	@Test
	public void testAtoi13() {
		AtoI o = new AtoI();
		o.setStr("21474836471");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), true);
	}
	
	@Test
	public void testAtoi14() {
		AtoI o = new AtoI();
		o.setStr(" 2147 483 647");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), false);
		assertEquals(2147483647, result);
	}
	
	@Test
	public void testAtoi15() {
		AtoI o = new AtoI();
		o.setStr("2147483648");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), true);
	}
	
	@Test
	public void testAtoi16() {
		AtoI o = new AtoI();
		o.setStr("-2147483647");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), false);
		assertEquals(-2147483647, result);
	}
	
	@Test
	public void testAtoi17() {
		AtoI o = new AtoI();
		o.setStr("+2147483647");
		o.atoi();
		result=o.getInt();
		assertEquals(o.getInvalidSign(), false);
		assertEquals(2147483647, result);
	}

}
