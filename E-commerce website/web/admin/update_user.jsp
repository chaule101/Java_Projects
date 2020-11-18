<%-- 
    Document   : update_user
    Created on : Feb 19, 2020, 7:46:53 PM
    Author     : hanhl
--%>

<%@page import="com.poly.models.User"%>
<%@page import="com.poly.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>THÊM TÀI KHOẢN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
                <style>
                body {
                  font-family: Arial, Helvetica, sans-serif;
                   background-color: #666666;
                }

                * {
                  box-sizing: border-box;
                }

                /* Add padding to containers */
                .container {
                  padding: 5px;
                  background-color: white;
                }

                /* Full-width input fields */
                input[type=text], input[type=password] {
                  width: 100%;
                  padding: 10px;
                  margin: 5px 0 22px 0;
                  display: inline-block;
                  border: none;
                  background: #f1f1f1;
                }

                input[type=text]:focus, input[type=password]:focus {
                  background-color: #ddd;
                  outline: none;
                }

                /* Overwrite default styles of hr */
                hr {
                  border: 1px solid #f1f1f1;
                  margin-bottom: 25px;
                }

                /* Set a style for the submit button */
                .registerbtn {
                  background-color: #4CAF50;
                  color: white;
                  padding: 6px 10px;
                  margin: 8px 0;
                  border: none;
                  cursor: pointer;
                  width: 10%;
                  opacity: 2;
                }
                

              
                
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
                     <td width="21%">
                     
                      <%@include file="/admin/leftmenu.jsp" %>
                     </td>
                     <td width="79%">
                           <%
            int id  =Integer.parseInt(request.getParameter("id")) ;
            UserDao userdao = new UserDao();
            User user = userdao.findById(id);
            %>
        <h2>  UPDATE USERS</h2>
        <p>Please fill in this form to add an account.</p>
        <form action="../UserController" method="get">
            ID : <%=user.getID() %>
            <input type="hidden" name="id" value="<%= user.getID() %>"></input><br>
            <label for="usm"><b>Username :</b></label>
            <input type="text"  name="username" value="<%= user.getUSERNAME() %>"></input><br><br>
            <label for="pass"><b> Password :</b></label>
            <input type="text"  name="password" value="<%= user.getPASSWORD()%>"></input><br><br>
            <label for="fname"><b> FullName :</b></label>
            <input type="text"  name="fullname" value="<%= user.getFULLNAME()%>"></input><br><br>
             <label for="mail"><b> Email :</b></label>
            <input type="text"  name="email"value="<%= user.getEMAIL()%>"></input><br><br>
             <label for="telephone"><b>Phone :</b></label>
            <input type="text"  name="phone"value="<%= user.getPHONE()%>"></input><br><br>
           
           
           
            <% int role= user.getRole();%>
            Role:<br> <input type="radio" name="role" value="1"<%if(role==1) out.print("checked");%>
                             >Admin</input><br>
             <input type="radio" name="role" value="0"<%if(role==0) out.print("checked");%>
            > User</input><br><br>
             <div class="registerbtn">
            <input type="submit" name="_type" value="EXE_UPDATE">
            </div>
        </form>
                     </td>
                    
                </tr>
                
                
            </tbody>
        </table>
        
        
        
        
        
        
        
        
        
        
       
    </body>
</html>
