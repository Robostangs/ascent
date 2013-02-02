/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * Manages the conveyors system
 * maintainer: Sam
 */
public class Conveyors {
    private static Conveyors instance = getInstance();
    private static CANJaguar ingestConveyor, shooterConveyor;
    
    private Conveyors() {
        
    }
    
    public static Conveyors getInstance() {
        if (instance == null) {
            instance = new Conveyors();
        }
        
        return instance;
    }
    
    public static void ingestMode() {
        
    }

    public static void loadShooter() {

    }

    public static void feedMode() { 

    }

    public static void stopShooter() { 
    
    }

    public static void stopBoth() {
    
    }

    public static void shakeIngest() {

    }

    public static void shakeShooter() {

    }
}
