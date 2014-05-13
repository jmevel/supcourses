package com.supinfo.supcourses.entities;

import com.supinfo.supcourses.entities.Course;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-23T20:33:27")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Long> id;
    public static volatile CollectionAttribute<User, Course> takenCourses;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, UUID> token;
    public static volatile SingularAttribute<User, String> password;

}