package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Lifter class
 * maintainer: Sam 
 */

public class Lifter {
    private static Lifter instance = null;
    private static CANJaguar lift;
    private static ProximitySensor topProx;
    private static ProximitySensor bottomProx;

    private Lifter() { 
        try {
            lift = new CANJaguar(Constants.LIFTER_JAG_POS);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }

        topProx = new ProximitySensor(Constants.LIFTER_TOP_PROX_DIGITAL_PORT, Constants.LIFTER_TOP_PROX_SOLENOID_PORT);
        bottomProx = new ProximitySensor(Constants.LIFTER_BOTTOM_PROX_DIGITAL_PORT, Constants.LIFTER_BOTTOM_PROX_SOLENOID_PORT);
    }
  
    public static Lifter getInstance() {
        if (instance == null) {
            instance = new Lifter();
        }
        return instance;
    }

    public static boolean atBottom() {
        return !bottomProx.get();
    }

    public static boolean atTop() {
        return !topProx.get();
    }
   /**
    * Lifter goes up
    */
    public static void raise() {
        try {
            lift.setX(Constants.LIFTER_UP_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

   /**
    * Lifter goes down
    */
    public static void lower() {
        try {
            lift.setX(-Constants.LIFTER_DOWN_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Lifter down at slow speed
     */
    public static void slowDown() {
        try {
            lift.setX(-Constants.LIFTER_SLOW_DOWN_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Lifter up at slow speed for maintaining height
     */
    public static void slowUp() {
        try {
            lift.setX(Constants.LIFTER_SLOW_UP_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @return the absolute value of the current on the lifter jag
     */
    public static double getJagCurrent() {
        try {
            return Math.abs(lift.getOutputCurrent());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Uses the prox sensor to move basket to top position
     * Runs at low power at top to maintain height
     */
    public static void sensorUp() {
        if (atTop()) {
            constantUp();
        } else {
            raise();
        }
    }

    /**
     * Uses the prox sensor to move basket to ingest position
     */
    public static void sensorDown() { 
        if (atBottom()) {
            stop();
        } else {
            lower();
        }
    }

    /**
     * Stops the lifter
     */
    public static void stop() {
        try {
            lift.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Manual lifter control
     */
    public static void manual(double speed) {
        if (atTop() && speed > 0) {
            //at top, trying to go up, hold at constant slow speed
            constantUp();
            speed = 0;
        } else if (atBottom() && speed < 0) {
            //at bottom, trying to go down, don't move basket
            speed = 0;
        }

        try {
            lift.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static double getSetSpeed() {
        try {
            return lift.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }
    }
}
