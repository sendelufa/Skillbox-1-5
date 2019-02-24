<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <a href="filelist" class="btn btn-info btn-sm" role="button">Список принятых заявок</a>
                        <a href="browsers" class="btn btn-info btn-sm" role="button">Статистика браузеров</a>
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
    <c:forEach items="${fileList.files}" var="file"  varStatus="сounter">
        <a role="button" class="list-group-item list-group-item-action" 
           <c:if test="${file.value.fileSize != ''}">href="download?f=${file.value.fileName}"
               </c:if>>
            <div id="file-" class="filetitle" width="300px">${сounter.count}. ${file.value.creditInfo}</div> 
           
                <div class="info">${file.value.giveCredit}</div>
                
                <div class="info">${file.value.fileName}</div>
                <div class="info">${file.value.fileSize}</div>
                <div class="info">${file.value.fileDate}</div>
               
            
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

