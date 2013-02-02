/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Generic xbox controller class
 * maintainer: Nicholas
 */

//TODO: XBOXCONTROLLER: methods to use triggers as buttons

public class XboxController extends Joystick {
    
    public XboxController(int port) {
        super(port);
    }
    
    public boolean aButton(){
        return getRawButton(1);
    }
    
    public boolean bButton(){
        return getRawButton(2);
    }
    
    public boolean xButton(){
        return getRawButton(3);
    }
    
    public boolean yButton(){
        return getRawButton(4);
    }
    
    public boolean lBump(){
        return getRawButton(5);
    }
    
    public boolean rBump(){
        return getRawButton(6);
    }
    
    public boolean backButton(){
        return getRawButton(7);
    }
    
    public boolean startButton(){
        return getRawButton(8);
    }
    
    public boolean leftJoystickButton(){
        return getRawButton(9);
    }
    
    public boolean rightJoystickButton(){
        return getRawButton(10);
    }
    
    public double leftStickXAxis(){
        return getRawAxis(1);
    }
    
    public double leftStickYAxis(){
        return getRawAxis(2);
    }
    
    public double triggerAxis(){
        return getRawAxis(3);
    }
    
    public double rightStickXAxis(){
        return getRawAxis(4);
    }
    
    public double rightStickYAxis(){
        return getRawAxis(5);
    }
    
}
