/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Handles the shooter
 * maintainer: Nicholas
 */
public class Shooter {
    public static Shooter instance = null;
    private static CANJaguar shooter;
    private static boolean feedMode = false;
    
    private Shooter() {
        try {
            shooter = new CANJaguar(Constants.SHOOTER_JAG_POS);
        } catch(CANTimeoutException ex) {
            System.out.println("CAN ERROR AT SHOOTER");
            ex.printStackTrace();
        }
        
        /**
        *Try catch for jags
        */
    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
        
        /**
        *setting up singleton
        */
    }

    public static void shoot() {
        try{
            shooter.setX(Constants.SHOOTER_MAX_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        feedMode = false;
        /**
         * Set shooter to max power
         * Shut down feedMode
         */
    }

    public static void feed() {
        try {
            shooter.setX(-Constants.SHOOTER_FEED_POWER); //feed shouldn't run @ full
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        feedMode = true;
        /**
         * Set feeder power to negative
         * Start feedMode
         */
    }

    public static void stop() {
        try{
            shooter.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        /**
         * Shut down shooter jag
         */
    }

    public static boolean isFeedMode() {
        return feedMode;
    }

    public static boolean readyToShoot() {
        //TODO: get shooter jag current?
        return false;
    }
}
