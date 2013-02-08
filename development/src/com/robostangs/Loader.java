/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * Uses conveyors, lifter, ingestor to get frisbees to shooter
 * maintainer: Tejas
 */
public class Loader {
    private static Loader instance = null;
    
    private Loader() {
        Conveyors.getInstance();
        Ingestor.getInstance();
        Lifter.getInstance();
    }

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }

        return instance;
    }

        public static void allOff(){
        Lifter.stop();
        Conveyors.stopBoth();
        Ingestor.turnOff();
    }
    
    public static void ingest(){
        Ingestor.turnOn();
        
    }
    
    public static void loadShooter(){
        Lifter.enable();
        Conveyors.feedMode();
    }
    
    public void runAll(){
        Ingestor.turnOn();
        Conveyors.ingestMode();
        Conveyors.feedMode();
    }
    
    public static void feed(){
        Lifter.reverse();
    }
    
    public static void liftDown(){
        Lifter.enable(Constants.LIFT_DOWN);
    }
    

    public static void liftUp() {
        Lifter.reverse(Constants.LIFT_UP);
    }

    public static void ingestorOff() {
        Ingestor.turnOff();
    }

    public static void ingestConveyorOff() {
        Conveyors.stopIngest();
    }

    public static void liftOff() {
        Lifter.stop();
    }

    public static void shooterConveyorOff() {
        Conveyors.shakeShooter();
    }
}
