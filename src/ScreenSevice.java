import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.ticket.pojo.Row;
import com.ticket.pojo.Screen;

public class ScreenSevice {

	Screen s;
	

	public Screen getS() {
		return s;
	}

	public void setS(Screen s) {
		this.s = s;
	}
	public boolean saveScreen()throws Exception {
		Connection con=MyConnection.getConnection();
		PreparedStatement pt=con.prepareStatement("INSERT INTO screen VALUES(?,?)");
		pt.setString(1, s.getName());
		pt.setInt(2, s.getSeatInfo().getA().getNumberOfSeats()+s.getSeatInfo().getB().getNumberOfSeats()+s.getSeatInfo().getD().getNumberOfSeats());
		pt.executeUpdate();
		saveRows(con);
		saveAsile(con);
		con.close();
		return true; 
		
	}
	protected void saveRows(Connection con) throws Exception{
		
		PreparedStatement pt1=con.prepareStatement("INSERT INTO rows VALUES(?,?,?)");
		pt1.setString(1, s.getName());
		pt1.setString(2, "A");
		pt1.setInt(3, s.getSeatInfo().getA().getNumberOfSeats());
		
		PreparedStatement pt2=con.prepareStatement("INSERT INTO rows VALUES(?,?,?)");
		pt2.setString(1, s.getName());
		pt2.setString(2, "B");
		pt2.setInt(3, s.getSeatInfo().getB().getNumberOfSeats());
		
		PreparedStatement pt3=con.prepareStatement("INSERT INTO rows VALUES(?,?,?)");
		pt3.setString(1, s.getName());
		pt3.setString(2, "D");
		pt3.setInt(3, s.getSeatInfo().getD().getNumberOfSeats());
		
		pt1.executeUpdate();
		pt2.executeUpdate();
		pt3.executeUpdate();
		
	}
	
	
	protected void saveAsile(Connection con)throws Exception {
		
		Statement stmt=con.createStatement();
		ArrayList<String> queries=new ArrayList<String>();
		Row a=s.getSeatInfo().getA();
		Row b=s.getSeatInfo().getB();
		Row d=s.getSeatInfo().getD();
		
		for(int i=0;i<a.getAisleSeats().size();i++)
		{
			String q="INSERT INTO asile VALUES ('"+s.getName()+"', 'A', "+a.getAisleSeats().get(i)+")";
			queries.add(q);
		}
		
		for(int i=0;i<b.getAisleSeats().size();i++)
		{
			String q="INSERT INTO asile VALUES ('"+s.getName()+"', 'B', "+b.getAisleSeats().get(i)+")";
			queries.add(q);
		}
		
		for(int i=0;i<d.getAisleSeats().size();i++)
		{
			String q="INSERT INTO asile VALUES ('"+s.getName()+"', 'D', "+d.getAisleSeats().get(i)+")";
			queries.add(q);
		}
		
		for(String str:queries) {
			stmt.addBatch(str);
		}
		stmt.executeBatch();
	}
	
}
