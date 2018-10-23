package com.ticket.pojo;
import java.util.*;
public class AvailableSeats {

	private String row;
	private ArrayList<Integer> seats;
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public ArrayList<Integer> getSeats() {
		return seats;
	}
	public void setSeats(ArrayList<Integer> seats) {
		this.seats = seats;
	}
	
	public String toString() {
		return "{ \"availableSeats\" :{ \""+row+"\":"+seats+" }}";
	}
	public static void main(String[] args) {
		AvailableSeats a=new AvailableSeats();
		ArrayList<Integer> al=new ArrayList<Integer>();
		al.add(3);
		al.add(4);
		al.add(5);
		a.setRow("A");
		a.setSeats(al);
		System.out.println(a);
	}
}
