package com.robostangs;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Counts frisbees, tracks location in robot
 * maintainer: Michael
 */
public class FrisbeeCounter {
    private static FrisbeeCounter instance = null;
    private static DigitalInput ingestSwitch, liftSwitch;
    private static int numberOfFrisbees = 0;
    private static Timer ingestTimer, liftTimer;
    
    private FrisbeeCounter() {
        //ingestSwitch = new DigitalInput(Constants.INGEST_SWITCH_POS);
        //liftSwitch = new DigitalInput(Constants.LIFT_SWITCH_POS);
        ingestTimer = new Timer();
        liftTimer = new Timer();
    }
    
    public static FrisbeeCounter getInstance() {
        if (instance == null) {
            instance = new FrisbeeCounter();
        }
        return instance;
    }
    
    /**
     * gets the number of discs in the robot
     * @return number of frisbees
     */
    public static int getNumberOfFrisbees() {
        return numberOfFrisbees;
    }
    
    /**
     * adds a count of discs when in feed mode
     * subtracts when shot
     * @param feedMode 
     *
    public static void count(boolean feedMode) {
        if (FrisbeeCounter.ingestFrisbee()) {
            numberOfFrisbees++;
        }
    }
    
    /**
     * counts the time to ingest a frisbee
     * makes sure that the robot does not ingest 2 frisbees at the same time
     * @return true when the robot finishes ingesting
     *
    public static boolean ingestFrisbee() {
        if (ingestSwitch.get()) {
            //accounts for switch being pressed for a period of time
            ingestTimer.start();
            if (ingestTimer.get() >= Constants.INGEST_FRISBEE_TIMER) {
                return true;
            }           
        } else {
            ingestTimer.stop();
            ingestTimer.reset();
            return false;
        }
        return false;
    }
    
    /**
     * 
     */
    public static void manualCountIncrease() {
        numberOfFrisbees++;
    }
    
    public static void manualCountDecrease() {
        numberOfFrisbees--;
    }
    
    /**
     * counts the time to lift a frisbee
     * @return true when lifting is done
     *
    public static boolean liftFrisbee() {
        if (liftSwitch.get() == true) {
             //accounts for switch being pressed for a period of time
            liftTimer.start();
            if (liftTimer.get() >= Constants.LIFT_FRISBEE_TIMER) {
                return true;
            }      
        } else {
            liftTimer.stop();
            liftTimer.reset();
            return false;
        }
        return false;
    }
    */
    
    /**
     * sends frisbee data to dashboard
     */
    public static void sendFrisbeeDataToDash() {
        SmartDashboard.putNumber("Number of frisbees", numberOfFrisbees);
        if (numberOfFrisbees == 4) {
            SmartDashboard.putString("AT MAX FRISBEES", "");
        } else if (numberOfFrisbees > 4) {
            SmartDashboard.putString("TOO MANY FRISBEES!!1!", "DUMP " 
                    + (numberOfFrisbees - 4) + " STAT.");
        }
    }
}