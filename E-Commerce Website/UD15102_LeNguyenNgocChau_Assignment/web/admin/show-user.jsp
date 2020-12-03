<%-- 
    Document   : show-user
    Created on : Feb 18, 2020, 10:41:04 AM
    Author     : VDPKGL
--%>

<%@page import="com.poly.models.User"%>
<%@page import="java.util.ArrayList"%>
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
            <h1>Danh sách người dùng</h1>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Username</td>
                    <td>Fullname</td>
                    <td>Email</td>
                    <td>Phone</td>
                    <td>Role</td>
                    <td>Delete</td>
                    <td>Edit</td>
                </tr>
                <%                    UserDAO user = new UserDAO();
                    ArrayList<User> dsUser = user.fillAll();
                    for (int i = 0; i < dsUser.size(); i++) {%>
                <tr>
                    <td><%= dsUser.get(i).getId()%></td>
                    <td><%= dsUser.get(i).getUsername()%></td>
                    <td><%= dsUser.get(i).getFullname()%></td>
                    <td><%= dsUser.get(i).getEmail()%></td>
                    <td><%= dsUser.get(i).getPhone()%></td>
                    <td><%= dsUser.get(i).getRole()%></td>
                <form action="../UserController" method="get">
                    <td><input type="submit" name="_type" value="DELETE"></td>
                    <td><input type="submit" name="_type" value="UPDATE"></td>
                    <input type="hidden" name="userid" value="<%= dsUser.get(i).getId()%>"/>
                </form>
                </tr>
                <%}%>
            </table>
        </div>        
    </body>
</html>
