/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import asia.furusawa.lps.util.DateUtil;
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
public class HouseLoanEventTest {
    
    public HouseLoanEventTest() {
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
     * Test of handle method, of class HouseLoanEvent.
     */
//    @Test
//    public void testHandle() {
//        //System.out.println("handle");
//        CapitalSimulator simulator = null;
//        HouseLoanEvent instance = null;
//        instance.handle(simulator);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of calcPaymentByMonth method, of class HouseLoanEvent.
     */
    @Test
    public void testCalcPaymentByMonth() {
        //System.out.println("calcPaymentByMonth");
        HouseLoanEvent instance = new HouseLoanEvent(DateUtil.getDateByMonth(2013,1), HouseLoanEvent.LoanType.EQUAL_BODY, 1000*10000, 0.1, 1);
        int result = instance.calcPaymentByMonth();
        assertEquals(916666, result);
        instance.payByMonth();
        result = instance.calcPaymentByMonth();
        
        assertEquals(909722, result);
        

    }

    /**
     * Test of payByMonth method, of class HouseLoanEvent.
     */
    @Test
    public void testPayByMonth() {
        //System.out.println("payByMonth");
        final int initial = 1000*10000;
        HouseLoanEvent event = new HouseLoanEvent(DateUtil.getDateByMonth(2013,1), HouseLoanEvent.LoanType.EQUAL_BODY, initial, 0.1, 1);;
        int paymentByMonth = event.calcPaymentByMonth();
        event.payByMonth();
        assertEquals(2,event.getPaymentCount() );
        assertEquals(initial - event.calcLoanBodyValueByMonth() , event.getRestOfLoanValue());
        assertEquals(paymentByMonth,event.getCommulativePayedValue());
        
        int paymentOfNextMonth = event.calcPaymentByMonth();
        event.payByMonth();
        assertEquals(3,event.getPaymentCount() );
        assertEquals(initial - event.calcLoanBodyValueByMonth()*2 , event.getRestOfLoanValue());
        assertEquals(paymentByMonth+paymentOfNextMonth,event.getCommulativePayedValue());

    }

    @Test
    public void testPaymentLimit(){
        final int initial = 1000*10000;
        HouseLoanEvent event = new HouseLoanEvent(DateUtil.getDateByMonth(2013,1), HouseLoanEvent.LoanType.EQUAL_BODY, initial, 0.1, 2);;
        
        for(int i = 0; i < 12 * 2; i++){
            event.payByMonth();
        }
        assertEquals(0, event.getRestOfLoanValue());
        
        event.payByMonth();
        assertEquals(0, event.getRestOfLoanValue());
        
    }
    @Test
    public void testCalcLoanBodyValueByMonth(){
         final int initial = 1000*10000;
        HouseLoanEvent event = new HouseLoanEvent(DateUtil.getDateByMonth(2013,1), HouseLoanEvent.LoanType.EQUAL_BODY, initial, 0.1, 1);;
        assertEquals(833333, event.calcLoanBodyValueByMonth());
    }
}