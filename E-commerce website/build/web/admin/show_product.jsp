<%-- 
    Document   : show-product
    Created on : Feb 19, 2020, 6:27:16 PM
    Author     : hanhl
--%>

<%@page import="com.poly.dao.CategoryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.poly.models.Product"%>
<%@page import="com.poly.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
             h2{
                    text-align: center;
                  border: 3px solid  #CE5454;
                }
        </style>
    </head>
    <body>
         <table width="100%" border="1">
            <tbody>
               
                 <tr>
                    <td colspan="2">
                    <%@include file="/topmenu.jsp" %>
                    </td>
                    
                </tr>
                 <tr>
                     <td width="21%"style="background-color:black">
                     
                      <%@include file="/admin/leftmenu.jsp" %>
                     </td>
                     <td width="79%">
                          <h2>PRODUCTS LIST</h2>
        <table border="1" style="width:100%;height:600px">
            <tr style="background-color: yellow">
                <td>ID</td>
                <td>CATEGORIES_ID</td>
                <td>NAME</td>
                <td>PRICE</td>
                <td>NOTE</td>
                <td>IMAGE</td>
                <td>DELETE</td>
                <td>EDIT</td>
                
            </tr>
            
        
       <%
       
                ProductDao product = new ProductDao();
                CategoryDao cate = new CategoryDao();
                ArrayList<Product> list = product.fillAll();
                for(int i =0;i<list.size();i++)
                { %>
        <tr>
                <td><%=list.get(i).getId()%></td>
                <td>
                    <%= cate.getCategoryByID(list.get(i).getCategories_id()) %>
                
                
                </td>
                
                <td><%= list.get(i).getName()%></td>
                <td><%=list.get(i).getPrice()%></td>
                <td><%=list.get(i).getNote()%></td>
                <td>
                <img src="../images/<%=list.get(i).getImage()%>" width="150" height="180"/>              
                </td>
         
            <form method="get" action="../ProductController" >
                <td><input type="submit" name="_type" value="DELETE"</td>
                <td><input type="submit" name="_type" value="UPDATE"</td>
                <input type="hidden" name="productid" value="<%=list.get(i).getId()%>"/>
            </form>
        </tr>
                    
               <% } %> 

                                      
     </table>    
                     </td>
                    
                </tr>
                
                
            </tbody>
        </table>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    </body>
</html>
