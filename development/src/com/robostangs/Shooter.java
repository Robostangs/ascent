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
    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }

        return instance;
    }

    public static void shoot() {

    }

    public static void feed() {

    }

    public static void stop() {

    }

    public static boolean isFeedMode() {
        return false;
    }

    public static boolean readyToShoot() {
        return false;
    }
}
