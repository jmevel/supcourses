/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supinfo.supcourses.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String email;
    
    @Column
    private UUID token;


    public void setToken(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
    
    @Column
    private String password;
    
    private Collection<Course> takenCourses;

    public void setTakenCourses(Collection<Course> takenCourses) {
        this.takenCourses = takenCourses;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @ManyToMany/*(mappedBy = "user",cascade=CascadeType.ALL)*/
    public Collection<Course> getTakenCourses() {
        return takenCourses;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public void setPassword(String password)
    {
        this.password = encryptPassword(password);
    }
    
     public static String encryptPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new Error("No MD5 support in this VM");
        }
        md.update(password.getBytes(), 0, password.length());
        String encryptedPassword = new BigInteger(1, md.digest()).toString(16);
        return encryptedPassword;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supinfo.supcourses.entities.User[ id=" + id + " ]";
    }
    
}
