<%-- 
    Document   : Error
    Created on : Dec 1, 2010, 8:48:19 PM
    Author     : ddumas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <%
            Exception e = ((Exception)request.getAttribute("error"));
            out.println("Error: "+e.getMessage());
            out.println("Custom Message:"+request.getAttribute("message"));
            out.println("Stack Trace: "+e.getStackTrace().toString());
        %>
    </body>
</html>
