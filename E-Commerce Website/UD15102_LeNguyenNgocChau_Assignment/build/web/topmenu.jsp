<%-- 
    Document   : topmenu
    Created on : Feb 21, 2020, 6:12:29 PM
    Author     : VDPKGL
--%>

<%@page import="com.poly.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
    <li style="list-style: none;"><a class="app-menu__item" href="<%= request.getContextPath()%>/index.jsp">Trang chủ</a></li>
    <li style="list-style: none;"><a class="app-menu__item" href="<%= request.getContextPath()%>/show-cart.jsp">Giỏ hàng</a></li>
        <%
            User user2 = (User) session.getAttribute("userLogined");
            if (user2 == null) {%>
    <li style="list-style: none;"><a class="app-menu__item" href="<%= request.getContextPath()%>/login.jsp">Đăng nhập</a></li>
    <li style="list-style: none;"><a class="app-menu__item" href="<%= request.getContextPath()%>/register.jsp">Đăng ký</a></li>
        <%} else {%>
    <li style="list-style: none;"><a class="app-menu__item" href="<%= request.getContextPath()%>/logout.jsp">Đăng xuất</a></li>
        <%}%>
</body>
</html>
