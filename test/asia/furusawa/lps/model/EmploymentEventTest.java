/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

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
public class EmploymentEventTest {
    
    public EmploymentEventTest() {
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
     * Test of getSalaryByMonth method, of class EmploymentEvent.
     */
    @Test
    public void testGetSalaryByMonth() {
        
        EmploymentEvent instance = new EmploymentEvent(new Date());
        int expResult = 590000;
        int result = instance.getSalaryByMonth();
        assertEquals(expResult, result);

    }
}
