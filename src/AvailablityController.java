

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticket.pojo.AvailableSeats;
import com.ticket.pojo.PositionService;
import com.ticket.pojo.SeatsPojo;

/**
 * Servlet implementation class AvailablityController
 */
@WebServlet("/seats")
public class AvailablityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvailablityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String us=request.getParameter("status");
	//	response.getWriter().println("Request comes"+us);
		if(us!=null) {
			
			//API 3 Logic with Model(For business logic ) as AvailablityService and pojo as SeatsPojo
			
			if(us.equals("unreserved")) {
				String screen=request.getAttribute("screen").toString();
				//response.getWriter().println(screen);
				AvailablityService serve=new AvailablityService();
				serve.setScreen(screen);
				SeatsPojo seat=new SeatsPojo();
				Connection con=MyConnection.getConnection();
				seat.setSeats(serve.getAvailableSeats(con));
				response.setContentType("application/json");
				PrintWriter writer=response.getWriter();
				writer.write(seat.toString());
				con.close();
				return ;
			}
		}
		else {
			
			//API 4 Logic with Model(For logical task ) as PositionService and pojo as AvailableSeats 
			String screen=request.getAttribute("screen").toString();
			int numSeats=Integer.parseInt(request.getParameter("numSeats"));
			String choice=request.getParameter("choice");
			int choiceSeat=choice.charAt(1)-48;
			String choiceRow=""+choice.charAt(0);
			PositionService ab=new PositionService();
			ab.setNumberSeats(choiceSeat);
			ab.setRow(choiceRow);
			ab.setScreen(screen);
			ab.setTotal(numSeats);
			Connection con=MyConnection.getConnection();
			ArrayList<Integer> seats=ab.getAtPoisition(con);
			if(seats==null) {
				//response.setStatus(response.SC_BAD_REQUEST);
				response.getWriter().println("{Status : { Not Available }}");
				return;
			}
			AvailableSeats av=new AvailableSeats();
			av.setRow(choiceRow);
			av.setSeats(seats);
			PrintWriter writer=response.getWriter();
			writer.write(av.toString());
			con.close();
			return ;
		}
		}
		catch(Exception e) {response.getWriter().println(e);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
