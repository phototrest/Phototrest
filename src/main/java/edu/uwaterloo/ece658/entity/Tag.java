/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author mier
 */
@Entity
@Table(name = "T_TAG")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "subscribedTags")
    protected List<User> subscribedUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "tags")
    protected List<Photo> photosUnderThisTag = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void setSubscribedUsers(List<User> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }

    public void addSubscribedUser(User user) {
        this.subscribedUsers.add(user);
    }

    public void removeSubscribedUser(User user) {
        this.subscribedUsers.remove(user);
    }

    public List<Photo> getPhotosUnderThisTag() {
        return photosUnderThisTag;
    }

    public void setPhotosUnderThisTag(List<Photo> photosUnderThisTag) {
        this.photosUnderThisTag = photosUnderThisTag;
    }

    public void addPhotoUnderThisTag(Photo photo) {
        this.photosUnderThisTag.add(photo);
    }

    public void removePhotoUnderThisTag(Photo photo) {
        this.photosUnderThisTag.remove(photo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uwaterloo.ece658.entity.Tag[ id=" + id + " ]";
    }

}
