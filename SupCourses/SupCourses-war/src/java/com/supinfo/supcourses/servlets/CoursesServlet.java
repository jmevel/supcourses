package com.supinfo.supcourses.servlets;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CoursesServlet", urlPatterns = {"/courses"})
public class CoursesServlet extends HttpServlet {

    @EJB
    CourseDao courseDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // I KNOW THIS IS NOT A GOOD USE OF SERVLET
        // BUT I HATE TO SEE THE URL CHANGED WITH "FORWARD" WHILE WE ARE ON THE SAME PAGE
        // SO I USE SESSION TO PASS PARAMETERS
        HttpSession session = request.getSession();
        session.setAttribute("homeSuccessMessage",  null);
        session.setAttribute("homeErrorMessage",  null);
        session.setAttribute("indexSuccessMessage",  null);
        session.setAttribute("indexErrorMessage",  null);
        session.setAttribute("quizzSuccessMessage",  null);
        session.setAttribute("quizzErrorMessage",  null);
        
        ArrayList<Course> courses = new ArrayList<Course>(courseDao.getAllCourses());
        User currentUser = (User)session.getAttribute("user");
        for (int i = 0; i < courses.size(); i++) 
        {
            ArrayList<User> usersTookThisCourse = new ArrayList<User>(courses.get(i).getUsers());
            for(int j=0; j<usersTookThisCourse.size(); j++)
            {
                if(usersTookThisCourse.get(j).getEmail().equals(currentUser.getEmail()))
                {
                    courses.get(i).setIsTaken(true);
                }
            }
        }
        session.setAttribute("courses", courses);
        this.getServletContext().getRequestDispatcher("/jsp/Courses.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("coursesErrorMessage", null);
        session.setAttribute("coursesSuccessMessage", null);
        
        User currentUser = (User)session.getAttribute("user");
        String courseId = request.getParameter("courseToTake");
        boolean result = courseDao.takeCourse(currentUser, Long.valueOf(courseId));
        if(result == false)
        {
            session.setAttribute("coursesErrorMessage", "An error occured, maybe you already took this course");
        }
        response.sendRedirect(request.getContextPath()+"/courses");
        //this.getServletContext().getRequestDispatcher("/jsp/Courses.jsp").forward(request, response);
    }
}
