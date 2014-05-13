package com.supinfo.supcourses.entities;

import com.supinfo.supcourses.entities.Level;
import com.supinfo.supcourses.entities.Quizz;
import com.supinfo.supcourses.entities.Subject;
import com.supinfo.supcourses.entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-23T20:33:27")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, Long> id;
    public static volatile SingularAttribute<Course, Level> levelDifficulty;
    public static volatile SingularAttribute<Course, Boolean> isTaken;
    public static volatile SingularAttribute<Course, Integer> duration;
    public static volatile CollectionAttribute<Course, User> users;
    public static volatile SingularAttribute<Course, Subject> subject;
    public static volatile SingularAttribute<Course, String> description;
    public static volatile SingularAttribute<Course, String> name;
    public static volatile SingularAttribute<Course, Quizz> quizz;

}