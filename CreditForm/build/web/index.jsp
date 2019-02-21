<%-- 
    Document   : index
    Created on : 17 февр. 2019 г., 9:40:30
    Author     : sendel
--%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");
//filePath with end slash
ServletContext context = getServletContext();
System.out.println(context.getInitParameter("filePath"));
//prepare request to work with datafields and files
RequestTransformer requestStorage = new RequestTransformer(request);
    requestStorage.Parse();
//upload files from form
    FileUpload files = new FileUpload(requestStorage.getRequestFiles(), context.getInitParameter("filePath"));
    files.saveFileToDisk();

CreditDecision form = new CreditDecision(requestStorage.getRequestFields());%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Запрос кредита М15</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="js/jquery/jquery-ui.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <section class="section">
            <div class="container-fluid">
                <h1 class="title">
                    <i class="fab fa-aviato"></i> Заполните онлайн-заявку на кредит наличными
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



            <%


                
                if (!form.isFormFieldsValid() && form.isActionCalculate()) {
            %>
            <div class="container"><div class="row"><div class="col-12"><div class="alert alert-danger" role="alert">
                            <%= form.getErrorMsg()%>
                        </div></div></div></div>
                        <% } else if (form.isFormFieldsValid() && form.isActionCalculate()) {
                        %>
            <div class="container"><div class="row"><div class="col-12"><div class="alert alert-info" role="alert">
                            Данные формы верны!
                            
                        </div><%
                                //CreditDecision creditDecision = new CreditDecision(requestStorage.getRequestFields());
                                    out.print("<div class=\"alert alert-warning\" role=\"alert\">" + form.getDecision() + "</div>");
                                %></div></div></div>
                        <%
                            }
                            /*for (String key : form.postMap.keySet()){
                                out.print(key + "<br>");
                            }*/
                        %>

            
                <form action="" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="calculate">
                    <div class="row">
                        <div class="col-sm-6 col-md-6 col-lg-4">
                            <h4><i class="fas fa-money-check-alt"></i> <b>Основная информация</b></h4>

                            <label for="summ">Сумма кредита</label>
                            <div class="input-group ">
                                <input type="text" class="form-control" name="credit_summ"  id="credit_summ" min="10000" max="2000000" value="1000000" onchange="rangeinput1.value = credit_summ.value" />
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon2">₽</span>
                                </div>
                                <input type="range" oninput="credit_summ.value = rangeinput1.value" class="form-control-range slider custom-range" type="range" min="10000" max="2000000" value="1000000" id="rangeinput1" step="10000" onchange="credit_summ.value = rangeinput1.value" ></div>

                            <label for="credit_term">Срок кредита</label>
                            <div class="input-group ">
                                <input type="text" class="form-control text-left" name="credit_term"  id="credit_term" min="6" max="60" value="24" onchange="rangeinput2.value = credit_term.value" />
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon2">мес.</span>
                                </div>
                                <input type="range" oninput="credit_term.value = rangeinput2.value" class="form-control-range slider custom-range" type="range" min="6" max="60" value="24" id="rangeinput2" step="1" onchange="credit_term.value = rangeinput2.value" ></div>

                            <label class="label" for="fullName">Фамилия Имя Отчество</label>
                            <div class="input-group">
                                <input class="form-control" type="text" name="credit_fullName" placeholder="Иванов Андрей Алексеевич" required="required" value="Иванов Андрей Алексеевич">
                            </div>

                            <label class="label" for="tel">Телефон</label>
                            <div class="input-group">
                                <input class="form-control" type="tel" name="credit_tel" placeholder="+79991234567" required="required" value="+79991234567">
                            </div>

                            <label class="label" for="birthday">Дата рождения</label>
                            <div class="input-group">
                                <input class="form-control" type="text" id="datepicker" name="credit_birthday" placeholder="20.02.1980" required="required" value="01.01.1970">
                            </div>

                        </div>

                        <div class="col-sm-6 col-md-6 col-lg-4">
                            <h4><i class="fas fa-passport"></i> <b>Паспортные данные</b></h4>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="passport_series">Серия</label>
                                    <div class="input-group">
                                        <input class="form-control" type="text" name="credit_passport_series" placeholder="8006" required="required" value="5003">
                                    </div>
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="passport_number">Номер</label>
                                    <div class="input-group">
                                        <input class="form-control" type="text" name="credit_passport_number" placeholder="123456" required="required" value="123456">
                                    </div>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="passport_date">Дата выдачи</label>
                                    <div class="input-group">
                                        <input class="form-control" type="text" id="datepicker1" name="credit_passport_date" placeholder="20.02.2011" required="required" value="24.08.2003">
                                    </div>
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="passport_division">Код подразделения</label>
                                    <div class="input-group">
                                        <input class="form-control" type="text" name="credit_passport_division" placeholder="022-006" required="required" value="056-356">
                                    </div>
                                </div>
                            </div>

                            <label class="label" for="passport_issued_by">Кем выдан</label>
                            <div class="input-group">
                                <input class="form-control" type="text" name="credit_passport_issued_by" placeholder="Кировским РУВД имени Ленина" required="required" value="Кировским РУВД имени Ленина">
                            </div>

                            <label class="label" for="adress">Адрес регистрации</label>
                            <div class="input-group">
                                <input class="form-control" type="text" name="credit_adress" placeholder="Можайск, ул. Ленина 6" required="required" value="Можайск, ул. Ленина 6">
                            </div>

                            <label class="label" for="sex">Пол</label>
                            <div class="input-group">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="credit_sex" id="sex1" value="M" checked>
                                    <label class="form-check-label" for="sex1">Муж</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="credit_sex" id="sex2" value="F">
                                    <label class="form-check-label" for="sex2">Жен</label>
                                </div>
                            </div>

                                       <div class="input-group">
        <label class="input-group-btn my-0">
                    <span class="btn btn-large btn-info rounded-0" id="browse">
                        <i class="fas fa-file-import"></i> Выбрать&hellip; 
                      <input name="credit_passport_scan" class="file-input" id="passport-input" type="file" accept=".jpg, .jpeg, .pdf">
                    </span>
                </label>
        <input type="text" class="form-control rounded-0" readonly placeholder="Загрузите скан паспорта">
      </div>

                        </div>
                        <div class="col-sm-6 col-md-6 col-lg-4">
                            <h4><i class="fas fa-user-circle"></i>  <b>Дополнительно</b></h4>
                            <label class="label" for="employer">Работодатель</label>
                            <div class="input-group">
                                <input class="form-control" type="text" name="credit_employer" placeholder='ООО "ЯВА"' required="required" value="СпецКодПроект">
                            </div>


                            <label class="label" for="employer_industry">Отрасль работодателя</label>
                            <div class="input-group">
                                <select class="form-control" id="employer_industry" name="credit_employer_industry">
                                    <option value="it" selected>IT</option>
                                    <option value="industry">Промышленность</option>
                                    <option value="telecommunications">Телекоммуникации</option>
                                    <option value="services">Услуги</option>
                                    <option value="other">Проче</option>
                                </select>
                            </div>

                            <label class="label" for="family_status">Семейное положение</label>
                            <div class="input-group">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="credit_family_status" id="family_status1" value="1" checked>
                                    <label class="form-check-label" for="family_status1">женат/замужем</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="family_status" id="family_status2" value="0">
                                    <label class="form-check-label" for="family_status2">не замужем/не женат</label>
                                </div>
                            </div>

                            <label class="label" for="education">Образование</label>
                            <div class="input-group">
                                <select class="form-control" id="education" name="credit_education">
                                    <option value="1" selected>неполное среднее</option>
                                    <option value="2">общее среднее</option>
                                    <option value="3">среднее профессиональное</option>
                                    <option value="4">высшее</option>
                                    <option value="5">прочее</option>
                                </select>
                            </div>

                            <label for="month_income">Месячный доход</label>
                            <div class="input-group ">
                                <input type="text" class="form-control" name="credit_month_income"  id="month_income" min="10000" max="400000" value="50000" onchange="rangeinput3.value = month_income.value" />
                                <input type="range" oninput="month_income.value = rangeinput3.value" class="form-control-range slider  custom-range" type="range" min="10000" max="500000" value="50000" id="rangeinput3" step="1000" onchange="month_income.value = rangeinput3.value">
                            </div>

                

       <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="1" id="agree_gpdr" required="1" name="credit_agree_gpdr">
                                <label class="form-check-label" for="agree_gpdr">
                                    Согласен на обработку персональных данных
                                </label>
                            </div>
                        


                    </div>
                    <div class="col-md-4"> 
                        <button class="btn btn-success btn-block" type="submit" formaction=""><i class="fas fa-check-circle"></i> Проверить форму</button>
                    </div>
                    <div class="col-md-4">
                        <button class="btn btn-info btn-block" type="submit" formaction="get_json_file.jsp"><i class="fas fa-file-download"></i> Скачать JSON файл</button>
                    </div>
                    <div class="col-md-4">
                        <button class="btn btn-info btn-block" type="submit" formaction="get_json_text.jsp"><i class="fas fa-align-justify"></i> Получить JSON текстом</button>
                    </div>

            </div></form>
        
    </section>
    <hr>
    <footer><p>&nbsp;2019 - Банк</p></footer>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/jquery/jquery-ui.min.js"></script>
    <script src="js/sendel.creditform.js"></script>

</body>
</html>

