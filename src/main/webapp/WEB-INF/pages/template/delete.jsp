<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <jsp:useBean id="organization" scope="request"
	type="com.ptas.common.controller.OrganizationController" /> --%>
<%-- <%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%> --%>
<c:url value="/admin/template/delete.html" var="action" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Template Header</title>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.js"></script>
<script>
$(document).ready(function(e) {
	$(document).on('click','#deleteTemplate',function() {
		if(confirm("confirm deletion?")) {
			$("#templatemaster").submit();
		}
	});
});
</script>
</head>
<body>
	<form:form method="POST" commandName="templatemaster" action="${action}">
<form:hidden  path="mrecordID" />
<form:hidden path="org_ID" />
	<table>
		<tbody>
		<tr>
				<td>Template Name:</td>				
				<td><form:input path="temp_name" /></td>
			</tr>			
			<tr>
				<td>Template Type:</td>
				<td><form:input path="temp_type" /></td>
			</tr>
			<tr>
				<td>Template Number:</td>
				<td><form:input path="templateNumber" /></td>
			</tr>
	
		<tr>
		<td>Version:</td>
				<td><form:input path="version" /></td>
		</tr>		
			<tr>
				<td>Manufacturer:</td>
				<td><form:input path="manufacturer" /></td>
			</tr>			
			<tr>
				<td>Category:</td>
				<td><form:input path="category" /></td>
			</tr>
			
			<tr>
				<td>Model:</td>
				<td><form:input path="model" /></td>
			</tr>
			<tr>
				<td>Status:</td>
				<td><form:input path="status" /></td>
			</tr>
			
			<tr>
				<td>Year:</td>				
				<td><form:input path="year" /></td>
			</tr>			
			
		</tbody>
	</table>
		<input type="button" id="deleteTemplate" value="Delete Template Header" />
	</form:form>
</body>
</html>