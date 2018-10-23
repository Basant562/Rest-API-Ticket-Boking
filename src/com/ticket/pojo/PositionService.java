package com.ticket.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PositionService {
	private String row;
	private int numberSeats;
	private int total;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}



	private String screen;
	
	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public int getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}

	public ArrayList<Integer> getAtPoisition(Connection con)throws Exception{
		ArrayList<Integer> available=getRow(con,row);
		 ArrayList<Integer> result=new ArrayList<Integer>();
			
			int index=available.indexOf(numberSeats);
			int pre=numberSeats;
			
			for(int i=index-1;i>=0;i--)
			{
				
				if((pre-available.get(i))!=1)
				break;
				else{
					pre=available.get(i);
					result.add(available.get(i));
				}
				
				if((result.size())==total-1) {
					System.out.println(result.size());
					 result.add(numberSeats);
					 return result;
				}
			}
		//	result.clear();
			result.add(numberSeats);
			pre=numberSeats;
			for(int i=index+1;i<available.size();i++) {
				
				if(available.get(i)-pre!=1)
					return result;
				else {
					result.add(available.get(i));
					pre=available.get(i);
				}
				if(result.size()==total)
					return result;
			}
			return null;
		}
	
	
	
	private ArrayList<Integer> getRow(Connection con,String rowName)throws Exception {
		PreparedStatement pt=con.prepareStatement("SELECT seats FROM rows WHERE screen=? AND rname=?");
		pt.setString(1, screen);
		pt.setString(2, rowName);
		int totalSeats=0;
		ResultSet rs=pt.executeQuery();
		if(rs.next())
			totalSeats=rs.getInt(1);
		
		ArrayList<Integer> seats=new ArrayList<Integer>();
		for(int i=0;i<=totalSeats+1;i++)
			seats.add(i);
		//Removing reserved
		PreparedStatement pt1=con.prepareStatement("SELECT seats FROM reserve WHERE screen=? AND rname=?");
		pt1.setString(1, screen);
		pt1.setString(2, rowName);
		ResultSet rs1=pt.executeQuery();
		while(rs1.next()) {
			seats.remove(rs1.getInt(1));
		}
		//Removing asile
		PreparedStatement pt3=con.prepareStatement("SELECT seat FROM asile WHERE screen=? AND rname=?");
		pt3.setString(1, screen);
		pt3.setString(2, rowName);
		ResultSet rs3=pt.executeQuery();
		while(rs3.next()) {
			seats.remove(rs3.getInt(1));
		}
		
		return seats;
		
	}

	

}
