package edu.uwaterloo.ece658.servlet;
import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.service.AccountService;
import edu.uwaterloo.ece658.service.BrowseService;
import edu.uwaterloo.ece658.service.S3Service;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@WebServlet(name="LoginServlet", urlPatterns={"/usermain"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Display the list of users:
        req.getRequestDispatcher("/usermain.jsp").forward(req, resp);
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
        if(accountService.checkUserCredentials(userName, password)) {  
            List<Photo> photolist = browseService.fetchUploadedPhotosOfUser(userName);
            Map<Tag, List<Photo>> map = browseService.fetchPhotosClassifiedBySubscribedTagsOfUser(userName);
            Set<Tag> tags = map.keySet();
            for(Tag tag : tags) {
                List<Photo> photos = map.get(tag);
                for(Photo photo : photos) {
                    URL url = s3Service.getDownloadURL(photo.getS3Key());
                }
             }
             String FullName = accountService.retrieveFullNameOfUser(userName);
            request.setAttribute("photolist", photolist);
            request.setAttribute("map", map);
            request.setAttribute("tags", tags);
            //request.setAttribute("url", url);
            session.setAttribute("FullName", FullName); 
            session.setAttribute("username", userName);
            doGet(request, response);
        } else {  
           request.setAttribute("errormsg1","wrong!"); 
           request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
}
}
    




    
     
