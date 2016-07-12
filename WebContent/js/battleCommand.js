//These functions are for *in battle* requests and stuff
var battleAjaxer = getXmlHttpObject();


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

///////////This is used on a timer to poll the game servlet for battle messages/////
function pollForBattleMessage()
{
    battleAjaxer.onreadystatechange=function()
    {
        if(battleAjaxer.readyState==4 && battleAjaxer.status==200)
        {
            var newMessage = battleAjaxer.responseText;
            if(newMessage != "none")
            {
                processBattleMessage(newMessage);
            }

            if(newMessage != "over")
            {
                setTimeout("pollForBattleMessage()", 2000);
            }
            else
            {
                battleAjaxer = getXmlHttpObject();
            }
        }
    }

    battleAjaxer.open("POST", "GameServlet?type=battleMessagePoll", true);
    battleAjaxer.send();
}

function cleanBattle()
{
    battleAjaxer.onreadystatechange=function()
    {
        if(battleAjaxer.readyState==4 && battleAjaxer.status==200)
        {
            var dummy = "david";
        }
    }
    battleAjaxer.open("POST", "GameServlet?type=cleanBattle", true);
    battleAjaxer.send();
}

function processBattleMessage(battleMessage)
{
    var battleParts = battleMessage.split(",");

    //alert("battleMessage: "+battleParts);

    messageType = battleParts[0];

    if(charId == battleParts[1])
        {
            curHPplayer = battleParts[2];
            curMPplayer = battleParts[3];
            curHPenemy = battleParts[4];
            curMPenemy = battleParts[5];
        }
    else
        {
            curHPenemy = battleParts[2];
            curMPenemy = battleParts[3];
            curHPplayer = battleParts[4];
            curMPplayer = battleParts[5];
        }
    
    critical = battleParts[6];
    canvasImgSrc = battleParts[7];
    skillName = battleParts[8];
    numHits = battleParts[9];
    turn = battleParts[10];

    if(messageType == "over")
    {
        winnerId = battleParts[11];
        curExperience = battleParts[12];
        gold = battleParts[13];
        itemName = battleParts[14];
        itemImg = battleParts[15];
    }

    refreshStatus();
}