<%-- 
    Document   : show-product
    Created on : Feb 18, 2020, 10:40:56 AM
    Author     : VDPKGL
--%>

<%@page import="com.poly.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.poly.dao.ProductDAO"%>
<%@page import="com.poly.dao.CategoryDAO"%>
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
            <h1>Danh sách sản phẩm</h1>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Note</td>
                    <td>Image</td>
                    <td>Category_ID</td>
                    <td>Delete</td>
                    <td>Edit</td>
                </tr>
                <%                    ProductDAO product = new ProductDAO();
                    ArrayList<Product> dsProduct = product.fillAll();
                    for (int i = 0; i < dsProduct.size(); i++) {%>
                <tr>
                    <td><%= dsProduct.get(i).getId()%></td>
                    <td><%= dsProduct.get(i).getName()%></td>
                    <td><%= dsProduct.get(i).getPrice()%></td>
                    <td><%= dsProduct.get(i).getNote()%></td>
                    <td><img src="../img/<%= dsProduct.get(i).getImage()%>" width="150" height="150"/></td>
                    <td><%= product.getCategoryByID(dsProduct.get(i).getCategory_id())%></td>
                <form action="../ProductController" method="get">
                    <td><input type="submit" name="_type" value="DELETE"></td>
                    <td><input type="submit" name="_type" value="UPDATE"></td>
                    <input type="hidden" name="productid" value="<%= dsProduct.get(i).getId()%>"/>
                </form>
                </tr>
                <%}%>
            </table>  
        </div>
    </body>
</html>
