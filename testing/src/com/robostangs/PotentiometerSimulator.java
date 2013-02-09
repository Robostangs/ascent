/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;


/**
 *
 * @author Haley
 */
//TODO: Log some integer
  public class Potentiometer {
    
    public Potentiometer(int port) {
        super(port);  //calls AnalogChannel constructor
        //TODO: Rewrite super
    }

    public double pidGet() {
        return getAverageValue();
        
    }
    
    public double getAverageValue () {
        return 3.3;
        //TODO: Give value that varies
    }
    
    public double getValue () {
        return 3.214;
        //TODO: Let value vary more than average value
    }
}

