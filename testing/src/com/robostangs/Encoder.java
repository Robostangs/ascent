/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

/**
 * 
 * @author Silvio and Haley
*/
public class Encoder {
    //TODO: Log past values
    
    //Initializing Variable Distance
    double distance = 6.7; //Random number for testing purposes
    double distancePerPulse = 0;
    static int pulses = 4; //Random number for testing purposes
        
    public Encoder (int left, int right){
        
    }
    
    public double getDistance(){
        //TODO: Change the pulses variable to update realisticly
        //TODO: Log.write should put the left/right encoder was read and include date and time and distance where it was returned
        distance = distancePerPulse * pulses;
        return distance;
    }
    
    public void reset(){
        pulses = 0;
    }
    
    public void setDistancePerPulse(double input){
        distancePerPulse = input;
    }

}
