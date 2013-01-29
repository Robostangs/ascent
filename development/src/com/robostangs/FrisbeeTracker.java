/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author sky
 * keeps track of the number of frisbees
 */
public class FrisbeeTracker {
    private static FrisbeeTracker instance = null;
    private DigitalInput ingestSwitch, shootSwitch, liftSwitch;
    private int numberOfFrisbees;
    
    private FrisbeeTracker() {
        
    }
    
    public static FrisbeeTracker getInstance() {
        if (instance == null) {
            instance = new FrisbeeTracker();
        }
        
        return instance;
    }
    
    public int getNumberOfFrisbees() {
        return 0;
    }
    
    public void count(boolean feedMode) {
        
    }
    
    public boolean ingestFrisbee() {
        return false;
    }
    
    public boolean liftFrisbee() {
        return false;
    }
    
    public boolean shotFrisbee() {
        return false;
    }
    
    
}
