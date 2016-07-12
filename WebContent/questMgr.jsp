<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.*" %>
<%@ page import="edu.nmt.cs.itweb.*" %>
<%
	//quest.jsp?action=Create|Update|Delete&id=[questID]
	Quest quest = new Quest();
	String action = "", message = "";
	try {
		action = request.getParameter("action");
	} catch (Exception e) {

	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=action%> Quest</title>
<link href="css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function confirmDelete(){
		var r = confirm('Are you sure you want to delete this quest? (This cannot be undone.)');
		if (r == true) {
			document.location = "QuestMgr?action=Delete&questId=<%=quest.getId()%>"
		}
	}
</script>
</head>
<body>
<h2><%=action%> Quest</h2>
<form id="questForm" name="questForm" method="post" action="QuestMgr?action=<%=action%>&questId=<%=quest.getId()%>">
<%
if (action.equals("Update")) {
        try{
        int id = Integer.parseInt(request.getParameter("questId"));
	quest = Quest.getQuest(id);}catch(Exception e){}
%>
<div id="delete"><input type="button" onclick="confirmDelete()" value="Delete Quest" /></div>
<%
	}
%>
<table>
	<tr>
		<td width="50"><label for="questName">Name</label></td>
		<td><input type="text" name="questName" id="questName" tabindex="1" value="<%=quest.getName()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questLore">Lore</label></td>
		<td><input type="text" name="questLore" id="questLore" tabindex="1" value="<%=quest.getType()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questRequired_level">Required Level</label></td>
		<td><input type="text" name="questRequired_level" id="questRequired_level" tabindex="1" value="<%=quest.getRequired_level()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questSuggested_level">Suggested Level</label></td>
		<td><input type="text" name="questSuggested_level" id="questSuggested_level" tabindex="1" value="<%=quest.getSuggested_level()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questDifficulty">Difficulty</label></td>
		<td><input type="text" name="questDifficulty" id="questDifficulty" tabindex="1" value="<%=quest.getDifficulty()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questType">Type</label></td>
		<td><input type="text" name="questType" id="questType" tabindex="1" value="<%=quest.getType()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questReward_gold">Reward Gold</label></td>
		<td><input type="text" name="questReward_gold" id="questReward_gold" tabindex="1" value="<%=quest.getReward_gold()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questReward_experience">Reward Experience</label></td>
		<td><input type="text" name="questReward_experience" id="questReward_experience" tabindex="1" value="<%=quest.getReward_experience()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questRepeatable">Repeatable</label></td>
		<td><input type="text" name="questRepeatable" id="questRepeatable" tabindex="1" value="<%=quest.getRepeatable()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="questReq_pre_quest">Required Pre-Quest</label></td>
		<td><input type="text" name="questReq_pre_quest" id="questReq_pre_quest" tabindex="1" value="<%=quest.getReq_pre_quest()%>" /></td>
	</tr>

<%
	if (action.equals("Create")) {

	}
%>
</table>
<input type="submit" name="submitButton" id="submitButton" value="<%=action%>" tabindex="12" onclick="this.value = 'Please wait...';" />&nbsp;
<a href="userHome.jsp"><input type="button" name="cancelButton" id="cancelButton" value="Cancel" /></a></form>
</body>
</html>
