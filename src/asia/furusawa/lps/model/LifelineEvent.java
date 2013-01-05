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
    private static final int water = 6000;
    private static final int COMMUNICATION = 10000;
    private static final int food = 0;
    
    
    public LifelineEvent (Date whenOccured){
        super(whenOccured);
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setLifeline(this);
    }
    
    public int getLifelineValueByMonth(){
        return 0;
    }
    
}
