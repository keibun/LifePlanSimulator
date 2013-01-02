/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import java.util.Date;

/**
 *
 * @author hiro
 */
public class HouseLoanEvent extends LPSEvent {

    public HouseLoanEvent(Date whenOccured ){
        super(whenOccured);
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setHouseLoan(this);
    }
    
}
