/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Counts frisbees, tracks location in robot
 * maintainer: Michael
 */
public class FrisbeeTracker {
    private static FrisbeeTracker instance = null;
    private static DigitalInput ingestSwitch = new DigitalInput(Constants.INGEST_SWITCH_POS);
    private static DigitalInput shootSwitch = new DigitalInput(Constants.SHOOT_SWITCH_POS);
    private static DigitalInput liftSwitch = new DigitalInput(Constants.LIFT_SWITCH_POS);
    private static int numberOfFrisbees = 0;
    
    private FrisbeeTracker() {
        
    }
    
    public static FrisbeeTracker getInstance() {
        if (instance == null) {
            instance = new FrisbeeTracker();
        }
        
        return instance;
    }
    
    public static int getNumberOfFrisbees() {
        return numberOfFrisbees;
    }
    
    public static void count(boolean feedMode) {
        //if in feedMode, adds frisbees; if not, subtracts
        if (feedMode = true) {
            if (FrisbeeTracker.shotFrisbee() == true) {
                numberOfFrisbees ++;
            }
        if (feedMode = false) {
            if (FrisbeeTracker.shotFrisbee() == true) {
                numberOfFrisbees --;
            }
        }
        }
    }
            
    public static boolean ingestFrisbee() {
        if (ingestSwitch.get() == true) {
            //accounts for switch being pressed for a period of time
            StopWatch time = new StopWatch();
            time.start();
            while (ingestSwitch.get() == true) {                
            }
            time.stop();
            if (time.getSeconds() >= Constants.INGEST_FRISBEE_TIMER) {
                numberOfFrisbees = numberOfFrisbees + 1;
                return true;
            }            
            time.reset();
        } 
        return false;
    }
    
    public static boolean liftFrisbee() {
        if (liftSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            StopWatch time = new StopWatch();
            while (liftSwitch.get() == true) {                
            }
            time.start();
            if (time.getSeconds() >= Constants.LIFT_FRISBEE_TIMER) {
                return true;
            }      
            time.reset();
        } 
        return false;
    }
    
    public static boolean shotFrisbee() {
        if (shootSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            StopWatch time = new StopWatch();
            time.start();
            while (shootSwitch.get() == true) {                
            }
            time.stop();
            if (time.getSeconds() >= Constants.SHOOT_FRISBEE_TIMER) {
                return true;
            }        
            time.reset();
        }
        return false;
    }
    
    
}