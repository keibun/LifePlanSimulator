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
public abstract class LPSEvent {
    private String name;
    private Date whenOccured;
    protected CapitalSimulator simulator;
    private LPSEvent(){}
    
    public LPSEvent(Date date){
        this.whenOccured = date;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    public abstract void handle(CapitalSimulator simulator);
    
    public Date getWhenOccured(){
        return whenOccured;
    }
    
    //public abstract void process();
}
