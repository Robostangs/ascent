/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

/**
 *
 * @author Kevin
 */
public class Gyro 
{ 
    private double position;
    private double Angle;
    
    public void getAngle(Double position)
    {
        Angle = position.doubleValue();
        String logEntry = position.toString();
        Log.write("Gyro angle is configured to: " + logEntry);
    }
    
    public double getAngle()
    {
        return Angle;
    }
}
