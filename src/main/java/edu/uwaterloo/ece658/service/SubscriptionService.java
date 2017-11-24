/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import javax.ejb.Stateless;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.entity.User;
import java.util.List;

/**
 *
 * @author Daniel
 */
@Stateless
public class SubscriptionService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
    /*public boolean createNewTag(String tag) {
        // creates a new tag entry into the data base and new entitiy class?
        return false;
    }
    public boolean subscribeToTag(String tag) {
        // check if tag exists
        // if not then createNewTag(tag);
        
        // add tag to user's subscriptions in database
        return false;
    }

    public boolean unsubscribeFromTag(String tag) {
        // remove tag from user's subscriptions in database
        return false;
    }*/
    
    private void sendNotification(User user, Photo photo) {
        // TODO: send notification? 
    }
    
    private void notify(List<User> users, Photo photo) {
        for (User user : users) {
            sendNotification(user, photo);
        }
    }
    
    public String notifyUsers(List<Tag> tags, Photo photo) {
        // iterate through list of users in each tag and send notification
        for (Tag tag : tags) {
            notify(tag.getSuscribedUsers(), photo);
        }
        return null;
    }
    
    
    
}
