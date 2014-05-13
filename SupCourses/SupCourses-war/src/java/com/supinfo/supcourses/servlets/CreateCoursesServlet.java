package com.supinfo.supcourses.servlets;

import com.supinfo.supcourses.dao.MockDao;
import com.supinfo.supcourses.utils.JpaMockDao;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreateCoursesServlet", urlPatterns = {"/auth/createCourses"})
public class CreateCoursesServlet extends HttpServlet {

   /* @EJB
    private CourseDao courseDao = new JpaCourseDao();*/
    
    @EJB
    private MockDao mockDao = new JpaMockDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // I KNOW THIS IS NOT A GOOD USE OF SERVLET
        // BUT I HATE TO SEE THE URL CHANGED WITH "FORWARD" WHILE WE ARE ON THE SAME PAGE
        // SO I USE SESSION TO PASS PARAMETERS
        HttpSession session = request.getSession();
        session.setAttribute("homeSuccessMessage",  null);
        session.setAttribute("homeErrorMessage",  null);
        
        boolean result = mockDao.CreateFakeDataInDb();
        //boolean result = courseDao.CreateSomeCoursesInDb();
        if(result == true)
        {
            session.setAttribute("homeSuccessMessage",  "3 courses and 3 quizz have been created for testing");
        }
        else
        {
            session.setAttribute("homeErrorMessage",  "Courses have already been created !");
        }
        response.sendRedirect("home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
