/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Relay;

/**
 * runs the ingestor
 * maintainer: Sam
 */
public class Ingestor {
    private static Ingestor instance = null;
    private static Relay ingest;
    private static boolean isOn;
    
    private Ingestor() {
        ingest = new Relay(Constants.INGEST_RELAY);
    }
    
    public static Ingestor getInstance() {
        if (instance == null) {
            instance = new Ingestor();
        }
        
        return instance;
    }
    
    public static void turnOn() {
        ingest.set(Relay.Value.kForward);
    }
    
    public static void turnOff() {
        ingest.set(Relay.Value.kOn);
    }
    
    public static void reverse() {
        ingest.set(Relay.Value.kReverse);
    }
    
    public static boolean getState() {
        return isOn;
    }
    
}
