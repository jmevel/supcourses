package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.dao.QuizzDao;
import com.supinfo.supcourses.entities.Answer;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.Question;
import com.supinfo.supcourses.entities.Quizz;
import com.supinfo.supcourses.entities.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JpaQuizzDao implements QuizzDao{

    @PersistenceContext(unitName = "SupCourses-ejbPU")
    private EntityManager em;
   
    @EJB
    private CourseDao courseDao = new JpaCourseDao();
     
    @Override
    public Quizz createQuizz(/*Long courseId, */Quizz quizz) {
        em.persist(quizz);
        return quizz;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    @Override
    public Quizz getQuizzByCourseId(Long courseId) {

        Course course = courseDao.findCourseById(courseId);
        if(course == null)
        {
            return null;
        }
        return course.getQuizz();
    }
    
    @Override
    public int correctQuizz(long courseId, ArrayList<Answer> answers) {
        int badAnswer = 0;
        Quizz quizz = getQuizzByCourseId(courseId);
        ArrayList<Question> questions = new ArrayList<Question>(quizz.getQuestions());
        for(int i=0; i<answers.size(); i++)
        {
            int questionId = answers.get(i).getQuestionId();
            Question question = questions.get(questionId-1);
            if(question.getGoodAnswerIndex()!=answers.get(i).getAnswerId())
            {
                badAnswer++;
            }
        }
        return badAnswer;
    }
    
}
