/**
 * Class to control the shooter arm
 * maintainer: Lauren
 */

package com.robostangs;

import edu.wpi.first.wpilibj.PIDController;

public class Arm {
    private static Arm instance = null;
    private Potentiometer potA, potB;
    private ArmMotors motors;
    private PIDController pidA, pidB; 
    private boolean useB;
    
    private Arm() {
        potA = new Potentiometer(Constants.POT_A_PORT);
        potB = new Potentiometer(Constants.POT_B_PORT);
        motors = ArmMotors.getInstance();
        pidA = new PIDController(Constants.ARM_KP_A, Constants.ARM_KI_A, Constants.ARM_KD_A, pidGet(), pidWrite()); 
        pidB = new PIDController(Constants.ARM_KP_B, Constants.ARM_KI_B, Constants.ARM_KD_B, pidGet(), pidWrite()); 
    }
    
    public static Arm getInstance() {
        if (instance == null) {
            instance = new Arm();
        }        
        return instance;
    }
    
    public int getPotA() {
        return potA.getAverageValue(); 
    }    

    public int getPotB() {
        return potB.getAverageValue(); 
    }
    
     public double getAngle() {
        double w;
        if (useB == false) {
            w = Constants.INIT_POT_VALUE + getPotA() * Constants.POT_IN_DEGREES;
            return w;
        }
        else if (useB == true) {
            w = Constants.INIT_POT_VALUE + getPotB() * Constants.POT_IN_DEGREES;
            return w;
        }
        else {
            w = 0;
            return w;
        }
     }
    
    public static void setJags(double power) {
        ArmMotors.set(power);
    }
    

    public static void setPosition(double potValue) { 
        ArmMotors.getAverageValue(potValue);
    }
        
    public boolean pidEnabled() {
        if( pidA.isEnable() || pidB.isEnable()) {
            return true;
        }
        else {
            return false;
        }    
    }
    
    public void disablePID() {
        if( pidA.isEnable()) {
            pidA.disable();
        }
        if (pidB.isEnable()) {
            pidB.disable();
        }
    }
    
    public void stop() {
        ArmMotors.set(0);
    }
    
    public void usePotA() {
        pidA.enable();
        pidB.disable();
        useB = false;
    }
    
    public void usePotB() {
        pidB.enable();
        pidA.disable();
        useB = true;
    }
    
    public void switchPot() {
        // if both are disabled then it uses potA
        if (pidA.isEnable() && !pidB.isEnable()) {
            Arm.getInstance().usePotB();
        }
        else if (!pidA.isEnable() || pidB.isEnable()) {
            Arm.getInstance().usePotA();
        }        
    }
    
    public void sendAngle() {
        SmartDashboard.putData("Angle: ", getAngle());
    }
    
    public void sendPotData() {
        if(potA.isEnable()) {
            SmartDashboard.putData(getPotA());
        }
        if(potB.isEnable()) {
            SmartDashboard.putData(getPotB());
        }
    }
    
    public void sendWhichPotInUse() {
        if(potA.isEnable()) {
            SmartDashboard.putString("Pot A: ");
        }
        if(potB.isEnable()){
            SmartDashboard.putString("Pot B: ");
        }
    } 
    
    public boolean isPotAFunctional() {
        // must be enabled and within possble range
        if (pidA.isEnable() && Constants.POT_MIN_VALUE <= getPotA() && getPotA() <= Constants.POT_MAX_VALUE) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isPotBFunctional() {
        // must be enabled and within possible range
        if (pidB.isEnable() && Constants.POT_MIN_VALUE <= getPotB() && Constants.POT_MAX_VALUE <= getPotB()) {
            return true;
        }
        else {
            return false;
        }
    }     
}
