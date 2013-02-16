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
    private double pos;
    public double Output;
    public CANJaguar(double p)
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
    public void setX(double output)
    {
    Double o = new Double(output);
    String logTime = o.toString();
    Output = o.doubleValue();
    Log.write("Jag power output configured to: " + logTime);
    }
    public double getX()
    {
        return Output;
    }
    public double getCurrent()
    {
        return 230;
        
    }
    public double getVoltage()
    {
        return 24;
    }
}
