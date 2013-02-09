/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

/**
 *
 * @author VikasV
 */
public class CANJaguar {
    private int pos;
    public double Output;
    public CANJaguar(int p)
    {
        pos = p;
        Output = 0;
    }
    public void configFaultTime(Double faultTime)
    {
    String logTime = faultTime.toString();
    Log.write("Fault time configured to: ");
    Log.write(logTime);
    }
    public void setX(Double output)
    {
    Output = output.doubleValue();
    String logTime = output.toString();
    Log.write("Jag power output configured to: " + logTime);
    }
    public double getX()
    {
        return Output;
    }
    public 
}
