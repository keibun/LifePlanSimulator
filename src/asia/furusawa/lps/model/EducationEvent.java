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
public class EducationEvent extends LPSEvent{
    public static final int YEAR_START_SCHOOL_KINDER = 4;
    public static final int YEAR_END_SCHOOL_KINDER = 6;
    public static final int YEAR_START_SCHOOL_ELEMENTARY = 7;
    public static final int YEAR_END_SCHOOL_ELEMENTARY = 12;
    public static final int YEAR_START_SCHOOL_JUNIOR = 13;
    public static final int YEAR_END_SCHOOL_JUNIOR = 15;
    public static final int YEAR_START_SCHOOL_HIGH = 16;
    public static final int YEAR_END_SCHOOL_HIGH = 18;
    public static final int YEAR_START_SCHOOL_BUCHEROR = 19;
    public static final int YEAR_END_SCHOOL_BUCHEROR = 22;        
    public static final int YEAR_START_SCHOOL_MASTER = 23;
    public static final int YEAR_END_SCHOOL_MASTER = 24;
    
    public static final int VALUE_KINDER_PUBLIC = 19600;
    public static final int VALUE_KINDER_PRIVATE = 42500;

    public static final int VALUE_ELEMENTARY_PUBLIC = 26100;
    public static final int VALUE_ELEMENTARY_PRIVATE = 114400;
    
    public static final int VALUE_JUNIOR_PUBLIC = 39200;
    public static final int VALUE_JUNIOR_PRIVATE = 106100;
    
    public static final int VALUE_HIGH_PUBLIC = 43100;
    public static final int VALUE_HIGH_PRIVATE = 86100;
    
    public static final int VALUE_UNIV_PUBLIC = 59800;
    public static final int VALUE_UNIV_PRIVATE = 121000;

    private boolean isPrivate = false;
    private static Child[] children = {new Child(DateUtil.getDateByMonth(2009, 5)), new Child(DateUtil.getDateByMonth(2011, 5)), new Child(DateUtil.getDateByMonth(2013, 5))};    
    private static Child[] privateChildren = {new Child(DateUtil.getDateByMonth(2009, 5)), new Child(DateUtil.getDateByMonth(2011, 5)), new Child(DateUtil.getDateByMonth(2013, 5))};    
//    public EducationEvent(Date whenOccured){
//        super(whenOccured);
//    }
        public EducationEvent(Date whenOccured, boolean isPrivate){
        super(whenOccured);
        this.isPrivate = isPrivate;
    }
    @Override
    public void handle(CapitalSimulator simulator) {
        simulator.setEducation(this);
    }
    
    public int getEducationValueByMonth(Date current){
        int sum = 0;
        for ( Child aChild: children){
            sum += getEducationValueByMonth(current, aChild);
        }
        return sum;
    }
    public int getEducationValueByMonth(Date current, Child aChild){
       // System.out.format("%s, %s\n", current, aChild.getAge(current));
        int age = aChild.getAge(current);
//System.out.println("age:"+new Integer(age).toString());
        if (age >= YEAR_START_SCHOOL_KINDER && age <= YEAR_END_SCHOOL_KINDER){
//            if (isPrivate){
//             return VALUE_KINDER_PRIVATE;   
//            }
            return VALUE_KINDER_PUBLIC;
        }
        if (age >= YEAR_START_SCHOOL_ELEMENTARY && age <= YEAR_END_SCHOOL_ELEMENTARY){
//            if (isPrivate){
//             return VALUE_ELEMENTARY_PRIVATE;   
//            }
            return VALUE_ELEMENTARY_PUBLIC;
        }
        if (age >= YEAR_START_SCHOOL_JUNIOR && age <= YEAR_END_SCHOOL_JUNIOR){
            if (isPrivate){
//                System.out.println("junior private");
             return VALUE_JUNIOR_PRIVATE;   
            }            
//                            System.out.println("junior public");
            return VALUE_JUNIOR_PUBLIC;
        }
        if (age >= YEAR_START_SCHOOL_HIGH && age <= YEAR_END_SCHOOL_HIGH){
            if (isPrivate){
             return VALUE_HIGH_PRIVATE;   
            }
            return VALUE_HIGH_PUBLIC;
        }
        if (age >= YEAR_START_SCHOOL_BUCHEROR && age <= YEAR_END_SCHOOL_BUCHEROR){
            return VALUE_UNIV_PUBLIC;
        }
        if (age >= YEAR_START_SCHOOL_MASTER && age <= YEAR_END_SCHOOL_MASTER){
            return VALUE_UNIV_PUBLIC;
        }
//System.out.println("edu: return 0")        ;
        return 0;
    }
}
