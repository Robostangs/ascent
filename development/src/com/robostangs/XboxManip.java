/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;

/**
 * For manip's controller
 * should adjust for joystick drift
 * maintainer: Nicholas
 */
public class XboxManip extends XboxController {
    
    int a;
    
    private static XboxManip instance = null;
    
    private XboxManip(int port) {
        super(port);
    }

    public static XboxManip getInstance() {
        if (instance == null) {
            instance = new XboxManip(2);
        }

        return instance;
    }
    
    public void antidrift(double leftStick, double rightStick)
    {
        if(Math.abs(leftStick) < 0.1){
            leftStick = 0;
        }
        
        if(Math.abs(rightStick) < 0.1){
            rightStick = 0;
        }
    }
    
    
}
