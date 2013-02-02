/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Joystick;

/**
 * For driver's controller
 * should adjust for joystick drift
 * maintainer: Nicholas
 */
public class XboxDriver extends XboxController {
    private static XboxDriver instance = getInstance();
    
    private XboxDriver(int port) {
        super(port);
    }

    public static XboxDriver getInstance() {
        if (instance == null) {
            instance = new XboxDriver(1);
        }

        return instance;
    }
    
}
