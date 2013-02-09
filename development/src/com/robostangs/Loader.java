/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;


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
    //singleton declaration statement
    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }

        return instance;
    }
    //turns everything under loader control off    
    public static void allOff(){
        Lifter.stop();
        Conveyors.stopBoth();
        Ingestor.turnOff();
    }
    //runs ingestor + ingestConveyor
    public static void ingest(){
        Ingestor.turnOn();
        
    }
    //runs lift, shoot conveyor
    public static void loadShooter(){
        Lifter.enable();
        Conveyors.feedMode();
    }
    //runs all in ingest -> shoot dir
    public void runAll(){
        Ingestor.turnOn();
        Conveyors.ingestMode();
        Conveyors.feedMode();
    }
    //reverses to feed from station
    public static void feed(){
        Conveyors.readyShooter();
    }
    //moves lift down
    public static void liftDown(){
        Lifter.reverse();
    }
    //moves lift up
    public static void liftUp() {
        Lifter.enable();
    }
    //turns off ingestor
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
