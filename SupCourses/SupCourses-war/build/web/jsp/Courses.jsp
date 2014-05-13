<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Courses</title>
        <%@ page import="java.util.*" %>
        <link rel="stylesheet" type="text/css" href="css/semantic.css">

        <script type="text/javascript" src="javascript/jquery-1.11.0.js"></script>
        <script src="javascript/semantic.js"></script>
        <script type="text/javascript">
            function loginMessage() {
                alert("you have to login");
            }
        </script>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("coursesErrorMessage") != null) {
        %><div class="ui error message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("coursesErrorMessage")); %>
            </div>
        </div><%
            }
            if (request.getSession().getAttribute("coursesSuccessMessage") != null) {
        %><div class="ui success message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("coursesSuccessMessage")); %>
            </div>
        </div><%
            }
        %>
        <%@ include file="/WEB-INF/menuBar.jspf" %> 
        <c:if test="${courses.size() == 0}">
            <h2 style="text-align: center">There is no courses created</h2>
            <h2 style="text-align: center">To create courses for testing, login and click the button in Home page</h2>
        </c:if>
        <c:if test="${courses.size() > 0}">
            <table class="ui table segment">
                <thead>
                    <tr><th>Name</th>
                        <th>Level</th>
                        <th>Subject</th>
                        <th>Description</th>
                        <th>Duration</th>
                        <th>Action</th>
                        <th>Quizz</th>
                       <!-- <th>Certification</th>-->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${courses}">
                        <tr>
                            <td><c:out value="${course.getName()}" /></td>
                            <td><c:out value="${course.getLevelDifficulty()}" /></td>
                            <td><c:out value="${course.getSubject()}" /></td>
                            <td><c:out value="${course.getDescription()}" /></td>
                            <td><c:out value="${course.getDuration()}" /> hours</td>
                            <c:if test="${course.isIsTaken() == false}">
                                <c:if test="${user == null}">
                                    <td><a onclick="loginMessage();" href="" class="ui orange button">Take this course</a></td>
                                </c:if>
                                <c:if test="${user != null}">
                                    <td>
                                        <form method="POST">
                                            <input type="hidden" name="courseToTake" value="${course.getId()}"/>
                                            <input type="submit" value="Take this course" class="ui orange button"/>
                                        </form>
                                    </td>
                                    <td>
                                        <div class="ui disabled button">
                                            Pass quizz
                                        </div>
                                    </td>
                                   <!-- <td>
                                         <div class="ui icon button disabled orange">
                                            <i class="disabled print icon"></i>
                                        </div>
                                    </td>-->
                                </c:if>
                            </c:if>
                            <c:if test="${course.isIsTaken() == true}">
                                <td>
                                    <div class="ui green message">
                                        <div class="header">
                                            <i class="checkmark icon"></i>
                                            Course taken
                                        </div>

                                    </div>
                                </td>
                                <td>
                                    <form method="GET" action="${pageContext.request.contextPath}/auth/quizz">
                                        <input type="hidden" name="courseId" value="${course.getId()}"/>
                                        <input type="submit" value="Pass quizz" class="ui orange button"/>
                                    </form>
                                </td>
                               <!-- <td>
                                    <form method="POST" action="/auth/printCertification">
                                        <input type="hidden" name="courseId" value="${course.getId()}"/>
                                        <div class="ui icon button orange">
                                            <i class="print icon"></i>
                                        </div>
                                    </form>
                                </td>-->
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
