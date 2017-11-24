/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import edu.uwaterloo.ece658.util.S3Util;
import com.amazonaws.HttpMethod;
import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.session.PhotoFacade;
import edu.uwaterloo.ece658.session.TagFacade;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class DataTransmissionService {

    @EJB
    private SubscriptionService subscriptionService;

    @EJB
    private PhotoFacade photoFacade;
    
    @EJB
    private TagFacade tagFacade;

    @EJB
    private S3Util s3Util;

    public URL upload(String s3Key) {
        URL uploadURL = s3Util.getS3Url(s3Key, HttpMethod.PUT);
        return uploadURL;
    }
    
    public URL download(String S3key) {
        URL downloadURL = s3Util.getS3Url(S3key, HttpMethod.GET);
        return downloadURL;
    }
    
    private Photo initializePhoto(String S3key, Integer size, boolean isPrivate, List<Tag> tags) {
        Photo photo = new Photo();
        photo.setIsPrivate(isPrivate);
        //photo.setMd5();
        photo.setS3Key(S3key);
        photo.setSize(size);
        photo.setTags(tags);
        //photo.setUploadedTime(uploadedTime);
        //photo.setUploadedUsers(uploadedUsers);
        
        for (Tag tag : tags) {
            tag.addPhoto(photo);
        }
        return photo;
    }
    
    private Tag initializeTag(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        return tag;
    }
    
    private List<Tag> initializeTags(List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();
        
        for (String tagName : tagNames) {
            tags.add(initializeTag(tagName));
        }
        return tags;
    }
    
    public boolean updateDatabase(String S3key, Integer size, boolean isPrivate, List<String> tagNames) {
        // store key in table with tags
        // TODO: get user
        List<Tag> tags = initializeTags(tagNames);
        Photo photo = initializePhoto(S3key, size, isPrivate, tags);
        
        photoFacade.create(photo);
        for (Tag tag : tags) {
            tagFacade.create(tag);
        }
        
        // call notify to send messages to users
        subscriptionService.notifyUsers(tags, photo);
        
        return true;
    }
    
    public boolean deleteImage(String S3key) {
        //delete key in table
        Photo photo = photoFacade.find(S3key);
        if (photo != null) {
            photoFacade.remove(photo);
            
        } else {
            // cannot find photo
            return false;
        }
        // delete image in s3
        return s3Util.deleteImage(S3key);
    }
}
