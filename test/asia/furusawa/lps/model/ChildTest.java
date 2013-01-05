/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import asia.furusawa.lps.util.DateUtil;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hiropon
 */
public class ChildTest {
    
    public ChildTest() {
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
     * Test of getAge method, of class Child.
     */
    @Test
    public void testGetAge() {
        //System.out.println("getAge");
        Date current = DateUtil.getDateByMonth(2013, 1);
        Child instance = new Child(DateUtil.getDateByMonth(2009, 5));


        assertEquals(3, instance.getAge(current));
        
        current = DateUtil.getDateByMonth(2013, 4);

        assertEquals(4, instance.getAge(current));
        // TODO review the generated test code and remove the default call to fail.

    }
}
