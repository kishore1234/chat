

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class chat
 */
@WebServlet("/chat")
public class chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("/chat.jsp");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException , IllegalStateException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.setContentType("text/plain");
      //  response.setCharacterEncoding("UTF-8");
		//response.getWriter().write("jeya");
		
		// response.setContentType("text/html;charset=UTF-8");
		
		try{
			PrintWriter out = response.getWriter();
			  // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
			   String DB_URL = "jdbc:mysql://localhost:3306/ChatDb";
			  // out.println("1");
			   //  Database credentials
			   String USER = "root";
	           String PASS = "root";
	           //out.println("2");
	         // Register JDBC driver
	           Class.forName("com.mysql.jdbc.Driver");  
	           //out.println("3");
	         // Open a connection
	         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         //out.println("4");
	         // Execute SQL query
	         Statement stmt = conn.createStatement();
	         //out.println("5");
	         String sql;
	         sql = "SELECT records FROM records_db WHERE records LIKE '%"+ request.getParameter("data") +"%'";
	         ResultSet rs = stmt.executeQuery(sql);
	         //out.println("6");
	         //out.println(sql);
	        // out.println("dsdsd");
	         if(rs.next()){
		        	 out.println(rs.getString("records"));
		     }
	         else{
	        	 out.println("Please call the customer care Executive using this number 404-345-1234");
	         }
	         out.close();
		}catch(Exception ex){
			//ResultSet rs = stmt.executeQuery(sql);
	         PrintWriter out = response.getWriter();
	         //out.println(sql);
	         out.println("Error"+ex);
	         out.close();
		}
	}

}
