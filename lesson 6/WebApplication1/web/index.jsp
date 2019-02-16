<%-- 
    Document   : index
    Created on : 13 февр. 2019 г., 9:40:30
    Author     : sendel
--%>

<%@page import="sendel.voteanalyzer.*"%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta contentType="text/html" pageEncoding="UTF-8">
        <title>JSP Page Module 14</title>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <h1>Время работы участков голосования.</h1>
        <%
    //создание контроллера для управления выводом
    Controller ctrl;
    //путь к файлу
    String filepath = getServletContext().getRealPath("/") + "WEB-INF/data-0.2M.xml";
    try {
    ctrl = new Controller(filepath);
    
    %>
      <%= ctrl.getStationsWorkPeriodTable("voterStations") %>
      <%
          }
    catch (Exception e){
        String str = "<p>Ошибка обработки файла <b>" + filepath + "</b>!</p>";
                out.println(str);
                       
    }%>
    </body>
</html>
