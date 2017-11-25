/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.session;

import edu.uwaterloo.ece658.entity.Photo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mier
 */
@Stateless
public class PhotoFacade extends AbstractFacade<Photo> {

    @PersistenceContext(unitName = "PhototrestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoFacade() {
        super(Photo.class);
    }

    public Photo getPhotoByS3Key(String s3Key) {
        TypedQuery query = em.createQuery(
                "SELECT p FROM Photo p WHERE p.s3Key=:s3Key", Photo.class);
        query.setParameter("s3Key", s3Key);
        List<Photo> resultList = query.getResultList();
        assert resultList.isEmpty() || resultList.size() == 1;
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
