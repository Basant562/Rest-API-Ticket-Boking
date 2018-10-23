package com.ticket.pojo;
import java.util.*;

public class Row
{
private int numberOfSeats;

private ArrayList<Integer> aisleSeats;

public int getNumberOfSeats ()
{
return numberOfSeats;
}

public void setNumberOfSeats (int numberOfSeats)
{
this.numberOfSeats = numberOfSeats;
}

public ArrayList<Integer> getAisleSeats ()
{
return aisleSeats;
}

public void setAisleSeats (ArrayList<Integer> aisleSeats)
{
this.aisleSeats = aisleSeats;
}

@Override
public String toString()
{
return "{\"numberOfSeats\" : "+numberOfSeats+", \"aisleSeats\" : "+aisleSeats+"}";
}

public static void main(String[] args) {
	Row r=new Row();
	r.setNumberOfSeats(7);
	ArrayList<Integer> al=new ArrayList<Integer>();
	al.add(0);
	al.add(5);
	al.add(6);
	al.add(9);
	r.setAisleSeats(al);
	System.out.println(r);
}

}