<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	session = request.getSession();
	String playerName = ", "+session.getAttribute("firstName").toString(),
		rpgTitle = session.getAttribute("rpgTitle").toString(),
		email = session.getAttribute("email").toString(),
		message = session.getAttribute("message").toString();
	session.setAttribute("message","");
	if (playerName.equals(", ")) playerName = "";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>World View</title>
<link rel="stylesheet" type="text/css" href="css/all.css" />
<script src="js/gameServletAjaxFunctions.js" type="text/javascript"></script>
<style type="text/css">
.section {
	padding: 0px;
	margin: 0px;
	float: left;
}

.sectionHeader {
	font-size: 14px;
	font-weight: bold;
	margin-top: 7px;
	margin-bottom: 7px;
	text-align: center;
}

#characterList {
	height: 150px;
	width: 365px;
	border: 2px solid white;
	padding: 0px;
	margin: 0px;
}

#quicklinks {
	min-width: 150px;
}
</style>
</head>
<body>
<div align=center><%=message%></div>
<h2>Welcome back<%=playerName%>!</h2>
<div id="quicklinks" class="section">
	<div class="sectionHeader">Quick Links</div>
	<a href="l2p.html">Learn to play</a><br />
	F.A.Q.<br />
	<a href="battleRequest.jsp">Quick battle</a><br />
	Recent updates<br />
	Report an issue<br />
	<a href="market.jsp">Marketplace</a><br />
	<a href="rankings.jsp">Player rankings</a><br />
	<a href="account.jsp?action=Update">Update account info</a><br />
	<a href="character.jsp?action=Create">Create a new character</a>
<% if (session.getAttribute("userRole").equals("Admin")) { %>
	<div class="sectionHeader">Admin Tools</div>
	<table>
	<tr><td>Item Manager:</td><td><a href="itemMgr.jsp?action=Create">Create</a></td></tr>
    <tr><td>Quest Manager:</td><td><a href="questMgr.jsp?action=Create">Create</a></td></tr>
    <tr><td>NPC Manager:</td><td><a href="npcMgr.jsp?action=Create">Create</a></td></tr>
    </table>
<% } %>
</div>
<div id="characterList" class="section">
	<iframe src="GameServlet?type=getChars&email=<%=email%>" frameborder="0">Your characters are loading...</iframe>
</div>
</body>
</html>