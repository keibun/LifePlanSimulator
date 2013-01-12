/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import java.util.Date;

/**
 *
 * @author hiropon
 */
public class RentalQuitEvent extends LPSEvent {

    public RentalQuitEvent(Date whenOccured){
        super(whenOccured);
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setRentalHouse(null);
    }
    
}
