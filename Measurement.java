package BPMS;

import java.io.Serializable;
import java.sql.Date;

public class Measurement implements Serializable 
{
    private static final long serialVersionUID = 1L;

    private int userID;
    public java.util.Date date;
    public int systolicBP;
    public int diastolicBP;
    public int heartRate;

    
    public Measurement(int uid, java.util.Date d, int s, int dbp, int hr) 
    {
        this.userID = uid;
        this.date = d;
        this.systolicBP = s;
        this.diastolicBP = dbp;
        this.heartRate = hr;
    }

    public Measurement() {}

    public void print() 
    {
        System.out.println("UserID:" + this.userID + "\tDate:" + this.date.toString() + "\tsystolicBP:" + this.systolicBP + "\tdiastolicBP:" + this.diastolicBP + "\thartRate:" + this.heartRate);
    }

        
    public String toString() 
    {
        return this.userID + "\t\t|" + this.date.toString() + "\t|" + Integer.toString(this.systolicBP) + "\t\t|" + Integer.toString(this.diastolicBP) + "\t\t|" + Integer.toString(this.heartRate) + "\t|\n";
    }

    public int getUserId() 
    {
        return this.userID;
    }
}
