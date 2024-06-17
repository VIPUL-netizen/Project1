<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles.css">
<title>Patient INFO-------</title>
<script>
function performSearch() {
    var searchQuery = document.getElementById('searchQuery').value;
    if (!document.getElementById('date_time').value) {
        alert("Please select a date/time.");
        return false; // Prevent form submission
    }
    document.getElementById('searchForm').action = 'SearchForm.jsp?query=' + encodeURIComponent(searchQuery);
    document.getElementById('searchForm').submit();
}

function performDelete(){
    var deleteQuery = document.getElementById('deleteQuery').value;
    if (!document.getElementById('date_time').value) {
        alert("Please select a date/time.");
        return false; // Prevent form submission
    }
    document.getElementById('DeleteForm').action = 'DeleteForm.jsp?query=' + encodeURIComponent(deleteQuery);
    document.getElementById('DeleteForm').submit();
}
</script>

</head>
<body class="body">


    <div class="blue-title-bar">
        <h1>Patient Information</h1>        
    </div>
    
  <form  id="DeleteForm" id="searchForm" action="PatientServlet1" method="post">
    <fieldset>
    <legend><b><h1><font color="cyan">ENTER THE PAITIENT INFORMATION:</font></b></h1></legend>
    <div class="input-row">
    
    		 <div>
            <br><label for="Ragister_no" class="required-field"><b><font color="cyan">Enter The Register No:<font color="cyan"></b></label><br>
            <input type="number" id="Reg_No" name="Reg_No">
            <!-- Insert a line break here to position Birth-Date below First Name -->
            <br>
        </div>
    		
        <div>
            <br><label for="firstName" class="required-field"><b><font color="cyan">First Name:<font color="cyan"></b></label><br>
            <input type="text" id="firstName" name="firstName">
            <!-- Insert a line break here to position Birth-Date below First Name -->
            <br>
        </div>
        
        <div>
           <br> <label for="middleName" class="required-field"><b><font color="cyan">Middle Name:<font color="cyan"></b></label><br>
            <input type="text" id="middleName" name="middleName">
        </div>
        
        <div>
           <br> <label for="lastName" class="required-field"><b><font color="cyan">Last Name:<font color="cyan"></b></label><br>
            <input type="text" id="lastName" name="lastName">
        </div>
    </div>
    
    <div class="input-row_1">
       
       <div><br><br>
        <br> <label for="Birth-Date" class="required-field"><b><font color="cyan">Birth-Date:<font color="cyan"></b></label><br>
         <input type="date" id="date" name="Birth-Date">
       </div>
        
       <div>
	 <br><label for="Datetime_Phone" class="required-field"><b><font color="cyan">DateTime /Phone:<font color="cyan"></b></label>
       <input type="datetime-local" id="date_time" name="Date-time">
       </div>
             
                     
    </div>
    
    <div class="input-row_3">
     			<div><br>
    					<br><p class="required-field"><b><font color="cyan">Is This Your First Visit to Our Offices?<font color="cyan"></b></p><br>
    					<input type="radio" id="YES" name="Choose" value="YES">
    	 				<label for="YES">YES</label>
    					<input type="radio" id="NO" name="Choose" value="NO">
    					 <label for="NO">NO</label> 
    			</div><br>       
   </div>
             
             
             <div class="centered-button">
    							<input type="submit" value="Submit">
    							   							
                                 <input type="submit" value="Search" onclick="performSearch()" id="searchQuery">
                                 
                                 <input type="submit" value="Delete" onclick="performDelete()" id="deleteQuery">
                                 
						</div>
   				
						  
  </fieldset>  
              		

  </form>
 
    
     <div class="blue-title-bar">
        <h1><font color="black">Appointment Information</font></h1>        
    </div>
    <form action="AppointmentServlet1" method="post">
     <fieldset>
     <legend><b><h1><font color="cyan">ENTER THE APPOINTMENT INFORMATION:</font></b></h1></legend>
     
    <div class="input-row_2">
    
     <div>
        <label for="number" class="required-field"><b><font color="black">Appoint_no-:</font></b></label>
        <input type="number" id="number_1" name="Number">
    </div>
    
    <div>
        <label for="Date" class="required-field"><b><font color="black">Select The Date-:</font></b></label>
        <input type="date" id="date_1" name="Date">
    </div>
    
    
    
</div>

<div class="input-row_4">
     			<div><br>
    					<br><p class="required-field"><b><font color="cyan">Preferred Physician</font></b></p><br>
    					<input type="radio" id="Dr.Anderson" name="Choose" value="Dr.Anderson">
    	 				<label for="Dr.Anderson">Dr.Anderson</label>
    	 				
    					<input type="radio" id="Dr.Jones" name="Choose" value="Dr.Jones">
    					 <label for="Dr.Jones">Dr.Jones</label>
    					 
    					<input type="radio" id="Dr.Patel" name="Choose" value="Dr.Patel">
    					 <label for="Dr.Patel">Dr.Patel</label>
    					 
    					<input type="radio" id="Dr.Kulkarni" name="Choose" value="Dr.Kulkarni">
    					 <label for="Dr.Kulkarni">Dr.Kulkarni</label>
    					 
    					  
    			</div><br>       
    			
    			
    			<br><p class="required-field"><b><font color="cyan">Please Describe the reasons for this Visit Upto to 40 Characters:--</font></b></p><br>
    			
    			<textarea id="t-area" name="visit1" rows="6" cols="50">
     				
				</textarea>
   </div>
 
 						<div class="centered-button">
    							<input type="submit" value="Submit">
						</div>
						
			<div id="chatbot" class="chatbot">
    				<div class="bubble">
        						<span class="chatbot-text"><font color="black">Hi How can I assist you today?</font></span>
    				</div>
   			 		<div class="input-area">
       					 <input type="text" id="userInput" placeholder="Type your message...">
        					<button onclick="sendMessage()">Send</button>
    				</div>
		</div>
						
  </fieldset>
  
   				
    </form>
    
</body>
</html>
