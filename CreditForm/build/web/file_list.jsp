<%-- 
    Document   : index
    Created on : 17 февр. 2019 г., 9:40:30
    Author     : sendel
--%>
<%@page import="sendel.bank.FileList"%>
<%@page import="sendel.bank.FileUpload"%>
<%@page import="sendel.bank.RequestTransformer"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="sendel.bank.CreditDecision"%>
<%@page import="sendel.bank.FormCreditHandler"%>
<%@page import="sendel.bank.JsonHandler"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");
//filePath with end slash
ServletContext context = getServletContext();

//set context.setAttribute fileList
FileList fileList = new FileList(context);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Список загруженных файлов М15</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="js/jquery/jquery-ui.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <section class="section">
            <div class="container-fluid">
                <h1 class="title">
                   <i class="fas fa-file-download"></i> Список загруженных файлов
                </h1>
                <div class="row">
                    <div class="col-6">
                        <a href="index.jsp" class="btn btn-info btn-sm" role="button">Заявка на кредит</a>
                        <a href="file_list.jsp" class="btn btn-info btn-sm" role="button">Список загруженых файлов</a>
                    </div>
                    <div class="col-6 text-right">
                        <p class="subtitle">
                            <i class="fas fa-unlock-alt"></i> <strong>Ваши данные надежно защищены!</strong>
                        </p>
                    </div>
                </div>
                <hr/>

            </div>

            <div class="container">


 

<div class="list-group filelist">
    <c:forEach items="${fileList}" var="files"  varStatus="сounter">
        <a role="button" class="list-group-item list-group-item-action" href="download.jsp?f=${files.key}">
            <div id="file-" class="filetitle" width="300px">${сounter.count}. ${files.key}</div> 
            <c:forEach items="${files.value}" var="info"  varStatus="сounter1">
                <div class="info">${info}</div>
                </c:forEach>
            
        </a>
</c:forEach>
</div>
            </div>
        
    </section>
    <hr>
    <footer><p>&nbsp;2019 - Банк</p></footer>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/jquery/jquery-ui.min.js"></script>
    <script src="js/sendel.creditform.js"></script>

</body>
</html>

