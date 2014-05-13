package com.supinfo.supcourses.servlets;

import com.supinfo.supcourses.entities.Quizz;
import com.supinfo.supcourses.entities.User;
import com.supinfo.supcourses.services.JmsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CertificateServlet", urlPatterns = {"/auth/certif"})
public class CertificateServlet extends HttpServlet {

    @EJB
    private JmsService jmsService = new JmsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");
        try 
        {
            jmsService.printCertificate(currentUser, Long.valueOf(courseId));
            session.setAttribute("quizzSuccessMessage", "Certificate printed !");
            response.sendRedirect(request.getContextPath()+"/auth/quizz?courseId="+courseId);
        } 
        catch (NamingException ex) 
        {
             System.out.println(ex);
             session.setAttribute("quizzErrorMessage", "An error appeared when trying to print certificate !");
             response.sendRedirect(request.getContextPath()+"/auth/quizz?courseId="+courseId);
        } 
        catch (JMSException ex)
        {
            System.out.println(ex);
            session.setAttribute("quizzErrorMessage", "An error appeared when trying to print certificate !");
            response.sendRedirect(request.getContextPath()+"/auth/quizz?courseId="+courseId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
