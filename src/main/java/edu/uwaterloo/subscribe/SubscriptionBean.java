/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.subscribe;

import javax.ejb.Stateless;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.util.List;

/**
 *
 * @author Daniel
 */
@Stateless
public class SubscriptionBean {

    final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
    public boolean createNewTag(String tag) {
        return false;
    }
    public boolean subscribeToTag(String tag) {
        return false;
    }

    public boolean unsubscribeFromTag(String tag) {
        return false;
    }

    public String notifyUsers(List<String> tags, String imgURL) {
        
        return null;
    }
    
    
    
}
