/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.model;

import asia.furusawa.lps.util.DateUtil;
import java.util.Date;

/**
 *
 * @author hiropon
 */
public class Child {
    public Child(Date bornDate){
        this.bornDate = bornDate;
    }
    private Date bornDate;



    /**
     * 4月始まりでの年齢を返す.
     * 2001年2月生まれなら2002年5月の年齢は
     * @param current
     * @return 
     */
    public int getAge(Date current){
        int currentTerm = DateUtil.getYearByTerm(current);
        int bornTerm = DateUtil.getYearByTerm(bornDate);
        return currentTerm - bornTerm;
    }
}
