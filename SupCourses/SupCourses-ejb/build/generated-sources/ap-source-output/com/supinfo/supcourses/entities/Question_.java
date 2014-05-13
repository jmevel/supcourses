package com.supinfo.supcourses.entities;

import com.supinfo.supcourses.entities.Quizz;
import java.util.Collection;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-23T20:33:27")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Long> id;
    public static volatile SingularAttribute<Question, String> description;
    public static volatile SingularAttribute<Question, Integer> goodAnswerIndex;
    public static volatile SingularAttribute<Question, Quizz> quizz;
    public static volatile SingularAttribute<Question, Collection> answers;

}