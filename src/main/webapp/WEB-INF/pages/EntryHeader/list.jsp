<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<%-- <jsp:useBean id="list" scope="request" type="com.ptas.common.controller.EntryHeaderController" /> --%>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Barcode</th>
				<th>MFG</th>
				<th>Model</th>
				<th>ORG_ID</th>
				<th>Series</th>
				<th>Version:</th>
				<th>VIN No:</th>
				<th>Year:</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="org" items="${entryHeader}">
				<tr>
					<td>${org.id}</td>
					<td>${org.barcode}</td>
					<td>${org.manufacturer}</td>
					<td>${org.model}</td>
					<td>${org.orgId}</td>
					<td>${org.series}</td>
					<td>${org.version}</td>
					<td>${org.vin}</td>
					<td>${org.year}</td>
					<td  style='text-decoration:underline;'>
						<a href="edit/${org.id}">edit</a><br />
						<a href="delete/${org.id}">delete</a><br />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>