<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String message = "&nbsp;";
	try { 
		if (session.getAttribute("email") == null) {
			message = session.getAttribute("message").toString();
			session.setAttribute("message","");
		} else response.sendRedirect("frameset.jsp");
	} catch (Exception e) {
		
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/all.css"/>
<style type="text/css">
	.date {
		font-size: medium;
		font-style: italic;
		color: yellow;
		font-weight: bold;
	}
	label {
		font-weight: bold;
	}
	input {
		width: 200px;
		float: right;
	}
	#login td {
		padding-right: 5px;
		padding-left: 5px;
	}
	#updates {
		border: 2px solid white;
		padding-right: 10px;
		padding-left: 10px;
		position: absolute;
		top: 130px;
		left: 0px;
		right: 0px;
		bottom: 0px;
		overflow: auto;
	}
	.message {
		font-size: 10px;
	}
</style>
<script type="text/javascript">
	function validateLogin() {
		var format = /^[A-Za-z][\w.-]+@\w[\w.-]+\.[\w.-]*[A-Za-z][A-Za-z]$/
		if (format.test(document.getElementById("email").value) != "" && document.getElementById("passwd").value != "" ) {
			document.getElementById("submitButton").disabled = false;
		} else document.getElementById("submitButton").disabled = true;
	}
</script>
</head>
<body>
<noscript>Your browser does not support JavaScript or you have it disabled. In any case, you cannot play this game without those features.</noscript>
<div style="margin: 10px;">
	<div align="center">
    <form id="login" name="login" method="post" action="AccountMgr?action=Login" target="_top">
    	<div class="message"><%=message%></div>
        <table>
            <tr>
                <td align="left"><label for="email">Email:</label></td>
                <td align="left"><input id="email" name="email" type="text" onkeyup="validateLogin()"/></td>
            </tr>
            <tr>
                <td align="left"><label for="passwd">Password:</label></td>
                <td align="left"><input id="passwd" name="passwd" type="password" onkeyup="validateLogin()"/></td>
            </tr>
            <tr>
            	<td></td>
                <td align="left"><input type="submit" id="submitButton" name="submitButton" value="Login!" disabled="disabled" style="width:110px; float:none;" onclick="this.value='Authenticating...'" /></td>
            </tr>
            <tr>
            	<td></td>
                <td align="left">Forgot password?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="account.jsp?action=Create">Register</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="frameset.html">Skip login</a></td>
            </tr>
        </table>
    </form>
	<div id="updates"><iframe src="updates.html" frameborder="0">Your browser doesn't support iframes.</iframe></div>
    </div>
</div>
</body>
</html>