<%-- 
    Document   : add-product
    Created on : Feb 18, 2020, 10:40:16 AM
    Author     : VDPKGL
--%>

<%@page import="com.poly.models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.poly.dao.ProductDAO"%>
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
            <h1>Thêm sản phẩm</h1>
            <form action = "UploadFile.jsp" method = "post" enctype = "multipart/form-data">
                <input type = "file" name = "file" size = "50" /><br><br>
                <input type = "submit" value = "Upload File" />
            </form><br>
            <%                String filename = request.getParameter("filename");
            %>
            <form action="../ProductController" method="get" class="form-group">
                Name: <input type="text" name="name" class="form-control"></input><br><br>
                Price: <input type="text" name="price" class="form-control"></input><br><br>
                Note: <input type="text" name="note" class="form-control"></input><br><br>           
                Image: <input type="text" name="image" value="<%= filename%>" class="form-control"></input><br><br>
                Category: <select name="category_id">
                    <% ProductDAO dao = new ProductDAO();
                        ArrayList<Category> ds = dao.fillAllCategory();
                        for (int i = 0; i < ds.size(); i++) {
                            out.print("<option value=" + ds.get(i).getId() + ">" + ds.get(i).getName() + "</option>");
                        }
                    %>
                </select><br><br>
                <input type="submit" name="_type" value="INSERT">
            </form>
        </div>
    </body>
</html>
