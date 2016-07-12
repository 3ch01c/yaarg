<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" href="css/all.css" />
<base target="mainframe" />
<script type="text/javascript" src="js/gameServletAjaxFunctions.js"></script>
<style type="text/css">
#mainframe {
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 110px;
}

#chatframe {
	position: absolute;
	left: 0px;
	bottom: 0px;
	height: 100px;
	width: 50%;
}

#commandframe {
	position: absolute;
	right: 0px;
	bottom: 0px;
	height: 100px;
	width: 50%;
}

input {
	height: 100%;
	width: 100%;
}
.hud {
	position: absolute;
	top: 0px;
	right: 0px;
	width: 20%;
	bottom: 100px;
	visibility: hidden;
	background-color: black;
}
</style>
</head>
<body>
<div id="chatframe"><iframe src="chat.html" frameborder=0></iframe></div>
	<div id="commandframe">
	</div>
	<div id="mainframe"><iframe src="userHome.jsp" frameborder=0 name="mainframe"></iframe></div>
	<div id="inventory" class="hud"><iframe src="inventory.jsp" frameborder="0">Your browser doesn't support iframes.</iframe></div>
	<div id="questLog" class="hud"><iframe src="questLog.jsp" frameborder="0">Your browser doesn't support iframes.</iframe></div>
	<div id="battleRequest" class="hud"><iframe src="" frameborder="0"></iframe>
</div>
<script type="text/javascript">populateButtons("firstNavigation");</script>
<script type="text/javascript">pollGameServlet();</script>
</body>
</html>
