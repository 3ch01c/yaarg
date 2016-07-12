
<%@page import="edu.nmt.cs.itweb.GameCharacter"%>
<%@page import="edu.nmt.cs.itweb.GameBattle"%>
<!--
	This document is called by battleContainer.html. The elements on this page are directly associated with the battle process,
	and all information being presented to the user asside from the background streetview itself should reside here.
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="js/battleCommand.js"></script>
    
    <%
        GameBattle currentBattle = (GameBattle)session.getAttribute("battle");
        GameCharacter player1;
        GameCharacter player2;
        if( currentBattle.getPlayer1().getId() == ((GameCharacter)session.getAttribute("currentChar")).getId()){
            player1 = currentBattle.getPlayer1();
            player2 = currentBattle.getPlayer2();
        }
        else{
            player1 = currentBattle.getPlayer2();
            player2 = currentBattle.getPlayer1();
        }
    %>
	<script type="text/javascript">
		//canvas based on ex: http://www.brighthub.com/internet/web-development/articles/38744.aspx?p=3
		// target frames per second
		const FPS = 30;
		var x = 0;
		var y = 100;
		var xDirection = 1;
		var yDirection = -1;
		var canvasImg = new Image();
		canvasImg.src = "images/pbolt.png";
		var criticalImg = new Image();
		criticalImg.src = "images/Critical.png";
		var winnerImg = new Image();
		winnerImg.src = "images/Winner.png";
		var LoserImg = new Image();
		LoserImg.src = "images/Loser.png";

                var calledClean = false;
		
		var actionCanvas = null;
                var intervalID;
		var experienceCanvas = null;
		var context2D = null;
		var ec2d = null;

                //Set variables related to the battle:
                var messageType;
                var critical;
                var skillName;
                var numHits;
                var turn;
                var gold;
                var itemName;
                var itemImg;
                var winnerId;
                var canvasImgSrc;

		//Set the values of the character's current status
                var charId = <%= player1.getId() %>;
                var curExperience = <%= player1.getExp() %>;
                var curHPplayer = <%= player1.getCurrent_hp() %>;
		var curMPplayer = <%= player1.getCurrent_energy()%>;
		var curHPenemy = <%= player2.getCurrent_hp() %>;
		var curMPenemy= <%= player2.getCurrent_energy() %>;

                //Set maximum values for character status
                var experienceTotal = <%= player1.getRequired_xp() %>;
		var hPplayerTotal = <%= player1.getHp() %>;
		var mPplayerTotal = <%= player1.getEnergy() %>;
		var hPenemyTotal = <%= player2.getHp() %>;
		var mPenemyTotal = <%= player2.getEnergy() %>;

                //set the percentage values of the maximum for each field
		var curExperiencePercentage = <%= player1.getExp() / player1.getRequired_xp() %>;
		var curHPplayerPercentage = <%= player1.getCurrent_hp() / player1.getHp() %>;
		var curMPplayerPercentage = <%= player1.getCurrent_energy() / player1.getEnergy() %>;
		var curHPenemyPercentage = <%= player2.getCurrent_hp() / player2.getHp() %>;
		var curMPenemyPercentage = <%= player2.getCurrent_energy() / player2.getEnergy() %>;

                function refreshStatus()
                {
                    //alert("curHPp"+curHPplayer+"\ncurMPp"+curMPplayer+"\ncurHPe"+curHPenemy+"\ncurMPe"+curMPenemy);

                    canvasImg.src = canvasImgSrc;
                    initAttack();
                    curExperiencePercentage = curExperience / experienceTotal;
                    curHPplayerPercentage = curHPplayer / hPplayerTotal;
                    curMPplayerPercentage = curMPplayer / mPplayerTotal;
                    curHPenemyPercentage = curHPenemy / hPenemyTotal;
                    curMPenemyPercentage = curMPenemy / mPenemyTotal;

                    //alert("curHPp"+curHPplayer+"\ncurMPp"+curMPplayer+"\ncurHPe"+curHPenemy+"\ncurMPe"+curMPenemy);

                    initPage();
                                    
                }
		function initPage()
		{
                    //open all of the canvas status bars:
                    drawHPplayer();
                    drawMPplayer();
                    drawHPenemy();
                    drawMPenemy();
		}
		function initAttack()
		{
                   x=0;
                   y=100;
		   actionCanvas = document.getElementById('actionCanvas');
		   context2D = actionCanvas.getContext('2d');
		   setInterval(drawAttack, 1000 / FPS);
		}

                function drawAttack()
		{
                    context2D.clearRect(0, 0, actionCanvas.width, actionCanvas.height);
                    if (critical == "true" && messageType != "over")
                    {
                        //this is a crit, print crit on the canvas as well
                        context2D.drawImage(criticalImg, 50, 50);
                    }
                    context2D.drawImage(canvasImg, x, y);
                    x += 8 * xDirection;
                    y += 4 * yDirection;

                    if(x >= actionCanvas.width)
                    {
                        clearInterval(intervalID);
                    }

                    if(messageType == "over")
                    {
                        clearInterval(intervalID);
                        //The match has ended, check to see who the winner was
                        //alert("winnerID: " +winnerId + " charId: " +charId)
                        if(winnerId == charId)
                        {
                            drawExperience();
                            //This match was won, do stuff
                            context2D.drawImage(winnerImg, 50, 50);
                            //wait a few seconds before drawing loot to the canvas portion of the screen
                            //allows for the 'winner' tag to be displayed.
                            setTimeout("drawLoot()",3000);
                        }
                        else
                        {
                            //This match was lost, do stuff
                            context2D.drawImage(LoserImg, 50, 50);
                        }
                    }
                }
                function drawLoot()
                {
                    var writeLoot = document.getElementById("canvasContainer");
                    writeLoot.innerHTML="<h3 style='text-align: center'>Loot</h3><br/> Gold: " + gold;
                    writeLoot.innerHTML+= "<br/>Item:<br/> " + itemName +" <div style='"+itemImg+"' class='sprite'></div> ";
                    writeLoot.innerHtml+="EXP Gained: "+curExperience+"<br/>";
                    writeLoot.style.background = "black";
                    if(!calledClean)
                    {
                        cleanBattle();
                        calledClean = true;
                    }
                }

		function drawExperience()
		{
			var experienceCanvas = document.getElementById('experienceCanvas');
			//calculate experience in px:
			var expFill = experienceCanvas.width * curExperiencePercentage;
			var ctxExp = experienceCanvas.getContext('2d');
                        ctxExp.clearRect(0, 0, experienceCanvas.width, experienceCanvas.height);
			ctxExp.fillStyle = '#FC0'; // gold
			ctxExp.fillRect(0, 0, expFill, experienceCanvas.height);
		}
		function drawHPplayer()
		{
			var HPplayerCanvas = document.getElementById('HPplayerCanvas');
			//calculate hp in px:
			var HPFillplayer = HPplayerCanvas.width * curHPplayerPercentage;
			var ctxHPplayer = HPplayerCanvas.getContext('2d');
                        ctxHPplayer.clearRect(0, 0, HPplayerCanvas.width, HPplayerCanvas.height);
			ctxHPplayer.fillStyle = '#f00'; // red
			ctxHPplayer.fillRect(0, 0, HPFillplayer, HPplayerCanvas.height);
		}
		function drawMPplayer()
		{
			var MPplayerCanvas = document.getElementById('MPplayerCanvas');
			//calculate hp in px:
			var MPFillplayer = MPplayerCanvas.width * curMPplayerPercentage;
			var ctxMPplayer = MPplayerCanvas.getContext('2d');
                        ctxMPplayer.clearRect(0, 0, MPplayerCanvas.width, MPplayerCanvas.height);
			ctxMPplayer.fillStyle = '#00f'; // blue
			ctxMPplayer.fillRect(0, 0, MPFillplayer, MPplayerCanvas.height);
		}
		function drawHPenemy()
		{
			var HPenemyCanvas = document.getElementById('HPenemyCanvas');
			//calculate hp in px:
			var HPFillenemy = HPenemyCanvas.width * curHPenemyPercentage;
			var ctxHPenemy = HPenemyCanvas.getContext('2d');
                        ctxHPenemy.clearRect(0, 0, HPenemyCanvas.width, HPenemyCanvas.height);
			ctxHPenemy.fillStyle = '#f00'; // red
			ctxHPenemy.fillRect(0, 0, HPFillenemy, HPenemyCanvas.height);
		}
		function drawMPenemy()
		{
			var MPenemyCanvas = document.getElementById('MPenemyCanvas');
			//calculate hp in px:
			var MPFillenemy = MPenemyCanvas.width * curMPenemyPercentage;
			var ctxMPenemy = MPenemyCanvas.getContext('2d');
                        ctxMPenemy.clearRect(0, 0, MPenemyCanvas.width, MPenemyCanvas.height);
			ctxMPenemy.fillStyle = '#00f'; // blue
			ctxMPenemy.fillRect(0, 0, MPFillenemy, MPenemyCanvas.height);
		}
	</script>
	<link rel="stylesheet" type="text/css" href="css/all.css" />
	<link rel="stylesheet" type="text/css" href="css/sprite.css" />
	<style type="text/css">
		html {
			height: 100%;
			width: 100%;
		}
		body {
			height: 100%;
			width: 100%;
		}
		#enemy {
			height: 200px;
			right: 5px;
			top: 0%;
			float: right;
		}
		#player {
			height: 200px;
			left: 5px;
			bottom: 30px;
		}
		#canvasContainer {
			top: 20%;
			left: 200px;
			right: 200px;
		}
		#HPenemy {
			width: 500px;
			top: 0%;
			left: 0%;
		}
		#MPenemy{
			width: 500px;
			top: 25px;
			left: 0%;
		}
		#HPplayer {
			width: 500px;
			bottom: 55px;
			right: 0%;
		}
		#MPplayer{
			width: 500px;
			bottom: 30px;
			right: 0%;
		}
		#PlayerExperience {
			width: 100%;
			bottom: 5px;
			right: 0%;
		}
	</style>
</head>

<body onload="initPage()">
    <!--Enemy-->
    <div id="enemy" style="position: absolute;">
        <img src="<%= player2.getIcon_url() %>" alt="Enemy"
                        style="height: 100%"></img>
    </div>

    <!--Player-->
    <div id="player" style="position: absolute;">
        <img src="<%= player1.getIcon_url() %>" alt="Enemy"
                    style="height: 100%"></img>
    </div>

    <!--Action Canvas-->
    <div id="canvasContainer" style="position: absolute;">
            <canvas id="actionCanvas" style="width:90%; height: 50%;">
            </canvas>
    </div>

    <!--Enemy HP-->
    <div id="HPenemy" style="position: absolute;" >
            <canvas id="HPenemyCanvas" style="height:20px; width: 500px;">
            </canvas>
    </div>

    <!--Enemy MP-->
    <div id="MPenemy" style="position: absolute;" >
            <canvas id="MPenemyCanvas" style="height:20px; width: 500px;">
            </canvas>
    </div>


    <!--Player HP-->
    <div id="HPplayer" style="position: absolute;" >
            <canvas id="HPplayerCanvas" style="height:20px; width: 500px;">
            </canvas>
    </div>

    <!--Player MP-->
    <div id="MPplayer" style="position: absolute;" >
            <canvas id="MPplayerCanvas" style="height:20px; width: 500px;">
            </canvas>
    </div>

    <!--Player Experience-->
    <div id="PlayerExperience" style="position: absolute;" >
            <canvas id="experienceCanvas" style="width:90%; height: 20px;">
            </canvas>
    </div>
    <script type="text/javascript">pollForBattleMessage();</script>
</body>
</html>