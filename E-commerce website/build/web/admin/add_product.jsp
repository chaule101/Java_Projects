

<%@page import="com.poly.models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.poly.dao.ProductDao"%>
<%@page import="com.poly.dao.CategoryDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            body {
                font-family: Arial, Helvetica, sans-serif;
                /*                  background-color: #f1f1f1;*/
                background-color: #666666;
            }

            * {
                box-sizing: border-box;
            }

            /* Add padding to containers */
            .container {
                padding: 5px;
                background-color: #ffffff;
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



            /* Add a blue text color to links */
            a {
                color: dodgerblue;
            }

            /* Set a grey background color and center the text of the "sign in" section */
            /*                .signin {
                              background-color: #f1f1f1;
                              text-align: center;
                            }*/
            h3{
                text-align: center;
                border: 3px solid #00aeef;
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
            <div class="menu">
                <tr>
                    <td colspan="2" style="font-weight: bold">
                        <%@include file="/topmenu.jsp" %>
                    </td>

                </tr>
                <tr>
                    <td width="21%" style="background-color:black" >

                        <%@include file="/admin/leftmenu.jsp" %>
                    </td>
                    <td width="79%">
            </div>

            <div class="container">          
                <h2>ADD PRODUCT</h2>             
                <form action = "UploadFile.jsp" method = "post"
                      enctype = "multipart/form-data">
                    <input type = "file" name = "file" size = "50" /> <br> <br>              
                    <input type = "submit" value = "Upload File" />          
                    <br>
                     </form>
                    <%      
                        String filename = request.getParameter("filename");
                        if(filename!=null){
                            out.print("upload thành công");
                            }                       
                    %>
               
                <br>
                <form action="../ProductController" method="get">

                    Category:
                    <select  name="categories_id">
                        <%
                            CategoryDao cate = new CategoryDao();

                            ArrayList<Category> list = cate.fillAllCategories();
                            for (int i = 0; i < list.size(); i++) {
                                out.print("<option value=" + list.get(i).getCategories_id() + ">" + list.get(i).getName() + "</option>");
                            }
                        %>
                    </select><br><br>            
                    <label for="name"><b>Name :</b></label>
                    <input type="text" name="name"></input><br><br>
                    <label for="price"><b>Price :</b></label>
                    <input type="text" name="price"></input><br><br>
                    <label for="note"><b>Note :</b></label>
                    <input type="text" name="note"></input><br><br>
                    <label for="images"><b>Image :</b></label>
                    <input type="text" name="image" value="<%=filename%>" ></input><br><br>
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
