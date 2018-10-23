package com.ticket.pojo;
import java.util.*;


public class Seats
{
    private ArrayList<Integer> D=new ArrayList<Integer>();

    private ArrayList<Integer> A=new ArrayList<Integer>();

    private ArrayList<Integer> B=new ArrayList<Integer>();

    public ArrayList<Integer> getD ()
    {
        return D;
    }

    public void setD (ArrayList<Integer> D)
    {
        this.D = D;
    }

    public ArrayList<Integer> getA ()
    {
        return A;
    }

    public void setA (ArrayList<Integer> A)
    {
        this.A = A;
    }

    public ArrayList<Integer> getB ()
    {
        return B;
    }

    public void setB (ArrayList<Integer> B)
    {
        this.B = B;
    }

    @Override
    public String toString()
    {
        return "\"D\" : "+D+", \"A\": "+A+", \"B\" : "+B+"";
    }

}