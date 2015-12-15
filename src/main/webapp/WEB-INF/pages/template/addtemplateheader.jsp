<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Template</title>
</head>
<body>
<form:form method="POST" commandName="addtemplateheader" action="${action}">
	<table>
		<tbody>
		<tr>
		<td>Version:</td>
				<td><form:input path="Version" /></td>
		</tr>
		
			<tr>
				<td>Org ID:</td>
				<td><form:input path="Org_ID" /></td>
			</tr>			
			<tr>
				<td>Manufacturer:</td>
				<td><form:input path="Manufacturer" /></td>
			</tr>			
			<tr>
				<td>Series:</td>
				<td><form:input path="Series" /></td>
			</tr>
			
			<tr>
				<td>Model:</td>
				<td><form:input path="Model" /></td>
			</tr>
			<tr>
				<td>Year:</td>				
				<td><form:input path="Year" /></td>
			</tr>			
			<tr>
				<td>Status:</td>
				<td><form:input path="Status" /></td>
			</tr>			
		</tbody>
	</table>
	<input type="submit" value="Create Template"/>
</form:form>
</body>
</html>