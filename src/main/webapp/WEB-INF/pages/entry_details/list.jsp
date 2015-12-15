<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <jsp:useBean id="organization" scope="request"
	type="com.ptas.common.controller.Entry_detailsController" /> --%>


<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


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
				<th>RecordID</th>
				<th>HeaderID</th>
				<th>CheckItem</th>
				<th>Type</th>
				<th>Value</th>
				<th>Comment:</th>
				<th>Pic</th>
				<th>valuetype </th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="org" items="${organizations}">
				<tr>
					<td>${org.eRecordID}</td>
					<td>${org.eHeaderID}</td>
					<td>${org.eCheckItem}</td>
					<td>${org.eType}</td>
					<td>${org.eValue}</td>
					<td>${org.eComment}</td>
					<td>${org.ePic}</td>
					<td>${org.evaluetype}</td>
					<td  style='text-decoration:underline;'>
						<a href="edit/${org.eRecordID}">edit</a><br />
						<a href="delete/${org.eRecordID}">delete</a><br />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>