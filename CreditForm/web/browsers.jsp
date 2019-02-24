<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Статистика посещений данной страницы М15</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="js/jquery/jquery-ui.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <section class="section">
            <div class="container-fluid">
                <h1 class="title">
                   <i class="fas fa-file-download"></i> Статистика посещений данной страницы
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


 

<table class="table table-striped table-dark">
      <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Браузер</th>
      <th scope="col">Кол-во посещений</th>
      <th scope="col">% от общего</th>
    </tr>
  </thead>
   <tbody>
       <c:forEach items="${browsers}" var="browser"  varStatus="сounter">
           <tr>
               <th scope="row">${сounter.count}</th>
               <td> ${browser.key}</td> 
               <td>${browser.value}</td> 
               <td> 
                   <fmt:formatNumber type = "number" maxIntegerDigits = "3" value = "${(browser.value / summ_visits)*100}" />
               </td>


           </tr>
       </c:forEach>
  </tbody>
</table>
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

