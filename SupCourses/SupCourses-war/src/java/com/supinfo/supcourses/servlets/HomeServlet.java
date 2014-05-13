package com.supinfo.supcourses.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HomeServlet", urlPatterns = {"/auth/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
        
        // I KNOW THIS IS NOT A GOOD USE OF SERVLET
        // BUT I HATE TO SEE THE URL CHANGED WITH "FORWARD" WHILE WE ARE ON THE SAME PAGE
        // SO I USE SESSION TO PASS PARAMETERS
        HttpSession session = request.getSession();
        session.setAttribute("coursesErrorMessage", null);
        session.setAttribute("coursesSuccessMessage", null);
        session.setAttribute("indexSuccessMessage",  null);
        session.setAttribute("indexErrorMessage",  null);
        session.setAttribute("quizzSuccessMessage",  null);
        session.setAttribute("quizzErrorMessage",  null);
        
        this.getServletContext().getRequestDispatcher("/jsp/auth/Home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
