/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hiro
 */
public class CapitalSimulator {
    private static final Logger logger = LoggerFactory.getLogger(CapitalSimulator.class);
    public static final int INITIAL_ASSET_CASH = 11000000;
    private Date simulateFromMonth;
    private Date simulateToMonth;
    public static final int DURATION_CALENDAR_UNIT = Calendar.MONTH;
    private List<LPSEvent> lpsEvents = new ArrayList<>();
    private EmploymentEvent employment;
    private RentalHouseEvent rentalHouse;
    private RentalHelpEvent rentalHelp;
    private HouseLoanEvent houseLoan;
    
    public List<CapitalSummaryTrack> simulate() {
        List<CapitalSummaryTrack> tracks = new ArrayList<>();
        Date currentSimulateDate = simulateFromMonth;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentSimulateDate);
        
        //Asset cash = new Asset();
        //cash.setName(Asset.TYPE.CASH.name());
        //cash.setValue( INITIAL_ASSET_CASH);
        int cash = INITIAL_ASSET_CASH;
        while (true){
            //logger.debug(currentSimulateDate.toString());
            if (  ! currentSimulateDate.toString().equals(simulateToMonth.toString()) &&
                currentSimulateDate.after(simulateToMonth) ) { //endign condition
                break;
            }
            CapitalSummaryTrack track = new CapitalSummaryTrack();

            
            for (LPSEvent event: lpsEvents){ // set event to simulator
                logger.debug(event.getWhenOccured().toString(), currentSimulateDate.toString());
                if (event.getWhenOccured().toString().equals(currentSimulateDate.toString())) {
                  event.handle(this);
                }
            }
            //newCash.setName(Asset.TYPE.CASH.name());
            int newCash = cash;
            
            if ( employment != null ){
               Asset income = new Asset();
               newCash +=employment.getSalaryByMonth();
               track.setSalaryByTrack(employment.getSalaryByMonth());
               if (tracks.isEmpty()){
                   track.setCommulativeSalary(employment.getSalaryByMonth());
                   
               }
               else {
                   track.setCommulativeSalary(tracks.get(tracks.size()-1).getCommulativeSalary()+employment.getSalaryByMonth());
                   
               }
            }
            else {
                 if (!tracks.isEmpty()){
                   track.setCommulativeSalary(tracks.get(tracks.size()-1).getCommulativeSalary());
         
                 }
            }
            
            if ( rentalHouse != null){
                if ( rentalHelp != null){
                    newCash = newCash -  rentalHouse.getRentalFeeByMonth() + rentalHelp.getRentalHelpValue(currentSimulateDate);
                    track.setRentalFee(rentalHouse.getRentalFeeByMonth()-rentalHelp.getRentalHelpValue(currentSimulateDate));
                }
                else {
                    newCash -= rentalHouse.getRentalFeeByMonth();
                    track.setRentalFee(rentalHouse.getRentalFeeByMonth());
                }
                
                
               if (tracks.isEmpty()){
                track.setCommulativeRentalFee(rentalHouse.getRentalFeeByMonth());
                   
               }
               else {
                track.setCommulativeRentalFee(tracks.get(tracks.size()-1).getCommulativeRentalFee()+rentalHouse.getRentalFeeByMonth());
                   
               }            
            }
            else {
                if (!tracks.isEmpty()){
                track.setCommulativeRentalFee(tracks.get(tracks.size()-1).getCommulativeRentalFee());
    
                }
            }
            
//            if ( houseLoan != null ){
//                if (tracks.isEmpty()){
//                CapitalSummaryTrack prevTrack = tracks.get(tracks.size()-1);
//                int houseLoanValue = houseLoan.calcHouseLoanValue(prevTrack.getHouseLoanRest());
//                track.setCommulativeHouseLoan(prevTrack.getCommulativeHouseLoan()+houseLoanValue);
//                   
//               }
//               else {
//                track.setCommulativeHouseLoan();
//                   
//               }                           
//            }
//            else {
//                 if (!tracks.isEmpty()){
//                   track.setCommulativeHouseLoan(tracks.get(tracks.size()-1).getCommulativeHouseLoan());
//         
//                 }
//            }            
            if (houseLoan != null) {
                int payment = houseLoan.calcPaymentByMonth();
                newCash -= payment;
                //logger.info(new Integer(newCash).toString());
                //logger.info("\t"+new Integer(payment).toString());
                houseLoan.payByMonth();
                track.setCommulativeHouseLoan(houseLoan.getCommulativePayedValue());
                
            }
            track.setAssetCash(newCash);
            tracks.add(track);
                        
            cash = newCash; // save for next simulation
            calendar.add(CapitalSimulator.DURATION_CALENDAR_UNIT, 1); // next time unit month.
            currentSimulateDate = calendar.getTime(); // next simulation date
            logger.debug(currentSimulateDate.toString());
        }
        return tracks;
    }

    public void setDuration(Date from, Date to) {
        this.simulateFromMonth = from;
        this.simulateToMonth = to;
    }
    public void addLPSEvent(LPSEvent event){
        lpsEvents.add(event);
    }

    public void setEmployment(EmploymentEvent employment) {
        this.employment = employment;
    }

    public void setRentalHouse(RentalHouseEvent aThis) {
        this.rentalHouse = aThis;
    }

    public void setRentalHelp(RentalHelpEvent event) {
        this.rentalHelp = event;
    }
    public RentalHelpEvent getRentalHelp(){
        return this.rentalHelp;
    }



    /**
     * @return the houseLoan
     */
    public HouseLoanEvent getHouseLoan() {
        return houseLoan;
    }

    /**
     * @param houseLoan the houseLoan to set
     */
    public void setHouseLoan(HouseLoanEvent houseLoan) {
        this.houseLoan = houseLoan;
    }
}
