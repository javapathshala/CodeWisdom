<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>File Up Loader</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>
<font color="orange">
<b>
Please Upload the File
</b>
</font>

<form name="MyForm" ENCTYPE="multipart/form-data" METHOD="POST" action="FileUpLoader">
	<input TYPE="FILE" NAME="fupload" size="40"><br/><br/>
	<input type="submit" name="upload" value="Up Load File">

</form>


</body>
</html>
