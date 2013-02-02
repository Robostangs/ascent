/**
 * Class to control the shooter arm
 * maintainer: Lauren
 */

package com.robostangs;

import edu.wpi.first.wpilibj.PIDController;

public class Arm {
    private static Arm instance = getInstance();
    private static Potentiometer potA;
    private static Potentiometer potB;
    private static ArmMotors motors = ArmMotors.getInstance();
    private static PIDController pidA;
    private static PIDController pidB;
    private static boolean useB;
    
    private Arm() {
        
    }
    
    public static Arm getInstance() {
        if (instance == null) {
            instance = new Arm();
        }
        
        return instance;
    }
    
    public static int getPotA() {
        return 0;
    }
    
    public static int getPotB() {
        return 0;
    }
    
    public static double getAngle() {
        return 0;
    }
    
    public static void setJags(double power) {
        
    }
    
    public static void setPosition(double potValue) {
        
    }
    
    public boolean pidEnabled() {
        return false;
    }
    
    public static void disablePID() {
        
    }
    
    public static void stop() {
        
    }
    
    public static void usePotA() {
        
    }
    
    public static void usePotB() {
        
    }
    
    public static void switchPot() {
        
    }
    
    public static void sendAngle() {
        
    }
    
    public static void sendPotData() {
        
    }
    
    public static void sendWhichPotInUse() {
        
    }
    
    public static boolean isPotAFunctional() {
        return false;
    }
    
    public static boolean isPotBFunctional() {
        return false;
    }
}

