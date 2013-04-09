<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="counter" value="0" />
<c:forEach items="${viewCustomerForm.customerList}" var="CustomerListForm">
		<c:set var="custName" value="${CustomerListForm.customerName}" />
		<c:set var="customericebid" value="${CustomerListForm.custId}" />
		<c:url var="custDetails" value="CustomerListViewDisplay.do?custDetails=true&customericebid=${customericebid}"/>
		<c:choose>
			<c:when test="${counter%2==0}">    
				<c:set var="css" value="rowOdd" />
			</c:when>
			<c:otherwise>    
				<c:set var="css" value="rowEven" />
			</c:otherwise>
		</c:choose>	
	<tr> 

		<td width="40%" class="${css}">
		<a href="${custDetails}" class="${css}">${custName}</a>
		</td>  
		<td width="15%" class="${css}">
			${customericebid}
			&nbsp;
		</td>
		<td width="15%" class="${css}">
			${CustomerListForm.city}
			&nbsp;
		</td>
		<td width="20%" class="${css}">
		${CustomerListForm.country}
			&nbsp;
		</td>
	</tr>
	<c:set var="counter" value="${counter+1 }" />
</c:forEach>
