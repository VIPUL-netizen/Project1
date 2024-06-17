import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/PatientServlet1")
public class PatientServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter Out = response.getWriter();
        int regNo;
        Connection con=null;
        PreparedStatement Ps=null;
        Statement St=null ;
        // Server-side validation starts here
        try {
            // Example of getting a parameter and checking if it's not null or empty
            String regNoParam = request.getParameter("Reg_No");
            if (regNoParam == null || regNoParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Registration number is required.");
                return; // Exit the method early if validation fails
            }
             regNo = Integer.parseInt(regNoParam);
            System.out.println("Registration Number Of Patient Is" + regNo);

            // If the registration number is valid, proceed with the rest of the servlet logic
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid registration number.");
            return; // Exit the method early if validation fails
        }
        // End of server-side validation

        // The rest of your servlet code follows
        String First_Name = request.getParameter("firstName");
        System.out.println("Your First Name Is" + First_Name);

        String Middle_Name = request.getParameter("middleName");
        System.out.println("Your Middle Name Is" + Middle_Name);

        String Last_Name = request.getParameter("lastName");
        System.out.println("Your Last Name Is" + Last_Name);

        String Birthdate = request.getParameter("Birth-Date");
        System.out.println("Your Birth Date Is" + Birthdate);

        String DateAndTime = request.getParameter("Date-time");

        // Parse the date and time string using LocalDateTime and DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(DateAndTime, formatter);

        // Convert LocalDateTime to java.sql.Timestamp
        Timestamp timestamp = Timestamp.valueOf(dateTime);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vipul", "root", "root");
            System.out.println("Connection Is Established");

             Ps = con.prepareStatement("INSERT INTO patient_info VALUES (?,?,?,?,?,?)");
            Ps.setInt(1, regNo);
            Ps.setString(2, First_Name);
            Ps.setString(3, Middle_Name);
            Ps.setString(4, Last_Name);
            Ps.setDate(5, java.sql.Date.valueOf(Birthdate));
            Ps.setTimestamp(6, timestamp);

            Ps.executeUpdate();

              St = con.createStatement();
            ResultSet Rs = St.executeQuery("SELECT * FROM patient_info");
            if (Rs.next()==true) {
                
                  System.out.println(Rs.getInt(1) + "... " + Rs.getString(2) + "... " + Rs.getString(3) + "... " + Rs.getString(4) + ".... " + Rs.getDate(5) + "... " + Rs.getTimestamp(6));
                 // String s=Rs.getInt(1)+Rs.getString(2) + ".." + Rs.getString(3) + "... " + Rs.getString(4) + ".... " + Rs.getDate(5) + "... " + Rs.getTimestamp(6);
                 
                  //----setting The Attribute With The Help of request Object
                  request.setAttribute("Result", "Patient Information Inserted Into Database Successfully");
            }
            else {
            	
            	   //----setting The Attribute With The Help of request Object
                request.setAttribute("Result", "Not Inserted Properly");
            }
            
            //---Traversing Result From 1 jsp Page to another jsp Page----------------------//
            
            request.getRequestDispatcher("ResultSubmitForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
