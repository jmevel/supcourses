package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entities.User;
import java.util.UUID;
import javax.ejb.Local;


@Local
public interface UserDao {
     User createUser(User newUser);
     User findUserByEmail(String email);
     User authenticateUser(String email, String password);
     User updateUser(User user);
     User getUserByToken(UUID token);
     User getUserById(Long userId);
}
