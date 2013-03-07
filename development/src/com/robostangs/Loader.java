package com.robostangs;


/**
 * Uses conveyors, lifter, ingestor to get frisbees to shooter
 * maintainer: Tejas
 */

//TODO: reenable ingestor, automatic lifter
public class Loader {
    private static Loader instance = null;
    
    private Loader() {
        Conveyors.getInstance();
        //Ingestor.getInstance();
        Lifter.getInstance();
    }
    
    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }
    
    /**
     * turns everything under loader control off
     */
    public static void allOff(){
        Lifter.stop();
        Conveyors.stopBoth();
        //Ingestor.turnOff();
    }

    public static void stopShooterConveyor() {
        Conveyors.stopShooter();
    }
    
    /**
     * runs ingestor + ingestConveyor
     */
    public static void ingest(){
        if (Lifter.getPos()) {
            //liftDown();
        } else {
            Lifter.stop();
        }
        //Ingestor.turnOn();
        Conveyors.ingest();
    }
    
    /**
     * runs shooter conveyor, moves lifter to top pos if not there
     */
    public static void loadShooter(){
        if (!Lifter.getPos()) {
            //liftUp();
        } else {
            Lifter.stop();
        }
        Conveyors.feedMode();
    }
    
    /**
     * reverses to feed from station
     */
    public static void feed(){
        if (!Lifter.getPos()) {
            //liftUp();
        } else {
            Lifter.stop();
        }
        Conveyors.feedMode();
    }
    
    /**
     * moves lift down
     */
    public static void liftDown(){
        while(Lifter.timedDown() == 0);
    }
    
    /**
     * moves lift up
     */
    public static void liftUp() {
        while(Lifter.timedUp() == 0);
    }
    
    /**
     * turns off ingestor
     */
    public static void ingestorOff() {
        //Ingestor.turnOff();
        Conveyors.stopIngest();
    }
    
    public static void stopConveyors() {
        Conveyors.stopBoth();
    }

    public static void liftOff() {
        Lifter.stop();
    }
}
