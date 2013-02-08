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
    private static StopWatch timer;
    
    private Shooter() {
        try {
            shooter = new CANJaguar(Constants.SHOOTER_JAG_POS);
        } catch(CANTimeoutException ex) {
            System.out.println("CAN ERROR AT SHOOTER");
            ex.printStackTrace();
        }
        
        timer = new StopWatch();
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
    
    /**
     * Shoots a certain number of frisbees
     * @param number 
     * @return 0 if in progress, 1 if complete
     */
    public static int shoot(int number) {
        double time = 0;
        
        switch (number) {
            case 1:
                time = Constants.TIME_TO_SHOOT_ONE;
                break;
            case 2: 
                time = Constants.TIME_TO_SHOOT_TWO;
                break;
            case 3: 
                time = Constants.TIME_TO_SHOOT_THREE;
                break;
            case 4: 
                time = Constants.TIME_TO_SHOOT_FOUR;
                break;
            default:
                time = 0;
                break;
        }
        
        timer.start();
        shoot();
        
        if (timer.getSeconds() > time) {
            stop();
            timer.stop();
            timer.reset();
            return 1;
        }        
        
        return 0;
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
