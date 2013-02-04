package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Controls the conveyor system 
 * maintainer: Sam
 */
public class Conveyors {
    private static Conveyors instance = null;
    private static CANJaguar ingestConveyor, shooterConveyor;
    private static boolean direction = false; //false is in
    
    private Conveyors() {
        try{
            ingestConveyor = new CANJaguar(Constants.CONV_INGEST_JAG_POS);
            shooterConveyor = new CANJaguar(Constants.CONV_SHOOT_JAG_POS);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Conveyors getInstance() {
        if (instance == null) {
            instance = new Conveyors();
        }
        
        return instance;
    }
    
    public static void ingestMode() {
        try {
            ingestConveyor.setX(Constants.CONV_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void reverseIngest(){
        try {
            ingestConveyor.setX(-Constants.CONV_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void stopIngest() {
        try {
            ingestConveyor.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void readyShooter() {
        try {
            shooterConveyor.setX(Constants.CONV_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void feedMode() { 
        try {
            shooterConveyor.setX(-Constants.CONV_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void stopShooter() { 
        try {
            shooterConveyor.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void stopBoth() {
        stopIngest();
        stopShooter();
    }

    public static void shakeIngest() {
        StopWatch timer = new StopWatch();
        timer.start();
        while (timer.getSeconds() < 2) {
            if (direction) {
                try {
                    ingestConveyor.setX(Constants.CONV_POWER);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }           
            } else {
                try {
                    ingestConveyor.setX(-Constants.CONV_POWER);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }
            }
        }
        direction = !direction;
        timer.reset();
    }            

    public static void shakeShooter() {
        StopWatch timer = new StopWatch();
        while (timer.get() < 2) {
            if (direction) {
                try {
                    shooterConveyor.setX(Constants.CONV_POWER);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }           
            } else {
                try {
                    shooterConveyor.setX(-Constants.CONV_POWER);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }
            }
        }
        direction = !direction;
        timer.reset();
    }
}
