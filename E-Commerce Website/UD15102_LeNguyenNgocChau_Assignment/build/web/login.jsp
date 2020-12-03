<%-- 
    Document   : login
    Created on : Feb 21, 2020, 5:29:35 PM
    Author     : VDPKGL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <table width="100%" border="1">
            <div class="app-sidebar">
                <%@include file="/topmenu.jsp" %>           
            </div>
            <div class="app-content">
                <form action="LoginController" method="get" class="form-group">
                    Username: <input type="text" name="username" class="form-control"></input><br><br>
                    Password: <input type="text" name="password" class="form-control"></input><br><br>
                    <input type="submit" value="Login">
                    <%
                        String message = request.getParameter("message");
                        if (message != null) {
                            out.print(message);
                        }
                    %>
                </form>
            </div>
    </body>
</html>
