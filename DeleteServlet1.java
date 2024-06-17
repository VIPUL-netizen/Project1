

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class DeleteServlet1
 */
@WebServlet("/DeleteServlet1")
public class DeleteServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		      
					response.setContentType("text/html");  //---Setting The Content Type With The Help of Text/html
					PrintWriter out=response.getWriter();    //------Calling geWriter Function With the Help of  response Objecct
					//String s=request.getParameter(")
					int number=Integer.parseInt(request.getParameter("Delquery"));
		
					System.out.println("Your Number To Be Deleted Is"+number);
					
					//----------------CONNECTING TO THE MYSQL DATABASE--------------------------------//
					
					try {
							//1)-------------Establishing The Driver-------------------------------//
								Class.forName("com.mysql.cj.jdbc.Driver"); 
							//2)Establishing The Connection-------------------------------------//	
								
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vipul","root","root");
						
							System.out.println("Connection Is Establish");
							
							PreparedStatement Ps=con.prepareStatement("DELETE FROM patient_info WHERE Reg_no=?");
							
							Ps.setInt(1, number); //----Setting The Integer ----------------//
							
							Ps.executeUpdate();
								
							//-----Statement  Definition-----------------//
							
							Statement St=con.createStatement();    //-----Calling Crete Statement Function With The Help Of con Object
							
							ResultSet Rs=St.executeQuery("select * from patient_info");
							
										if(Rs.next()==true) {
										
										 System.out.println(Rs.getString(2) + "... " + Rs.getString(3) + "... " + Rs.getString(4) + "... " + Rs.getDate(5) + "... " + Rs.getTimestamp(6));

										   // String s = Rs.getString(2) + ".." + Rs.getString(3) + "... " + Rs.getString(4) + ".... " + Rs.getDate(5) + "... " + Rs.getTimestamp(6);
											
											request.setAttribute("Result", "Deleted The Record Scuuessfully!!");
											
											}
										else {
											
											request.setAttribute("Result", "Deletion Is Not Possible!");
										}
									
										//---Dispatch The Request from One Jsp To Another Jsp------------
										request.getRequestDispatcher("ResultDeleteForm.jsp").forward(request, response);	
									
							
					}catch(Exception e) {
						
						e.printStackTrace();
						
					}
					
					
					
					
					
	}

}
