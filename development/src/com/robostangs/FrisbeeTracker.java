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
    private static int numberOfFrisbees;
    
    private FrisbeeTracker() {
        
    }
    
    public static FrisbeeTracker getInstance() {
        if (instance == null) {
            instance = new FrisbeeTracker();
        }
        
        return instance;
    }
    
    public static int getNumberOfFrisbees() {
        return 0;
    }
    
    public static void count(boolean feedMode) {
        
    }
    
    public static boolean ingestFrisbee() {
        return false;
    }
    
    public static boolean liftFrisbee() {
        return false;
    }
    
    public static boolean shotFrisbee() {
        return false;
    }
    
    
}
