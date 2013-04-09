var selectedSuggestionIndex;
var content="";
var autoData = new Array();
var tempArray = new Array();
var lastTid = false;
var isBusy = false;
var txtBox;
var txtBoxWidth;
var autocompletelayer;
var focusme=false;
var layerColor1, layerColor1, layerColorSelect, fontColor, tableBorderColor;
var debug=false;
var numRequest=0;
var usedDevice="";
var httpObj = makeHttpObject();
var responseList=new Array();

function displayList(e,txtb){
autocompletelayer = document.getElementById("autocompletedata"); //Div to display results
	
	txtBox = txtb; //Typed in value
	txtBoxWidth = parseInt(txtBox.style.width); //width of the box
	var KeyID = (window.event) ? event.keyCode : e.keyCode; //handling IE and firefox
	
	if(KeyID==38 || KeyID==40 || KeyID==27) //up and down arrow
	{
		KeyCheck(KeyID,responseList);
	}
	else
	{
		if(lastTid)
			clearTimeout(lastTid); //If alredy timeout set cleat ir 
		lastTid = setTimeout("callHttp("+KeyID+")", 50); //Wait for 50ms and call HTTP Request
	}
}
function hide()
{
	if(focusme==false)
	{
		autocompletelayer = document.getElementById("autocompletedata");
		autocompletelayer.style.display='none';
	}
}

function KeyCheck(KeyID,responseList)
{
	var str = txtBox.value;
	var show=1;
	if(str.length > 0)
	{
		responseList = content.split(",");
		if(KeyID==38)
		{
			if(selectedSuggestionIndex > 0)
			{
				selectedSuggestionIndex--;
			}
			else
			{
				selectedSuggestionIndex = responseList.length-1;
			}
		}
		else if(KeyID==40)
		{
			if(selectedSuggestionIndex < responseList.length-1)
			{
				selectedSuggestionIndex++;
			}
			else
			{
				selectedSuggestionIndex = 0;
			}
		}
		else if(KeyID==27)
		{
			show = 0;
			focusme	= false;
			hide();
		}
		
		if(show)
		{
			normal(selectedSuggestionIndex,responseList);
			endup('k');
		}
	}
}

function normal(selectedIndex,responseList)
{
	selectedSuggestionIndex = selectedIndex; 
	var str="";
	if(responseList.length > 0)
	{
		str+='<table BORDER="1" cellspacing="0" cellpadding="2" STYLE="border-color:#808080;border-collapse:collapse;width:'+txtBoxWidth+'px;" onmouseover="focusme=true;" onmouseout="focusme=false;">'+"\n";

		for(i=0;i<responseList.length;i++)
		{
			var bgcolor, fontColor;
			namestr=responseList[i];
			if(i%2==0)
			{
				bgcolor=' BGCOLOR="#ffffff"'; //'+layerColor1+'"';
				fontColor='color:#000000;';
			}
			else
			{
				bgcolor=' BGCOLOR="#ffffff"'; //'+layerColor2+'"';
				fontColor='color:#000000;';
			}
			if(i==selectedSuggestionIndex)
			{
				bgcolor=' BGCOLOR="#ff9933"'; //'+layerColorSelect+'"';
				fontColor='color:#ffffff;';
			}
			str+='<tr>';
			str+='<td class="frest" style="'+fontColor+'padding-left:5px;width:'+txtBoxWidth+'px"'+bgcolor+' onclick="endup(\'m\');autocompletelayer.style.display=\'none\';" onmousemove="normal(\''+i+'\',\''+namestr+'\);">'+namestr+'</td>';
			str+='</tr>'+"\n";
		}
		str+='</table>';
}
	autocompletelayer.style.display='block';
	autocompletelayer.innerHTML=str;
	autocompletelayer.style.zIndex = '1000';
	autocompletelayer.style.position = 'absolute';
	autocompletelayer.style.left = findPosX(txtBox);
	autocompletelayer.style.top = findPosY(txtBox);
}

function callHttp(KeyID)
{
	if(isBusy)
	{		
		httpObj = makeHttpObject();
	}
	// Find the value typed in autocomplete text Box	
	var str = txtBox.value.toLowerCase();
	str = refineText(str);
	// Calling when the text in autocomplete box has length > 2 
	if(str.length > 0 && str.match(new RegExp("[a-z]+", "i")) && KeyID!=37 && KeyID!=39)
	{
		if(!checkExists(str))
		{
			autocompletelayer.innerHTML="";
				var url="http://localhost:9083/Ajax/GetHints.jsp?str="+str;
					url=url+"&sid"+Math.random();
			try
			{
				isBusy = true;
				numRequest++;				
				httpObj.open("GET",url,true);
				httpObj.onreadystatechange = getHttpResponse;
				httpObj.send(null);
				if(document.forms[0].ac) 
				document.forms[0].ac.value=usedDevice+","+numRequest+","+lastSelectedValue;
			}
			catch (err)
			{
				// document.forms[0].mylog.value+="Error in sending request "+err+"\n";
			}
		}
	}
	else if(KeyID!=37 && KeyID!=39)
	{
		autocompletelayer.innerHTML="";
	}
}

function refineText(rStr)
{
	rStr = rStr.replace(/[^a-z0-9_ .+#]+/ig," ");
	rStr = rStr.replace(/\s+/g," ");
	rStr = rStr.replace(/(^(\s*,\s*)+|(\s*,\s*)+$|^\s+)/g,"");
	return rStr;
}

function checkExists(str)
{

	if(autoData[str.substring(0,2)] == undefined)
	{
		return false;
	}
	else if(autoData[str.substring(0,2)].match(new RegExp("[a-z]+","i")))
	{
		content = seprateContent(autoData[str.substring(0,2)]);
		if(matchResponse(responseList))
		{
			normal(-1,responseList);
		}
		else
		{
			autocompletelayer.innerHTML="";
		}
		return true;
	}
	return false;
}

function getHttpResponse()
{
	if(httpObj.readyState == 4)
	{
		try
		{
			if(httpObj.status == 200)
			{
				content = httpObj.responseText;
				isBusy = false;
					responseList=content.split(",");
				if(matchResponse(responseList))
				{
					normal(-1,responseList);
				}
				else
				{
					autocompletelayer.innerHTML="";
				}
			}
		}
		catch (err)
		{
		}
	}
}
function makeHttpObject()
{
	
	try
	{
	
		xmlHttpObj = new ActiveXObject("Msxml2.XMLHTTP");
	
	}
	catch (e)
	{
		try
		{
			xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
		
		}
		catch (e)
		{
			xmlHttpObj = false;
		}
	}

	if(!xmlHttpObj && typeof XMLHttpRequest !='undefined')
	{
		try
		{
			xmlHttpObj = new XMLHttpRequest();
			
		}
		catch (e)
		{
			xmlHttpObj = false;
		}
	}
	return xmlHttpObj;
}

function seprateContent(str)
{
	var txtData = refineText(txtBox.value);
	var reg = new RegExp('[.]','g');
	txtData = txtData.replace(reg,'\\.');
	reg = new RegExp('[+]','g');
	txtData = txtData.replace(reg,'\\+');
	var freshContent = '';
	var tempStr = '';
	tempArray = str.split(',');
	var countsg = 0;
	for(sg in tempArray)
	{
		tempStr = refineText(tempArray[sg])
		if(tempStr.match(new RegExp('^'+txtData,"i")))
		{
			freshContent+=tempArray[sg]+",";
			countsg++;
		}
		if(countsg==10)
		{
			break;
		}
	}
	freshContent = freshContent.replace(/\|$/,"");
	return freshContent;
}



function endup(device)
{
	responseList = content.split(",");
	txtBox.value = responseList[selectedSuggestionIndex];
	txtBox.value = responseList[selectedSuggestionIndex].split(":")[0];
	lastSelectedValue = responseList[selectedSuggestionIndex].split(":")[0];
	usedDevice=device;
	txtBox.focus();
	if(document.forms[0].ac) document.forms[0].ac.value=usedDevice+","+numRequest+","+lastSelectedValue;
}

function getPos()
{
	var str = txtBox.value;
	var pos = str.lastIndexOf(' ');
	if(str.lastIndexOf(',') > pos)
	{
		pos = str.lastIndexOf(',');
	}
	if(str.lastIndexOf(',') > pos)
	{
		pos = str.lastIndexOf(',');
	}
	return pos;
}

function checkAutoFocus()
{
	responseList = content.split(",");
	txtBox.value = responseList[selectedSuggestionIndex];
	txtBox.focus();
}

function findPosX(obj)
{
	var curleft = 0;
	if (obj.offsetParent){
		while (obj.offsetParent){
			curleft += obj.offsetLeft;
			obj = obj.offsetParent;
		}
	}
	else if (obj.x)
		curleft += obj.x;
	return curleft;
}

function findPosY(obj)
{
	var curtop = 0;
	if (obj.offsetParent){
		curtop += obj.offsetHeight;
		while (obj.offsetParent){
			curtop += obj.offsetTop;
			obj = obj.offsetParent;
		}
	}
	else if (obj.y){
		curtop += obj.y;
		curtop += obj.height;
	}
	return curtop;
}

function initAutoComplete(layercolor1, layercolor2, layercolorselect, fontcolor, tablebordercolor)
{
	layerColor1 = layercolor1;
	layerColor2 = layercolor2;
	layerColorSelect = layercolorselect;
	fontColor = fontcolor;
	tableBorderColor = tablebordercolor;
}

function matchResponse(responseList)
{
	var str="";
	var y=0;
		for(i=0;i<responseList.length;i++){
		str=responseList[i];
		str = refineText(str);
		if(str==null){
		return false;
		}
		}
	return true;
	
}

function Debug()
{
	if(!debug)
	{
		debug=true;
	}
	else
	{
		debug=false;
	}
}
