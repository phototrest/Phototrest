package edu.uwaterloo.ece658.servlet;

import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.service.AccountService;
import edu.uwaterloo.ece658.session.UserFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    AccountService accountFacade;

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Display the list of users:
        request.setAttribute("users", accountFacade.getAllUsers());
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Handle a new guest:
        String name = request.getParameter("name");
        if (name != null) {
            if (accountFacade.exist(name)) {

            } else {
                User user = new User();
                user.setUserName(name);
                accountFacade.signUpNewUser(name, "", "");
            }
        }

        // Display the list of guests:
        doGet(request, response);
    }
}
