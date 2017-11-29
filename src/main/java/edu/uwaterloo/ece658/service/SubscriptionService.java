/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import javax.ejb.Stateless;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.session.TagFacade;
import edu.uwaterloo.ece658.session.UserFacade;
import java.net.URL;
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
    
    @EJB
    private SnsService snsService;

    public boolean subscribeToTag(String username, String tagName) {
        Tag tag = tagFacade.retrieveTagByName(tagName);

        User user = userFacade.retrieveUserByUserName(username);
        user.addSubscribedTag(tag);
        tag.addSubscribedUser(user);
        snsService.subscribeToTopic(tag, user);
        
        tagFacade.edit(tag);
        userFacade.edit(user);

        return false;
    }

    public boolean unsubscribeFromTag(String username, String tagName) {
        // remove tag from user's subscriptions in database
        Tag tag = tagFacade.retrieveTagByName(tagName);
        User user = userFacade.retrieveUserByUserName(username);

        tag.removeSubscribedUser(user);
        user.removeSubscribedTag(tag);
        snsService.unsubscribeFromTopic(tag, user);

        tagFacade.edit(tag);
        userFacade.edit(user);

        return false;
    }

    public void publishPhotoWithSingleTag(Tag tag, URL url) {
        snsService.publishToTopic(url, tag.getTopicArn());
    }
    
    public void publishPhotoWithMultipleTags(List<Tag> tags, URL url) {
        // iterate through list of users in each tag and send notification
        for (Tag tag : tags) {
            snsService.publishToTopic(url, tag.getTopicArn());
        }
    }

}
