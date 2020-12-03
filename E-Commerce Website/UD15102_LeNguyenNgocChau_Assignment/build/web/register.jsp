<%-- 
    Document   : register
    Created on : Feb 22, 2020, 11:04:08 AM
    Author     : VDPKGL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="app-sidebar">
            <%@include file="/topmenu.jsp" %>           
        </div>
        <div class="app-content">
            <h1>Đăng ký</h1>
            <form action="../UserRegController" method="get" class="form-group">
                Username: <input type="text" name="username" class="form-control"></input><br><br>
                Password: <input type="text" name="password" class="form-control"></input><br><br>
                Fullname: <input type="text" name="fullname" class="form-control"></input><br><br>
                Email: <input type="text" name="email" class="form-control"></input><br><br>
                Phone: <input type="text" name="phone" class="form-control"></input><br><br>
                <input type="submit" name="_type" value="Register">
            </form>
        </div>
    </body>
</html>
