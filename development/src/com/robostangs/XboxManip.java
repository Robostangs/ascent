/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Joystick;

/**
 * For manip's controller
 * should adjust for joystick drift
 * maintainer: Nicholas
 */
public class XboxManip extends XboxController {
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
    
}
