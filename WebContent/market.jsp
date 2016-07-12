<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.*" %>
<%@ page import="edu.nmt.cs.itweb.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Marketplace</title>
<style type="text/css">
<!--
th {
	color: #FFF;
}
-->
</style>
<link href="css/all.css" rel="stylesheet" type="text/css" />
<link href="css/sprite.css" rel="stylesheet" type="text/css" />
</head>

<body>
<h2>Marketplace</h2>
<table width="100%" border="1" cellpadding="2" cellspacing="1">
  <tr bgcolor="#666666">
    <th width="35" scope="col">&nbsp;</th>
    <th scope="col">Name</th>
    <th scope="col">Type</th>
    <th scope="col">Level</th>
    <th scope="col">Cost</th>
    <th scope="col">Quantity</th>
    <th scope="col">Seller</th>
  </tr>
<%
    ArrayList<MarketPlace> mpList = MarketPlace.listMarketPlace();
    Iterator<MarketPlace> mpItr = mpList.iterator();
    MarketPlace mp = new MarketPlace();
    while (mpItr.hasNext()){
        mp = mpItr.next();
        Item item = mp.getItem();
%>
  <tr>
    <td width="35"><div id="<%=item.getIcon()%>" class="sprite" style="float: right;"></div></td>
    <td><%= item.getName()%></td>
    <td><%= item.getType()%></td>
    <td><%= item.getLevel()%></td>
    <td align="right">$<%= mp.getPrice()%></td>
    <td><%= mp.getQty()%></td>
    <td><%= mp.getChar_name()%></td>
  </tr>
<%
    }
%>
</table>
<p>&nbsp;</p>
</body>
</html>
