package com.ticket.pojo;

import java.util.ArrayList;

public class SeatInfo {
	 private Row A;

	 private Row B;

	 private Row D;
	 
	 public Row getD ()
	    {
	        return D;
	    }

	    public void setD (Row D)
	    {
	        this.D = D;
	    }

	    public Row getA ()
	    {
	        return A;
	    }

	    public void setA (Row A)
	    {
	        this.A = A;
	    }

	    public Row getB ()
	    {
	        return B;
	    }

	    public void setB (Row B)
	    {
	        this.B = B;
	    }

	    @Override
	    public String toString()
	    {
	        return "\"SeatInfo\" {\"D\" : "+D+", \"A\" : "+A+", \"B\" : "+B+"}";
	    }
	   
}
