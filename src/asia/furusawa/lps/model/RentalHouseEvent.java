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
public class RentalHouseEvent extends LPSEvent {
    private int renteHouseValue;
    public static final int OBERGE_RENTAL_HOUSE_VALUE = 126000;
    public RentalHouseEvent(Date whenOccured){
        super(whenOccured);
        setRentalHouseValue(RentalHouseEvent.OBERGE_RENTAL_HOUSE_VALUE);
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setRentalHouse(this);
        this.simulator = simulator;
    }


    public int getRentalFeeByMonth() {
        return getRentalHouseValue();
    }

    /**
     * @return the renteHouseValue
     */
    public int getRentalHouseValue() {
        return renteHouseValue;
    }

    /**
     * @param renteHouseValue the renteHouseValue to set
     */
    public void setRentalHouseValue(int renteHouseValue) {
        this.renteHouseValue = renteHouseValue;
    }
    
}
