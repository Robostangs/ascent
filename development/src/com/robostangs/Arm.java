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
        pidA = new PIDController(Constants.ARM_KP_A, Constants.ARM_KI_A, Constants.ARM_KD_A); 
        pidB = new PIDController(Constants.ARM_KP_B, Constants.ARM_KI_B, Constants.ARM_KD_B); 
    }
    
    public static Arm getInstance() {
        if (instance == null) {
            instance = new Arm();
        }        
        return instance;
    }
    
    public static int getPotA() {
        return potA.getAverageValue(); 
    }    

    public static int getPotB() {
        return potB.getAverageValue(); 
    }
    
     public static double getAngle() {
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
    
    public static void setJags(double power) {
        ArmMotors.set(power);
    }
    

    public static void setPosition(double potValue) { 
        ArmMotors.getAverageValue(potValue);
    
    public boolean pidEnabled() {
        if( pidA.isEnable() || pidB.isEnable()) {
            return true;
        }
        else {
            return false;
        }    
    }
    
    public static void disablePID() {
        if( pidA.isEnable()) {
            pidA.disable();
        }
        if (pidB.isEnable()) {
            pidB.disable();
        }
    }
    
    public static void stop() {
        ArmMotors.set(0);
    }
    
    public static void usePotA() {
        pidA.enable();
        pidB.disable();
        useB = false;
    }
    
    public static void usePotB() {
        pidB.enable();
        pidA.disable();
        useB = true;
    }
    
    public static void switchPot() {
        // if both are disabled then it uses potA
        if (pidA.isEnable() && !pidB.isEnable()) {
            Arm.getInstance().usePotB();
        }
        else if (!pidA.isEnable() || pidB.isEnable()) {
            Arm.getInstance().usePotA();
        }        
    }
    
    public static void sendAngle() {
        SmartDashboard.putData("Angle: ", getAngle());
    }
    
    public static void sendPotData() {
        if(potA.isEnable()) {
            SmartDashboard.putData(getPotA());
        }
        if(potB.isEnable()) {
            SmartDashboard.putData(getPotB());
        }
    }
    
    public static void sendWhichPotInUse() {
        if(potA.isEnable()) {
            SmartDashboard.putString("Pot A: ");
        }
        if(potB.isEnable()){
            SmartDashboard.putString("Pot B: ");
        }
    } 
    
    public static boolean isPotAFunctional() {
        // must be enabled and within possble range
        if (pidA.isEnable() && Constants.POT_MIN_VALUE <= getPotA() && getPotA() <= Constants.POT_MAX_VALUE) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean isPotBFunctional() {
        // must be enabled and within possible range
        if (pidB.isEnable() && Constants.POT_MIN_VALUE <= getPotB() && Constants.POT_MAX_VALUE <= getPotB()) {
            return true;
        }
        else {
            return false;
        }
    }     
}
