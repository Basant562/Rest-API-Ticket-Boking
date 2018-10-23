

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ticket.pojo.SeatsPojo;

/**
 * Servlet implementation class Reserve
 */
@WebServlet("/reserve")
public class Reserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb=new StringBuilder();
		BufferedReader reader=request.getReader();
		String line;
		while((line=reader.readLine())!=null)
			sb.append(line);
		String json=sb.toString();
		Gson gson=new Gson();
		SeatsPojo seats=gson.fromJson(json, SeatsPojo.class);
		String screen=request.getAttribute("screen").toString();
		//response.getWriter().println(" "+screen);
		 ReserveService res=new  ReserveService();
		 res.setScreen(screen);
		 res.setS(seats);
		 if(res.service(response.getWriter()))
		 {
			 response.getWriter().println("{{status: done}}");
			 response.setStatus(200);// Respose status 200 ,a success status
		 }
		 else {
			 response.setStatus(202);//Respose status 202 ,unsuccess status
			 response.getWriter().println("{{status: not reserve}}");
		 }
	}

}
