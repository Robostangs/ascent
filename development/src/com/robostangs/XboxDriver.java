/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author sky
 * needs to have methods for all buttons, joysticks
 * TODO: constants
 */
public class XboxDriver extends Joystick{
    private static XboxDriver instance = null;
    
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
