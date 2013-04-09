<html>
	<body>
	Hello ${actionBean.name}</br>
	<br/>
	<s:link beanclass="HelloAction">
		<s:param name="name" value="John"/>
		Try again
	</s:link>
	<br>
	</body>
</html>