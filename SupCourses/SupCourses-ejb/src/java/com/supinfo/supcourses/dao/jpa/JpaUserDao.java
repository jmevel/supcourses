/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.UserDao;
import com.supinfo.supcourses.entities.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class JpaUserDao implements UserDao {

    @Override
    public User getUserByToken(UUID token) {
         Query query = em.createQuery("SELECT u from User u WHERE u.token=:token");
        query.setParameter("token", token);

        List<User> users = Collections.checkedList(query.getResultList(), User.class);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @PersistenceContext//(name = "SupCourses-ejbPU")
    private EntityManager em;

    @Override
    public User createUser(User newUser) {
        User user = findUserByEmail(newUser.getEmail());
        if (user != null)        
        {
            return null;
        }
        em.persist(newUser);
        return newUser;
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = em.createQuery("SELECT u from User u WHERE u.email=:email");
        query.setParameter("email", email);

        //@SuppressWarnings("unchecked")
        List<User> users = Collections.checkedList(query.getResultList(), User.class);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
    
      
    @Override
    public User authenticateUser(String email, String password)
    {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        String encryptedPassword = User.encryptPassword(password);
        query.setParameter("password", encryptedPassword);
        List<User> users = Collections.checkedList(query.getResultList(), User.class) ;
        if(users.isEmpty())
        {
            return null;
        }
        User user = users.get(0);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User userInDb = (User)em.find(User.class, user.getId());
        userInDb.setEmail(user.getEmail());
        //userInDb.setPassword(user.getPassword());
        userInDb.setTakenCourses(user.getTakenCourses());
        userInDb.setToken(user.getToken());
        return userInDb;
    }

    @Override
    public User getUserById(Long userId) {
        Query query = em.createQuery("SELECT u from User u WHERE u.id=:id");
        query.setParameter("id", userId);

        //@SuppressWarnings("unchecked")
        List<User> users = Collections.checkedList(query.getResultList(), User.class);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

}
