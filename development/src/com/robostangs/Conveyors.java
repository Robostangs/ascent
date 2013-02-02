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
        ingestConveyor = new CANJaguar(Constants.INGEST_CONV_JAG);
        shooterConveyor = new CANJaguar(Constants.SHOOT_CONV_JAG);
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
            ingestConveyor.setX(1.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void reverseIngest(){
        try {
            ingestConveyor.setX(-1.0);
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
            shooterConveyor.setX(1.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void feedMode() { 
        try {
            shooterConveyor.setX(-1.0);
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
        try {
            ingestConveyor.setX(0.0);
            shooterConveyor.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void shakeIngest() {
        StopWatch timer = new StopWatch();
        while(timer.get() < 2){
            if(direction){
                try {
                    ingestConveyor.setX(.5);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }           
            }else{
                try {
                    ingestConveyor.setX(-.5);
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
        while(timer.get() < 2){
            if(direction){
                try {
                    shooterConveyor.setX(.5);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }           
            }else{
                try {
                    shooterConveyor.setX(-.5);
                } catch (CANTimeoutException ex) {
                    ex.printStackTrace();
                }
            }
        }
        direction = !direction;
        timer.reset();
    }
}
