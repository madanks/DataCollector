<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <jsp:useBean id="organization" scope="request"
	type="com.ptas.common.controller.Entry_detailsController" /> --%>
<%-- <%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%> --%>
<c:url value="/admin/Entry_Details/delete.html" var="action" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Organization</title>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.js"></script>
<script>
$(document).ready(function(e) {
	$(document).on('click','#deletentryDetail',function() {
		
		if(confirm("Are you sure You want to delete this record???")) {
			$("#entry").submit();
			
			
		}
	});
});
</script>
</head>
<body>
	<form:form method="POST" commandName="entry" action="${action}">
		<form:hidden  path="eRecordID" />
		<table>
			<tbody>
				
				<tr>
					<td>HeaderID:</td>
					<td><form:input path="eHeaderID" /></td>
				</tr>
				<tr>
					<td>CheckItem:</td>
					<td><form:input path="eCheckItem" /></td>
				</tr>
				<tr>
					<td>Type:</td>
					<td><form:input path="eType" /></td>
				</tr>
				<tr>
					<td>Value:</td>
					<td><form:input path="eValue" /></td>
				</tr>
						<tr>
					<td>Comment:</td>
					<td><form:input path="eComment" /></td>
				</tr>
						<tr>
					<td>Pic:</td>
					<td><form:input path="ePic" /></td>
				</tr>
						<tr>
					<td>valuetype:</td>
					<td><form:input path="evaluetype" /></td>
				</tr>
				
			</tbody>
		</table>
		<input type="button" id="deletentryDetail" value="Delete Organization" />
	</form:form>
</body>
</html>