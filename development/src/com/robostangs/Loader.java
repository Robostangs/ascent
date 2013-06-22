package com.robostangs;

/**
 * Uses conveyors, lifter, ingestor to get frisbees to shooter
 * maintainer: Tejas
 */

public class Loader {
    private static Loader instance = null;
    
    private Loader() {
        Conveyors.getInstance();
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
    }
    
    /**
     * runs ingest conveyor, moves lifter down
     */
    public static void ingest(){
        Conveyors.ingest();
        Lifter.sensorDown();
    }

    /**
     * runs shooter conveyor, moves lifter to top pos if not there
     * ingestor runs to prevent jams
     */
    public static void loadShooter(){
        Conveyors.ingest();
        Lifter.sensorUp();
        Conveyors.loadShooter();
    }
}
