<%@ page language="java"  contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact</title>
</head>
<body>
	<h1>Contact Info</h1>
	${successMsg} ${failMsg}
	<form:form action="saveContact" method="POST" modelAttribute="contact">
		<table>
			<tr>
				<td>Name : </td>
				<td><form:input path="contactName"/></td>
			</tr>
			<tr>
				<td>Email : </td>
				<td><form:input path="contactEmail"/></td>
			</tr>
			<tr>
				<td>Mobile : </td>
				<td><form:input path="contactNumber"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>