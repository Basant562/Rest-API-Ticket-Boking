

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ticket.pojo.*;
/**
 * Servlet implementation class AddScreen
 */
@WebServlet("/Add")
public class AddScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddScreen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//String json= request.getParameter("example");
		//	String json=request.getAttribute("example").toString();
			StringBuilder sb=new StringBuilder();
		BufferedReader reader=request.getReader();
		String line;
		while((line=reader.readLine())!=null)
			sb.append(line);
		String json=sb.toString();
		reader.close();
		Gson g=new Gson();
		Screen s=g.fromJson(json, Screen.class);
		ScreenSevice service=new ScreenSevice();
		service.setS(s);
		service.saveScreen();
		response.getWriter().print("{Status:\"Success\"}");
		}catch(Exception e) {response.getWriter().print("{Status:\"Fail\"}");
			//response.getWriter().println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
