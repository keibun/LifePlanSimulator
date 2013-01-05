/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import asia.furusawa.lps.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class CapitalSimulatorTest {
    
    public CapitalSimulatorTest() {
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

    @Test
    public void testSimulateDuration() {
        CapitalSimulator simulator = new CapitalSimulator();
        Date from = createFromDate();
        Date to = createToDateMax();
        simulator.setDuration(from, to);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        
        assertEquals(summaries.size(), 35*12);

    }
    @Test
    public void testSimulateEmploymentEvent(){
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(createFromDate(), DateUtil.getDateByMonth(2013, 3));// 3 months
        EmploymentEvent event = new EmploymentEvent(createFromDate());
        simulator.addLPSEvent(event);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        
//        for(CapitalSummaryTrack summary : summaries){
//           List<Asset> assets = summary.getAssets();
//           for(Asset asset: assets){
//              // sum += asset.getValue();
//               //System.out.format("%s: %d\n", asset.getName(),asset.getValue());
//           }
//        }
        assertEquals(3, summaries.size()); // 3 track for 3 month duration
        assertEquals(summaries.get(summaries.size()-1).getAssetCash(), CapitalSimulator.INITIAL_ASSET_CASH + EmploymentEvent.SALALY_BY_MONTH * 3 );
    }
    @Test
    public void testSimulateRentalHouseEvent(){
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(createFromDate(), DateUtil.getDateByMonth(2013, 6)); // 6 months
        RentalHouseEvent event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 4));
        simulator.addLPSEvent(event);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        assertEquals(6,summaries.size());
        assertEquals(summaries.get(summaries.size()-1).getAssetCash(),simulator.INITIAL_ASSET_CASH - RentalHouseEvent.OBERGE_RENTAL_HOUSE_VALUE*3);
    }
    
    @Test
    public void testSimulationEmploymentAndHouseEvent(){
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(createFromDate(), DateUtil.getDateByMonth(2013, 6)); // 6 months
        LPSEvent event = new EmploymentEvent(createFromDate());
        simulator.addLPSEvent(event);
        event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 4));
        simulator.addLPSEvent(event);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        assertEquals(6, summaries.size());
        assertEquals(CapitalSimulator.INITIAL_ASSET_CASH + EmploymentEvent.SALALY_BY_MONTH * 6  - RentalHouseEvent.OBERGE_RENTAL_HOUSE_VALUE*3,summaries.get(summaries.size()-1).getAssetCash());
        
    }
    
    @Test
    public void testSimulationRentalHelp(){
         CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(createFromDate(), DateUtil.getDateByMonth(2013, 6)); // 6 months
        LPSEvent event = new RentalHelpEvent(createFromDate());
        simulator.addLPSEvent(event);
        event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 4));
        simulator.addLPSEvent(event);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        assertEquals(summaries.size(), 6);
        assertEquals(CapitalSimulator.INITIAL_ASSET_CASH   - RentalHouseEvent.OBERGE_RENTAL_HOUSE_VALUE*3 + RentalHelpEvent.RENTAL_HELP_NSSOL *3 ,summaries.get(summaries.size()-1).getAssetCash());
       
    }
    @Test
    public void testSimulationHouseLoan(){
           CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(createFromDate(), DateUtil.getDateByMonth(2014, 5)); // 6 months
        final int initial = 1000*10000;
        LPSEvent event = new HouseLoanEvent(DateUtil.getDateByMonth(2013,4), HouseLoanEvent.LoanType.EQUAL_BODY, initial, 0.1, 1);;

        simulator.addLPSEvent(event);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        assertEquals(17,summaries.size() );
        assertEquals(CapitalSimulator.INITIAL_ASSET_CASH - 10541661 -1  ,summaries.get(summaries.size()-1).getAssetCash());
             
    }
    @Test
    public void testSimulationLifeline(){
           CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(createFromDate(), DateUtil.getDateByMonth(2013, 6)); // 6 months
        LPSEvent event = new LifelineEvent(createFromDate());
        simulator.addLPSEvent(event);
        List<CapitalSummaryTrack> summaries = simulator.simulate();
        assertEquals(summaries.size(), 6);
        assertEquals(CapitalSimulator.INITIAL_ASSET_CASH   - 170000*6, summaries.get(summaries.size()-1).getAssetCash());
      
    }
    private Date createFromDate(){
                Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 0, 1, 0, 0, 0);
       // System.out.println(calendar.getTime());
        return calendar.getTime();
    }
        private Date createToDateMax(){
                Calendar calendar = Calendar.getInstance();
               calendar.set(2013+34,11,1,0,0,0);

        return calendar.getTime();
    }
}
