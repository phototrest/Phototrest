/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.servlet;

import edu.uwaterloo.ece658.service.DataHandlerService;
import edu.uwaterloo.ece658.service.S3Service;
import edu.uwaterloo.ece658.service.SubscriptionService;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static sun.security.krb5.Confounder.bytes;
/**
 *
 * @author chris
 */
@WebServlet(name = "UploadPhotosServlet", urlPatterns = {"/landing"})
public class UploadPhotosServlet extends HttpServlet {

    private final String ENCODING_PREFIX = "base64,";
    
    @EJB S3Service s3Service;
    @EJB DataHandlerService dataHandlerService;
    @EJB SubscriptionService subscriptionService;
    
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String[] photos = request.getParameterValues("photos");
       // Integer photoSize = request.getParameter("sizes");
        String tags = request.getParameter("tags");
         String[] arrayStr =new String[]{};
         arrayStr = tags.split(";");
         List<String> tagnames = java.util.Arrays.asList(arrayStr);
         String param = String.valueOf(request.getParameter("isPrivatePhoto"));
        boolean isPrivatePhoto = false;
        if ("private".equals(param)){isPrivatePhoto= true;}
        Date uploadDate = new java.util.Date();
        for(String photo : photos) {
            // Calcualte md5 == s3key
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UploadPhotosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             byte[] s3key = md5.digest(photo.getBytes());
             String s3Key = Arrays.toString(s3key);
            try {
                // Initialize streams, writers
                URL uploadURL = s3Service.getUploadURL(s3Key);
                HttpURLConnection connection=(HttpURLConnection) uploadURL.openConnection();
		//connection.setDoInput(true);
                connection.setDoOutput(true);
                //connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type", "image/jpeg");
                connection.setRequestMethod("PUT");
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int startingIndex = photo.indexOf(ENCODING_PREFIX) + ENCODING_PREFIX.length();
                String s = photo.substring(startingIndex);
                byte[] photoBinary = Base64.getDecoder().decode(s);
                out.write(photoBinary, 0, photoBinary.length);
                out.writeTo(connection.getOutputStream());
                connection.getOutputStream().close();
                out.close();
                int reponseCode = connection.getResponseCode();
//                outputStream.write(photoBinary);
//                outputStream.flush();
                dataHandlerService.updateDatabaseWithPhotoInformation(username, s3Key, s3Key, 100, uploadDate, isPrivatePhoto, tagnames);
                for(String tag : tagnames) {
                    subscriptionService.subscribeToTag(username, tag);
                }
            // Write to url 怎么把base64 string写到uploadurl里面
            } catch(IOException e) {
                request.getRequestDispatcher("/upload.jsp").forward(request, response);
            } finally {
                //
            }
    }
    request.getRequestDispatcher("/landing.jsp").forward(request, response);
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

    private String calculateMD5(String p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
