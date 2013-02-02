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
    }

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }

        return instance;
    }

    public static void allOff() {

    }

    public static void ingest() {

    }

    public static void loadShooter() {
    }

    public static void runAll() {

    }

    public static void feed() {

    }

    public static void liftDown() {

    }

    public static void liftUp() {

    }

    public static void ingestorOff() {

    }

    public static void ingestConveyorOff() {

    }

    public static void liftOff() {

    }

    public static void shooterConveyorOff() {

    }
}
