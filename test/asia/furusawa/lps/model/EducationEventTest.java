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
public class EducationEventTest {
    
    public EducationEventTest() {
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
     * Test of handle method, of class EducationEvent.
     */
//    @Test
//    public void testHandle() {
//        System.out.println("handle");
//        CapitalSimulator simulator = null;
//        EducationEvent instance = null;
//        instance.handle(simulator);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getEducationValueByMonth method, of class EducationEvent.
     */
    @Test
    public void testGetEducationValueByMonth() {
        //System.out.println("getEducationValueByMonth");
        Date current = DateUtil.getDateByMonth(2013, 5);
        EducationEvent instance = new EducationEvent(DateUtil.getDateByMonth(2013, 1),false);
        
        int result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_KINDER_PUBLIC, result);

        current = DateUtil.getDateByMonth(2015, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_KINDER_PUBLIC*2 , result);

        current = DateUtil.getDateByMonth(2016, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_ELEMENTARY_PUBLIC +EducationEvent.VALUE_KINDER_PUBLIC*1 , result);

        current = DateUtil.getDateByMonth(2021, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_ELEMENTARY_PUBLIC*3, result);

        current = DateUtil.getDateByMonth(2022, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_JUNIOR_PUBLIC*1 + EducationEvent.VALUE_ELEMENTARY_PUBLIC*2, result);
    }
        @Test
    public void testGetEducationValueByMonthPrivate() {
        //System.out.println("getEducationValueByMonth");
        Date current = DateUtil.getDateByMonth(2013, 5);
        EducationEvent instance = new EducationEvent(DateUtil.getDateByMonth(2013, 1),true);
        
        int result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_KINDER_PUBLIC, result);

        current = DateUtil.getDateByMonth(2015, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_KINDER_PUBLIC*2 , result);

        current = DateUtil.getDateByMonth(2016, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_ELEMENTARY_PUBLIC +EducationEvent.VALUE_KINDER_PUBLIC*1 , result);

        current = DateUtil.getDateByMonth(2021, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_ELEMENTARY_PUBLIC*3, result);

        current = DateUtil.getDateByMonth(2022, 4);
        result = instance.getEducationValueByMonth(current);
        assertEquals(EducationEvent.VALUE_JUNIOR_PRIVATE*1 + EducationEvent.VALUE_ELEMENTARY_PUBLIC*2, result);

    }
}
