<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="edu.nmt.cs.itweb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory</title>
<link rel="stylesheet" type="text/css" href="css/all.css" />
<link rel="stylesheet" type="text/css" href="css/sprite.css" />
</head>
<body>
<table>
	<%
		//Get access to the session information for the user which contains
		//their inventory
		GameCharacter player = (GameCharacter) session.getAttribute("currentChar");
		@SuppressWarnings(value = "unchecked")
		ArrayList<Item> inventory = (ArrayList<Item>) session.getAttribute("inventory");
                if(inventory == null)
                    {
                        inventory = new ArrayList<Item>();
                    }
		if (inventory.size() == 0) {
	%>
			<tr><td>You have no items! You should go <a href="market.jsp">get some</a>!</td></tr>
	<%	}
		Iterator<Item> itemIterator = inventory.iterator();

		//Create a placeholding item to hold the attributes of the current item in the iterator
		while (itemIterator.hasNext()) {
			Item item = (Item) itemIterator.next();
			int qty = 0;
			try {
				qty = Inventory.getQty(item.getId(), player.getId());
			} catch (Exception e) {
				session.setAttribute("message", e.toString());
			}
			//print out the next item:
	%>
	<tr>
		<td><div id="<%=item.getIcon()%>" class="sprite"></div></td>
		<td><a href="details?type=item&id=<%=item.getId()%>" target="_new"><%=item.getName()%> (<%=qty%>)</a></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>