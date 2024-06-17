<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<title>Searching The Patient</title>
</head>
<body class="body">

			<div class="blue-title-bar">
        <h1><font color="black">Search The Patient Information</font></h1>        
    	</div>

			<form   action="SearchServlet1" method="post">
    		<fieldset>
    		
    			<h1><font color="cyan">Enter The PatientId As Per Mentioned In The Given Form:--</font></h1>
    				
    			<div class="centered-button">
							<input type="number" id="searchQuery" name="query" placeholder="Enter search term">
							<input type="submit" value="submit">
				</div>
				
				
		</fieldset>		
		</form>		
				
</body>
</html>