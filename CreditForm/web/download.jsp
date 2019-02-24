<%-- 
    Document   : download
    Created on : 21 февр. 2019 г., 11:23:54
    Author     : sendel
--%>
<%@page import="sendel.bank.FileUpload"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="sendel.bank.FormCreditHandler"%>
<%@page import="sendel.bank.JsonHandler"%>
<%@page contentType="application/octet-stream" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");

    ServletContext context = getServletContext();

    String fileName = request.getParameter("f");

    File file = new File(context.getInitParameter("filePath") + fileName);

    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=\""
            + FileUpload.getContentDespositionFilename(file.getName()) + "\"");
    response.setCharacterEncoding("UTF-8");

    try (InputStream input = new FileInputStream(file);
            OutputStream output = response.getOutputStream()) {
        //give internet browser lenght of file
        response.setContentLength((int) file.length());

        byte[] buf = new byte[4096];
        int length;
        while ((length = input.read(buf)) >= 0) {
            output.write(buf, 0, length);
        }
    }


%>


