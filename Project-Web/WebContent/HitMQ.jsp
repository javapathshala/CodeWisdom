<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>HitMQ</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>
<font color="orange">
<b>
Please write the message to be sent

Please click on the button below to send the message to the MQ Server Queue. <br/>
The MQ Queue Test.IN will recieve the message. <br/>
Listener will pick up the message, process it &amp; then send it to TEST.OUT queue
</b>
</font>

<form name="MyForm" method="post" action="SendMessage">
	<input type="text" name="txtMsg" size="40" value="Test Message" id="txtMsg"><br/><br/>
	<input type="submit" name="Send" value="Send Message">

</form>


</body>
</html>
