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

    private User findUserByUserNameAndRole(String userName, boolean isAdmin) {
        TypedQuery query = em.createQuery("from User u where u.userName=:arg1"
                + "and u.isAdmin=:arg2", User.class);
        query.setParameter("arg1", userName);
        query.setParameter("arg2", isAdmin);
        List<User> resultList = query.getResultList();
        // User either doesn't exist or only one user exist
        assert resultList.isEmpty() || resultList.size() == 1;
        return resultList.isEmpty() ? null : resultList.get(0);
    }
    
    public boolean exist(String userName) {
        return exist(userName, false);
    }
    
    public boolean exist(String userName, boolean isAdmin) {
        return findUserByUserNameAndRole(userName, isAdmin) != null;
    }

    public boolean checkUserCredentials(
            String userName, String password) {
        return checkUserCredentials(userName, password, false);
    }
    
    public boolean checkUserCredentials(
            String userName, String password, boolean isAdminLogin) {
        User user = findUserByUserNameAndRole(userName, isAdminLogin);
        if(user != null) {
            // User exists, check password
            return user.getPassword().contentEquals(password);
        }
        // Use doesn't exist
        return false;
    }
    
    public boolean signUpNewUser(String userName, String password, String email) {
        return signUpNewUser(userName, password, email, false);
    }
    
    public boolean signUpNewUser(
            String userName, String password, String email, boolean isAdmin) {
        assert !exist(userName, isAdmin);
        User newUser = new User();
        // All mandatory fields to construct a User
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setIsAdmin(isAdmin);
        create(newUser);
        return true;
    }
    
    public void resetPassword(String userName, String password) {
        resetPassword(userName, password, false);
    }
    
    public void resetPassword(
            String userName, String password, boolean isAdmin) {
        assert exist(userName, isAdmin);
        User user = findUserByUserNameAndRole(userName, isAdmin);
        user.setPassword(password);
        edit(user);
    }
    
    public void updateAdditionalInformation(String userName,
            String newFName, String newLName, String newGender, int newAge) {
        updateAdditionalInformation(userName, false, newFName, newLName,
                newGender, newAge);
    }
    
    public void updateAdditionalInformation(String userName, boolean isAdmin,
            String newFName, String newLName, String newGender, int newAge) {
        assert exist(userName, isAdmin);
        User user = findUserByUserNameAndRole(userName, isAdmin);
        user.setfName(newFName);
        user.setlName(newLName);
        user.setGender(newGender);
        user.setAge(newAge);
        edit(user);
    }
}
