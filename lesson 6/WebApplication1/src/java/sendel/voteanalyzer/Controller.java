/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.voteanalyzer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author sendel
 */
public class Controller {
    private HashMap<Integer, WorkTime> stations = new HashMap<>();
    
    private SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
    private SimpleDateFormat comparePeriods = new SimpleDateFormat ("yyyy.MM.dd");
    
    public Controller(String fileName) throws Exception{
        ParserVotes.parseFile(fileName);
        stations.putAll(ParserVotes.getVoteStationWorkTimes());
        
    }
    
    public String getStationsWorkPeriodTable(String elementId){
        TimePeriod.getWorkingDates();
        StringBuilder resultHtml = new StringBuilder();
        resultHtml.append("<table id=\"" + elementId + "\"><tr>");
        resultHtml.append("<td> </td>");
        //Поиск уникальных дней работы участков
        for (Date date : TimePeriod.getWorkingDates()){
            resultHtml.append("<th>" + ft.format(date) + "</th>");
        }
        resultHtml.append("</tr>\n");

        //обходим каждый участок и проверяем соответсвие текущего дня записи о времени
        for (Integer stationId : stations.keySet()) {
            resultHtml.append("<tr class=\"stationNumber\"><th>Участок № " + stationId + "</th>");
            for (Date date : TimePeriod.getWorkingDates()){
                boolean isDayHavePeriod = false;
                for (TimePeriod period : stations.get(stationId).getPeriods())
                { 
                     if (period.toString().split(" ")[0].equals(comparePeriods.format(date))){
                            resultHtml.append("<td>" + period.toString().split(" ")[1] + "</td>");
                            isDayHavePeriod = true;
                            break;
                    }
                }
                
                if(!isDayHavePeriod){
                resultHtml.append("<td>-</td>");
            }
        }
            resultHtml.append("</tr>\n");
        }
        resultHtml.append("</table>");
        return resultHtml.toString();
        }
    
}
