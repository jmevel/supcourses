package com.supinfo.supcourses.services;

import com.supinfo.supcourses.dao.CourseDao;
import com.supinfo.supcourses.dao.jpa.JpaCourseDao;
import com.supinfo.supcourses.entities.User;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
@LocalBean
public class JmsService {
    
    @EJB
    CourseDao courseDao = new JpaCourseDao();
    
     public void printCertificate(User user, Long courseId) throws NamingException, JMSException {

        Context ctx = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/GlassFishPrintConnectionFactory");

        Destination destination = (Destination) ctx.lookup("jms/GlassFishPrintQueue");

        Connection cnx = connectionFactory.createConnection();
        Session session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(destination);

        String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        
        //LAZY TO ADD First name and Last name to my registration process now
        //So I use just the email address, I opologize for that
        String txt = "CERTIFICATION: "
                +"\n SupCourses Certificate that "+user.getEmail()
                +" passed successfully the course "+courseDao.findCourseById(courseId).getName()
                +"\n Date: "+timeStamp;

        TextMessage message = session.createTextMessage();
        message.setText(txt);

        producer.send(message);
        System.out.println("Sending JMS....");
        System.out.println(txt);

        cnx.close();

    }
    
}
