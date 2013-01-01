/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import asia.furusawa.lps.model.CapitalSimulator;
import asia.furusawa.lps.model.LPSEvent;
import java.util.Date;

/**
 *
 * @author hiro
 */
public class RetirementEvent extends LPSEvent {

    public RetirementEvent(Date dateByMonth) {
        super(dateByMonth);
        
    }

    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setEmployment(null);
    }
    
}
