package com.ticket.pojo;

import com.google.gson.Gson;

public class SeatsPojo
{
    private Seats seats;

    public Seats getSeats ()
    {
        return seats;
    }

    public void setSeats (Seats seats)
    {
        this.seats = seats;
    }

    @Override
    public String toString()
    {
        return "{ \"seats\" :{ "+seats+"}}";
    }
    public static void main(String[] args) {
    	String json="{ \"seats\": { \"B\": [1, 2], \"D\": [ 6, 7] ,\"A\": []} }";
    	Gson gson=new Gson();
    	SeatsPojo s=gson.fromJson(json, SeatsPojo.class);
    	System.out.println(s);
    }
}