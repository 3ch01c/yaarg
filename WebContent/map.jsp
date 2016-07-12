<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ page import="edu.nmt.cs.itweb.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.security.SecureRandom" %>
<%
	session = request.getSession();
	String address, streetAddress = session.getAttribute("streetAddress").toString(),
			city = session.getAttribute("city").toString(),
			state = session.getAttribute("state").toString(),
			zip = session.getAttribute("zip").toString(),
			country = session.getAttribute("country").toString(),
			debug = "pve";
	if (!streetAddress.equals("")) address = streetAddress+", "+city+", "+state+" "+zip+", "+country;
	else address = "Socorro, NM";
	GameCharacter player = (GameCharacter) session.getAttribute("currentChar");
	ArrayList<GameCharacter> npcs = GameCharacter.listCharacters("dev@null.com");
	SecureRandom random = (SecureRandom) session.getAttribute("random");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>World View</title>
<link rel="stylesheet" type="text/css" href="css/all.css" />
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">
	var playerName = "<%=player.getName()%>";
	var player;
	var address = "<%=player.getLocation()%>";
	//alert("Entering world at "+address+"...");
	var lat = 34.054937, lng = -106.896788;
	var geocode = new google.maps.Geocoder();
	var myHouse = new google.maps.LatLng(34.054937, -106.896788);
	var map;
	var player;
	var infowindow;
	var mob = [];
	function initialize() {
		var myOptions = {
			zoom : 17,
			center : myHouse,
			mapTypeId : google.maps.MapTypeId.TERRAIN
		};
		map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);
		/*
		street = map.getStreetView();
		street.setPov({
			heading: 265,
			zoom:1,
			pitch:0}
		);
		street.setPosition(myHouse);
		street.setVisible(true);
		 */
		infowindow = new google.maps.InfoWindow({
			    'size': new google.maps.Size(292, 120)
			  });
		  
		// Geocode the player's address and build a player object.
		geocode.geocode({'address': address}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				player = new MapObject();
				player.id = <%=player.getId()%>;
				player.title = "<%=player.getName()%>";
				player.type = "Self";
				player.charClass = "<%=player.getChar_class()%>";
				player.level = "<%=player.getLevel()%>";
				player.marker = new google.maps.Marker({
					position : results[0].geometry.location,
					title : playerName,
					icon : new google.maps.MarkerImage('<%=player.getIcon_url()%>',
							new google.maps.Size(50, 50),
							new google.maps.Point(0, 0), // origin
							new google.maps.Point(0, 50)), // anchor,
					shadow : new google.maps.MarkerImage('images/shadow.png',
							new google.maps.Size(50, 50),
							new google.maps.Point(0, 0), // origin
							new google.maps.Point(0, 50)), // anchor
					map : map
				});
				player.info = setInfo(player, "Self");
				google.maps.event.addListener(player.marker, 'click', function() {
					getInfoWindow(player);
				});
				map.setCenter(player.marker.getPosition());

				<% if (debug.equals("pve")) { %>
					<% GameCharacter mob = npcs.get(random.nextInt(npcs.size())); %>
					var mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					var mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob1 = new MapObject();
					mob1.id = <%=mob.getId()%>;
					mob1.title = "<%=mob.getName()%>";
					mob1.type = "NPC";
					mob1.charClass = "<%=mob.getChar_class()%>";
					mob1.level = "<%=mob.getLevel()%>";
					mob1.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob1.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob1.info = setInfo(mob1, "NPC");
					google.maps.event.addListener(mob1.marker, 'click', function() {
						getInfoWindow(mob1);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob2 = new MapObject();
					mob2.id = <%=mob.getId()%>;
					mob2.title = "<%=mob.getName()%>";
					mob2.type = "NPC";
					mob2.charClass = "<%=mob.getChar_class()%>";
					mob2.level = "<%=mob.getLevel()%>";
					mob2.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob2.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob2.info = setInfo(mob2, "NPC");
					google.maps.event.addListener(mob2.marker, 'click', function() {
						getInfoWindow(mob2);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob3 = new MapObject();
					mob3.id = <%=mob.getId()%>;
					mob3.title = "<%=mob.getName()%>";
					mob3.type = "NPC";
					mob3.charClass = "<%=mob.getChar_class()%>";
					mob3.level = "<%=mob.getLevel()%>";
					mob3.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob3.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob3.info = setInfo(mob3, "NPC");
					google.maps.event.addListener(mob3.marker, 'click', function() {
						getInfoWindow(mob3);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob4 = new MapObject();
					mob4.id = <%=mob.getId()%>;
					mob4.title = "<%=mob.getName()%>";
					mob4.type = "NPC";
					mob4.charClass = "<%=mob.getChar_class()%>";
					mob4.level = "<%=mob.getLevel()%>";
					mob4.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob4.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob4.info = setInfo(mob4, "NPC");
					google.maps.event.addListener(mob4.marker, 'click', function() {
						getInfoWindow(mob4);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob5 = new MapObject();
					mob5.id = <%=mob.getId()%>;
					mob5.title = "<%=mob.getName()%>";
					mob5.type = "NPC";
					mob5.charClass = "<%=mob.getChar_class()%>";
					mob5.level = "<%=mob.getLevel()%>";
					mob5.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob5.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob5.info = setInfo(mob5, "NPC");
					google.maps.event.addListener(mob5.marker, 'click', function() {
						getInfoWindow(mob5);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob6 = new MapObject();
					mob6.id = <%=mob.getId()%>;
					mob6.title = "<%=mob.getName()%>";
					mob6.type = "NPC";
					mob6.charClass = "<%=mob.getChar_class()%>";
					mob6.level = "<%=mob.getLevel()%>";
					mob6.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob6.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob6.info = setInfo(mob6, "NPC");
					google.maps.event.addListener(mob6.marker, 'click', function() {
						getInfoWindow(mob6);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob7 = new MapObject();
					mob7.id = <%=mob.getId()%>;
					mob7.title = "<%=mob.getName()%>";
					mob7.type = "NPC";
					mob7.charClass = "<%=mob.getChar_class()%>";
					mob7.level = "<%=mob.getLevel()%>";
					mob7.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob7.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob7.info = setInfo(mob7, "NPC");
					google.maps.event.addListener(mob7.marker, 'click', function() {
						getInfoWindow(mob7);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob8 = new MapObject();
					mob8.id = <%=mob.getId()%>;
					mob8.title = "<%=mob.getName()%>";
					mob8.type = "NPC";
					mob8.charClass = "<%=mob.getChar_class()%>";
					mob8.level = "<%=mob.getLevel()%>";
					mob8.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob8.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob8.info = setInfo(mob8, "NPC");
					google.maps.event.addListener(mob8.marker, 'click', function() {
						getInfoWindow(mob8);
					});
					<% mob = npcs.get(random.nextInt(npcs.size())); %>
					mob_lat = player.marker.getPosition().lat() + .0000000001 * <%=random.nextInt()%>;
					mob_lng = player.marker.getPosition().lng() + .0000000001 * <%=random.nextInt()%>;
					//alert(mob_lat+", "+mob_lng);
					mob9 = new MapObject();
					mob9.id = <%=mob.getId()%>;
					mob9.title = "<%=mob.getName()%>";
					mob9.type = "NPC";
					mob9.charClass = "<%=mob.getChar_class()%>";
					mob9.level = "<%=mob.getLevel()%>";
					mob9.marker = new google.maps.Marker({
						position : new google.maps.LatLng(mob_lat,mob_lng),
						title : mob9.title,
						icon : new google.maps.MarkerImage('<%=mob.getIcon_url()%>',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor,
						shadow : new google.maps.MarkerImage('images/shadow.png',
								new google.maps.Size(50, 50),
								new google.maps.Point(0, 0), // origin
								new google.maps.Point(0, 50)), // anchor
						map : map
					});
					mob9.info = setInfo(mob9, "NPC");
					google.maps.event.addListener(mob9.marker, 'click', function() {
						getInfoWindow(mob9);
					});
				<% } %>
			} else {
				alert("Geocode was not successful! " + status);
			}
		});

		google.maps.event.addListener(map, 'click', function(event) {
			movePlayer(event.latLng);
		});
	}

	function setInfo(object, type) {
		if (type == "Self") {
			return "<div class='marker'><b>"+object.title+"</b><br />"+
				"Level "+object.level+" "+object.charClass+"<br /></div>";
		} else if (type == "NPC") {
			return "<div class='marker'><b>"+object.title+"</b><br />"+
			"Level "+object.level+" "+object.charClass+"<br />"+
			"<br /><a href='GameServlet?type=startBattle&enemyId="+object.id+"'>Fight</a> | <a href=''>Speak</a></div>";
		}
		else return "This object has no info.";
	}

	function getInfoWindow(object) {
		infowindow.open(map,object.marker);
		infowindow.setContent(object.info);
		//alert(marker.getTitle());
	}

	function MapObject() {
		var id;
		var title;
		var type;
		var charClass;
		var level;
		var info;
		var marker;
	}

	function movePlayer(loc) {
		//alert("You clicked at "+loc);
		player.marker.setPosition(loc);
		map.setCenter(loc);
		lat = player.marker.getPosition().lat();
		lng = player.marker.getPosition().lng();
	}

	function detectEvent() {
		e = event;
		var keynum = e.keyCode || e.which;
		var keychar = String.fromCharCode(keynum);
		switch (keychar) {
		case "w":
			lat += .0001;
			updateLoc();
			break;
		case "a":
			lng -= .0001;
			updateLoc();
			break;
		case "s":
			lat -= .0001;
			updateLoc();
			break;
		case "d":
			lng += .0001;
			updateLoc();
			break;
		case "i":
			inventory();
			break;
		case "q":
			questLog();
			break;
		case "W":
			lat += .001;
			updateLoc();
			break;
		case "A":
			lng -= .001;
			updateLoc();
			break;
		case "S":
			lat -= .001;
			updateLoc();
			break;
		case "D":
			lng += .001;
			updateLoc();
			break;
		default:
			//alert('The "' + keychar + '" key doesn\'t do anything... yet.');
			break;
		}
	}

	function updateLoc() {
		loc = new google.maps.LatLng(lat, lng);
		player.marker.setPosition(loc);
		map.setCenter(loc);
	}

	function inventory() {
		if (document.getElementById("inventory").style.visibility == "hidden") {
			document.getElementById("inventory").style.visibility = "visible";
			document.getElementById("questLog").style.visibility = "hidden";
		} else
			document.getElementById("inventory").style.visibility = "hidden";
	}

	function questLog() {
		if (document.getElementById("questLog").style.visibility == "hidden") {
			document.getElementById("questLog").style.visibility = "visible";
			document.getElementById("inventory").style.visibility = "hidden";
		} else
			document.getElementById("questLog").style.visibility = "hidden";
	}
</script>
<style type="text/css">
html,body,#map_canvas {
	height: 100%;
	width: 100%;
}
.hud {
	position: absolute;
	top: 0px;
	right: 0px;
	width: 20%;
	height: 100%;
	visibility: hidden;
	background-color: black;
}
.marker {
	color: black;
}
.marker a {
	color: blue;
}
.marker a:hover {
	color: lightblue;
}
.marker a:visited {
	color: blue;
}
</style>
</head>
<body onload="initialize(); document.focus();" onkeypress=detectEvent();>
<div id="map_canvas"></div>
<div id="inventory" class="hud"><iframe src="inventory.jsp" frameborder="0">Your browser doesn't support iframes.</iframe></div>
<div id="questLog" class="hud"><iframe src="questLog.jsp" frameborder="0">Your browser doesn't support iframes.</iframe></div>
<div id="battleRequest" class="hud"><iframe src="battleRequest.jsp" frameborder="0">Your browser doesn't support iframes.</iframe></div>
</body>
</html>