/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 * @author sky
 */
public class Conveyors {
    private static Conveyors instance = null;
    private CANJaguar ingestConveyor, shooterConveyor;
    
    private Conveyors() {
        
    }
    
    public static Conveyors getInstance() {
        if (instance == null) {
            instance = new Conveyors();
        }
        
        return instance;
    }
    
    public void ingestMode() {
        
    }

    public void loadShooter() {

    }

    public void feedMode() { 

    }

    public void stopShooter() { 
    
    }

    public void stopBoth() {
    
    }

    public void shakeIngest() {

    }

    public void shakeShooter() {

    }
}
