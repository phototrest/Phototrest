/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.session.TagFacade;
import edu.uwaterloo.ece658.session.UserFacade;
import edu.uwaterloo.ece658.session.UserTagFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Service that handles all kinds of browsing requests from front end.
 *
 * @author mier
 */
@Stateless
public class BrowseService {

    @EJB
    private UserFacade userFacade;
    @EJB
    private TagFacade tagFacade;
    @EJB
    private UserTagFacade userTagFacade;

    /**
     * Given a username, fetch all the photos uploaded by this user.
     *
     * @param userName user name whose uploaded photos are being fetched
     * @return list of photos uploaded by this user
     */
    public List<Photo> fetchUploadedPhotosOfUser(String userName) {
        User user = userFacade.retrieveUserByUserName(userName);
        return user.getUploadedPhotos();
    }

    /**
     * Given a username, fetch all photos classified by the subscribed tags by
     * this user.
     *
     * @param userName user name whose uploaded photos are being fetched
     * @return mapping between subscribed tag and photos under the tag of the
     * user
     */
    public Map<Tag, List<Photo>> fetchPhotosClassifiedBySubscribedTagsOfUser(String userName) {
        User user = userFacade.retrieveUserByUserName(userName);
        Map<Tag, List<Photo>> result = new HashMap<>();
        for (Tag tag : user.getSubscribedTags()) {
            // User explicitly subscribed to some tags => Directly show photos
            // under these tags is ok. This includes UserTag that the user
            // has subscribed to.
            result.put(tag, tag.getPhotosUnderThisTag());
        }
        return result;
    }

    /**
     * Retrieves all photos that are tagged by a tag, either normal tag or user
     * tag.
     *
     * @param tagName tag name whose photos are being fetched
     * @return list of photos under this tag
     */
    public List<Photo> searchPhotosByTag(String tagName) {
        Tag tag;
        if (tagName.startsWith("@")) {
            // User tag
            // TODO I'm not sure if calling userTagFacade is the only way to fetch
            // UserTag. If tagFacade can also do this job, there is no need to
            // call two different facades here.
            tag = userTagFacade.retrieveUserTagByUserName(tagName);
        } else {
            // Normal tag
            tag = tagFacade.retrieveNormalTagByName(tagName);
        }
        // Concrete type of tag doesn't matter, because here getPhotosUnderThisTag()
        //is called, which is in super class - Tag
        return tag.getPhotosUnderThisTag();
    }

}
