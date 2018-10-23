

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is main servlet that handles all request coming to this web services 
 */
@WebServlet("/*")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url=request.getRequestURL().toString().split("/");
		
		// Request will transfere to AddScreen Servlet for Parsing and storing json data into Mysql database
		
		if(url.length==4)
		{
			//response.getWriter().println("True");
			RequestDispatcher d=request.getRequestDispatcher("/Add");
			d.forward(request, response);
		}
		
		// Request will transfere to the AvailablityController i.e controller for API 3 and 4
		else if(url.length==6) {
			String service=url[5];
			String screen=url[4];
			if(service.equals("seats")) {
				request.setAttribute("screen", screen);
				RequestDispatcher d=request.getRequestDispatcher("/seats");
				d.forward(request, response);
			}
			
			// Request will transfere to the Reserve servlet  i.e controller for API 2
			else if(service.equals("reserve")) {
				request.setAttribute("screen", screen);
				RequestDispatcher d=request.getRequestDispatcher("/reserve");
				d.forward(request, response);
			}
		}	
	
		response.getWriter().println("{status: {\"Bad_Request\"}}");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
