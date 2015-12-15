<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%-- <jsp:useBean id="entryheader" scope="request" type="com.ptas.common.controller.EntryHeaderController" /> --%>

<%-- <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> --%>
	
<c:url value="/admin/entryheader/edit.html" var="action" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new organization</title>
</head>
<body>
	<form:form method="POST" commandName="ehEdit" action="${action}">
		<form:hidden  path="id" />
		<table>
			<tbody>
				<tr>
					<th>ID</th>
					<td><form:input path="id" /></td>
				</tr>
				<tr>
					<th>Barcode</th>
					<td><form:input path="barcode" /></td>
				</tr>
				<tr>
				
					<th>MFG</th>
					<td><form:input path="manufacturer" /></td>
				</tr>
				<tr>
					<th>Model</th>
					<td><form:input path="model" /></td>
				</tr>
				<tr>
					<th>ORG_ID</th>
					<td><form:input path="orgId" /></td>
				</tr>
				<tr>
					<th>Series</th>
					<td><form:input path="series" /></td>
				</tr>
				<tr>
					<th>Version:</th>
					<td><form:input path="version" /></td>
				</tr>
				<tr>
					<th>VIN No:</th>
					<td><form:input path="vin" /></td>
				</tr>
				<tr>
					<th>Year:</th>
					<td><form:input path="year" /></td>
				</tr>
				
			</tbody>
		</table>
		<input type="submit" value="Update EntryHeader" />
	</form:form>
</body>
</html>