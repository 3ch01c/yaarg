
var playerName = "<%=charName%>";
var player;
var address = "<%=address%>";
var startPoint = new google.maps.LatLng();
alert("Entering world at "+address+"...");
var lat = 34.054937, lng = -106.896788;
var geocode = new google.maps.Geocoder();
var myHouse = new google.maps.LatLng(34.054937, -106.896788);
var map;
var player;
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
	var charIcon = new google.maps.MarkerImage('images/auron.png',
			new google.maps.Size(31, 50), new google.maps.Point(0, 0), // origin
			new google.maps.Point(0, 50)) // anchor

	var iconShadow = new google.maps.MarkerImage('images/shadow.png',
			new google.maps.Size(50, 50), new google.maps.Point(0, 0), // origin
			new google.maps.Point(0, 50)) // anchor

	geocode.geocode({'address': address}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			player = new google.maps.Marker({
				position : results[0].geometry.location,
				title : playerName,
				icon : charIcon,
				shadow : iconShadow,
				map : map,
				draggable : true
			});
		} else {
			alert("Geocode was not successful! " + status);
		}
	});
/*		startPoint = player.getPosition();
		map.setCenter(startPoint);
		alert(startPoint);
*/
	google.maps.event.addListener(map, 'click', function(event) {
		movePlayer(event.latLng);
	});

	google.maps.event.addListener(player, 'click', function() {
		playerInfo();
	});

}

function playerInfo() {
	loc = player.getPosition();
	alert("You clicked at "+loc);
}

function movePlayer(loc) {
	//alert("You clicked at "+loc);
	player.setPosition(loc);
	map.setCenter(loc);
	lat = player.getPosition().lat();
	lng = player.getPosition().lng();
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
	player.setPosition(loc);
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
