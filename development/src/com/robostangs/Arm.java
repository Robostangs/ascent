/**
 * Class to control the shooter arm
 * maintainer: Lauren
 */

package com.robostangs;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
    private static Arm instance = null;
    private static Potentiometer potA, potB;
    private static ArmMotors motors;
    private static PIDController pidA, pidB; 
    private static boolean useB = false;
    
    private Arm() {
        potA = new Potentiometer(Constants.POT_A_PORT);
        potB = new Potentiometer(Constants.POT_B_PORT);
        motors = ArmMotors.getInstance();
        pidA = new PIDController(Constants.ARM_KP_A, Constants.ARM_KI_A, Constants.ARM_KD_A, potA, motors); 
        pidB = new PIDController(Constants.ARM_KP_B, Constants.ARM_KI_B, Constants.ARM_KD_B, potB, motors); 
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
        double angle = 0;
        if (!useB) {
            angle = (getPotA() - Constants.ARM_POT_ZERO) * Constants.POT_TO_DEGREES;
            return angle;
        } else {
            angle = (getPotB() - Constants.ARM_POT_ZERO) * Constants.POT_TO_DEGREES;
            return angle;
        }
     }
    
     /**
      * @param power power of arm jags
      * disables pid
      * sets the power of the arm jags
      */
    public static void setJags(double power) {
        if (pidEnabled()) {
            disablePID();
        }
        ArmMotors.set(power);
    }
    /**
     * @param potValue value of pot 
     * disables the other pot
     * sets the value of the pot
     * enables the pot
     */

    public static void setPosition(double potValue) { 
        if (useB) {
            pidA.disable();
            pidB.setSetpoint(potValue);
            pidB.enable();
        } else {
            pidB.disable();
            pidA.setSetpoint(potValue);
            pidA.enable();
        }
    }
    /**
     * checks if either pid is enabled
     * @return true if either pid is enabled, false if neither is enabled
     */
    public static boolean pidEnabled() {
        if (pidA.isEnable() || pidB.isEnable()) {
            return true;
        } else {
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
        useB = false;
    }
    
    public static void usePotB() {
        useB = true;
    }
    
    public static void switchPot() {
        useB = !useB;        
    }
    
    public static void sendAngle() {
        SmartDashboard.putNumber("Angle: ", getAngle());
    }
    
    public static void sendPotData() {
        SmartDashboard.putNumber("Pot A: ", getPotA());
        SmartDashboard.putNumber("Pot B: ", getPotB());
    }
    
    public static void sendWhichPotInUse() {
        if (useB) {
            SmartDashboard.putString("CURRENT POT: ", "POT B");
        } else {
            SmartDashboard.putString("CURRENT POT: ", "POT A");
        }
    } 
    
    public boolean isPotAFunctional() {
        // must be within possble range
        if (getPotA() >= Constants.POT_MIN_VALUE  && getPotA() <= Constants.POT_MAX_VALUE) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isPotBFunctional() {
        // must be within possible range
        if (getPotB() >= Constants.POT_MIN_VALUE  && getPotB() <= Constants.POT_MAX_VALUE) {
            return true;
        }
        else {
            return false;
        }
    }     
}
