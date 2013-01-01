/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import asia.furusawa.lps.util.DateUtil;
import java.util.Date;

/**
 *
 * @author hiro
 */
public class RentalHelpEvent extends LPSEvent {
    public static final int RENTAL_HELP_NSSOL = 40000;
    public RentalHelpEvent(Date whenOccured){
        super(whenOccured);
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setRentalHelp(this);
    }
    public int getRentalHelpValue(Date current){
        if (current.before(DateUtil.getDateByMonth(1976+40, 11))){
            return RentalHelpEvent.RENTAL_HELP_NSSOL;
        }
        return 0;
    }
}
