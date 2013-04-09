<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<title>Customer List View & Ajax - Spring Framework</title> 
<link type="text/css" rel="stylesheet" href="/SPRING_WEB/stylesheets/jp.css"/>
<link type="text/css" rel="stylesheet" href="/SPRING_WEB/stylesheets/newjp.css"/>
 
<script type="text/javascript" src="./javascript/ajaxHelper.js"></script>
<body class="bodyNew">  
	<table cellspacing="0" cellpadding="0" border="0" width="100%"> 
		<tr>
			<td colspan="3"> 
				<jsp:include page="/jsp/HeaderSpring.jsp" flush="true" />
			</td> 
		</tr>  
	</table>  

	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">  
				<%@ include file="/jsp/navBar.jsp" %>
			</td>
			<td class="vt" width="100%" >
				<form:form method="post" commandName="viewCustomerForm" action="CustomerListViewDisplay.do?showCustButton=true" name="viewCustomerForm">
					<div class="header">Spring Framework Demo by Smylee Consultants!!</div><br/>			
					<div class="errorbgc">${viewCustomerForm.errorField }  </div>												
					<table class="filter" cellpadding="2" cellspacing="5" border="0" width="100%">
						<tr>
	 						<td class="normalar" width="20%">
	 							Customer Name:
	 						</td>
	 						<td  width="20%">
	 						<form:input path="customerNameFilter" id="customerNameFilter" htmlEscape="false" autocomplete="off"  size="34" maxlength="34" cssClass="textDecorate"  onkeyup="displayList(event,this)" onblur="hide();"  />																	
								<div id ="autocompletedata" style="DISPLAY: none" align="center" />
								<INPUT type="text" name="ac">
	 						</td> 
	 						<td class="normalal">
								<input src="/SPRING_WEB/images/buttons/searchNew.gif" type="image" name="showCustButton" alt="Display" hspace="4" id="Display"/>
							</td>
						</tr>
									
						<tr>
							<td class="dottedLine" colspan="3"></td>
						</tr>
						<tr>
							<td colspan="3">
								<img src="/SPRING_WEB/images/buttons/printNew.gif" border="0" hspace="4" id="Print" alt="Print"/>							
							</td>
						</tr>						  
						<tr>
							<td class="dottedLine" colspan="3"></td> 
						</tr>					
					</table>    
							<table cellpadding="1" cellspacing="2" border="0" summary="This table displays a filtered list of customers in alphabetical order with columns for the organisation type, BIB Identifier and status" width="100%">
								<tr>
									<th class="newTable" id="customerName" abbr="Customer Name">
										Customer Name: 
									</th>
									<th class="newTable" id="orgType" abbr="Organisation Type">
										Customer ID:  
										<!-- New -->
										&nbsp;
										<a href ="CustomerListViewDisplay.do?showSort=true&sort=0&sea=${customerNameFilter}" class="newTable">
											<img src="/SPRING_WEB/images/buttons/sort-down.gif" border="0"  />
										</a>&nbsp;
										<a href ="CustomerListViewDisplay.do?showSort=true&sort=1&sea=${customerNameFilter}" class="newTable">
											<img src="/SPRING_WEB/images/buttons/sort-up.gif" border="0"  />
										</a>
										<!-- New -->
									</th>									
									<th class="newTable" id="bibidentifier" abbr="BIB Identifier">
										City:
									</th>
									
									<th class="newTable" id="status" abbr="Status">
										Country:
									</th>
								</tr> 
	 								<div id="CustomerList"> 
										<%@ include file="/jsp/CustomerListResults.jsp" %>										
									</div>
							</table>
						</form:form>
						</td>
					</tr>
				</table>
	
</body>
