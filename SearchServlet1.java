import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet1")
public class SearchServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = null;
        try {
            int no = Integer.parseInt(request.getParameter("query"));
            System.out.println("Searching for registration number: " + no);

            // Establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vipul", "root", "root");
            System.out.println("Connection Is Established");

            // Prepare the statement
            String selectSql = "SELECT * FROM patient_info WHERE Reg_no =?";
            PreparedStatement selectPs = con.prepareStatement(selectSql);
            selectPs.setInt(1, no); // Set the Reg_no parameter
            ResultSet rs = selectPs.executeQuery();
            
            if (rs.next()) {
                System.out.println("Registration number " + no + " is present.");
                String s = rs.getString(2) + ".." + rs.getString(3) + "... " + rs.getString(4) + ".... " + rs.getDate(5) + "... " + rs.getTimestamp(6);
                request.setAttribute("Result", "<h1><b><font color='cyan'>Your Data is Present In Our Server</font></b></h1>" + s);
            } else {
                System.out.println("Registration number " + no + " is NOT present.");
                request.setAttribute("Result", "<h1><b><font color='cyan'>Your Data is not present in database</font></b></h1>");
            }

            request.getRequestDispatcher("ResultSearchForm.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            out.println("<h1><b><font color='red'>Invalid Registration Number Format</font></b></h1>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
