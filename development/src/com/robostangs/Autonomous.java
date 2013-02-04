/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

/**
 *
 * @author sky
 */
public class Autonomous {
    private static Autonomous instance = null;
    private static int mode = 0;
    
    private Autonomous() {
        
    }
    
    public static Autonomous getInstance() {
        if (instance == null) {
            instance = new Autonomous();
        }
        return instance;
    }
    
    /**
     * Gets required information from the dashboard
     */
    public static void getInfo() {
        
    }
    
    public static void setMode(int m) {
        mode = m;
    }
    
}
