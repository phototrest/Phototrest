/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author mier
 */
public class EntityCreationTest extends EntityPersistenceTest {

    @Override
    protected void setUpDatabase() throws Exception {
        System.out.println("Setting up database");
        User user = new User();
        user.setUserName("m2ta");
        user.setPassword("123456");
        Tag tag = new Tag();
        tag.setName("dog");
        Tag userTag = new Tag();
        userTag.setName("@topnessman");
        Photo photo = new Photo();
        photo.setS3Key("123-s3-mdfs");
        List<User> users = new LinkedList<>(Arrays.asList(user));
        List<Tag> tags = new LinkedList<>(Arrays.asList(tag, userTag));
        List<Photo> photos = new LinkedList<>(Arrays.asList(photo));
        user.setSubscribedTags(tags);
        user.setUploadedPhotos(photos);
        tag.setSubscribedUsers(users);
        tag.setPhotosUnderThisTag(photos);
        userTag.setSubscribedUsers(users);
        userTag.setPhotosUnderThisTag(photos);
        photo.setUploadedUsers(users);
        photo.setTags(tags);
        em.persist(user);
        em.persist(tag);
        em.persist(userTag);
        em.persist(photo);
    }

    @Test
    public void test1() {
        String queryGetUser = "select u from User u where u.userName=:arg1";
        TypedQuery<User> queryUser = em.createQuery(queryGetUser, User.class);
        queryUser.setParameter("arg1", "m2ta");
        List<User> resultUsers = queryUser.getResultList();
        Assert.assertEquals("User has wrong size. Should be 1!", 1,
                resultUsers.size());
        Assert.assertEquals("Wrong user returned", "m2ta",
                resultUsers.get(0).getUserName());
        User user = resultUsers.get(0);
        List<Tag> tags = user.getSubscribedTags();
        Set<String> tagsAsSet = new HashSet<>();
        for (Tag t : tags) {
            tagsAsSet.add(t.getName());
        }
        Assert.assertEquals("Should be 2!", 2, tags.size());
        Assert.assertTrue("Should be true", tagsAsSet.containsAll(
                Arrays.asList("dog", "@topnessman")));
    }

    @Test
    public void test2() {
        String queryGetTag = "select t from Tag t where t.name=:arg1";
        TypedQuery<Tag> queryTag = em.createQuery(queryGetTag, Tag.class);
        queryTag.setParameter("arg1", "dog");
        List<Tag> resultTags = queryTag.getResultList();
        Assert.assertEquals("Should only return 1 tag", 1, resultTags.size());
        Assert.assertEquals("Wrong tag returned", "dog", resultTags.get(0).getName());
    }

    @Test
    public void test3() {
        String queryGetPhoto = "select p from Photo p where p.s3Key=:arg1";
        TypedQuery<Photo> queryPhoto = em.createQuery(queryGetPhoto, Photo.class);
        queryPhoto.setParameter("arg1", "123-s3-mdfs");
        List<Photo> resultPhotos = queryPhoto.getResultList();
        Assert.assertEquals("Should only return 1", 1, resultPhotos.size());
        Assert.assertEquals("Wrong tag returned", "dog",
                resultPhotos.get(0).getTags().get(0).getName());
        Assert.assertEquals("Wrong tag returned", "@topnessman",
                resultPhotos.get(0).getTags().get(1).getName());
    }

    // TODO Investigate this in real environment
    @Test
    public void test4() throws Exception {
        String queryGetTag = "select t from Tag t where t.name=:arg1";
        TypedQuery<Tag> queryTag = em.createQuery(queryGetTag, Tag.class);
        queryTag.setParameter("arg1", "dog");
        List<Tag> resultTags = queryTag.getResultList();
        Assert.assertEquals("Should only return 1 tag", 1, resultTags.size());

        String queryGetPhoto = "select p from Photo p where p.s3Key=:arg1";
        TypedQuery<Photo> queryPhoto = em.createQuery(queryGetPhoto, Photo.class);
        queryPhoto.setParameter("arg1", "123-s3-mdfs");
        List<Photo> resultPhotos = queryPhoto.getResultList();
        Assert.assertEquals("Should only return 1 photo", 1, resultPhotos.size());

        Tag dogTag = resultTags.get(0);
        Photo photoWithDogTag = resultPhotos.get(0);
        /// Below two lines are enough to generate deletion SQL statement to
        // delete the row in the joined table. Only calling method on owning side
        // of the many-to-many relationship can generate correct deletion statement.
        // I tried cascade on inverse side - Tag#photosUnderThisTag, nothing worked.
        // I don't even need to call persist() method, if I call method on owning
        // side, which is really wierd.
        photoWithDogTag.removeTag(dogTag);
        em.flush();//This is necessary for generating SQL deletion statement to write
        // changes to the database
        /// Above two lines are enough to generate correct deletion SQL statement.
        em.refresh(photoWithDogTag);// Resetting state from database to remove the
        // effect of Java object
        em.refresh(dogTag);// Resetting state from database for the same reason
        Assert.assertEquals(1, photoWithDogTag.getTags().size());// Should only
        // shave "@topnessman"
        Assert.assertEquals(0, dogTag.getPhotosUnderThisTag().size());
    }

}
