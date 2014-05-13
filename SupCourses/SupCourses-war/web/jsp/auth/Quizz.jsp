<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<c:set var="rand"><%= java.lang.Math.round(java.lang.Math.random() * 2)%></c:set>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Quizz</title>
            <link rel="stylesheet" type="text/css" href="../css/semantic.css">

            <script type="text/javascript" src="../javascript/jquery-1.11.0.js"></script>
            <script src="../javascript/semantic.js"></script>
            <script type="text/javascript">
                $(document).ready(function() {
                    $('.ui.radio.checkbox').checkbox();
                });
            </script>
        </head>
        <body>
        <%
            if (request.getSession().getAttribute("quizzErrorMessage") != null) {
        %><div class="ui error message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("quizzErrorMessage")); %>
            </div>
        </div><%
            }
            if (request.getSession().getAttribute("quizzSuccessMessage") != null) {
        %><div class="ui success message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("quizzSuccessMessage")); %>
            </div>
        </div><%
            }
        %>
        <%@ include file="/WEB-INF/menuBar.jspf" %>
        <form action="${pageContext.request.contextPath}/auth/quizz" method="POST" class="ui form segment">
            <input type="hidden" value="${courseId}" name="courseId"/>
            <table class="ui table segment">
                <thead>
                    <tr><th>Questions</th>
                        <th>Answers</th>
                    </tr></thead>
                <tbody>
                    <c:set var="countQuestion" value="1"/>
                    <c:forEach var="question" items="${quizz.getQuestions()}">
                        <tr>
                            <td>${question.getDescription()}</td>
                            <td class="field">
                                <c:set var="countAnswer" value="1"/>
                                <input type="radio" name="${countQuestion}" style="visibility: hidden;" value="null" checked="checked"/>
                                <c:forEach var="answer" items="${question.getAnswers()} ">
                                    <div class="field">
                                        <div class="ui radio checkbox">
                                            <input type="radio" name="${countQuestion}" value="${countAnswer}"/>
                                            <label>${answer}</label>
                                        </div>
                                    </div>
                                    <c:set var="countAnswer" value="${countAnswer + 1}"/>
                                </c:forEach>
                                <c:set var="countQuestion" value="${countQuestion + 1}"/>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div  class="field">
                <input type="submit" value="Submit answers" class="ui orange submit button floated right" style="text-align: right!important;"/>
            </div>
        </form>
        <c:if test="${quizzSuccessMessage == null}">
        <td>
            <div class="ui icon button disabled orange floated right">
                Print Certificate
            </div>
        </td>
    </c:if>
    <c:if test="${quizzSuccessMessage != null}">
        <form method="GET" action="${pageContext.request.contextPath}/auth/certif">
            <input type="hidden" name="courseId" value="${courseId}"/>
            <div  class="field">
                <input type="submit" value="Print Certificate" class="ui orange submit button floated right" />
            </div>
        </form>
    </c:if>
</body>
</html>
