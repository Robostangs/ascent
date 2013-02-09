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
    public CANJaguar()
    {
        pos = 0;
    }
    public void configFaultTime(Double faultTime)
    {
    String logTime = faultTime.toString();
    Log.write("Fault time configured to: ");
    Log.write(logTime);
    }
    public void setX(Double output)
    {
    String logTime = output.toString();
    Log.write("Jag power output configured to: " + logTime);
    }
    public void 
    
}
