/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author sendel
 */
@WebServlet(name = "SrvIndexPage", urlPatterns = {"/index.jsp"})
public class SrvIndexPage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         ServletContext context = getServletContext();
  
        RequestTransformer requestStorage = new RequestTransformer(req, context);
        requestStorage.Parse();

        CreditDecision form = new CreditDecision(requestStorage.getRequestFields(), requestStorage.getRequestFiles());

        req.setAttribute("form_answer", form);
        req.setAttribute("form_valid", form.isFormFieldsValid());
        req.setAttribute("form_action_calc", form.isActionCalculate());
        Cookies cookies = new Cookies(req.getCookies());
        FileUpload files = null;
        if (form.isFormFieldsValid() && form.isActionCalculate()) {
            files = new FileUpload(requestStorage.getRequestFiles(), context.getInitParameter("filePath"));
            req.setAttribute("uploaded_files", files.getUploadedFileNames());
            //read Cookies
            
            
        
        form.saveFormResultToDB(files.getUploadedFileNames(), context);
        }
        cookies.setCookies(form.getPostMap(), resp);
        //get and http encode all fields
        req.setAttribute("cookies_enc", cookies.getReqCookies());
       processRequest(req, resp);
    
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookies cookies = new Cookies(req.getCookies());
        //get and http encode all fields
        cookies.getCookies(req);
        req.setAttribute("cookies_enc", cookies.getReqCookies());
        
        //work with SESSION
        HttpSession session = req.getSession();
        if (session.getAttribute("visit_count") != null){
            session.setAttribute("visit_count", Integer.parseInt(session.getAttribute("visit_count").toString()) + 1);
        }else
        {
            session.setAttribute("visit_count", 0);
        }
        req.setAttribute("visits", session.getAttribute("visit_count"));
        //work with SESSION
        
         processRequest(req, resp);
    }
    
    
    
        /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //filePath with end slash
        //ServletContext context = getServletContext();
        
        
        
        request.getRequestDispatcher("start.jsp").forward(request, response);
    }
    
}
