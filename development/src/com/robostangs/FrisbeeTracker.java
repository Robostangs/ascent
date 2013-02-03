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
    private static DigitalInput ingestSwitch, shootSwitch, liftSwitch;
    private static int numberOfFrisbees = 0;
    
    private FrisbeeTracker() {
        ingestSwitch = new DigitalInput(Constants.INGEST_SWITCH_POS);
        shootSwitch = new DigitalInput(Constants.SHOOT_SWITCH_POS);
        liftSwitch = new DigitalInput(Constants.LIFT_SWITCH_POS);
    
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
                numberOfFrisbees++;
            }
        if (feedMode = false) {
            if (FrisbeeTracker.shotFrisbee() == true) {
                numberOfFrisbees--;
            }
            //ingesting frisbees
        }
        if (FrisbeeTracker.ingestFrisbee() == true) {
            numberOfFrisbees++;
        }
        }
    }
            
    public static boolean ingestFrisbee() {
        StopWatch time = new StopWatch();
        if (ingestSwitch.get() == true) {
            //accounts for switch being pressed for a period of time
            time.start();
            if (time.getSeconds() >= Constants.INGEST_FRISBEE_TIMER) {
                return true;
            }           
        } 
        time.stop();
        time.reset();
        return false;
    }
    
    public static boolean liftFrisbee() {
        StopWatch time = new StopWatch();
        if (liftSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            time.start();
            if (time.getSeconds() >= Constants.LIFT_FRISBEE_TIMER) {
                return true;
            }      
        } 
        time.stop();
        time.reset();
        return false;
    }
    
    public static boolean shotFrisbee() {
        StopWatch time = new StopWatch();
        if (shootSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            time.start();
            if (time.getSeconds() >= Constants.SHOOT_FRISBEE_TIMER) {
                return true;
            }        
            time.reset();
        }
        time.stop();
        time.reset();
        return false;
    }
    
    
}