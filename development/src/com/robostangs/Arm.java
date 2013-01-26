/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

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
    public Arm ()
    {
        try {
            mainPot = new Potentiometer(Constants.ARM_MAIN_POT);
            backUpPot = new Potentiometer(Constants.ARM_BACKUP_POT);
            jag1 = new CANJaguar(Constants.ARM_JAG1_POS);
            jag2 = new CANJaguar(Constants.ARM_JAG2_POS);
            motors = new MotorOutput(jag1, jag2);
            pid  = new PIDController(Constants.ARM_PID_KP, Constants.ARM_PID_KI, Constants.ARM_PID_KD, mainPot, motors);
            backUpPid = new PIDController(Constants.ARM_PID_KP, Constants.ARM_PID_KI, Constants.ARM_PID_KD, backUpPot, motors);
            pid.setInputRange(Constants.ARM_PID_MIN, Constants.ARM_PID_MAX);
            backUpPid.setInputRange(Constants.ARM_PID_MIN, Constants.ARM_PID_MAX);
            pid.setOutputRange(Constants.ARM_POW_MIN, Constants.ARM_POW_MAX);
            backUpPid.setOutputRange(Constants.ARM_POW_MIN, Constants.ARM_POW_MAX);
            pid.disable();
            backUpPid.disable();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
    }
    //enable backup POT and PID
    public void useBackUp()
    {
        useBackUp = true;
     }
    //disable backup POT and PID
    public void disableBackUp()
    {
        useBackUp = false;
    }
    //manually set the speed of the arm motors and disable PID
    public void speedManual(double speed)
    {
        if (pid.isEnable()){
            pid.disable();
        } else if (backUpPid.isEnable()){
            backUpPid.disable();
        }
        
        if (getAngle() < Constants.ARM_ANGLE_MIN) {
            motors.set(speed * Constants.ARM_POWER_COEFF);
        } else {
            motors.set(0);
        }
        if (getAngle() > Constants.ARM_ANGLE_MAX) {
            motors.set(speed * Constants.ARM_POWER_COEFF);
        } else {
            motors.set(0);
        }
    }
    //get the angle of the arm
    public double getAngle()
    {
        double angle;
        if (useBackUp = false) {
            angle = (mainPot.getPotentiometer() - Constants.);
        } else {
            angle = (backUpPot.getPotentiometer() - Constants.);   
        }
        return angle;
    }
}
