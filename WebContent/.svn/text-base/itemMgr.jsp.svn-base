<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.*"%>
<%@ page import="edu.nmt.cs.itweb.*"%>
<%//item.jsp?action=Create|Update|Delete&id=itemId
Item item = new Item();
String action = "",
	message = "";
try {
	action = request.getParameter("action");
} catch (Exception e) {

}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=action%> Item</title>
<link href="css/all.css" rel="stylesheet" type="text/css" />
<link href="css/sprite.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var oldSprite = <%=item.getIcon()%>;
	function confirmDelete() {
		var r = confirm('Are you sure you want to delete this item? (This cannot be undone.)');
		if (r == true) {
			document.location = "ItemMgr?action=Delete&itemId=<%=item.getId()%>"
		}
	}
</script>
</head>
<body>
<h2><%=action%> Item</h2>
<form id="itemForm" name="itemForm" method="post"
	action="ItemMgr?action=<%=action%>&itemId=<%=item.getId()%>">
<%int itemId=0;
if (action.equals("Update")) {
    try{
        itemId = Integer.parseInt(request.getParameter("itemId").toString());
	item = Item.getItem(itemId);}catch(Exception e){}
%>
<script type="text/javascript">oldSprite = <%=item.getIcon()%></script>
<div id="delete"><input type="button" onclick="confirmDelete()"
	value="Delete Item" /></div>
<%
}
%>
<a href="itemMgr.jsp?action=Create"><input type="button" value="Randomize!" /></a>

<table>
	<tr>
		<td width="50"><label for="itemName">Name</label></td>
		<td><input type="text" name="itemName" id="itemName" tabindex="1"
			value="<%=item.getName()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemType">Type</label><br />
		<select name="itemType" id="itemType" size="10">
		<%
		String[] list = { "Food", "Potion", "Sword", "Dagger", "Claw",
				"Gun", "Arrow", "Dart", "Shuriken", "Spear", "Club", "Mace",
				"Wand", "Staff", "Axe", "Bow", "Shield", "Tunic", "Mail",
				"Cap", "Helm", "Sandals", "Boots", "Gloves", "Gem", "Ore",
				"Key" };
		
		for (int i = 0; i < list.length; i++) {
			String itemType = list[i];
		%>
			<option value="<%=itemType%>"<% if(itemType == item.getType()) { %>selected="selected"<% } %>><%=itemType%></option>
		<% } %>
		</select></td>
		<td><div style="float: left"><label for="itemIcon">Icon</label><br/>
		<select name="itemIcon" id="itemIcon" tabindex="1" size="10">
			<%
			String[][] icons = {
				{ "pberry", "cherry", "gapple", "rapple", "orange", "ggrapes",
						"pgrapes", "watermelon", "strawberry", "papaya",
						"lemon", "pineapple", "banana", "acorn", "radish",
						"carrot", "gpepper", "ypepper", "rpepper", "mushroom",
						"egg", "bread", "pie", "cheese", "rfish", "rsteak",
						"cfish", "csteak" },
				{ "brpot", "bopot", "bypot", "bbpot", "bppot", "bgpot",
						"bwpot", "srpot", "sopot", "sypot", "sbpot", "sppot",
						"sgpot", "swpot", "trpot", "topot", "typot", "tbpot",
						"tppot", "tgpot", "twpot", "mrpot", "mopot", "mypot",
						"mbpot", "mppot", "mgpot", "mwpot", "tepot", "sepot",
						"bepot", "mepot", "mbubpot", "gpicklepot",
						"ppicklepot", "sgbilepot", "bgbilepot", "bbbilepot",
						"mpbilepot", "sybilepot", "trocketpot", "trainbowpot",
						"greenjuice" },
				{ "ssword", "msword", "lsword", "r1sword", "r2sword",
						"sfatsword", "bfatsword", "joustsword", "arrowsword",
						"goldsword", "longsword", "cloudsword", "bentsword",
						"billysword", "godsword", "sandsword" },
				{ "mknife", "sknife", "aknife", "sawblade", "r1assassinblade",
						"r2assassinblade", "r3assassinblade" },
				{ "r1claw", "r2claw", "r3claw", "r4claw", "r5claw", "r6claw",
						"r7claw", "r1glove", "r2glove", "r3glove", "r4glove",
						"r5glove" },
				{ "r1gun", "r2gun", "r3gun" },
				{ "arrow" },
				{ "kunai" },
				{ "r1shuriken", "r2shuriken", "r3shuriken" },
				{ "r1pole", "r2pole", "r3pole", "r4pole", "r5pole", "r6pole",
						"r7pole", "r8pole", "r9pole", "r10pole", "r11pole",
						"r12pole", "r13pole" },
				{ "r1club", "r2club" },
				{ "r3club", "r4club", "r5club", "r1mace", "r1slapperchain" },
				{ "r1wand", "r2wand", "r3wand", "r4wand" },
				{ "r5wand", "r6wand", "r7wand" },
				{ "gaxe", "r1axe", "r2axe", "r3axe", "r4axe", "r5axe", "r6axe",
						"r7axe", "r8axe", "r9axe", "r10axe", "r11axe",
						"r12axe", "r13axe", "r14axe" },
				{ "r1bow", "r2bow", "r3bow", "r4bow", "r5bow", "r6bow",
						"r7bow", "r8bow", "r9bow", "r10bow", "r11bow",
						"r12bow", "r13bow", "r14bow" },
				{ "r1shield", "r2shield", "r3shield", "r4shield", "r5shield",
						"r6shield", "r7shield", "r8shield", "r9shield",
						"r10shield", "r11shield", "r12shield", "r13shield",
						"r14shield" },
				{ "r1chest", "r2chest", "r3chest" },
				{ "r4chest", "r5chest", "r6chest", "r7chest" },
				{ "r1hat", "r2hat", "r3hat" },
				{ "r4hat", "r5hat", "r6hat", "r7hat" },
				{ "r3shoe" },
				{ "r1shoe", "r2shoe", "r4shoe", "r5shoe", "r6shoe", "r7shoe" },
				{ "r1glove", "r2glove", "r3glove", "r4glove", "r5glove",
						"r6glove", "r7glove" },
				{ "r1gem", "r2gem", "r3gem", "r4gem", "r5gem", "r6gem", "r7gem" },
				{ "r1ore", "r2ore", "r3ore", "r4ore", "r5ore", "r6ore", "r7ore" },
				{ "r1key", "r2key", "r3key", "r4key", "r5key" } };
			for (int i = 0; i < list.length; i++) {
				for (int j = 0; j < icons[i].length; j++) {
			%>
				<option value="<%=icons[i][j]%>" <% if (icons[i][j] == item.getIcon()) { %>selected = "selected" <% } %> onclick="document.getElementsByTagName('div')[1].id = this.value; oldSprite = this.value;" onkeyup="document.getElementsByTagName('div')[1].id = this.value; oldSprite = this.value;"><%=icons[i][j] %></option>
			<%
				}
			}
			%>
			</select></div>
		<div id="<%=item.getIcon()%>" class="sprite" style="float: right;"></div></td>
	</tr>
	<tr>
		<td width="50"><label for="itemHp_bonus">Health</label></td>
		<td><input type="text" name="itemHp_bonus" id="itemHp_bonus"
			tabindex="1" value="<%=item.getHp_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemHp_regen_bonus">Health Regen
		Bonus</label></td>
		<td><input type="text" name="itemHp_regen_bonus"
			id="itemHp_regen_bonus" tabindex="1"
			value="<%=item.getHp_regen_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemMana_bonus">Energy</label></td>
		<td><input type="text" name="itemMana_bonus" id="itemMana_bonus"
			tabindex="1" value="<%=item.getMana_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemMana_regen_bonus">Energy Regen</label></td>
		<td><input type="text" name="itemMana_regen_bonus"
			id="itemMana_regen_bonus" tabindex="1"
			value="<%=item.getMana_regen_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemStr_bonus">Strength Bonus</label></td>
		<td><input type="text" name="itemStr_bonus" id="itemStr_bonus"
			tabindex="1" value="<%=item.getStr_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemMag_bonus">Magic Bonus</label></td>
		<td><input type="text" name="itemMag_bonus" id="itemMag_bonus"
			tabindex="1" value="<%=item.getMag_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemDex_bonus">Dexterity Bonus</label></td>
		<td><input type="text" name="itemDex_bonus" id="itemDex_bonus" tabindex="1" 
			value="<%=item.getDex_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemLuck_bonus">Luck Bonus</label></td>
		<td><input type="text" name="itemLuck_bonus" id="itemLuck_bonus" tabindex="1"
			value="<%=item.getLuck_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemArmor_pen_bonus">Armor
		Penetration Bonus</label></td>
		<td><input type="text" name="itemArmor_pen_bonus" id="itemArmor_pen_bonus" tabindex="1"
			value="<%=item.getArmor_pen_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemMagic_pen_bonus">Magic
		Penetration Bonus</label></td>
		<td><input type="text" name="itemMagic_pen_bonus" id="itemMagic_pen_bonus" tabindex="1"
			value="<%=item.getMagic_pen_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemArmor_bonus">Armor Bonus</label></td>
		<td><input type="text" name="itemArmor_bonus" id="itemArmor_bonus" tabindex="1"
		value="<%=item.getArmor_bonus()%>" /></td>
	</tr>
	<tr>
		<td width="50"><label for="itemMagic_res_bonus">Magic
		Resistance Bonus</label></td>
		<td><input type="text" name="itemMagic_res_bonus" id="itemMagic_res_bonus" tabindex="1"
			value="<%=item.getMagic_res_bonus()%>" /></td>
	</tr><input type="hidden" id="itemId" name="itemId" value="<%=itemId%>" />
	<%
if (action.equals("Create")) {
	
}
%>
</table>
<input type="submit" name="submitButton" id="submitButton"
	value="<%=action%>" tabindex="12"
	onclick="this.value = 'Please wait...';" />&nbsp; <a
	href="userHome.jsp"><input type="button" name="cancelButton"
	id="cancelButton" value="Cancel" /></a></form>
</body>
</html>