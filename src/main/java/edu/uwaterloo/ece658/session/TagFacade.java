/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.session;

import edu.uwaterloo.ece658.entity.Tag;
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
public class TagFacade extends AbstractFacade<Tag> {

    @PersistenceContext(unitName = "PhototrestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }

    public Tag retrieveTagByName(String name) {
        TypedQuery query = em.createQuery(
                "SELECT t FROM Tag t WHERE t.name=:name", Tag.class);
        query.setParameter("name", name);
        List<Tag> resultList = query.getResultList();
        assert resultList.isEmpty() || resultList.size() == 1;
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public List<Tag> retrieveTagContainingName(String partialName) {
        TypedQuery query = em.createQuery(
                "SELECT t FROM Tag t WHERE t.name LIKE :partialName", Tag.class);
        query.setParameter("partialName", '%' + partialName + '%');
        List<Tag> resultList = query.getResultList();
        return resultList;
    }

}
