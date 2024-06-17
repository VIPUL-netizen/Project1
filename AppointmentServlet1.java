

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppointmentServlet1
 */
@WebServlet("/AppointmentServlet1")
public class AppointmentServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/hrml");
				
				PrintWriter out=response.getWriter();
				
					int  appoNo;
			        Connection con=null;
			        PreparedStatement Ps=null;
			        Statement St=null ;
			        ResultSet Rs=null;
			    //-------Server Validation Start here-------------------///
			        			        
			         try {
			        	 	String appointNumParam=request.getParameter("Number");
			        	 				if(appointNumParam==null || appointNumParam.isEmpty()) {
			        	 					 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Registration number is required.");
			        	 	                return; // Exit the method early if validation fails
			        	 				}
			        	 appoNo=Integer.parseInt(appointNumParam);   //----Convert String To THe Integer-------------------//
			        
			        	 System.out.println("Your Appoinment Numbr Is"+appoNo);
			         }catch(NumberFormatException e) {
			        	// Handle the case where the input is not a valid integer
			             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Appointment number.");
			             return; // Exit the method early if validation fails
			        	 
			         }
			        
			        String Date=request.getParameter("Date");
			         
			        System.out.println("Your Date Is"+Date);
			         
			         String Visit=request.getParameter("visit1");
			         
			         System.out.println("Your Visit Is"+Visit);
			         
			         //----------Connecting to the Database----------------------//      `
			         
			         try {
			         	//1-------Connecting The Driver--------------//
			        	 Class.forName("com.mysql.cj.jdbc.Driver");
			         	
			        	 //2-------Establishing THe Connection------------//
			        	 
			        	  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vipul","root","root");
			        	 
			        	  System.out.println("Connrction Is Establish");
			        	  
			        	  
			        	  Ps=con.prepareStatement("INSERT INTO Appointment values(?,?,?)");     //----calling the preparedStatement function with the help of con object  
			        	  
			        	  //---------Setting The value in Table-------------//
			        	  Ps.setInt(1, appoNo);
			        	  
			        	  Ps.setString(2, Date);
			        	  
			        	  Ps.setString(3, Visit);
			        	  
			        	  Ps.executeUpdate();
			        	  
			        	  //-----Display The Record Which Is Inserted-------------//
			        	  
			        	  St=con.createStatement();
			        	  
			        	  Rs=St.executeQuery("select * from Appointment");
			        	  	
			        	  			if(Rs.next()==true ) {
			        	  				
			        	  				System.out.println(Rs.getInt(1)+"........."+Rs.getString(2)+"........"+Rs.getString(3));	
			        	  				
			        	  				String s=Rs.getInt(1)+"........."+Rs.getString(2)+"........"+Rs.getString(3);
			        	  				
			        	  				//-------------Setting The Attribute --------------//
			        	  				request.setAttribute("Result", "<h1><b><font color='cyan'>Your Appointmant Is Confirm Now!</font></b></h1>");
			        	  			}
			        	  			else  {
			        	  				
			        	  				request.setAttribute("Result", "<h1><b><font color='cyan'>Your Appointmant Is  NOT Confirm Now!</font></b></h1>");
			        	  			}
			        	  			
			         		//-------------MOVING FORWARD TO THE NEXT JSP PAGE------------------------------//
			        	  			
			        	  	request.getRequestDispatcher("ResultAppointment.jsp").forward(request, response);		
			        	  			
			        }catch(Exception e) {
			        	
			         			e.printStackTrace();
			        }finally {
			            if (con!= null) {
			                try {
			                    con.close();
			                } catch (SQLException e) {
			                    e.printStackTrace();
			                }
			            }
			            if (Ps!= null) {
			                try {
			                    Ps.close();
			                } catch (SQLException e) {
			                    e.printStackTrace();
			                }
			            }
			            if (St!= null) {
			                try {
			                    St.close();
			                } catch (SQLException e) {
			                    e.printStackTrace();
			                }
			            }
			        }//Finally
			         
			         
			         
			         
		
	}

}
