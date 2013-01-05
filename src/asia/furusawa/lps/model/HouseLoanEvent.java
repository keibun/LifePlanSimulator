/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 *
 * @author hiro
 */
public class HouseLoanEvent extends LPSEvent {
    private final int loanBodyValue;
    private final int numberOfPaymentByYear;
    private final double interest;
    private int restOfLoanValue;
    private int commulativePayedValue;
    private int paymentCount;
    //private int paymentByMonth;
    private LoanType type;

    /**
     * @return the loanBodyValue
     */
    public int getLoanBodyValue() {
        return loanBodyValue;
    }

    /**
     * @return the numberOfPaymentByYear
     */
    public int getNumberOfPaymentByYear() {
        return numberOfPaymentByYear;
    }

    /**
     * @return the interest
     */
    public double getInterest() {
        return interest;
    }

    /**
     * @return the restOfLoanValue
     */
    public int getRestOfLoanValue() {
        return restOfLoanValue;
    }

    /**
     * @return the commulativePayedValue
     */
    public int getCommulativePayedValue() {
        return commulativePayedValue;
    }

    /**
     * @return the paymentCount
     */
    public int getPaymentCount() {
        return paymentCount;
    }
    public enum LoanType {
        EQUAL_BODY, EQUAL_INTEREST
    }
    /**
     * 
     * @param whenOccured 借入日.
     * @param type
     * @param loanBodyValue initial loan value.
     * @param interest percent devided by 100.
     * @param numberOfPaymentByYear  1 means you pay 12 times monthly.
     */
    public HouseLoanEvent(Date whenOccured, LoanType type, int loanBodyValue, double interest, int numberOfPaymentByYear ){
        super(whenOccured);
        this.loanBodyValue = loanBodyValue;
        this.restOfLoanValue = loanBodyValue;
        this.interest = interest;
        this.numberOfPaymentByYear = numberOfPaymentByYear;
        this.paymentCount = 1;
        this.type = type;
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setHouseLoan(this);
    }
   /**
    * 月毎返済額の計算（クラス内部状態の変更は行わない）.
    * @return 
    */
    public int calcPaymentByMonth(){
        if (paymentCount > this.numberOfPaymentByYear*12){
            return 0;
        }
        if (type == LoanType.EQUAL_BODY){
//　　　　　　　　　　　　　借入金額 
//　　　　　　　各回返済額＝―――――　×｛１＋（返済回数－計算対象の回数＋１）×利率｝ 
//　　　　　　　　　　　　　返済回数             
            //double result = this.loanBodyValue / this.numberOfPaymentByYear * (1+(this.numberOfPaymentByYear - paymentCount + 1)*interest);
            double result = this.getLoanBodyValue() / (this.getNumberOfPaymentByYear()*12) * (1+(this.getNumberOfPaymentByYear()*12 - getPaymentCount() + 1)*getInterest()/12);
            return  BigDecimal.valueOf(result).setScale(0, RoundingMode.HALF_UP).intValue();
        } 
        else {
            throw new RuntimeException("元利均等形式はまだ未サポート");
        }
        
    }
    public int calcLoanBodyValueByMonth(){
        BigDecimal result = new BigDecimal( this.loanBodyValue ).divide(new BigDecimal( this.numberOfPaymentByYear),2, RoundingMode.HALF_UP).divide(new BigDecimal(12),2,RoundingMode.HALF_UP);
        
        return result.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
    }
    public void payByMonth(){
        if (paymentCount > this.numberOfPaymentByYear*12){
            return;
        }
        int paymentOfThisMonth = calcPaymentByMonth();
        commulativePayedValue += paymentOfThisMonth;
        restOfLoanValue -= calcLoanBodyValueByMonth();
        paymentCount++;
        
        if(restOfLoanValue < 0){
            restOfLoanValue = 0;
        }
    }
    
}
