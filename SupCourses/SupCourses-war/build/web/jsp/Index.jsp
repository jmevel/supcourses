<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>      
        <%@ page import="java.util.*" %>
        <link rel="stylesheet" type="text/css" href="css/semantic.css">

        <script type="text/javascript" src="javascript/jquery-1.11.0.js"></script>
        <script src="javascript/semantic.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#registrationForm')
                        .form({
                            email: {
                                identifier: 'emailRegistration',
                                rules: [
                                    {
                                        type: 'email',
                                        prompt: 'Please enter a valid email address'
                                    }
                                ]
                            },
                            password: {
                                identifier: 'passwordRegistration',
                                rules: [
                                    {
                                        type: 'empty',
                                        prompt: 'Please enter a password'
                                    },
                                    {
                                        type: 'length[6]',
                                        prompt: 'Your password must be at least 6 characters'
                                    }
                                ]
                            },
                            passwordConfirm: {
                                identifier: 'passwordConfirmationRegistration',
                                rules: [
                                    {
                                        type: 'empty',
                                        prompt: 'Please confirm your password'
                                    },
                                    {
                                        type: 'match[passwordRegistration]',
                                        prompt: 'Password doesn\'t match'
                                    }
                                ]
                            }
                        });

                $('#loginForm')
                        .form({
                            email: {
                                identifier: 'emailLogin',
                                rules: [
                                    {
                                        type: 'email',
                                        prompt: 'Please enter a valid email address'
                                    }
                                ]
                            },
                            password: {
                                identifier: 'passwordLogin',
                                rules: [
                                    {
                                        type: 'empty',
                                        prompt: 'Please enter a password'
                                    },
                                    {
                                        type: 'length[6]',
                                        prompt: 'Your password must be at least 6 characters'
                                    }
                                ]
                            }
                        });
                $('#registrationForm').submit(function() {
                    var isValid = $('#registrationForm').form('validate form');
                    if (isValid === true) {
                        $('#loader').attr('class', 'ui active dimmer');
                        //event.preventDefault();
                    }
                });
                $('#loginForm').submit(function() {
                    var isValid = $('#loginForm').form('validate form');
                    if (isValid === true) {
                        $('#loader').attr('class', 'ui active dimmer');
                        //event.preventDefault();
                    }
                });
            });
        </script>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("indexErrorMessage") != null) {
        %><div class="ui error message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("indexErrorMessage")); %>
            </div>
        </div><%
            }
            if (request.getSession().getAttribute("indexSuccessMessage") != null) {
        %><div class="ui success message">
            <i class="close icon"></i>
            <div class="header">
                <% out.println(request.getSession().getAttribute("indexSuccessMessage")); %>
            </div>
        </div><%
            }
        %>
        <div class="ui raised segment">
            <div id="loader" class="ui active">
                <div class="ui text loader">Loading</div>
            </div>
            <%@ include file="/WEB-INF/menuBar.jspf" %> 
            <div class="ui two column grid basic segment">
                <div class="column">
                    <h3 class="ui inverted teal block header"> SIGN UP </h3>
                    <div class="ui teal stacked segment">
                        <form method="POST" action="register" id="registrationForm" class="ui form">
                            <div class="field">
                                <label> Email </label>
                                <div class="ui left labeled icon input">
                                    <input id="emailRegistration" name="emailRegistration" placeholder="Please enter valid email address" type="text">
                                    <i class="mail icon"></i>
                                    <div class="ui corner label">
                                        <i class="asterisk icon"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <label> Password </label>
                                    <div class="ui left labeled icon input">
                                        <input name="passwordRegistration" placeholder="At least 6 characters" type="password">
                                        <i class="lock icon"></i>
                                        <div class="ui corner label">
                                            <i class="asterisk icon"></i>
                                        </div>
                                    </div>   
                                </div>
                                <div class="field">
                                    <label> Confirm Password </label>
                                    <div class="ui left labeled icon input">
                                        <input name="passwordConfirmationRegistration" placeholder="Repeat password" type="password">
                                        <i class="lock icon"></i>
                                        <div class="ui corner label">
                                            <i class="asterisk icon"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="submit" value="Register" class="ui teal submit button">
                            <div class="ui error message"></div>
                        </form>
                    </div>
                </div>
                <div class="ui vertical divider"> OR </div>
                <div class="column" style="margin-top: 0px!important;">
                    <h3 class="ui inverted teal block header"> SIGN IN </h3>
                    <div class="ui teal stacked segment">
                        <form id="loginForm" method="POST" action="login" class="ui form">
                            <div class="field">
                                <label> Email </label>
                                <div class="ui left labeled icon input">
                                    <input id="emailLogin" name="emailLogin" placeholder="Your email address" type="text">
                                    <i class="user icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label> Password </label>
                                <div class="ui left labeled icon input">
                                    <input id="passwordLogin" name="passwordLogin" placeholder="Your password" type="password">
                                    <i class="lock icon"></i>
                                </div>
                            </div>
                            <input type="submit" value="Login" class="ui teal submit button">
                            <div class="ui error message"></div>
                        </form>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
