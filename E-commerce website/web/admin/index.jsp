<%-- 
    Document   : index
    Created on : Feb 22, 2020, 7:38:56 PM
    Author     : hanhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <style>
            
                body {
                  font-family: Arial, Helvetica, sans-serif;
                  background-color: #f1f1f1;
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
                  padding: 16px 20px;
                  margin: 8px 0;
                  border: none;
                  cursor: pointer;
                  width: 100%;
                  opacity: 0.9;
                }

                .registerbtn:hover {
                  opacity: 1;
                }

                /* Add a blue text color to links */
                a {
                  color: #000;
                  font-weight: bolder;
                }

                /* Set a grey background color and center the text of the "sign in" section */
                .signin {
                  background-color: #f1f1f1;
                  text-align: center;
                }
                h3{
                    text-align: center;
                    border: 3px solid #00aeef;
                }
               
                
            
        </style>
        
    </head>
    <body>
        <table width="100%" border="1">
            
           

                 <tr>
                    <td colspan="2">
                    <%@include file="../topmenu.jsp" %>
                    </td>
                    
                </tr>                
                 <tr>
                     <td width="30%"  >
                      
                     
                      <%@include file="leftmenu.jsp" %>
                     </td>
                     <td width="70%"  >
                        
                         
                     </td>
                  
                </tr> 
                
            </tbody>
        </table>
    </body>
</html>
