/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mier
 */
@Entity
@Table(name="T_TIME_TAG")
public class TimeTag extends Tag {

    @Override
    public String toString() {
        return "edu.uwaterloo.ece658.entity.TimeTag[ id=" + id + " ]";
    }
    
}
