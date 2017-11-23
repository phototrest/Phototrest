/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author mier
 */
@Entity
@Table(name = "T_USER_TAG")
public class UserTag extends Tag {

    @Override
    public String toString() {
        return "edu.uwaterloo.ece658.entity.UserTag[ id=" + id + " ]";
    }

}
