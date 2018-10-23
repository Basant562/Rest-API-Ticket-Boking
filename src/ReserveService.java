import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;


import com.ticket.pojo.*;
public class ReserveService {
	
	private SeatsPojo seat;
	private String screen;

	public void setScreen(String screen) {
		this.screen=screen;
	}
	public String getScreen() {
		return screen;
	}
	public SeatsPojo getS() {
		return seat;
	}

	public void setS(SeatsPojo s) {
		this.seat = s;
	}
	
	public boolean service(PrintWriter p) {
		Connection con=null;
		try {
			con=MyConnection.getConnection();
			if(!isAvailable(con,"A",seat.getSeats().getA()) || !isAvailable(con,"B",seat.getSeats().getB()) || !isAvailable(con,"D",seat.getSeats().getD())){
				return false;
			}
			markReserved(con,"A",seat.getSeats().getA());
			markReserved(con,"B",seat.getSeats().getB());
			markReserved(con,"D",seat.getSeats().getD());
			
			return true;
			
		}catch(Exception e) {p.print(e);}
		finally {
			if(con!=null) {
				try{con.close();}catch(Exception e) {}
				
			}
		}
		return false;
		
	}
	private boolean isAvailable(Connection con,String rowName,ArrayList<Integer> seats) throws Exception{
		
		HashSet<Integer> set=getReserverdSeats(con,rowName);
		for(Integer s:seats)
			if(set.contains(s))
				return false;
		return true;
	}
	private void markReserved(Connection con,String rowName,ArrayList<Integer> seats) throws Exception  {
		
		Statement stmt=con.createStatement();
		ArrayList<String> queries=new ArrayList<String>();
		
		for(Integer s:seats) {
			String q="INSERT INTO reserve VALUES ('"+this.screen+"', '"+rowName+"', "+s+")";
			queries.add(q);
		}
		for(String q:queries) {
			
			stmt.addBatch(q);
		}
		stmt.executeBatch();
	}
	
	private HashSet<Integer> getReserverdSeats(Connection con,String rowName)throws Exception{
		
		HashSet<Integer> set=new HashSet<Integer>();
		
		java.sql.PreparedStatement pt=con.prepareStatement("SELECT seats FROM reserve WHERE screen=? AND rname=?");
		pt.setString(1, screen);
		pt.setString(2, rowName);
		ResultSet rs=pt.executeQuery();
		while(rs.next()) {
			set.add(rs.getInt(1));
		}
		return set;
		
		
	}

}
