/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.transmit;

import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class dataTransmitBean {

    public String upload(String pathToImage) {
        return null;
    }
    
    public boolean updateS3key(String S3key, List<String> tags) { 
        return false;
    }
    
    public String download(String S3key) {
        return null;
    }
    
    public boolean deleteImage(String S3key) {
        return false;
    }
}
