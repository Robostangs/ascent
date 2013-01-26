/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;

/**
 *
 * @author sky
 * Arm is run with two jags, two pots (one back up and one main)
 * Needs PIDcontrol, manual control (should check to make sure PID is disabled)
 * Needs to be able to switch pots
 */
public class Arm {
    private Potentiometer mainPot, backUpPot;
    private CANJaguar jag1, jag2;
    private MotorOutput motors;
    private PIDController pid, backUpPid;
    private boolean useBackUp; 
    
    
}
