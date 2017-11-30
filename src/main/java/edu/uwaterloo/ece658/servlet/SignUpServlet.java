/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.servlet;

import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.service.AccountService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chris
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/registering"})
public class SignUpServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUpServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    AccountService accountService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        HttpSession session = request.getSession();
        if( username != null && !username.isEmpty()) {
            if( password != null && !password.isEmpty()) {
                if( email != null && !email.isEmpty()) {
                    if(!accountService.userAlreadyExist(username)) {
                         accountService.signUpNewUser(username, password, email); 
                         accountService.updateAdditionalInformation(username, fname, lname, gender, 0);
                        session.setAttribute("username", username);
                        session.setAttribute("FullName", accountService.retrieveFullNameOfUser(username));
                        //session.setAttribute("info", "Congratulations! Sign Up successfully!");
                        request.getRequestDispatcher("/ShowUserPhotosServlet").forward(request, response);
                    }  
                    else {
                        session.setAttribute("errormsg2", "Your UserName exists!");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    } 
                } 
                else {
                session.setAttribute("errormsg2", "The email should not be empty!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } 
            else {
            session.setAttribute("errormsg2", "The password should not be empty!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        else {
            session.setAttribute("errormsg2", "The UserName should not be empty!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }  
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
