 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String logout = "";
	String rpgTitle = "";
	String rpgTheme = "";

	try {
		session = request.getSession();
		rpgTitle = session.getAttribute("rpgTitle").toString();
		rpgTheme = session.getAttribute("rpgTheme").toString();
		String email = session.getAttribute("email").toString();
		logout = ("You are logged in as " + email + "<br/><a href='AccountMgr?action=Logout'>Logout</a>");
	} catch (Exception e) {
		// Title and subtitle word lists.
		String wordlist1[] = { "Legend of", "Saga of", "Legacy of",
				"World of", "Chronicle of", "Curse of", "Story of",
				"Secret of", "Gathering of", "Untold Mystics of",
				"Infinite Depth of", "Faithless Ruin of",
				"Captured Relics of", "Unfortunate Compilation of",
				"Epic of", "The Forgotten Tales of",
				"Cataclysmic Destruction of", "Unthinkable Fathoms of",
				"Mysterious Quests of", "Forgettable Riches of",
                                "Questionable Scent of", "Odd Resemblences of",
                                "Pauper's Act of", "Your Daughter's Tryst with", "Unthinkable Delay of"};
		String wordlist2[] = { "Mana", "the Forgotten Mystics",
				"the Eternal Order", "the Mystical Jewels",
				"the Soul King", "the Moon", "the Fallen Warriors",
				"Chaos", "the Dragon Knights", "Darkness",
				"the Demon Sword", "the Worst Human Rights Violations", "the Void Crystal",
				"the Reborn Prince", "the Ocean Kingdom",
				"the Cerulean Citadel", "Doom", "the Devil's Wet Dream",
				"the Eternal Emerald", "the Dead Empire",
				"the Forgotten Fool", "the Lifeless",
				"the Global Warming", "the Vault of King Rhal",
				"the Nested Beast", "the Farmers Garden",
				"the Awful Smelling Courtyard", "the Infinite Loop",
				"the Three Brothers", "the Warranty's Uselessness", "the Seven Sisters",
				"Yesterday's Wishes", "Unimaginative Titles",
				"the Encroaching Due Date", "Unforgivable Work Load", "the Database Connection"};
		String wordlist3[] = {
				"Outside the important kingdoms, there is no government whatsoever. That usually explains why it's difficult to get beyond the kingdoms.",
				"There will only be one boat in the world. Massive laboratories will have been built all over to service the lonely boat.",
				"If the main enemy was ever imprisoned by another group of heroes, one will secretly be following your party.",
				"There will be an obvious trap inside a city, but you can't complete the game without triggering it.",
				"There's always treasure behind a apothecary.",
				"There will usually be one male dancer in the main character's party, no matter how many female dancers there are.",
				"The suspicious man will be carrying a crystal that will eventually prove to be the key to either saving the world or destroying it.",
				"Summoners working with the evil empire are dreadfully overpowering, much more so than the goodly characters.",
				"The hero's fishmarket will be burned down at the start of the game.",
				"Fighting is only fun when you are winning. Unfortunately, you won't be doing much of that.",
				"Prolonged exposure to bright lights is bad for the eyes.",
				"Bugs are the least of the villages problems. New adventurers kill hundreds to level up.",
				"Knowing the right word could save the life of a wizard. The same word may also end the world.",
				"Flying is fun until the dragons come.",
				"A military academy can become a vehicle, so do not be alarmed when the military academy you're visiting decides to bring you along for an adventure.",
				"The teenaged male lead will begin the third night by sleeping in, being awakened by his uncle, and being reminded he's slept in so long he missed the royal ball.",
				"The enemy's manor will be sunken into the sand early in the game." };

		// Generate a title by taking combining a phrase from wordlist1 and wordlist2.
		Integer randomNumber1 = new Double(Math.random() * wordlist1.length).intValue();
		Integer randomNumber2 = new Double(Math.random() * wordlist2.length).intValue();
		rpgTitle = wordlist1[randomNumber1] + " " + wordlist2[randomNumber2];
		session.setAttribute("rpgTitle", rpgTitle);

		// Generate a subtitle by selecting a phrase from wordlist3.
		Integer randomNumber3 = new Double(Math.random()
				* wordlist3.length).intValue();
		rpgTheme = wordlist3[randomNumber3];
		session.setAttribute("rpgTheme", rpgTheme);
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="An online RPG" />
<link rel="stylesheet" type="text/css" href="css/all.css" />
<style type="text/css">
html {
	background-image: url(images/login.jpg);
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-position: center;
}

body {
	position: absolute;
	top: 5px;
	bottom: 0px;
	left: 5px;
	right: 5px;
	text-align: center;
}

#rpgTitle {
	font-size: 24px;
	font-family: Georgia, 'Times New Roman', Times, serif;
}

#rpgSubTitle {
	font-size: 12px;
	font-family: Georgia, 'Times New Roman', Times, serif;
	font-style: italic;
}

.gameFrame {
	position: absolute;
	top: 60px;
	left: 5px;
	right: 5px;
	bottom: 10px;
}

.gameFrame .transbox {
	position: absolute;
	top: -5px;
	left: -5px;
	right: -5px;
	bottom: -5px;
	z-index: -1;
}

#copyright {
	position: absolute;
	left: 10px;
	right: 10px;
	bottom: 0px;
	text-align: right;
	font-size: 10px;
	overflow: hidden;
	height: 12px;
}

#logout {
	position: absolute;
	right: 0px;
	top: 0px;
	font-size: 10px;
	text-align: right;
}
</style>
<link rel="shortcut icon" href="images/auron.ico" />
</head>
<body onload="//generator()">
<div id="logout"><%=logout%></div>
<div style="height: 60px; overflow: hidden;">
<div id="rpgTitle"><%=rpgTitle%></div>
<div id="rpgSubTitle"><%=rpgTheme%></div>
</div>
<script src="js/browserDetect.js" type="text/javascript" />
<script type="text/javascript">
	if (BrowserDetect.browser == "Internet Explorer") {
		var reply = confirm("You are using "
				+ BrowserDetect.browser
				+ " "
				+ BrowserDetect.version
				+ " which is not compatible with this game. We recommend the Chrome browser from Google for the optimum experience. Do you want to us to direct you to the Chrome download page?");
		if (reply)
			document.URL = "http://chrome.google.com";
		else
			document.URL = "http://www.wedontsupportie.com/";
	}
</script>
<div class="gameFrame">
<div class="transbox"></div>
<iframe src="login.jsp" frameborder="0" id="gameFrame">Your
browser doesn't support iframes.</iframe></div>
<div id="copyright">Copyright 2010</div>
</body>
</html>