/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.session;

import edu.uwaterloo.ece658.entity.User;
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
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "PhototrestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User findUserByUserNameAndRole(String userName, boolean isAdmin) {
        TypedQuery query = em.createQuery("SELECT u FROM User u WHERE u.userName=:arg1"
                + " and u.isAdmin=:arg2", User.class);
        query.setParameter("arg1", userName);
        query.setParameter("arg2", isAdmin);
        List<User> resultList = query.getResultList();
        // User either doesn't exist or only one user exist
        assert resultList.isEmpty() || resultList.size() == 1;
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public User findUserByUserName(String userName) {
        return findUserByUserNameAndRole(userName, false);
    }

    public boolean existUserWithRole(String userName, boolean isAdmin) {
        return findUserByUserNameAndRole(userName, isAdmin) != null;
    }

    public boolean existUser(String userName) {
        return existUserWithRole(userName, false);
    }
}
