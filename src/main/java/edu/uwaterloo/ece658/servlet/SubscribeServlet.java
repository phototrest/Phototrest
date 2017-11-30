/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.servlet;

import edu.uwaterloo.ece658.service.AccountService;
import edu.uwaterloo.ece658.service.BrowseService;
import edu.uwaterloo.ece658.service.S3Service;
import edu.uwaterloo.ece658.service.SubscriptionService;
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
@WebServlet(name = "SubscribeServlet", urlPatterns = {"/SubscribeServlet"})
public class SubscribeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubscribeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubscribeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        
    }

    @EJB
    SubscriptionService subscriptionService;
    @EJB
    S3Service s3Service;
    @EJB
    BrowseService browseService;
    @EJB
    AccountService accountService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String tagname = (String) session.getAttribute("tagname");
        String check= request.getParameter("check");
        if(check == "subscribed"){
            subscriptionService.subscribeToTag(username, tagname);
        } else {
            subscriptionService.unsubscribeFromTag(username, tagname);
        }
        request.getRequestDispatcher("/landing.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
