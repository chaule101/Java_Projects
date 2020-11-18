<%-- 
    Document   : show_user
    Created on : Feb 15, 2020, 2:16:16 PM
    Author     : hanhl
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.poly.dao.ConnectDao"%>
<%@page import="com.poly.dao.UserDao"%>
<%@page import="com.poly.models.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
                     <td width="21%" style="background-color:black">
                     
                      <%@include file="/admin/leftmenu.jsp" %>
                     </td>
                     <td width="79%">
                         <h2>USER LIST </h2>
       
        <table border="1" style="width:100%;height:600px" >
            
            <tr style="background-color: yellow" >
                <td style="text-align: center" >id</td>  
                <td style="text-align: center">USERNAME</td>
                <td style="text-align: center">PASSWORD</td>
                <td style="text-align: center">FULLNAME</td>
                <td style="text-align: center">EMAIL</td>
                <td style="text-align: center">PHONE</td>
                <td style="text-align: center">ROLE</td>
                <td style="text-align: center">DELETE</td>
                <td style="text-align: center">EDIT</td>
                
            </tr>
           
        
        
       <%
       
                UserDao user = new UserDao();
                ArrayList<User> list = user.fillAll();
                for(int i =0;i<list.size();i++)
                { %>
        <tr>
                <td><%=list.get(i).getID()%></td>
                <td><%= list.get(i).getUSERNAME()%></td>
                <td><%= list.get(i).getPASSWORD()%></td>
                <td><%=list.get(i).getFULLNAME()%></td>
                <td><%=list.get(i).getEMAIL()%></td>
                <td><%=list.get(i).getPHONE()%></td>
                <td><%=list.get(i).getRole()%></td>
                
            <form method="get" action="../UserController" >
                <td><input type="submit" name="_type" value="DELETE"</td>
                <td><input type="submit" name="_type" value="UPDATE"</td>
                <input type="hidden" name="userid" value="<%=list.get(i).getID()%>"/>
            
        </tr>
                    
               <% } %> 

                                      
     </table>                     
        
    </body>
</html>
