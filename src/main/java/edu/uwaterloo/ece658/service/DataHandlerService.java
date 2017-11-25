/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import edu.uwaterloo.ece658.entity.Photo;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.session.PhotoFacade;
import edu.uwaterloo.ece658.session.TagFacade;
import edu.uwaterloo.ece658.session.UserFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class DataHandlerService {

    @EJB
    private UserFacade userFacade;

    @EJB
    private SubscriptionService subscriptionService;

    @EJB
    private PhotoFacade photoFacade;
    
    @EJB
    private TagFacade tagFacade;

    @EJB
    private S3Service s3Util;
    
    private Photo initializePhoto(
            String S3key,
            String Md5,
            Integer photoSize, 
            Date uploadDate, 
            boolean isPrivatePhoto)
    {
        Photo photo = new Photo();        
        photo.setS3Key(S3key);
        photo.setMd5(Md5);
        photo.setSize(photoSize);
        photo.setUploadedTime(uploadDate);
        photo.setIsPrivate(isPrivatePhoto);
        photoFacade.create(photo);
        return photo;
    }
    
    private Tag createNewTag(String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tagFacade.create(tag);
        return tag;
    }
    
    private Tag initializeTag(String tagName) {
        Tag tag = tagFacade.find(tagName);
        if (tag == null) {
            tag = createNewTag(tagName);
        }
        return tag;
    }
    
    private List<Tag> initializeTags(List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();
        
        for (String tagName : tagNames) {
            tags.add(initializeTag(tagName));
        }
        return tags;
    }
    
    public boolean updateDatabaseWithPhotoInformation(
            String username, 
            String S3key,
            String Md5,
            Integer photoSize, 
            Date uploadDate,
            boolean isPrivatePhoto,
            List<String> tagNames)
    {
        // store key in table with tags
        List<Tag> tags = initializeTags(tagNames);
        User user = userFacade.find(username);
        Photo photo = initializePhoto(
                        S3key,
                        Md5,
                        photoSize,
                        uploadDate,
                        isPrivatePhoto);
        
        photo.setTags(tags);
        photo.addUser(user);
        
        user.addUploadedPhoto(photo);
        userFacade.edit(user);
        
        for (Tag tag : tags) {
            tag.addPhoto(photo);
            tagFacade.edit(tag);
        }
        
        // call notify to send messages to users
        return subscriptionService.notifyUsers(tags, photo);
    }
    
    public void removeTagFromPhoto(String username, String S3key, String tagName) {
        Photo photo = photoFacade.find(S3key);
        User user = userFacade.find(username);
        Tag tag = tagFacade.find(tagName);
        
        if (photo.getUploadedUsers().contains(user)) {
            photo.getTags().remove(tag);
            tag.getPhotosUnderThisTag().remove(photo);
            tagFacade.edit(tag);
            photoFacade.edit(photo);
        }
    }
    
    public void addTagToPhoto(String username, String S3key, String tagName) {
        Photo photo = photoFacade.find(S3key);
        User user = userFacade.find(username);
        Tag tag = initializeTag(tagName);
        
        if (photo.getUploadedUsers().contains(user)) {
            photo.addTag(tag);
            tag.addPhoto(photo);
            tagFacade.edit(tag);
            photoFacade.edit(photo);
        }
    }
    
    public boolean deleteImage(String username, String S3key) {
        Photo photo = photoFacade.find(S3key);
        User user = userFacade.find(username);
        if (photo != null && photo.getUploadedUsers().contains(user)) {
            user.getUploadedPhotos().remove(photo);
            userFacade.edit(user);
            for (Tag tag : photo.getTags()) {
                tag.getPhotosUnderThisTag().remove(photo);
                tagFacade.edit(tag);
            }
            photoFacade.remove(photo);
            
            return s3Util.deleteImage(S3key);
        }
        
        return false;
    }
}
