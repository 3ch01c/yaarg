<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="edu.nmt.cs.itweb.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<base target="_parent" />
<link rel="stylesheet" type="text/css" href="css/all.css" />
<script src="js/gameServletAjaxFunctions.js" type="text/javascript"></script>
</head>
<body>
<table>
	<tr>
		<%
			@SuppressWarnings(value = "unchecked")
			ArrayList<GameCharacter> characters = (ArrayList<GameCharacter>) request.getSession().getAttribute("CharacterList");
			if (characters.size() == 0) {
		%>
				<td><h3>You have no characters!</h3>Why don't you <a href="character.jsp?action=Create&charId=-1">create one</a>&#63;</td>
		<%
			} else {
				Iterator<GameCharacter> charItr = characters.iterator();
				while (charItr.hasNext()) {
					GameCharacter character = charItr.next();
					String[] location = character.getLocation().split(",");
					String city = "", state = "";
					try {
						city = location[1];
						state = location[2];
					} catch (Exception e) {
						city = location[0];
					}
		%>
		<td>
		<table class="character" width="150" style="background-color: black;">
			<tr>
				<td rowspan="4"><img src="<%=character.getIcon_url()%>" height="75"
					width="50" /></td>
				<td><%=character.getName()%></td>
			</tr>
			<tr>
				<td>Level <%=character.getLevel()%></td>
			</tr>
			<tr>
				<td><%=character.getChar_class()%></td>
			</tr>
			<tr>
				<td><%=city%> <%=state%></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<a href="character.jsp?action=Update&charId=<%=character.getId()%>">
				<input type="button" value="Edit" onclick="this.value = 'Please wait...';" /></a>&nbsp;
				<a href="GameServlet?type=charSelect&charId=<%=character.getId()%>">
				<input type="button" value="Play" onclick="this.value = 'Entering <%=city%> <%=state%>...';" /></a></td>
			</tr>
		</table>
		</td>
		<%
				}
			}
		%>
	</tr>
</table>
</body>
</html>