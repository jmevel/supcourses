package com.supinfo.supcourses.utils;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.dao.MockDao;
import com.supinfo.supcourses.dao.QuestionDao;
import com.supinfo.supcourses.dao.QuizzDao;
import com.supinfo.supcourses.dao.jpa.JpaCourseDao;
import com.supinfo.supcourses.dao.jpa.JpaQuestionDao;
import com.supinfo.supcourses.dao.jpa.JpaQuizzDao;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.Level;
import com.supinfo.supcourses.entities.Question;
import com.supinfo.supcourses.entities.Quizz;
import com.supinfo.supcourses.entities.Subject;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JpaMockDao implements MockDao{
    
    @PersistenceContext(unitName = "SupCourses-ejbPU")
    private EntityManager em;
    
    @EJB
    private CourseDao courseDao = new JpaCourseDao();
    
    @EJB
    private QuizzDao quizzDao = new JpaQuizzDao();
    
    @EJB
    private QuestionDao questionDao = new JpaQuestionDao();
     
    @Override
    public boolean CreateFakeDataInDb()
    {
        
        Course course1 = new Course();
        course1.setLevelDifficulty(Level.M1);
        course1.setName("4JVA");
        course1.setSubject(Subject.Java);
        course1.setDuration(48);
        course1.setDescription("Java course for M1 students");
        course1.setIsTaken(false);
        
        ArrayList<Question> questions1 = new ArrayList<>();
        
        Question question1 = new Question();
        question1.setDescription("What happens in Java island since 2006 ?");
        question1.setAnswers(new ArrayList<String>(Arrays.asList("Java became the official language", "People became really rich", "A mud volcano is in erruption")));
        question1.setGoodAnswerIndex(3);
        
        questions1.add(question1);
        
        Question question2 = new Question();
        question2.setDescription("When Java language has been created ?");
        question2.setAnswers(new ArrayList<String>(Arrays.asList("In 1942 during WW2", "In 1995", "In 1969 during hippie movement")));
        question2.setGoodAnswerIndex(2);
        questions1.add(question2);
        
        questionDao.createQuestions(questions1);
        
        Quizz quizz1 = new Quizz();
        quizz1.setQuestions(questions1);
        quizzDao.createQuizz(quizz1);
        course1.setQuizz(quizz1);

        if (courseDao.createCourse(course1) == null) {
            return false;
        }
        
/*******************************************************************************/
        
        Course course2 = new Course();
        course2.setLevelDifficulty(Level.B3);
        course2.setName("3NET");
        course2.setSubject(Subject.NET);
        course2.setDuration(35);
        course2.setDescription(".NET course for B3 students. (WPF, Windows phone)");
        course2.setIsTaken(false);
        
        ArrayList<Question> questions2 = new ArrayList<>();
        
        Question question3 = new Question();
        question3.setDescription("What is .NET ?");
        question3.setAnswers(new ArrayList<String>(Arrays.asList("A framework", "A language", "A top level domain (TLD)")));
        question3.setGoodAnswerIndex(1);
        questions2.add(question3);
        
        Question question4 = new Question();
        question4.setDescription("When .NET framework development started ?");
        question4.setAnswers(new ArrayList<String>(Arrays.asList("In 1789 during French revolution", "During 1990's", "In 2291. It comes from the future")));
        question4.setGoodAnswerIndex(2);
        questions2.add(question4);
        
        questionDao.createQuestions(questions2);
        
        Quizz quizz2 = new Quizz();
        quizz2.setQuestions(questions2);
        quizzDao.createQuizz(quizz2);
        course2.setQuizz(quizz2);
        
        if (courseDao.createCourse(course2) == null) {
            return false;
        }
        
/*******************************************************************************/
        
        Course course3 = new Course();
        course3.setLevelDifficulty(Level.B1);
        course3.setName("1MGT");
        course3.setSubject(Subject.Management);
        course3.setDuration(24);
        course3.setDescription("No management in B1 but I don't care, this is just an example");
        course3.setIsTaken(false);
        
        ArrayList<Question> questions3 = new ArrayList<>();
        
        Question question5 = new Question();
        question5.setDescription("Does management class boring ?");
        question5.setAnswers(new ArrayList<String>(Arrays.asList("No. I never seen something such interesting", "No it's ok", "Yes. a relocation is always boring so manage a relocation project...")));
        question5.setGoodAnswerIndex(3);
        questions3.add(question5);
        
        questionDao.createQuestions(questions3);
        
        Quizz quizz3 = new Quizz();
        quizz3.setQuestions(questions3);
        quizzDao.createQuizz(quizz3);
        course3.setQuizz(quizz3);
        
        if (courseDao.createCourse(course3) == null) {
            return false;
        }
        return true;
    }
}
