
package com.supinfo.supcourses.dao;

import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.User;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface CourseDao 
{
    Course createCourse(Course newCourse);
    Course findCourseByName(String name);
    Course findCourseById(Long courseId);
    Collection<Course> getAllCourses();
    boolean takeCourse(User user, Long courseId);
}
