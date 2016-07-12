<%-- 
    Document   : battleRequest
    Created on : Dec 3, 2010, 9:56:08 PM
    Author     : ddumas
--%>

<%@ page import="edu.nmt.cs.itweb.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Battle Request</title>
        <link rel="stylesheet" type="text/css" href="css/all.css" />
        <script type="text/javascript" src="js/gameServletAjaxFunctions.js"></script>
        <title>Battle Request</title>
        <%
            GameBattle battle = (GameBattle)session.getAttribute("battle");
            GameCharacter myCharacter, enemyCharacter;
            String charId = (String)session.getAttribute("charId");
            if(battle.getPlayer1().toString().equals(charId))
            {
                myCharacter = battle.getPlayer1();
                enemyCharacter = battle.getPlayer2();
            }
            else
            {
                enemyCharacter = battle.getPlayer1();
                myCharacter = battle.getPlayer2();
            }
        %>
        <script type="text/javascript">
            enemyId = <%= enemyCharacter.getId() %>;
        </script>
    </head>
    <body>
        <table>
            <tr>
                <td colspan="2">
                    <%=  enemyCharacter.getName()+" " %> Challenges you to a BATTLE
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <img id="enemyImg" src="<%= enemyCharacter.getIcon_url() %>" alt="enemy image" />
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="To Battle" onclick="buttonPressed(this);"/>
                </td>
                <td>
                    <input type="button" value="Eff that Noise" onclick="buttonPressed(this);"/>
                </td>
            </tr>
        </table>
    </body>
</html>
