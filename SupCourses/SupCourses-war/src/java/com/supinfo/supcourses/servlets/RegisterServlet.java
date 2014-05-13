/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supcourses.servlets;

import com.supinfo.supcourses.dao.UserDao;
import com.supinfo.supcourses.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserDao userDao;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/Index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // I KNOW THIS IS NOT A GOOD USE OF SERVLET
        // BUT I HATE TO SEE THE URL CHANGED WITH "FORWARD" WHILE WE ARE ON THE SAME PAGE
        // SO I USE SESSION TO PASS PARAMETERS
        HttpSession session = request.getSession();
        session.setAttribute("indexErrorMessage", null);
        session.setAttribute("indexSuccessMessage", null);
        
        String email = request.getParameter("emailRegistration");
        String password = request.getParameter("passwordRegistration");
        

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User createdUser = userDao.createUser(user);
        if(createdUser == null)
        {
             session.setAttribute("indexErrorMessage",  "This email address is already taken !");
             response.sendRedirect("index");
        }
        else
        {
            session.setAttribute("user", user);
            response.sendRedirect("auth/home");
        }
    }

}
