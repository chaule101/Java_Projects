<%-- 
    Document   : login
    Created on : Feb 22, 2020, 9:18:42 AM
  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>PS10835</title>

        <!-- Meta tag Keywords -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords"
              content="Space Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- Meta tag Keywords -->

        <!-- css files -->
        <link href="CSS/style2.css" rel="stylesheet" type="text/css" media="all" />
        <!-- css files -->

        <!-- Online-fonts -->
        <link
            href="//fonts.googleapis.com/css?family=Montserrat:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=latin-ext,vietnamese"
            rel="stylesheet">
        <!-- //Online-fonts -->

    </head>

    <body>
         
        <form action="loginController" method="post">
           <table width="100%" border="1">
            <tbody>
               
                 <tr>
                    <td colspan="4" style="font-weight: bold" >
                    <%@include file="/topmenu.jsp" %>
                    </td>                   
                </tr>
          
                
                 <tr>
                     
                    <td colspan="2">
        <!-- main -->
        <div class="main">
            <div class="main1">
                <div class="main2">
                    <h2><span>SIGN IN</span></h2>
                    <div class="social">
                        <a href="index.html">Facebook</a>
                    </div>
                    <h3>(or)</h3>
                    <form action="loginController" method="get">
                        <div>
                            <input type="text" class="form-control" name="username" placeholder="Username" required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" name="password" placeholder="Password" required="" />
                        </div>
                        <div>
                            <input type="submit" class="btn btn-default submit" value="submit">
                            </form>
                            <h6><a href="#">Forgot your password?</a></h6>
                        </div>
                </div>
            </div>

    </body>
</html>
