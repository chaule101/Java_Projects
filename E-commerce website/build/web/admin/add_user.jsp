<%-- 
    Document   : add-user
    Created on : Feb 19, 2020, 6:27:00 PM
    Author     : hanhl
--%>

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
                     <td width="21%" style="background-color:black">
                     
                      <%@include file="/admin/leftmenu.jsp" %>
                     </td>
                     <td width="79%">
                          
                           <div class="container">
       
        
   
        <h2>ADD USERS</h2> 
         <form action="../UserController" method="get">
            <label for="usm"><b>Username :</b></label>
            <input type="text" name="userName"></input><br><br>
            <label for="psw"><b>Password :</b></label>
            <input type="text" name="passWord"></input><br><br>
            <label for="fn"><b>FullName :</b></label>
            <input type="text" name="fullName"></input><br><br>
            <label for="email"><b>Email :</b></label>
            <input type="text" name="email"></input><br><br>
            <label for="phone"><b>Phone :</b></label>
            <input type="text" name="phone"></input><br><br>
             <label for="role"><b>Role :</b></label>
            <br> <input type="radio" name="role" value="1">Admin</input><input type="radio" name="role" value="0">User</input><br><br>
            <div class="registerbtn">               
            <input type="submit" name="_type" value="INSERT">
            </div> 
        </form>
                     </td>                   
                </tr>                               
            </tbody>
        </table>
    </body>
</html>
