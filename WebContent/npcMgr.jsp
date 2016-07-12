<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.*" %>
<%@ page import="edu.nmt.cs.itweb.*" %>
<%
	//npcMgr.jsp?action=Create|Update|Delete&id=npcId
	GameCharacter npc = new GameCharacter();
	String action = "", message = "";
	try {
		action = request.getParameter("action");
	} catch (Exception e) {

	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=action%> NPC</title>
<link href="css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function confirmDelete(){
		var r = confirm('Are you sure you want to delete this item? (This cannot be undone.)');
		if (r == true) {
			document.location = "NpcMgr?action=Delete&npcId=<%=npc.getId()%>"
		}
	}
</script>
</head>
<body>
<h2><%=action%> NPC</h2>
<form id="npcForm" name="npcForm" method="post"
	action="NpcMgr?action=<%=action%>&npcId=<%=npc.getId()%>">
<%
	if (action.equals("Update")) {
		int id = Integer.parseInt(request.getParameter("npcId"));
		npc = GameCharacter.getCharacter(id);
%>
<div id="delete"><input type="button" onclick="confirmDelete()" value="Delete NPC" /></div>
<%
	}
%>
<table>
	<tr>
		<td width="50"><label for="npcName">Name</label></td>
		<td><input type="text" name="npcName" id="npcName" tabindex="1" value="<%=npc.getName()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcChar_class">Class</label></td>
		<td><input type="text" name="npcChar_class" id="npcChar_class" tabindex="1" value="<%=npc.getChar_class()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcLevel">Level</label></td>
		<td><input type="text" name="npcLevel" id="npcLevel" tabindex="1" value="<%=npc.getLevel()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcExp">Experience</label></td>
		<td><input type="text" name="npcExp" id="npcExp" tabindex="1" value="<%=npc.getExp()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcGold">Reward Gold</label></td>
		<td><input type="text" name="npcGold" id="npcGold" tabindex="1" value="<%=npc.getGold()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcHp">Hp</label></td>
		<td><input type="text" name="npcHp" id="npcHp" tabindex="1" value="<%=npc.getHp()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcEnergy">Energy</label></td>
		<td><input type="text" name="npcEnergy" id="npcEnergy" tabindex="1" value="<%=npc.getEnergy()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcStrength">Strength</label></td>
		<td><input type="text" name="npcStrength" id="npcStrength" tabindex="1" value="<%=npc.getStrength()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcMagic">Magic</label></td>
		<td><input type="text" name="npcMagic" id="npcMagic" tabindex="1" value="<%=npc.getMagic()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcDexterity">Dexterity</label></td>
		<td><input type="text" name="npcDexterity" id="npcDexterity" tabindex="1" value="<%=npc.getDexterity()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcLuck">Luck</label></td>
		<td><input type="text" name="npcLuck" id="npcLuck" tabindex="1" value="<%=npc.getLuck()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcArmor">Armor</label></td>
		<td><input type="text" name="npcArmor" id="npcArmor" tabindex="1" value="<%=npc.getArmor()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcArmor_pen">Armor Penetration</label></td>
		<td><input type="text" name="npcArmor_pen" id="npcArmor_pen" tabindex="1" value="<%=npc.getArmor_pen()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcMagic_res">Magic Resistance</label></td>
		<td><input type="text" name="npcMagic_res" id="npcMagic_res" tabindex="1" value="<%=npc.getMagic_res()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcMagic_pen">Magic Penetration</label></td>
		<td><input type="text" name="npcMagic_pen" id="npcMagic_pen" tabindex="1" value="<%=npc.getMagic_pen()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcBase_phys_damage">Base Physical Damage</label></td>
		<td><input type="text" name="npcBase_phys_damage" id="npcBase_phys_damage" tabindex="1" value="<%=npc.getBase_phys_damage()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcBase_magic_damage">Base Magic Damage</label></td>
		<td><input type="text" name="npcBase_magic_damage" id="npcBase_magic_damage" tabindex="1" value="<%=npc.getBase_magic_damage()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcAttack_speed">Attack Speed</label></td>
		<td><input type="text" name="npcAttack_speed" id="npcAttack_speed" tabindex="1" value="<%=npc.getAttack_speed()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcHit_chance">Hit Chance</label></td>
		<td><input type="text" name="npcHit_chance" id="npcHit_chance" tabindex="1" value="<%=npc.getHit_chance()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcCrit_chance">Critical Chance</label></td>
		<td><input type="text" name="npcCrit_chance" id="npcCrit_chance" tabindex="1" value="<%=npc.getCrit_chance()%>" /></td>
	</tr>
        <tr>
		<td width="50"><label for="npcEvasion">Evasion</label></td>
		<td><input type="text" name="npcEvasion" id="npcEvasion" tabindex="1" value="<%=npc.getEvasion()%>" /></td>
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
