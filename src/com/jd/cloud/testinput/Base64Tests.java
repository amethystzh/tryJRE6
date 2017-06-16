package com.jd.cloud.testinput;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import sun.misc.BASE64Encoder;
 
public class Base64Tests {
 
	 private static byte[] randomBinaryData = new byte[5000000];
	 private static long durationCommons = 0;
	 private static long durationJava8 = 0;
	 private static long durationSun = 0;
	 private static byte[] encodedCommons;
	 private static byte[] encodedJava8;
	 private static String encodedSun;

	 @BeforeClass
	 public static void setUp() throws Exception {
		  //We want to test the APIs against the same data
		  new Random().nextBytes(randomBinaryData); 
	 }

	 @Test
	 public void testSunBase64Encode() throws Exception {
		  BASE64Encoder encoder = new BASE64Encoder();
		  long before = System.currentTimeMillis();
		  encodedSun = encoder.encode(randomBinaryData);
		  long after = System.currentTimeMillis();
		  durationSun = after-before;
		  System.out.println("Sun: " + durationSun);
	 }

	 @Test
	 public void testJava8Base64Encode() throws Exception {
		  long before = System.currentTimeMillis();
		  java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
		  encodedJava8 = encoder.encode(randomBinaryData);
		  long after = System.currentTimeMillis();
		  durationJava8 = after-before;
		  System.out.println("Java8: " + durationJava8);
	 }

	 @Test
	 public void testCommonsBase64Encode() throws Exception {
		  long before = System.currentTimeMillis();
		  encodedCommons = Base64.encodeBase64(randomBinaryData);
		  long after = System.currentTimeMillis();
		  durationCommons = after-before;
		  System.out.println("Commons: " + durationCommons);
	 }
	 
	 @Test
	 public void testStringJava8Base64Encode() throws Exception {

		 java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
		 java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
		 
		 final String text = "zhanchibing";
		 final byte[] textByte = text.getBytes("UTF-8");
		 //±àÂë
		 final String encodedText = encoder.encodeToString(textByte);
		 System.out.println(encodedText);
		 //½âÂë
		 String output = new String(decoder.decode(encodedText),"UTF-8");
		 System.out.println(output);
		 assertEquals(text, output);

		 String s = "cbzhan@gmail.com";
		 byte[] ab = s.getBytes();
		 encodedJava8 = encoder.encode(ab);		 
		 String temp = new String(encodedJava8);
		 System.out.println("Java8 encoded: "+temp);
		 
		 byte[] ac = decoder.decode(encodedJava8);
		 String temp2 = new String(ac);
		 System.out.println("Java8 decoder: "+temp2);
		 System.out.println("s="+s+", temp2="+temp2);
		 if (s.equals(temp2)){System.out.println("==");} else {System.out.println("!=");}
		 assertEquals("not equals", s, temp2); 
	 }

	 @AfterClass
	 public static void report() throws Exception {
		  //Sanity check
		  assertArrayEquals(encodedCommons, encodedJava8);
		  System.out.println(durationCommons*1.0/durationJava8);
	 }
	 
}

