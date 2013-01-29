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
 */
public class XboxManip extends Joystick{
    private static XboxManip instance = null;
    
    private XboxManip(int port) {
        super(port);
    }

    public static XboxManip getInstance() {
        if (instance == null) {
            instance = new XboxManip();
        }

        return instance;
    }
    
}
