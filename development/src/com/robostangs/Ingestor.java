/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Relay;

/**
 * Runs the Ingestor
 * maintainer: Sam
 */
public class Ingestor {
    private static Ingestor instance = getInstance();
    private static Relay ingest;
    private static boolean isOn;
    
    private Ingestor() {
        //this is the constructor, even though it is private
    }
    
    public static Ingestor getInstance() {
        if (instance == null) {
            instance = new Ingestor();
        }
        
        return instance;
    }
    
    public static void turnOn() {
        
    }
    
    public static void turnOff() {
        
    }
    
    public static void reverse() {
        
    }
    
    public static boolean getState() {
        return false;
    }
    
}
