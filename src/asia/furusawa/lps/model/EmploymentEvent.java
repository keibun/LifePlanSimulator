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
public class EmploymentEvent extends LPSEvent{
    
    public EmploymentEvent(Date whenOccured){
        super(whenOccured);
        setName("雇用");
    }
    public int getSalaryByMonth(){
        return SALALY_BY_MONTH;
    }
    private static final int taxAmountByYear = 850000;
    private static final int salaryAmountByYear = 9000000;
    private static final int socialInsuranceAmountByYear = 1000000;
    public static final int SALALY_BY_MONTH = (salaryAmountByYear - taxAmountByYear - socialInsuranceAmountByYear)/12/10000*10000;

    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setEmployment(this);
        this.simulator = simulator;
    }

}
