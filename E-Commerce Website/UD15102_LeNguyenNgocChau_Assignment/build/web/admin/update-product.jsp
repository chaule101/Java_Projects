i<%-- 
    Document   : update-product
    Created on : Feb 24, 2020, 2:11:55 PM
    Author     : VDPKGL
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.poly.models.Category"%>
<%@page import="com.poly.models.Product"%>
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
            <h1>Sửa thông tin sản phẩm</h1>
            <%                int id = Integer.parseInt(request.getParameter("id"));
                ProductDAO dao = new ProductDAO();
                Product product = dao.findById(id);
            %>
            <form action="../ProductController" method="get" class="form-group">
                <input type="hidden" name="id" value="<%= product.getId()%>"></input><br><br>
                Name: <input type="text" name="name" value="<%= product.getName()%>" class="form-control"></input><br><br>
                Price: <input type="text" name="price" value="<%= product.getPrice()%>" class="form-control"></input><br><br>
                Note: <input type="text" name="note" value="<%= product.getNote()%>" class="form-control"></input><br><br>
                Image: <input type="text" name="image" value="<%= product.getImage()%>" class="form-control"></input><br><br>
                Category: <select name="category_id">
                    <% ProductDAO dao1 = new ProductDAO();
                        ArrayList<Category> ds = dao1.fillAllCategory();
                        for (int i = 0; i < ds.size(); i++) {
                            out.print("<option value=" + ds.get(i).getId() + ">" + ds.get(i).getName() + "</option>");
                        }
                    %>
                </select><br><br>
                <input type="submit" name="_type" value="SAVE">
            </form>    
        </div>  
    </body>
</html>
