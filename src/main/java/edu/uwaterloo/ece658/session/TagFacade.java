/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.session;

import edu.uwaterloo.ece658.entity.Tag;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private UserFacade userFacade;

    @PersistenceContext(unitName = "PhototrestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }

    public void create(Tag entity) {
        if (entity.getName().startsWith("@") && !isValidUserTag(entity.getName())) {
            return;// TODO Should throw exception rather than silently ignoring
        }
        super.create(entity);
    }

    public void edit(Tag entity) {
        if (entity.getName().startsWith("@") && !isValidUserTag(entity.getName())) {
            return;// TODO Should throw exception rather than silently ignoring
        }
        super.create(entity);
    }

    // Validat a tag that starts with "@" and no other occurrences of "@" and the
    // user must exist in the User table
    private boolean isValidUserTag(String userTag) {
        return userFacade.existUser(userTag.substring(1));
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
