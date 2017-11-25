/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.session;

import edu.uwaterloo.ece658.entity.UserTag;
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
public class UserTagFacade extends AbstractFacade<UserTag> {

    @PersistenceContext(unitName = "PhototrestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTagFacade() {
        super(UserTag.class);
    }

    public UserTag getUserTagByUserName(String userName) {
        TypedQuery query = em.createQuery(
                "SELECT t FROM UserTag t WHERE t.name=:userName", UserTag.class);
        query.setParameter("name", userName);
        List<UserTag> resultList = query.getResultList();
        assert resultList.isEmpty() || resultList.size() == 1;
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
