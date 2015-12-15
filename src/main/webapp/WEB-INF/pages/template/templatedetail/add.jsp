<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Template Detail</title>
</head>
<body>
<form:form method="POST" commandName="addtempdet" action="${action}">
	<table>
		<tbody>		
			<tr>
				<td>
				Template :
				</td>
				<td>
				<select name="test">
				    <c:forEach items="${addtempdet}" var="obj">
				        <option value="checkItem">"checkItem"</option>
				    </c:forEach>
				</select>
				</td>
			</tr>				
			
			<tr>			
				<td>
					<select name="profileId">
						<option value="">-- Select ID from List --</option>
							<c:forEach var="postProfile" items="${objListTemplateHeaders}">
								<option value='<c:out value="${postProfile.recordID}"/>'>
							<c:out value="${postProfile.version}" />
						</option>
						</c:forEach>
					</select>	
				</td>						
			</tr>			
				
			<tr>
				<td>Pic :</td>
				<td><form:input path="allowPic" /></td>
			</tr>
		
			<tr>
				<td>Video :</td>
				<td><form:input path="allowVideo" /></td>
			</tr>			
			<tr>
				<td>Item :</td>
				<td><form:input path="checkItem" /></td>
			</tr>			
			<tr>
				<td>Comment :</td>
				<td><form:input path="comment" /></td>
			</tr>					
			<tr>
				<td>TemplateID:</td>				
				<td><form:input path="templateID" /></td>
			</tr>			
			<tr>
				<td>Type :</td>
				<td><form:input path="type" /></td>
			</tr>	
			
			<tr>
				<td>Value Options:</td>
				<td><form:input path="valueOption" /></td>
			</tr>	
			<tr>
				<td>Value Types:</td>
				<td><form:input path="valueType" /></td>
			</tr>					
		</tbody>
	</table>
	<input type="submit" value="Create Template"/>
</form:form>
</body>
</html>