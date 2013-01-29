/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author sky
 * runs the ingestor
 */
public class Ingestor {
    private static Ingestor instance = null;
    private Relay ingest;
    private boolean isOn;
    
    private Ingestor() {
        
    }
    
    public static Ingestor getInstance() {
        if (instance == null) {
            instance = new Ingestor();
        }
        
        return instance;
    }
    
    public void turnOn() {
        
    }
    
    public void turnOff() {
        
    }
    
    public void reverse() {
        
    }
    
    public boolean getState() {
        return false;
    }
    
}
