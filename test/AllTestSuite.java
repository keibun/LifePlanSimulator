/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asia.furusawa.lps.model.LPSModelTestSuite;
import asia.furusawa.lps.util.LPSUtilTestSuite;
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
@Suite.SuiteClasses({ LPSModelTestSuite.class, LPSUtilTestSuite.class} )
public class AllTestSuite {

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
