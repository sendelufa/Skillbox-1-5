package sendel.voteanalyzer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Danya on 24.02.2016.
 */
public class TimePeriod implements Comparable<TimePeriod> {
    private Date firstTime;
    private Date LastTime;

    private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static TreeSet<Date> workingDates = new TreeSet<>();

    /**
     * Time period within one day
     *
     * @param visitDate
     */


    public TimePeriod(Date visitDate) {
        this.firstTime = visitDate;
        this.LastTime = visitDate;
        addWorkingDates(visitDate);
        


    }
    
    private void addWorkingDates(Date visitDate){
        //Date without time
        workingDates.add(getDateWithoutTime(visitDate));
    }

    public static TreeSet<Date> getWorkingDates() {
        return workingDates;
    }
    
    

    public void appendTime(Date visitDate) {
        if (!dayFormat.format(firstTime).equals(dayFormat.format(new Date(visitDate.getTime()))))
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        long visitTime = visitDate.getTime();
        if (visitTime < firstTime.getTime()) {
            firstTime = visitDate;
        }
        if (visitTime > LastTime.getTime()) {
            LastTime = visitDate;
        }
    }

    public String toString() {
        String from = dateFormat.format(this.firstTime);
        String to = timeFormat.format(this.LastTime);
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period) {
        Date current = new Date();
        Date compared = new Date();
        try {
            current = dayFormat.parse(dayFormat.format(firstTime));
            compared = dayFormat.parse(dayFormat.format(period.firstTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return current.compareTo(compared);
    }

    public boolean isSameDay(long visitTime){
        Date current = new Date();
        Date compared = new Date();
        try {
            current = dayFormat.parse(dayFormat.format(firstTime));
            compared = dayFormat.parse(dayFormat.format(new Date(visitTime)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return current.compareTo(compared) == 0;
    } 
    
    public static boolean isSameDay(Date first, Date second){
        Date firstC = getDateWithoutTime(first);
        Date secondC = getDateWithoutTime(second);
        return firstC.equals(secondC);
    }
    
    private static Date getDateWithoutTime(Date date){
        Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
    }
}
