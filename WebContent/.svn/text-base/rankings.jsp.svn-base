<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.*" %>
<%@ page import="edu.nmt.cs.itweb.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Player Rankings</title>
<link rel="stylesheet" type="text/css" href="css/all.css" />
<link rel="stylesheet" type="text/css" href="css/ranking.css" />
<script src="js/sorttable.js" type=""></script>
<script src="js/tableFilter.js" type=""></script>
<script type="text/javascript">
setInterval("settime()", 1000);
//var lastUpdate = Date.parse(document.lastModified);
var lastUpdate = new Date();
 
function settime (){
  var curtime = new Date();
 
  var timeSinceUpdate = ((curtime-lastUpdate)/1000);
  
  document.all.lastupdate.innerHTML = "Rankings updated on "+parseInt(timeSinceUpdate/60/60/24)+" days, "+parseInt((timeSinceUpdate/60/60)%24)+" hours, "+parseInt((timeSinceUpdate/60)%60)+" minutes, "+parseInt(timeSinceUpdate%60)+" seconds ago.";
}
</script>
</head>

<body>
<h1>Player Rankings</h1>
<form name="filters" action="">
<table id="filterTable" class="filter">
	<caption>Filters</caption>
	<tr>
		<td rowspan="2">
		<fieldset><legend>Class</legend>
		<table>
			<tr>
				<td><input type="checkbox" name="cleric" id="cleric"
					checked="checked" value="Cleric"
					onclick="classFilter(this, 'rankings', 'Class');" /></td>
				<td><label for="cleric">Cleric</label></td>
				<td><input type="checkbox" name="thief" id="thief"
					checked="checked" value="Thief"
					onclick="classFilter(this, 'rankings', 'Class');" /></td>
				<td><label for="thief">Thief</label></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="druid" id="druid"
					checked="checked" value="Druid"
					onclick="classFilter(this, 'rankings', 'Class');" /></td>
				<td><label for="druid">Druid</label></td>
				<td><input type="checkbox" name="warrior" id="warrior"
					checked="checked" value="Warrior"
					onclick="classFilter(this, 'rankings', 'Class');" /></td>
				<td><label for="warrior">Warrior</label></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="shaman" id="shaman"
					checked="checked" value="Shaman"
					onclick="classFilter(this, 'rankings', 'Class');" /></td>
				<td><label for="shaman">Shaman</label></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</fieldset>
		</td>
		<td style="vertical-align: top">
		<fieldset><legend>Level range</legend>
		<div align="center"><select name="minLevel" id="minLevel"
			onchange="levelFilter(this, parent.maxLevel, 'rankings', 'Level');">
			<option value="1" selected="selected">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
		</select> &nbsp;to&nbsp; <select name="maxLevel" id="maxLevel"
			onchange="levelFilter(parent.minLevel, this, 'rankings', 'Level');">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20" selected="selected">20</option>
		</select></div>
		</fieldset>
		<fieldset>
		<legend>Email</legend>
		<input id="emailFilter" type="text" value="somebody@example.com" />
		</fieldset>
		</td>
	</tr>
</table>
</form>
<div class="update" id="lastupdate">&nbsp;</div>
<div class="ranking" id="ranking">
<table id="rankings" class="sortable" width="100%">
	<tr>
		<th>Name</th>
		<th>Level</th>
		<th>Class</th>
		<th>Kills</th>
		<th>Deaths</th>
		<th>Health</th>
		<th>Energy</th>
		<th>Strength</th>
		<th>Magic</th>
		<th>Quests</th>
	</tr>
<%
	ArrayList<GameCharacter> characters = GameCharacter.listCharacters();
	if (characters.size() == 0) {
%>
	<tr><td>There are no characters!</td></tr>
<%
	} else {
		Iterator<GameCharacter> charItr = characters.iterator();
		while (charItr.hasNext()) {
			GameCharacter player = charItr.next();
%>
	<tr>
		<td><%=player.getName()%></td>
		<td><%=player.getLevel()%></td>
		<td><%=player.getChar_class()%></td>
		<td><%=player.getKills()%></td>
		<td><%=player.getDeaths()%></td>
		<td><%=player.getHp()%></td>
		<td><%=player.getEnergy()%></td>
		<td><%=player.getStrength()%></td>
		<td><%=player.getMagic()%></td>
		<td><%=player.getCompletedQuests().size()%></td>
	</tr>
<% }} %>
</table>
</div>
</body>
</html>
