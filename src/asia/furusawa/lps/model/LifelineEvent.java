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
public class LifelineEvent extends LPSEvent {

    private static final int GAS = 6000;
    private static final int ELECTRORIC = 8000;
    private static final int WATER = 6000;
    private static final int COMMUNICATION = 10000;
    private static final int INNER_FOOD = 60000;
    private static final int OUTER_FOOD = 10000;
    private static final int HIROPON_FOOD = 20000;
    private static final int OTHERS = 50000;
    
    private int commulativeLifelineValue = 0;
    
    
    public LifelineEvent (Date whenOccured){
        super(whenOccured);
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setLifeline(this);
    }
    
    public int getLifelineValueByMonth(){
        return GAS+ELECTRORIC+WATER+COMMUNICATION+INNER_FOOD+OUTER_FOOD+HIROPON_FOOD+OTHERS;
    }

    void payByMonth() {
        this.commulativeLifelineValue += getLifelineValueByMonth();
    }
    public int getCommulativeLifelineValue(){
        return this.commulativeLifelineValue;
    }
    
}
