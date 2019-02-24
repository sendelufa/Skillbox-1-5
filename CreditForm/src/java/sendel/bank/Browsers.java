/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendel.bank;

import com.mysql.jdbc.Connection;
import eu.bitwalker.useragentutils.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sendel.bank.db.DBHandler;

/**
 *
 * @author sendel
 */
@WebServlet(name = "Browsers", urlPatterns = {"/browsers"})
public class Browsers extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        
        String browserName = new UserAgent(request.getHeader("User-Agent")).getBrowser().getName();
        
        Connection conn =  DBHandler.getConnection();
        ResultSet rs = null;
        String res = "";
        try {
               String sql = "INSERT INTO browsers(browser_name,`count`) VALUES('" + browserName + "', 1) ON DUPLICATE KEY UPDATE `count` = `count` + 1;";
               conn.createStatement().execute(sql);
               
               sql = "SELECT browser_name,`count` FROM learn.browsers;";
               rs = conn.createStatement().executeQuery(sql);
       
        
         

            HashMap<String, Integer> browsers = new HashMap<>();
            int summ_visits = 0;
            while (rs.next()){
                browsers.put(rs.getString(1), Integer.parseInt(rs.getString(2)));
                summ_visits += Integer.parseInt(rs.getString(2));
            }
            
            context.setAttribute("browsers", browsers);
            context.setAttribute("summ_visits", summ_visits);
            
            request.getRequestDispatcher("browsers.jsp").forward(request, response);
            
        }catch (SQLException ex) {
                Logger.getLogger(CreditDecision.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
