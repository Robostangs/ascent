/**
 * Class to control the shooter arm
 * maintainer: 
 */

package com.robostangs;

import edu.wpi.first.wpilibj.PIDController;

public class Arm {
    private static Arm instance = null;
    private Potentiometer potA, potB;
    private MotorOutput motors;
    private PIDController pidA, pidB;
    private boolean useB;
    
    private Arm() {
        
    }
    
    public static Arm getInstance() {
        if (instance == null) {
            instance = new Arm();
        }
        
        return instance;
    }
    
    public int getPotA() {
        return 0;
    }
    
    public int getPotB() {
        return 0;
    }
    
    public double getAngle() {
        return 0;
    }
    
    public void setJags(double power) {
        
    }
    
    public void setPosition(double potValue) {
        
    }
    
    public boolean pidEnabled() {
        return false;
    }
    
    public void disablePID() {
        
    }
    
    public void stop() {
        
    }
    
    public void usePotA() {
        
    }
    
    public void usePotB() {
        
    }
    
    public void switchPot() {
        
    }
    
    public void sendAngle() {
        
    }
    
    public void sendPotData() {
        
    }
    
    public void sendWhichPotInUse() {
        
    }
    
    public boolean isPotAFunctional() {
        return false;
    }
    
    public boolean isPotBFunctional() {
        return false;
    }
}

