/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.voteanalyzer;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author sendel
 */
public class ParserVotes {
    private static Handler handler;
    public static void parseFile(String fileName) throws Exception
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File(fileName), handler);
        //handler.printResults();
        TreeSet<Date> workingDates = TimePeriod.getWorkingDates();
        for (Date date : workingDates) {
            System.out.println(date.toString());
        }
       
    }
    
    public static HashMap<Integer, WorkTime> getVoteStationWorkTimes(){
        return handler.voteStationWorkTimes;
    }
}
