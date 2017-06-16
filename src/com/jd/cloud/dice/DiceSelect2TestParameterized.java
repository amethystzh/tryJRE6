package com.jd.cloud.dice;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@RunWith(Parameterized.class)
public class DiceSelect2TestParameterized {
	
	private static DiceSelect2 ds = new DiceSelect2();
	
	private static Logger log = Logger.getLogger(DiceSelect2TestParameterized.class);
	
	private String name;
	private int star;
	private int result;
	
	private int returnCode;

    //���캯�����Ա������г�ʼ��
    public DiceSelect2TestParameterized(String name, int star, int result) {
        this.name = name;
        this.star = star;
        this.result = result;
    }
        
    // ���������� 
    @Parameters(name = "TestCase{index}: {0},{1}: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        		{"����",5, 0},
        		{"����",3, 0},
        		{null, 1, -1},
        		{"����", -1, -2}
        });
    }
    
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
    public void testSetCandidates() {
        returnCode = ds.setCandidates(name, star);
        assertEquals(result, returnCode);
        
        if (returnCode==0){
        	log.info("pass");
        }
        else{
            log.info("fail");
        }
    }
}
