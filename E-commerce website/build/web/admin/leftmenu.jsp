<%-- 
    Document   : leftmenu
    Created on : Feb 22, 2020, 7:46:34 PM
    Author     : hanhl
--%>

<%@page import="com.poly.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Sider Menu Bar CSS</title>


        <script src="https://kit.fontawesome.com/a076d05399.js"></script>

        <style>



        </style>
    </head>
    <body>      

        <div class="container" style="background-color:black">
            <table width="50%" border="1">
          
            <h1  style="color: #F39C12" style="text-align:center">
                <%
                    User user2 = (User) session.getAttribute("userlogined");
                    if (user2 != null) {
                        out.print("Hello : " + user2.getUSERNAME());
                    } else {
                        response.sendRedirect(request.getContextPath() + "/login_new.jsp");
                    }
                %>
            </h1>
            <br><br>
            

                <ul>
                    <li><a href="show_user.jsp" style="color: #ffffff"><em class="fas fa-qrcode" ></em> DANH SÁCH NGƯỜI DÙNG</a></li><br><br><br><br>
                    <li><a href="add_user.jsp"  style="color: #ffffff"><i class="fas fa-link"></i>THÊM NGƯỜI DÙNG</a></li><br><br><br><br>

                    <li><a href="show_product.jsp"  style="color: #ffffff"><i class="fas fa-stream"></i>XEM SẢN PHẨM</a></li><br><br><br><br>
                    <li><a href="add_product.jsp"  style="color: #ffffff"><i class="fas fa-calendar-week"></i>THÊM SẢN PHẨM</a></li><br><br><br><br>

                </ul>

       
    </table>
 </div>
</body>
</html>
