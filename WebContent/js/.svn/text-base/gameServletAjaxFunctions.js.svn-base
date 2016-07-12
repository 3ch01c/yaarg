/*
 Herin, are a few ajax objects for making requests, and functions for
processing those requests
*/

//here are the key variables that we're going to use over and over

var gameServletAjaxer = getXmlHttpObject();
var buttonServletAjaxer = getXmlHttpObject();
var battleServletAjaxer = getXmlHttpObject();
var messageAjaxer = getXmlHttpObject();
var attackAjaxer = getXmlHttpObject();

var email = "";
var enemyId= -1;

//this will be used to show the user a battle request
var battleRequestDiv

//this function gets a cross-browser friendly ajax request maker object
function getXmlHttpObject()
{
    var xmlHttp = null;
    try
      {
          // Firefox, Opera 8.0+, Safari
          xmlHttp = new XMLHttpRequest();
      }
    catch (e)
      {
          // Internet Explorer
          try
            {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }
          catch (e)
            {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
      }
    return xmlHttp;
}
//////////End function go get AJAX Objects


////////Initially populates the command frame
function populateButtons(buttonType)
{
    var commandFrame = document.getElementById("commandframe");
    buttonServletAjaxer.onreadystatechange=function()
    {
        if (buttonServletAjaxer.readyState==4 && buttonServletAjaxer.status==200)
            {
                commandFrame.innerHTML=buttonServletAjaxer.responseText;
                if(buttonType == "Fight" || buttonType == "firstNavigation")
                {
                    disableButtons();
                }
            }
    }
    if(buttonType == "rePopFight")
    {
        buttonServletAjaxer.open("POST", "GameServlet?type=requestForButtons&buttonType=Fight", true);
    }
    else if(buttonType == "firstNavigation")
    {
        buttonServletAjaxer.open("POST", "GameServlet?type=requestForButtons&buttonType=navigation", true);
    }
    else
    {
        buttonServletAjaxer.open("POST", "GameServlet?type=requestForButtons&buttonType="+buttonType, true);
    }
    buttonServletAjaxer.send();
}
///////////End populateCommandFrame

///////////This is used on a timer to poll the game servlet/////
function pollGameServlet()
{
    gameServletAjaxer.onreadystatechange=function()
    {
        if(gameServletAjaxer.readyState==4 && gameServletAjaxer.status==200)
        {
            var newMessage = gameServletAjaxer.responseText;
            processMessage(newMessage);
        }
    }

    gameServletAjaxer.open("POST", "GameServlet?type=poll", true);
    gameServletAjaxer.send();
}

//////////This checks to see if we got anything from polling the game servlet and acts accordingly//////
function processMessage(message)
{
    if(message == "battleRequest")
    {
        getBattleRequest();
    }
    else if(message == "join")
    {
        closeFightRequestDialog();
        goFight("join");
    }
    else if(message == "accepted")
    {
        closeFightRequestDialog();
        goFight("startBattle");
    }
    else if(message == "turn")
    {
        enableButtons();
    }
    else if(message == "leaveBattle")
    {
        goHome();
    }
    else if(message == "enableButtons")
    {
        enableButtons();
    }
    
    setTimeout("pollGameServlet()", 3000);
}

//this disables the buttons on the page
function disableButtons()
{
    var commandFrame = document.getElementById("commandframe");
    var buttons = commandFrame.getElementsByTagName("input");
    for(var i = 0; i < buttons.length; i++)
    {
        buttons.item(i).disabled = true;
    }
}

function enableButtons()
{
    var commandFrame = document.getElementById("commandframe");
    var buttons = commandFrame.getElementsByTagName("input");
    for(var i = 0; i < buttons.length; i++)
    {
        buttons.item(i).disabled = false;
    }
}

////////////Button Functions
////////////These are funcitons that buttons call
function goHome()
{
    document.getElementsByName("mainframe").item(0).src = "map.jsp";
    populateButtons("navigation");
}
function goFight(fightType)
{
    if(fightType == "startBattle")
        {
            window.document.getElementsByName("mainframe").item(0).src = "GameServlet?type="+fightType+"&enemyId="+enemyId;
            populateButtons("Fight");
        }
    else if(fightType == "join")
        {
            window.document.getElementsByName("mainframe").item(0).src = "GameServlet?type="+fightType;
            populateButtons("Fight");
            setTimeout("initBattle()", 3000);
        }
    else if(fightType == "sendAccept")
        {
            messageAjaxer.onreadystatechange = function()
            {
                if(messageAjaxer.readyState==4 && messageAjaxer.status==200)
                    {
                        messageAjaxer.onreadystatechange =function()
                        {
                            var dummy = null;
                        }
                        messageAjaxer.open("POST", "GameServlet?type=sendMessage&message=accepted&recipient="+enemyId);
                        messageAjaxer.send();
                    }
            }
            messageAjaxer.open("POST", "GameServlet?type=sendMessage&message=join");
            messageAjaxer.send();
        }
    else if(fightType == "fightRequest")
        {
            populateButtons(fightType);
        }
}

////////////
function initBattle()
{
    attackAjaxer.onreadystatechange = function()
            {

                if(attackAjaxer.readyState == 4 && attackAjaxer.status == 200)
                    {
                        var dummy = "you";
                    }
            }
            attackAjaxer.open("POST", "BattleServlet?type=whoGoesFirst", true);
            attackAjaxer.send();
}
//////////////End button function callees

function getBattleRequest()
{
    var battleRequestDiv = document.getElementById("battleRequest");
    battleRequestDiv.style.visibility = "visible";
    battleRequestDiv.getElementsByTagName("iframe").item(0).src = "GameServlet?type=getBattleRequest";
}

function closeBattleRequest()
{
    battleServletAjaxer.onreadystatechange=function()
    {
        if(battleServletAjaxer.readyState==4 && battleServletAjaxer.status==200)
            {
                populateButtons("navigation");
                var battleRequestDiv = document.getElementById("battleRequest");
                battleRequestDiv.style.visibility = "hidden";
            }
    }
    battleServletAjaxer.open("POST", "GameServlet?type=refuseBattleRequest", true);
    battleServletAjaxer.send();
}

function sendBattleRequest()
{
    enemyId = document.getElementById("enemyIdVal").value;
    document.getElementById("requestMessage").innerHTML = "Requesting...";
    battleServletAjaxer.onreadystatechange=function()
    {
        var dummy = "you";
    }
    battleServletAjaxer.open("POST", "GameServlet?type=startBattle&enemyId="+enemyId, true);
    battleServletAjaxer.send();
}

function showFightRequestDialog()
{
    var battleRequestDiv = document.getElementById("battleRequest");
    battleRequestDiv.getElementsByTagName("iframe").item(0).src = "GameServlet?type=initBattleRequest";
    battleRequestDiv.style.visibility = "visible";
}

function closeFightRequestDialog()
{
    var battleRequestDiv = document.getElementById("battleRequest");
    battleRequestDiv.style.visibility = "hidden";
}

////////////This is a battle function for using a skill
function useSkill(skillName)
{
    attackAjaxer.onreadystatechange=function()
    {
        if(attackAjaxer.readyState == 4 && attackAjaxer.status == 200)
            {
                var dummy = "me";
            }
    }
    attackAjaxer.open("POST", "GameServlet?type=continueBattle&skill="+skillName, true);
    attackAjaxer.send();
    disableButtons();
}
///////////////
function inventory() {
		if (document.getElementById("inventory").style.visibility == "hidden") {
			document.getElementById("inventory").style.visibility = "visible";
			document.getElementById("questLog").style.visibility = "hidden";
		} else {
			document.getElementById("inventory").style.visibility = "hidden";
		}
}

function questLog() {
	if (document.getElementById("questLog").style.visibility == "hidden") {
		document.getElementById("questLog").style.visibility = "visible";
		document.getElementById("inventory").style.visibility = "hidden";
	} else {
		document.getElementById("questLog").style.visibility = "hidden";
	}
}

///////////This is the generic button function that is called when any button is pressed
///////////inside, we decide what actions exactly to take
function buttonPressed(obj)
{
    var buttonValue = obj.value;
    var buttonName = obj.name;

    //if button id is null, it's a standard button'
    if(buttonName == null || buttonName == "")
    {
        if(buttonValue == "Fight")
            {
                goFight("fightRequest");
            }
        else if(buttonValue == "PVE")
            {
                goFight("startBattle");
            }
        else if(buttonValue == "PVP")
            {
                showFightRequestDialog();
            }
        else if(buttonValue == "Home")
            {
                goHome();
            }
        else if(buttonValue == "Skills")
            {
                populateButtons("Skills");
            }
        else if(buttonValue == "Back")
            {
                populateButtons("rePopFight");
            }
        else if(buttonValue == "Run")
            {
                cleanBattle();
            }
        else if(buttonValue == "To Battle")
            {
                goFight("sendAccept");
            }
        else if(buttonValue == "Eff that Noise")
            {
                closeBattleRequest();
            }
        else if(buttonValue == "Cancel")
            {
                populateButtons("navigation");
            }
        else if(buttonValue == "Send Request")
            {
                sendBattleRequest();
            }
        else if(buttonValue == "Cancel Request")
            {
                closeFightRequestDialog();
            }
        else if(buttonValue == "Inventory")
            {
                inventory();
            }
        else if(buttonValue == "Quest")
            {
                questLog();
            }
    }
    //this is for skills only
    else if(buttonName == "skill")
    {
        buttonValue = escape(buttonValue);
        useSkill(buttonValue);
    }
}
///////////////End Generic Button Function