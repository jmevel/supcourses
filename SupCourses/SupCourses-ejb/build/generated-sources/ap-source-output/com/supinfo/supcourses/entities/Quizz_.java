package com.supinfo.supcourses.entities;

import com.supinfo.supcourses.entities.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-23T20:33:27")
@StaticMetamodel(Quizz.class)
public class Quizz_ { 

    public static volatile SingularAttribute<Quizz, Long> id;
    public static volatile CollectionAttribute<Quizz, Question> questions;

}