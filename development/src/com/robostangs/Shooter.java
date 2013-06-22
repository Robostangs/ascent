package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Handles the shooter
 * maintainer: Nicholas
 */
public class Shooter {
    private static Shooter instance = null;
    private static CANJaguar shooter1, shooter2, shooter3;
    
    /**
     * Initializing jags and timer
     */
    private Shooter() {
        try {
            shooter1 = new CANJaguar(Constants.SHOOTER_JAG1_POS);
            shooter2 = new CANJaguar(Constants.SHOOTER_JAG2_POS);
            shooter3 = new CANJaguar(Constants.SHOOTER_JAG3_POS);
        } catch(CANTimeoutException ex) {
            System.out.println("CAN ERROR AT SHOOTER");
            ex.printStackTrace();
        }
    }
    
    /**
     * setting up singleton
     */
    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    /**
     * Set shooter to max power
     * Shut down feedMode
     */
    public static void shoot() {
        try{
            shooter1.setX(Constants.SHOOTER_MAX_POWER * Constants.SHOOTER_INNER_WHEEL_POWER);
            shooter2.setX(Constants.SHOOTER_MAX_POWER * Constants.SHOOTER_INNER_WHEEL_POWER);
            shooter3.setX(Constants.SHOOTER_MAX_POWER * Constants.SHOOTER_OUTER_WHEEL_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        feedMode = false;
    }
    
    public static void fullShoot() {
        try{
            shooter1.setX(Constants.SHOOTER_MAX_POWER);
            shooter2.setX(Constants.SHOOTER_MAX_POWER);
            shooter3.setX(Constants.SHOOTER_MAX_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        feedMode = false;

    }
    public static double getVoltage() {
        try {
            return shooter1.getBusVoltage();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Shut down shooter jag
     */
    public static void stop() {
        try{
            shooter1.setX(0.0);
            shooter2.setX(0.0);
            shooter3.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
