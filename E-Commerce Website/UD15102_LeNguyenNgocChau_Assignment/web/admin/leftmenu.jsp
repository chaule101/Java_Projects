<%-- 
    Document   : leftmenu
    Created on : Feb 21, 2020, 6:17:12 PM
    Author     : VDPKGL
--%>

<%@page import="com.poly.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <hr>
        <a class="app-menu__item">
            <%
                User user1 = (User) session.getAttribute("userLogined");
                if (user1 != null) {
                    out.print("Xin chào: " + user1.getUsername());
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                }
            %>
        </a>
        <hr>
        <a href="show-user.jsp" class="app-menu__item">Danh sách người dùng</a><br>
        <a href="add-user.jsp" class="app-menu__item">Thêm người dùng</a><br>
        <a href="show-product.jsp" class="app-menu__item">Xem sản phẩm</a><br>
        <a href="add-product.jsp" class="app-menu__item">Thêm sản phẩm</a><br>
        <hr>
    </body>
</html>
