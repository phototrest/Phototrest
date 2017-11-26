/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import edu.uwaterloo.ece658.entity.User;
import edu.uwaterloo.ece658.session.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Service that handles business logic regarding accounts.
 *
 * This class is used in login check, signing up new users, resetting passwords,
 * and updating user's additional information, e.g. gender, age, first name and
 * last name etc.
 *
 * @author mier
 */
@Stateless
public class AccountService {

    @EJB
    UserFacade userFacade;

    public boolean checkUserCredentials(
            String userName, String password) {
        return checkUserCredentials(userName, password, false);
    }

    public boolean checkUserCredentials(
            String userName, String password, boolean isAdminLogin) {
        User user = userFacade.retrieveUserByUserNameAndRole(userName, isAdminLogin);
        if (user != null) {
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
        // Make sure user doesn't exist in the database
        assert !userFacade.existUserWithRole(userName, isAdmin);
        if (userName == null || userName.length() == 0) {
            return false;
        }
        User newUser = new User();
        // All mandatory fields to construct a User
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setIsAdmin(isAdmin);
        userFacade.create(newUser);
        return true;
    }

    public void resetPassword(String userName, String password) {
        resetPassword(userName, password, false);
    }

    public void resetPassword(
            String userName, String password, boolean isAdmin) {
        assert userFacade.existUserWithRole(userName, isAdmin);
        User user = userFacade.retrieveUserByUserNameAndRole(userName, isAdmin);
        user.setPassword(password);
        userFacade.edit(user);
    }

    public void updateAdditionalInformation(String userName,
            String newFName, String newLName, String newGender, int newAge) {
        updateAdditionalInformation(userName, false, newFName, newLName,
                newGender, newAge);
    }

    public void updateAdditionalInformation(String userName, boolean isAdmin,
            String newFName, String newLName, String newGender, int newAge) {
        assert userFacade.existUserWithRole(userName, isAdmin);
        User user = userFacade.retrieveUserByUserNameAndRole(userName, isAdmin);
        user.setfName(newFName);
        user.setlName(newLName);
        user.setGender(newGender);
        user.setAge(newAge);
        userFacade.edit(user);
    }

    /*=======================================================================*/
    //Demo Only Purpose methods. Should never be called. Call corresponding
    // methods in UserFacade instead.
    // This method is only needed in {@link UserServlet} for example purpose.
    // But not needed in ordinary account related tasks. Will be removed.
    @Deprecated
    public List<User> getAllUsers() {
        return userFacade.findAll();
    }

    // This method is only needed in {@link UserServlet} for example purpose.
    // But not needed in ordinary account related tasks. Will be removed.
    @Deprecated
    public boolean exist(String userName) {
        return exist(userName, false);
    }

    // This method is only needed in {@link UserServlet} for example purpose.
    // But not needed in ordinary account related tasks. Will be removed.
    @Deprecated
    public boolean exist(String userName, boolean isAdmin) {
        return userFacade.retrieveUserByUserNameAndRole(userName, isAdmin) != null;
    }
    /*=======================================================================*/
}
