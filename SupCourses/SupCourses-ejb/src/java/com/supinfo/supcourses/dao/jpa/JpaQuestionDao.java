

package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.QuestionDao;
import com.supinfo.supcourses.entities.Question;
import com.supinfo.supcourses.entities.Quizz;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JpaQuestionDao implements QuestionDao{

    @PersistenceContext(unitName = "SupCourses-ejbPU")
    private EntityManager em;

    @Override
    public ArrayList<Question> createQuestions(ArrayList<Question> questions) {
        for(int i=0; i<questions.size(); i++)
        {
            em.persist(questions.get(i));
        }
        return questions;
    }
     
    @Override
    public Question createQuestion(Question question) {
        em.persist(question);
        return question;
    }
    
}
