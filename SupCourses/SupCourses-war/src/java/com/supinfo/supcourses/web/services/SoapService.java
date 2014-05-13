package com.supinfo.supcourses.web.services;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.dao.QuizzDao;
import com.supinfo.supcourses.dao.UserDao;
import com.supinfo.supcourses.dao.jpa.JpaCourseDao;
import com.supinfo.supcourses.dao.jpa.JpaQuizzDao;
import com.supinfo.supcourses.dao.jpa.JpaUserDao;
import com.supinfo.supcourses.entities.Answer;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.Question;
import com.supinfo.supcourses.entities.Quizz;
import com.supinfo.supcourses.entities.User;
import com.supinfo.supcourses.services.JmsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.NamingException;

@WebService(serviceName = "SoapWebService", name="SoapWebService")
@Stateless()
public class SoapService {
    
    @EJB
    UserDao userDao = new JpaUserDao();
    
    @EJB
    CourseDao courseDao = new JpaCourseDao();
    
    @EJB
    QuizzDao quizzDao = new JpaQuizzDao();
    
    @EJB
    private JmsService jmsService = new JmsService();

    @WebMethod(operationName = "authenticate")
    public UUID authenticate(String email, String password) 
    {
        User user = userDao.authenticateUser(email, password);
        if(user == null)
        {
            return UUID.fromString("00000000-0000-0000-0000-000000000000");
        }
        else
        {
            user.setToken(UUID.randomUUID());
            UUID userToken = user.getToken();
            //User updatedUser = userDao.updateUser(user);
            return user.getToken();
        }
    }
    
    @WebMethod(operationName = "getUser")
    public User getUser(UUID token)
    {
        User user = userDao.getUserByToken(token);
        if(user==null)
        {
            return new User();
        }
        return user;
    }
    
    @WebMethod(operationName = "listCourses")
    public Collection<Course> listCourses()
    {
        ArrayList<Course> courses = new ArrayList<Course>(courseDao.getAllCourses());
        if(courses==null)
        {
            return new ArrayList<Course>();
        }
        return courses;
    }
    
    @WebMethod(operationName = "getCourse")
    public Course getCourse(Long courseId)
    {
        Course course = courseDao.findCourseById(courseId);
        if(course==null)
        {
            return new Course();
        }
        return course;
    }
    
    @WebMethod(operationName = "takeCourse")
    public boolean takeCourse(Long userId, Long courseId, UUID token)
    {
        User user = userDao.getUserById(userId);
        if(user==null)
        {
            return false;
        }
        UUID userToken = user.getToken();
        if(userToken.compareTo(token)!=0)
        {
            return false;
        }
        return courseDao.takeCourse(user, courseId);
    }
    
    @WebMethod(operationName = "passQuizz")
    public boolean passQuizz(Long userId, Long courseId, ArrayList<Answer> answers, UUID token)
    {
        User user = userDao.getUserById(userId);
        if(user==null)
        {
            return false;
        }
        UUID userToken = user.getToken();
        if(userToken.compareTo(token)!=0)
        {
            return false;
        }
        int badAnswers = quizzDao.correctQuizz(courseId, answers);
        if(badAnswers!=0)
        {
            return false;
        }
        return true;
    }
    
     @WebMethod(operationName = "printCertif")
    public boolean printCertif(Long userId, Long courseId, UUID token)
    {
        User user = userDao.getUserById(userId);
        if(user==null)
        {
            return false;
        }
        UUID userToken = user.getToken();
        if(userToken.compareTo(token)!=0)
        {
            return false;
        }
        Course course = courseDao.findCourseById(courseId);
        ArrayList<Course> courses = new ArrayList<Course>(courseDao.getAllCourses());
        boolean isTaken=false;
        for (int i = 0; i < courses.size(); i++) 
        {
            if(courses.get(i).getName().equals(course.getName()))
            {
                ArrayList<User> usersTookThisCourse = new ArrayList<User>(courses.get(i).getUsers());
                for(int j=0; j<usersTookThisCourse.size(); j++)
                {
                    if(usersTookThisCourse.get(j).getEmail().equals(user.getEmail()))
                    {
                        isTaken = true;
                        //courses.get(i).setIsTaken(true);
                    }
                }
            }
        }
        if(isTaken==false)
        {
            return false;
        }
        try 
        {
            jmsService.printCertificate(user, Long.valueOf(courseId));
            return true;
        } 
        catch (NamingException ex) 
        {
             System.out.println(ex);
             return false;
        } 
        catch (JMSException ex)
        {
            System.out.println(ex);
            return false;
        }
    }
}
