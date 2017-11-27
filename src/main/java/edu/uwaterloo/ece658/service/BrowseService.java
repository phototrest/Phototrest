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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    private S3Service s3Service;

    /**
     * Given a username, fetch URLs of all the photos uploaded by this user.
     *
     * @param userName user name whose uploaded photos are being fetched
     * @return list of photos URLs uploaded by this user
     */
    public List<URL> fetchUploadedPhotosOfUser(String userName) {
        User user = userFacade.retrieveUserByUserName(userName);
        return getURLs(user.getUploadedPhotos());
    }

    private List<URL> getURLs(List<Photo> photos) {
        List<URL> result = new ArrayList<>();
        for (Photo p : photos) {
            result.add(s3Service.getDownloadURL(p.getS3Key()));
        }
        return result;
    }

    /**
     * Given a username, fetch URLs of all publicly-available photos classified
     * by the subscribed tags by this user.
     *
     * @param userName user name whose subscribed photos are being fetched
     * @return mapping between subscribed tag and URLs of public photos under
     * the tag
     */
    public Map<String, List<URL>> fetchPhotosClassifiedBySubscribedTagsOfUser(
            String userName) {
        User user = userFacade.retrieveUserByUserName(userName);
        Map<String, List<URL>> result = new HashMap<>();
        for (Tag tag : user.getSubscribedTags()) {
            // User explicitly subscribed to some tags => Directly showing public
            // photos under these tags is ok. This also includes UserTag that the
            // user has subscribed to.
            List<Photo> publicPhotos = tag.getPhotosUnderThisTag().stream()
                    .filter(p -> !p.isPrivate())
                    .collect(Collectors.toList());
            result.put(tag.getName(), getURLs(publicPhotos));
        }
        return result;
    }

    /**
     * Retrieves URLs of all publicly-available photos that are tagged by the
     * tag.
     *
     * @param tagName tag name by which photos are tagged
     * @return list of URLs of public photos under this tag
     */
    public List<URL> searchPhotosByTag(String tagName) {
        Tag tag = tagFacade.retrieveTagByName(tagName);
        List<Photo> photos = tag.getPhotosUnderThisTag().stream().filter(
                p -> !p.isPrivate()).collect(Collectors.toList());
        return getURLs(photos);
    }

}
