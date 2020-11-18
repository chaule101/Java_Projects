<%-- 
    Document   : update_product
    Created on : Feb 22, 2020, 5:23:44 PM
    Author     : hanhl
--%>

<%@page import="com.poly.models.Product"%>
<%@page import="com.poly.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int id  =Integer.parseInt(request.getParameter("id")) ;
            ProductDao productdao = new ProductDao();
            Product product = productdao.findById(id);
            %>
        <h1>UPDATE PRODUCT</h1>
        <form action="../ProductController" method="get">
            ID : <%=product.getId()%>
            <input type="hidden" name="id" value="<%= product.getId()%>"></input><br>
            CATEGORIES_ID: <input type="text" name="Categories_id" value="<%= product.getCategories_id() %>"></input><br><br>
            NAME: <input type="text" name="name" value="<%= product.getName() %>"></input><br><br>
            PRICE: <input type="text" name="price"value="<%= product.getPrice() %>"</input><br><br>
            NOTE: <input type="text" name="note"value="<%= product.getNote() %>"</input><br><br>
            IMAGE: <input type="text" name="image"value="<%=product.getImage() %>"</input><br><br>
            
            
            <input type="submit" name="_type" value="EXE_UPDATE">
        </form>
    </body>
</html>
