/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import javax.ejb.Stateless;
import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.session.TagFacade;
import edu.uwaterloo.ece658.session.UserFacade;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Daniel
 */
@Stateless
public class SubscriptionService {

    @EJB
    private TagFacade tagFacade;
    
    @EJB
    private UserFacade userFacade;
    
    public boolean subscribeToTag(String username, String tagName) {
        Tag tag = tagFacade.find(tagName);
        
        User user = userFacade.find(username);
        user.addSubscribedTag(tag);
        tag.addSubscribedUser(user);
        
        tagFacade.edit(tag);
        userFacade.edit(user);
        
        return false;
    }

    public boolean unsubscribeFromTag(String username, String tagName) {
        // remove tag from user's subscriptions in database
        Tag tag = tagFacade.find(tagName);
        User user = userFacade.find(username);
        
        tag.getSubscribedUsers().remove(user);
        user.getSubscribedTags().remove(tag);
        
        tagFacade.edit(tag);
        userFacade.edit(user);
        
        return false;
    }
    
    private boolean sendNotification(User user, Photo photo) {
        // TODO: send notification? 
        return true;
    }
    
    private boolean notify(List<User> users, Photo photo) {
        for (User user : users) {
            if (!sendNotification(user, photo)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean notifyUsers(List<Tag> tags, Photo photo) {
        // iterate through list of users in each tag and send notification
        for (Tag tag : tags) {
            if (!notify(tag.getSubscribedUsers(), photo)) {
                return false;
            }
        }
        return true;
    }
    
    
    
}
