package com.supinfo.supcourses.servlets;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.dao.QuizzDao;
import com.supinfo.supcourses.dao.jpa.JpaCourseDao;
import com.supinfo.supcourses.dao.jpa.JpaQuizzDao;
import com.supinfo.supcourses.entities.Answer;
import com.supinfo.supcourses.entities.Course;
import com.supinfo.supcourses.entities.Quizz;
import com.supinfo.supcourses.entities.User;
import com.supinfo.supcourses.services.JmsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuizzServlet", urlPatterns = {"/auth/quizz"})
public class QuizzServlet extends HttpServlet {

    @EJB
    private QuizzDao quizzDao = new JpaQuizzDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // I KNOW THIS IS NOT A GOOD USE OF SERVLET
        // BUT I HATE TO SEE THE URL CHANGED WITH "FORWARD" WHILE WE ARE ON THE SAME PAGE
        // SO I USE SESSION TO PASS PARAMETERS
        HttpSession session = request.getSession();
        session.setAttribute("homeSuccessMessage", null);
        session.setAttribute("homeErrorMessage", null);
        session.setAttribute("indexSuccessMessage", null);
        session.setAttribute("indexErrorMessage", null);
        session.setAttribute("coursesSuccessMessage", null);
        session.setAttribute("coursesErrorMessage", null);

        String courseId = request.getParameter("courseId");
        Quizz quizz = quizzDao.getQuizzByCourseId(Long.valueOf(courseId));
        if (quizz == null) 
        {
            session.setAttribute("coursesErrorMessage", "No corresponding course nor quizz");
            response.sendRedirect("home");
        }
        else 
        {
            session.setAttribute("quizz", quizz);
            session.setAttribute("courseId", courseId);
            this.getServletContext().getRequestDispatcher("/jsp/auth/Quizz.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("quizzErrorMessage", null);
        session.setAttribute("quizzSuccessMessage", null);
        
        String courseId = request.getParameter("courseId");
        

        ArrayList<Answer> answers = new ArrayList<Answer>();
        Enumeration<String> parameterNames = request.getParameterNames();
        boolean badSubmit = false;
        while (parameterNames.hasMoreElements()) 
        {
            String paramName = parameterNames.nextElement();
            if(!paramName.equals("courseId"))
            {
                Answer answer = new Answer();
                answer.setQuestionId(Integer.parseInt(paramName));
                String[] paramValues = request.getParameterValues(paramName);
                if(paramValues[0].equals("null"))
                {
                    badSubmit = true;
                }
                else
                {
                    answer.setAnswerId(Integer.parseInt(paramValues[0]));
                    answers.add(answer);
                }
            }
        }
        if(badSubmit==true)
        {
            session.setAttribute("quizzErrorMessage", "You have to answer to all questions !");
            response.sendRedirect(request.getContextPath()+"/auth/quizz?courseId="+courseId);
        }
        else
        {
            int badAnswersCount = quizzDao.correctQuizz(Long.valueOf(courseId), answers);
            if(badAnswersCount > 0)
            {
                session.setAttribute("quizzErrorMessage", "You failed this quizz, "+badAnswersCount+" bad answers !");
                response.sendRedirect(request.getContextPath()+"/auth/quizz?courseId="+courseId);
            }
            else
            {
                session.setAttribute("quizzSuccessMessage", "Congratulation, you successfully passed this quizz. You can now print a certificate !");
                response.sendRedirect(request.getContextPath()+"/auth/quizz?courseId="+courseId);
            }
        }
    }
}
