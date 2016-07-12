<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.*" %>
<%@ page import="edu.nmt.cs.itweb.*" %>
<%@ page import="javax.servlet.http.HttpSession;" %>
<%
String action = request.getParameter("action"),
	message = "",
	charName = "",
	charIcon = "images/ash_t.png";
Integer charId = 0;
GameCharacter character = null;
try {
	charId = Integer.parseInt(request.getParameter("charId"));
} catch (Exception e) {
	
}
if (action.equals("Update")) {
	character = GameCharacter.getCharacter(Integer.parseInt(request.getParameter("charId")));
	charIcon = character.getIcon_url();
	System.out.println(charIcon);
}
if (action.equals("Delete")) {
	response.sendRedirect("CharacterMgr?action="+action+"&charId="+charId);
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=action%> Character</title>
<link href="css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function confirmDelete(){
		var r = confirm('Are you sure you want to delete this character? (This cannot be undone.)');
		if (r == true) {
			document.location = "CharacterMgr?action=Delete&charId=<%=charId%>"
		}
	}

	function Increment(id){
	    var num = document.getElementById(id).value;
	    var statnum2 = document.getElementById("statpoints").value;
	    if(statnum2 > 0){
	        num++, statnum2--;
	        document.getElementById(id).value = num;
	        document.getElementById("statpoints").value = statnum2;
	    }
	}
	
	function Decrement(id){
	    var num = document.getElementById(id).value;
	    var statnum3 = document.getElementById("statpoints").value;
	    if(num > 0){
	        num--, statnum3++;
	        document.getElementById(id).value = num;
	        document.getElementById("statpoints").value = statnum3;
	    }
	}
</script>
</head>
<body>
<h2><%=action%> Character</h2>
<form id="characterForm" name="characterForm" method="post" action="CharacterMgr?action=<%=action%>&charId=<%=charId%>">
<% if (action.equals("Update")) { %>
	<div id="delete"><input type="button" onclick="confirmDelete()" value="Delete character" /></div>
<% } %>
<table border=1>
	<tr><td colspan="3">Select an icon:<br />
	<select name="charIcon" id="charIcon" size="5" onclick="document.getElementById('iconImg').src = 'images/'+this.value;" onkeyup="document.getElementById('iconImg').src = 'images/'+this.value;">
		<option value="ash_t.png" <% if (charIcon.equals("images/ash_t.png")) { %>selected="selected"<% } %>>Ash</option>
		<option value="auron.png" <% if (charIcon.equals("images/auron.png")) { %>selected="selected"<% } %>>Auron</option>
		<option value="darkrai_t.png" <% if (charIcon.equals("images/darkrai_t.png")) { %>selected="selected"<% } %>>Darkrai</option>
		<option value="demon_t.png" <% if (charIcon.equals("images/demon_t.png")) { %>selected="selected"<% } %>>Demon</option>
		<option value="dervish_t.png" <% if (charIcon.equals("images/dervish_t.png")) { %>selected="selected"<% } %>>Velocirooster</option>
		<option value="gyarados_t.png" <% if (charIcon.equals("images/gyarados_t.png")) { %>selected="selected"<% } %>>Gyarados</option>
		<option value="linkImpo_t.png" <% if (charIcon.equals("images/linkImpo_t.png")) { %>selected="selected"<% } %>>Link</option>
		<option value="LuigiImpo_t.png" <% if (charIcon.equals("images/LuigiImpo_t.png")) { %>selected="selected"<% } %>>Luigi</option>
		<option value="marioImpo_t.png" <% if (charIcon.equals("images/marioImpo_t.png")) { %>selected="selected"<% } %>>Mario</option>
		<option value="peachImpo_t.png" <% if (charIcon.equals("images/peachImpo_t.png")) { %>selected="selected"<% } %>>Peach</option>
		<option value="shin2_t.png" <% if (charIcon.equals("images/shin2_t.png")) { %>selected="selected"<% } %>>Shin</option>
		<option value="shin_T.png" <% if (charIcon.equals("images/shin_T.png")) { %>selected="selected"<% } %>>Super Shin</option>
		<option value="wigga_t.png" <% if (charIcon.equals("images/wigga_t.png")) { %>selected="selected"<% } %>>Wigga</option>
	</select><img id="iconImg" src="<%=charIcon%>" style="margin: 10px;" /></td></tr>        

<%
        if (action.equals("Update")) {
%>

        <script type="text/javascript">
            function UpdateChange(){
            document.getElementById("strength").value -= <%=character.getStrength()%>;
            document.getElementById("magic").value -= <%=character.getMagic()%>;
            document.getElementById("dexterity").value -= <%=character.getDexterity()%>;
            document.getElementById("luck").value -= <%=character.getLuck()%>;
            return true;
            }
        </script>

        <tr>
		<td><label for="charName">Name:</label></td>
		<td colspan="4"><input type="text" name="charName" id="charName" tabindex="1" value="<%=character.getName()%>" /></td>
	</tr>

        <tr>
                <td><br/><br/><br/><b>Strength</b></td>
                <td width="25"><b><br/><br/><br/><input type="text" id="strength" name="strength" size="2" disabled=disabled value="<%=character.getStrength()%>"/></b></td>
                <td><br/><br/><br/><input type="button" value="+" onclick="Increment('strength')"/><input type="button" value="-" onclick="Decrement('strength')"/></td>
        </tr>
        <tr>
                <td><b>Magic</b></td>
                <td><b><input type="text" id="magic" name="magic" size="2" disabled=disabled value="<%=character.getMagic()%>"/></b></td>
                <td><input type="button" value="+" onclick="Increment('magic')"/><input type="button" value="-" onclick="Decrement('magic')"/></td>
            </tr>
            <tr>
                <td><b>Dexterity</b></td>
                <td><b><input type="text" id="dexterity" name="dexterity" size="2" disabled=disabled value="<%=character.getDexterity()%>"/></b></td>
                <td><input type="button" value="+" onclick="Increment('dexterity')"/><input type="button" value="-" onclick="Decrement('dexterity')"/></td>
            </tr>
            <tr>
                <td><b>Luck</b><br/><br/></td>
                <td><b><input type="text" id="luck" name="luck" size="2" disabled=disabled value="<%=character.getLuck()%>"/></b><br/><br/></td>
                <td><input type="button" value="+" onclick="Increment('luck')"/><input type="button" value="-" onclick="Decrement('luck')"/><br/><br/></td>
            </tr>
            <tr>
                <td><b>Remaining Stat Points</b><br/><br/><br/></td>
                <td><input type="text" id="statpoints" name="statpoints" size="2" disabled="disabled" value="<%=character.getStat_points()%>"/><br/><br/><br/></td>
            </tr>
<% } %>
<%
if (action.equals("Create")) {
	try {
		Statement stmt = DBConnection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT `class` FROM `it321`.`class`;");
%>

        <tr>
		<td width="50"><label for="charName">Name:</label></td>
		<td colspan="3"><input type="text" name="charName" id="charName" tabindex="1" value="<%=charName%>" /></td>
	</tr>
	<tr>
		<td><label for="charClass">Class</label></td>
		<td><select name="charClass" id="charClass" tabindex="3">
			<% while (rs.next()) {
				String charClass = rs.getString("class");%>
				<option value="<%=charClass%>"><%=charClass%></option>
			<% } %>
		</select></td>
	</tr>
<%
	} catch (SQLException e) {

	}
}
%>
</table>
<input type="submit" name="submitButton" id="submitButton" value="<%=action%>" tabindex="12" onclick="document.getElementById('strength').disabled = false; document.getElementById('magic').disabled = false; document.getElementById('dexterity').disabled = false; document.getElementById('luck').disabled = false; document.getElementById('statpoints').disabled = false; this.value = 'Please wait...';" />&nbsp;
<a href="userHome.jsp"><input type="button" name="cancelButton" id="cancelButton" value="Cancel" /></a></form>
</body>
</html>