/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 * @author sky
 * Runs the conveyor motors
 * needs a method to run forwards, and in reverse for all jags
 */
public class Loader {
    private static Loader instance = null;
    private Conveyors conveyors;
    private Lifter lift;
    private Ingestor ingest;
    
    private Loader() {
    }

    public static Loader getInstance() {
        if (instance == null) {
            instance = new Loader();
        }

        return instance;
    }

    public void allOff() {

    }

    public void ingest() {

    }

    public void loadShooter() {
    }

    public void runAll() {

    }

    public void feed() {


    }

    public void liftDown() {

    }

    public void liftUp() {

    }

    public void ingestorOff() {

    }

    public void ingestConveyorOff() {

    }

    public void liftOff() {

    }

    public void shooterConveyorOff() {

    }
}
