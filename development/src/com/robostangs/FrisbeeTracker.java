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
    private static StopWatch ingestTimer, liftTimer, shootTimer;
    
    private FrisbeeTracker() {
        ingestSwitch = new DigitalInput(Constants.INGEST_SWITCH_POS);
        shootSwitch = new DigitalInput(Constants.SHOOT_SWITCH_POS);
        liftSwitch = new DigitalInput(Constants.LIFT_SWITCH_POS);
        ingestTimer = new StopWatch();
        liftTimer = new StopWatch();
        shootTimer = new StopWatch();
    
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
        if (feedMode) {
            if (FrisbeeTracker.shotFrisbee()) {
                numberOfFrisbees++;
            }
        } else {
            if (FrisbeeTracker.shotFrisbee()) {
                numberOfFrisbees--;
            }
            //ingesting frisbees
        }
        if (FrisbeeTracker.ingestFrisbee()) {
            numberOfFrisbees++;
        }
    }
            
    public static boolean ingestFrisbee() {
        if (ingestSwitch.get()) {
            //accounts for switch being pressed for a period of time
            ingestTimer.start();
            if (ingestTimer.getSeconds() >= Constants.INGEST_FRISBEE_TIMER) {
                return true;
            }           
        } else {
            ingestTimer.stop();
            ingestTimer.reset();
            return false;
        }
        return false;
    }
    
    public static boolean liftFrisbee() {
        if (liftSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            liftTimer.start();
            if (liftTimer.getSeconds() >= Constants.LIFT_FRISBEE_TIMER) {
                return true;
            }      
        } else {
            liftTimer.stop();
            liftTimer.reset();
            return false;
        }
        return false;
    }
    
    public static boolean shotFrisbee() {
        if (shootSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            shootTimer.start();
            if (shootTimer.getSeconds() >= Constants.SHOOT_FRISBEE_TIMER) {
                return true;
            }        
        } else {
            shootTimer.stop();
            shootTimer.reset();
            return false;
        }
        return false;
    }
    
    
}