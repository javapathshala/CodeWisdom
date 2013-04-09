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


</b>
</font>

<form name="MyForm" method="post" action="../FilterServlet">
	<input type="text" name="txtMsg" size="40" value="Test Message" id="txtMsg"><br/><br/>
	<input type="submit" name="Send" value="Send Message">

</form>


</body>
</html>
