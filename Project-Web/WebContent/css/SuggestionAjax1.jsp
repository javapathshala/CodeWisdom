<html>
<head>
<title>SuggestionAjax.html</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

<script type="text/javascript" src="Scripts/autocomplete.js"></script>

</head>

<body>
<SCRIPT type="text/javascript">
		function showHint(str){	
				try{
					if(str.length==0){
						document.getElementById("hintSpan").innerHTML="No Suggestions";
						return;
					}				
					var url="http://localhost:9083/Ajax/GetHints.jsp?str="+str;
					url=url+"&sid"+Math.random();
					var xmlHttp=GetXmlHttpObject();
								 
					xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4){
					var response = (xmlHttp.responseText) ? xmlHttp.responseText : xmlHttp.responseXML.documentElement;
   alert(response);
   
  // document.getElementById(["droplist"]).value =xmlHttp.responseText;
					//	document.getElementById("hintSpan").innerText=response;
						document.getElementById("test").innerText=response;
					}	
				}
			xmlHttp.open("GET",url,true);
xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlHttp.send(url);			
			}catch(e){
				alert(e);			
			}
		}
		
		
		var txtBox;
var txtBoxWidth;
var lastTid = false;
var autocompletelayer;
function displayList(e,txtbox)
{
alert("display results");
	autocompletelayer = document.getElementById("autocompletedata"); //Div to display results
	alert(autocompletelayer);
	txtBox = txtbox; //Typed in value
	txtBoxWidth = parseInt(txtBox.style.width); //width of the box
	var KeyID = (window.event) ? event.keyCode : e.keyCode; //handling IE and firefox
	if(KeyID==38 || KeyID==40 || KeyID==27) //up and down arrow
	{
		KeyCheck(KeyID);
	}
	else
	{
		if(lastTid)
			clearTimeout(lastTid); //If alredy timeout set cleat ir 

		lastTid = setTimeout("callHttp("+KeyID+")", 50); //Wait for 50ms and call HTTP Request
	}
}
		
		
		</SCRIPT>
<!-- form name="suggestion">Names Are :<input type="text"
	name="sugId" onkeyup="showHint(this.value)" value=""> <span
	id="hintSpan"> </span> <br></form-->
<br>
<br>
<table width="755" align="center" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr>
			<td valign="top" width="551"><br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<span class="Heading4">Search Jobs</span>
			<br>
			Put Job Titles, Location, Company Name, Skills, Industry, etc. <br>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="style8" width="10%">
						<input name="fts" size="55"
							onkeydown="displayList(event,this)" style="width: 355px;"
							autocomplete="off" onblur="hide();" value="" type="text"/>
						<div id="autocompletedata"
							style="display: none; z-index: 1000; position: absolute; left: 254px; top: 318px;"
							align="center">
						<!--table
							style="border-color: rgb(128, 128, 128); border-collapse: collapse; width: 355px;"
							onmouseover="focusme=true;" onmouseout="focusme=false;"
							border="1" cellpadding="2" cellspacing="0">
							<tbody>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('0');" bgcolor="#ffffff">SAT</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('1');" bgcolor="#ffffff">SATA</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('2');" bgcolor="#ffffff">Satellite
									Networks</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('3');" bgcolor="#ffffff">SATA,
									Bangalore</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('4');" bgcolor="#ffffff">SAT, Mumbai</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('5');" bgcolor="#ffffff">SAT,
									Bangalore</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('6');" bgcolor="#ffffff">SAT, Chennai</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('7');" bgcolor="#ffffff">SAT, Delhi</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('8');" bgcolor="#ffffff">SAT,
									Hyderabad</td>
								</tr>
								<tr>
									<td class="frest"
										style="color: rgb(0, 0, 0); padding-left: 5px; width: 355px;"
										onclick="endup('m');autocompletelayer.style.display='none';"
										onmousemove="normal('9');" bgcolor="#ffffff">SATA, Delhi</td>
								</tr>
							</tbody>
						</table-->
						</div>
						</td>
						<td width="2%">&nbsp;<input name="ac" value="" type="hidden"></td>
						<td width="88%">
						<!-- input name="submit"
							src="Monster%20-%20Jobs%20in%20India,Delhi,Mumbai,Bangalore,IT%20Jobs,Sales%20Jobs%20on%20Monster.com_files/butt_go.gif"
							title="Search Jobs" value="" type="image" width="101" height="34"--></td>
					</tr>
					<tr>
						<td colspan="3"><a
							href="http://www.monsterindia.com/search_tips.html"
							title="Search Tips">Search Tips</a> | <a
							href="http://jobsearch.monsterindia.com/search.html"
							title="Find A Job">Quick Search</a> | <a
							href="http://jobsearch.monsterindia.com/advanced_job_search.html"
							title="Job Opportunity, Job Listings">Advanced Search</a></td>
					</tr>
					<tr>
						<td colspan="3" height="60">&nbsp;</td>
					</tr>
				</tbody>
			</table>

			<p>Suggestions are :</p>
			<br>
			<br>



			<br>

			<br>
			<a href="http://localhost:9083/WebProject/Index.jsp">Back</a>
</body>

</html>
