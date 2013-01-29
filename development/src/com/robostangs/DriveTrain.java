/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author sky
 * DT has 6 jags, encoder on each side, and a gyro
 * Needs basic drive, turning, human drive, methods to deal with sensors, driveSlow
 */
public class DriveTrain {
    private static DriveTrain instance = null;
    private CANJaguar leftFront, leftMid, leftBack, rightFront, rightMid, 
            rightBack, climber;
    private Encoder leftEncoder, rightEncoder;
    private Gyro gyro;
    private boolean climbMode;
    
    private DriveTrain() {
        
    }
    
    public static DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        
        return instance;
    }
    
    public void drive(double leftPower, double rightPower) {
        
    }
    
    public void turn(double power) {
        
    }
    
    public void humanDrive(double leftPower, double rightPower) {
        
    }
    
    public void arcadeDrive(double power, double angle) {
        
    }
    
    public void driveSlow(double leftPower, double rightPower) {
        
    }
    
    public void driveStraight(double power, double angle) {
        
    }
    
    public int driveStraight(double power, double angle, double distance) {
        return -1;
    }
    
    public int turn(double power, double angle) {
        return -1;
    }
    
    public int driveArc(double power, double x, double y) {
        return -1;
    }
    
    public int driveToPosition(double power, double x, double y) {
        return -1;
    }
    
    public double getLeftEncoderDistance() {
        return 0;
    }
    
    public double getRightEncoderDistance() {
        return 0;
    }
    
    public double getAngle() {
        return 0;
    }
    
    public void resetEncoders() {
        
    }
    
    public void sendEncoders() {
        
    }
    
    public void sendGyro() {
        
    }
    
    public boolean getMode() {
        return false;
    }
    
    public void enableClimbMode() {
        
    }
}
