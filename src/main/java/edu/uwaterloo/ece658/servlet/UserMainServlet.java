/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.servlet;

import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.service.BrowseService;
import edu.uwaterloo.ece658.service.S3Service;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
@WebServlet(name = "ShowUserPhotosServlet", urlPatterns = {"/ShowUserPhotosServlet"})
public class UserMainServlet extends HttpServlet {

    @EJB
    S3Service s3Service;
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
    BrowseService browseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<Photo> uploadedPhotolist = browseService.fetchUploadedPhotosOfUser(username);
        List<PhotoWrapper> uploaded = convertToPhotoWrappers(uploadedPhotolist);

        Map<Tag, List<Photo>> map = browseService.fetchPhotosClassifiedBySubscribedTagsOfUser(username);
        Map<String, List<PhotoWrapper>> subscribed = new HashMap<>();
        Set<Tag> tags = map.keySet();
        for (Tag tag : tags) {
            List<Photo> photos = map.get(tag);
            subscribed.put(tag.getName(), convertToPhotoWrappers(photos));
        }

        request.getSession().setAttribute("uploaded", uploaded);
        request.getSession().setAttribute("subscribed", subscribed);
        response.sendRedirect("usermain.jsp");
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
        doGet(request, response);
    }

    private List<PhotoWrapper> convertToPhotoWrappers(List<Photo> photos) {
        List<PhotoWrapper> photoWrappers = new ArrayList<>();
        for (Photo p : photos) {
            URL url = s3Service.getDownloadURL(p.getS3Key());
            StringBuilder sb = new StringBuilder();
            for (Tag t : p.getTags()) {
                sb.append(" #" + t.getName());
            }
            photoWrappers.add(new PhotoWrapper(sb.toString(), p.getS3Key(), url.toString()));
        }
        return photoWrappers;
    }

    public static class PhotoWrapper {

        public String tags;
        public String s3Key;
        public String url;

        public PhotoWrapper(String tags, String s3Key, String url) {
            this.tags = tags;
            this.s3Key = s3Key;
            this.url = url;
        }
    }
}
