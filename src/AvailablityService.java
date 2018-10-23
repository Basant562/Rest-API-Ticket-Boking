
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ticket.pojo.Seats;

public class AvailablityService {
	
	private String Screen;

	public String getScreen() {
		return Screen;
	}

	public void setScreen(String screen) {
		Screen = screen;
	}
	
	
	public Seats getAvailableSeats(Connection con)throws Exception {
				
			Seats seats=new Seats();
			seats.setA(getRow(con,"A"));
			seats.setB(getRow(con,"B"));
			seats.setD(getRow(con,"D"));
			
			return seats;
	}
	
	private ArrayList<Integer> getRow(Connection con,String rowName)throws Exception {
		PreparedStatement pt=con.prepareStatement("SELECT seats FROM rows WHERE screen=? AND rname=?");
		pt.setString(1, Screen);
		pt.setString(2, rowName);
		int totalSeats=-1;
		ResultSet rs=pt.executeQuery();
		if(rs.next())
			totalSeats=rs.getInt(1);
		
		ArrayList<Integer> seats=new ArrayList<Integer>();
		for(int i=0;i<=totalSeats;i++)
			seats.add(i);
		//Removing reserved
		PreparedStatement pt1=con.prepareStatement("SELECT seats FROM reserve WHERE screen=? AND rname=?");
		pt1.setString(1, Screen);
		pt1.setString(2, rowName);
		ResultSet rs1=pt.executeQuery();
		while(rs1.next()) {
			seats.remove(new Integer(rs1.getInt(1)));
		}
		//Removing asile
		PreparedStatement pt3=con.prepareStatement("SELECT seat FROM asile WHERE screen=? AND rname=?");
		pt3.setString(1, Screen);
		pt3.setString(2, rowName);
		ResultSet rs3=pt.executeQuery();
		while(rs3.next()) {
			seats.remove(new Integer(rs3.getInt(1)));
		}
		
		return seats;
		
	}

}
