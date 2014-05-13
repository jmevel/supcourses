package com.supinfo.supcourses.dao.jpa;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.Level;
import com.supinfo.supcourses.entities.Subject;
import com.supinfo.supcourses.entities.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class JpaCourseDao implements CourseDao {

    @PersistenceContext(unitName = "SupCourses-ejbPU")
    private EntityManager em;

    @Override
    public Course createCourse(Course newCourse) {
        Course course = findCourseByName(newCourse.getName());
        if (course != null) {
            return null;
        }
        em.persist(newCourse);
        return newCourse;
    }

    @Override
    public Collection<Course> getAllCourses() {
        Query query = em.createQuery("SELECT c FROM Course c");
        List<Course> courses = Collections.checkedList(query.getResultList(), Course.class);
        return courses;
    }

    @Override
    public Course findCourseByName(String name) {
        Query query = em.createQuery("SELECT c from Course c WHERE c.name=:name");
        query.setParameter("name", name);

        List<Course> courses = Collections.checkedList(query.getResultList(), Course.class);
        if (courses.isEmpty()) {
            return null;
        }
        return courses.get(0);
    }

    @Override
    public Course findCourseById(Long courseId) {
        Query query = em.createQuery("SELECT c from Course c WHERE c.id=:id");
        query.setParameter("id", courseId);

        List<Course> courses = Collections.checkedList(query.getResultList(), Course.class);
        if (courses.isEmpty()) {
            return null;
        }
        return courses.get(0);
    }

    @Override
    public boolean takeCourse(User user, Long courseId) 
    {
        Course course = findCourseById(courseId);
        ArrayList<User> users = new ArrayList<User>(course.getUsers());
        for (int i = 0; i < users.size(); i++) 
        {
            if (users.get(i).getEmail().equals(user.getEmail())) 
            {
                return false;
            }
        }
        
        users.add(user);
        course.setUsers(users);
        em.merge(course);
        return true;
    }
}
