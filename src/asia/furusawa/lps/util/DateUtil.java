/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.furusawa.lps.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author hiro
 */
public class DateUtil {
    private static final Calendar calendar = Calendar.getInstance();
    public static synchronized Date getDateByMonth(int year, int month){
        calendar.clear();
        calendar.set(year, month,1, 0, 0, 0);
        return calendar.getTime();
    }
}
