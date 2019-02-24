 <%@page import="sendel.bank.RequestTransformer"%>
<%@page import="sendel.bank.FormCreditHandler"%>
<%@page import="sendel.bank.JsonHandler"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Content-disposition", "attachment; filename=credit_form_data.json");
    //формирование JSON из формы с учетом префикса
    //prepare request to work with datafields and files
    RequestTransformer requestStorage = new RequestTransformer(request, getServletContext());
    requestStorage.Parse();
    FormCreditHandler form = new FormCreditHandler(requestStorage.getRequestFields());
    out.print(JsonHandler.getJsonIncludePrefixFromPost(form.getPostMap(), ""));
%>



