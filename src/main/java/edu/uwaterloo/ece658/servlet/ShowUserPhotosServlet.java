/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.servlet;
import java.io.PrintWriter;
import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.service.BrowseService;
import edu.uwaterloo.ece658.service.S3Service;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
@WebServlet(name = "ShowUserPhotosServlet", urlPatterns = {"/ShowUserPhotosServlet"})
public class ShowUserPhotosServlet extends HttpServlet {
    @EJB S3Service s3Service;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    @EJB BrowseService browseService;
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String username= request.getParameter("name");
         List<Photo> uploadedPhotolist = browseService.fetchUploadedPhotosOfUser(username);
         List<PhotoWrapper> uploadedPhotoWrapperList = new ArrayList<>();
         for(Photo p : uploadedPhotolist) {
             URL url = s3Service.getDownloadURL(p.getS3Key());
            // uploadedPhotoWrapperList.add(new PhotoWrapper(p.get));
         }

         Map<Tag, List<Photo>> map = browseService.fetchPhotosClassifiedBySubscribedTagsOfUser(username);
         Set<Tag> tags = map.keySet();
         for(Tag tag : tags) {
             List<Photo> photos = map.get(tag);
             for(Photo photo : photos) {
                 URL url = s3Service.getDownloadURL(photo.getS3Key());
                 
             }
         }
         //request.setAttribute("photolist", photolist);
         request.setAttribute("map", map);
         request.setAttribute("tags", tags);
         response.sendRedirect("usermain.jsp");
    }

    public static class PhotoWrapper {
        public List<String> tags;
        public String url;
        
        public PhotoWrapper(List<String> tags, String url) {
            this.tags = tags;
            this.url = url;
        }
    }
}
