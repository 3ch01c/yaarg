<!--
    This document is directly linked to battle.html. On this page, we deal with street view and calling an iframe for battle.html
    which is responsible for dealing with the rest of the elements.
    In short, this page is only loading the map and calling the iframe. Please do not implement functionality here that is directly
    linked to the actual battle process.
-->
<%@ page import="edu.nmt.cs.itweb.*" %>
<%
	GameBattle battle = (GameBattle) session.getAttribute("battle");
	GameCharacter player1 = battle.getPlayer1();
	System.out.println("Player 1's location is "+player1.getLocation());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Google Maps JavaScript API Example: StreetView Overlays</title>
<link
	href="http://code.google.com/apis/maps/documentation/javascript/examples/standard.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/all.css" />
<script src="http://maps.google.com/maps/api/js?sensor=false"
	type="text/javascript">
</script>
<script type="text/javascript">
	var map;
	var panorama;
	var charLoc = new google.maps.LatLng(34.054937, -106.896788);
	var address = "<%=player1.getLocation()%>";
	var geocode = new google.maps.Geocoder();

	function initialize() {
		// Geocode the player's address and build a player object.
		geocode.geocode({'address': address}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				//This function sets up the streetview for the battle.
				var panoOptions = {
					position : results[0].geometry.location,
					navigationControl : false,
					linksControl : false,
					scrollwheel : false,
					draggable : false,
					enableCloseButton : false
				};
		
				var map = new google.maps.StreetViewPanorama(document
						.getElementById("mapCanvas"), panoOptions);
				map.setPov( {
					heading : 95.209,
					pitch : -15.903,
					zoom : 1
				})
			} else {
				alert("Geocode was not successful! " + status);
				//This function sets up the streetview for the battle.
				var panoOptions = {
					position : charLoc,
					navigationControl : false,
					linksControl : false,
					scrollwheel : false,
					draggable : false,
					enableCloseButton : false
				};
		
				var map = new google.maps.StreetViewPanorama(document
						.getElementById("mapCanvas"), panoOptions);
				map.setPov( {
					heading : 95.209,
					pitch : -15.903,
					zoom : 1
				})
			}
		});
	}

	function handleNoFlash(errorCode) {
		//If the user does not have flash, throw an error.
		if (errorCode == FLASH_UNAVAILABLE) {
			alert("Error: Flash doesn't appear to be supported by your browser. If you have a script blocker, it must be disabled to play.");
			return;
		}
	}
</script>

<style type="text/css">
html {
	height: 100%;
	width: 100%;
}

body {
	height: 100%;
	width: 100%;
}

#battleFrame {
	height: 100%;
	width: 100%;
}
</style>

</head>

<body onload="initialize()" onunload="GUnload()">
<!--Map Canvas-->
<div id="mapCanvas" name="mapCanvas"
	style="position: absolute; width: 100%; height: 100%;"></div>
<div id="battleFrame" name="battleFrame"><iframe id="battle"
	name="battle" src="battle.jsp"
	style="position: absolute; height: 100%; width: 100%; border: 0px; margin: 0px; padding: 0px;" frameborder="0"></iframe>
</div>
</body>
</html>
