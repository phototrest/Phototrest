package edu.uwaterloo.ece658.servlet;

import edu.uwaterloo.ece658.service.AccountService;
import edu.uwaterloo.ece658.service.BrowseService;
import edu.uwaterloo.ece658.service.S3Service;
import java.io.IOException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/usermain"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Display the list of users:
        req.getRequestDispatcher("/ShowUserPhotosServlet").forward(req, resp);

    }

    //prevent the users from accessing this servlet by inputting the website address
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    S3Service s3Service;
    @EJB
    BrowseService browseService;
    @EJB
    AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (accountService.checkUserCredentials(userName, password)) {
            String FullName = accountService.retrieveFullNameOfUser(userName);
            session.setAttribute("FullName", FullName);
            session.setAttribute("username", userName);
            doGet(request, response);
        } else {
            request.setAttribute("errormsg1", "Invalid user name or password!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
