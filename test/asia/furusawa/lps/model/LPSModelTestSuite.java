/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author hiro
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({asia.furusawa.lps.model.EmploymentEventTest.class,
    asia.furusawa.lps.model.CapitalSimulatorTest.class,
    asia.furusawa.lps.model.ChildTest.class,
    HouseLoanEventTest.class,
    EducationEventTest.class})
public class LPSModelTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
