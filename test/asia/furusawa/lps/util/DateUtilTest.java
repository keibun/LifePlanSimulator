/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.util;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hiro
 */
public class DateUtilTest {
    
    public DateUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDateByMonth method, of class DateUtil.
     */
    @Test
    public void testGetDateByMonth() {
        int year = 2013;
        int month = 0;
        //Date expResult = null;
        Date result = DateUtil.getDateByMonth(year, month);
        assertEquals("Tue Jan 01 00:00:00 JST 2013", result.toString());

    }
}
