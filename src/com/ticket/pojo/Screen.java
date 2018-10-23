package com.ticket.pojo;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Screen {
	
	private SeatInfo seatInfo;

	    private String name;

	    public SeatInfo getSeatInfo ()
	    {
	        return seatInfo;
	    }

	    public void setSeatInfo (SeatInfo seatInfo)
	    {
	        this.seatInfo = seatInfo;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    @Override
	    public String toString()
	    {
	        return " { \"name\" : \""+name+"\", \"seatInfo\" : "+seatInfo+"}";
	    }

	    public static void main(String[] args) {
	    /*	Row r=new Row();
	    	r.setNumberOfSeats(7);
	    	ArrayList<Integer> al=new ArrayList<Integer>();
	    	al.add(0);
	    	al.add(5);
	    	al.add(6);
	    	al.add(9);
	    	r.setAisleSeats(al);
	    	SeatInfo s=new SeatInfo();
	    	s.setA(r);
	    	s.setB(r);
	    	s.setD(r);
	    	Screen sc=new Screen();
	    	sc.setName("Inox");
	    	sc.setSeatInfo(s);
	    	System.out.println(sc);
	    	*/
	    	String json="{ \"name\":\"inox\", \"seatInfo\": { \"A\": { \"numberOfSeats\": 10, \"aisleSeats\": [0, 5 ,6, 9] }, \"B\": { \"numberOfSeats\": 15, \"aisleSeats\": [0, 5 ,6, 9] }, \"D\": { \"numberOfSeats\": 20, \"aisleSeats\": [0, 5 ,6, 9] } } }\r\n" + 
	    			"";
	    	Gson g=new Gson();
	    	Screen s=g.fromJson(json,Screen.class );
	    	System.out.println(s);
	    }
	    
}
