<%-- 
    Document   : update-user
    Created on : Feb 19, 2020, 8:24:26 PM
    Author     : VDPKGL
--%>

<%@page import="com.poly.models.User"%>
<%@page import="com.poly.dao.UserDAO"%>
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
            <%@include file="../topmenu.jsp" %>
            <%@include file="leftmenu.jsp" %>
        </div>
        <div class="app-content">
            <h1>Sửa thông tin người dùng</h1>
            <%                int id = Integer.parseInt(request.getParameter("id"));
                UserDAO dao = new UserDAO();
                User user = dao.findById(id);
            %>
            <form action="../UserController" method="get" class="form-group">
                <input type="hidden" name="id" value="<%= user.getId()%>"></input><br><br>
                Username: <input type="text" name="username" value="<%= user.getUsername()%>" class="form-control"></input><br><br>
                Password: <input type="text" name="password" value="<%= user.getPassword()%>" class="form-control"></input><br><br>
                Fullname: <input type="text" name="fullname" value="<%= user.getFullname()%>" class="form-control"></input><br><br>
                Email: <input type="text" name="email" value="<%= user.getEmail()%>" class="form-control"></input><br><br>
                Phone: <input type="text" name="phone" value="<%= user.getPhone()%>" class="form-control"></input><br><br>
                <% int role = user.getRole(); %>
                Role: <input type="radio" name="role" value="1" <% if (role == 1) {
                        out.print("checked");
                    }%>>Admin</input>
                <input type="radio" name="role" value="0" <% if (role == 0) {
                        out.print("checked");
                    }%>>User</input><br><br>
                <input type="submit" name="_type" value="SAVE">
            </form>    
        </div>
    </body>
</html>
