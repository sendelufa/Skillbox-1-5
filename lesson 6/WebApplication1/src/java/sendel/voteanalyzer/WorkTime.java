package sendel.voteanalyzer;

import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Danya on 24.02.2016.
 */
public class WorkTime
{
    private TreeSet<TimePeriod> periods = new TreeSet<>();

    /**
     * Set of TimePeriod objects
     */
    public void addVisitTime(long visitTime)
    {
        Date visit = new Date(visitTime);
        for(TimePeriod period : periods)
        {
            if(period.isSameDay(visitTime))
            {
                period.appendTime(visit);
                return;
            }
        }
        periods.add(new TimePeriod(visit));
    }

    public String toString()
    {
        StringBuilder line = new StringBuilder();
        for(TimePeriod period : periods)
        {
            if(!(line.length() == 0)) {
                line.append(", ");
            }
            line.append(period);
        }
        return line.toString();
    }
    
    public TreeSet<TimePeriod> getPeriods(){
        return periods;
    }
}
