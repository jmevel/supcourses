<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="../css/semantic.css">

        <script type="text/javascript" src="../javascript/jquery-1.11.0.js"></script>
        <script src="../javascript/semantic.js"></script>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("homeErrorMessage") != null) {
        %><div class="ui error message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("homeErrorMessage")); %>
            </div>
        </div><%
            }
            if (request.getSession().getAttribute("homeSuccessMessage") != null) {
        %><div class="ui success message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("homeSuccessMessage")); %>
            </div>
        </div><%
            }
        %>
        <%@ include file="/WEB-INF/menuBar.jspf" %> 
        <div style="text-align: center">
            <h2 style="text-align: center">There is nothing to display here, except this button: </h2>
            <a href="createCourses" class="ui blue button">
                <i class="tasks icon"></i> Create 3 courses for testing
            </a>
        </div>

    </body>
</html>
