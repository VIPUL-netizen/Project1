<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<title>Insert title here</title>
</head>
<body class="body">
			
			<%
								
				String q=(String)request.getAttribute("Result");
			
			    out.println("<h1><font color=cyan>Your Result is</font></h1>"+q);
			
			%>
		



</body>
</html>