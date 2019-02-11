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
    public WorkTime()
    {      }

    public void addVisitTime(long visitTime)
    {
        Date visit = new Date(visitTime);
        TimePeriod newPeriod = new TimePeriod(visit, visit);
        for(TimePeriod period : periods)
        {
            if(period.compareTo(newPeriod) == 0)
            {
                period.appendTime(visit);
                return;
            }
        }
        periods.add(new TimePeriod(visit, visit));
    }

    public String toString()
    {
        String line = "";
        for(TimePeriod period : periods)
        {
            if(!line.isEmpty()) {
                line += ", ";
            }
            line += period;
        }
        return line;
    }
}
