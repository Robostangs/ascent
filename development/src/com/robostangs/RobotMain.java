/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.robostangs;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    private XboxDriver driver;
    private XboxManip manip;
    private Timer timer;
    private boolean fullShootMode, driveAfterAuto;
    private double potValue;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //avoid null pointers
        Log.getInstance();
        ReadConstants.init();
        Constants.init();
        Arm.getInstance();
        ArmCamera.getInstance();
        Camera.getInstance();
        Conveyors.getInstance();
        Climber.getInstance();
        DriveCamera.getInstance();
        DriveTrain.getInstance();
        FrisbeeCounter.getInstance();
        Loader.getInstance();
        Shooter.getInstance();
        Autonomous.getInstance();
        timer = new Timer();
        driver = XboxDriver.getInstance();
        manip = XboxManip.getInstance();
        potValue = 0;
        fullShootMode = false;
        driveAfterAuto = false;
    }

    public void dashInit() {
    }
    
    public void autonomousInit() {
        Autonomous.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        //Autonomous.printMode();
        Autonomous.run();
    }

    public void teleopInit() {
        /*
        if (driveAfterAuto) {
            DriveTrain.drive(-0.35, -0.5);
            timer.stop();
            timer.reset();
            timer.start();
        }
        * */
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        sendDataToDash();
        //Arm.printPotData();
        //Arm.outputPIDConstants();
        //System.out.println("encoders: " + DriveTrain.getLeftEncoderRaw() + " " + DriveTrain.getRightEncoderRaw());
        System.out.println("switch: " + Lifter.atBottom());
        System.out.println("Lifter JAG CURRENTO: " + Lifter.getJagCurrent());
        System.out.println("Proximity Sensor: " + Lifter.getBottomSensor());
        /*
        if (driveAfterAuto && timer.get() < Constants.TELEOP_DRIVE_TIME) {
            DriveTrain.drive(-0.45, -0.5);
            System.out.println("driving after auto!!");
        } else {
            timer.stop();
        } 

        if (driver.leftStickYAxis() != 0 || driver.rightStickYAxis() != 0) {
            driveAfterAuto = false;
            System.out.println("\n\n\n\n MANUAL CONTROL \n\n\n\n");
        }
        * */
        
        /*
         * Manip loader control
         * Left Trigger: Feed
	 	 * R Bumper: Load shooter
         */
        if (manip.rBumper()) {
            Loader.loadShooter();
        } else if (manip.lBumper()) {
            Loader.feed();
        } else {
            Loader.stopShooterConveyor();
        }

        /*
         * Manip shooter control
         * R Trigger: Shoot
         * L Trigger: Feed
         */
        if (manip.rightTriggerButton()) { 
            if (fullShootMode) {
                Shooter.fullShoot();
            } else {
                Shooter.shoot();
            }
        } else if (manip.leftTriggerButton()) {
            Shooter.feed();
        } else {
            Shooter.stop();
        }
        
        if (manip.bButton()) {
            fullShootMode = false;
        } else if (manip.aButton()) {
            fullShootMode = true;
        }
        
        if (manip.rightStickYAxis() != 0) {
            //fine control
            Arm.fineDrive(manip.rightStickYAxis());
        } else if (manip.yButton()) {
            Arm.noPotDrive(.15);
        } else if (manip.xButton()) {
            Arm.noPotDrive(-.15);
        } else {
            potValue = 0;
            Arm.stop();
        }
        
        if (manip.leftStickYAxis() != 0) {
            Lifter.manual(manip.leftStickYAxis());
        } else if (manip.rightTriggerButton()) {
            Lifter.sensorUp();
        } else if (driver.lBumper()) {
            Lifter.sensorDown();
        } else {
            Lifter.stop();
        }
        
		/*
		 * Driver Ingestor Controls
		 */
    	if (driver.lBumper()) {
            Loader.ingest();
        } else if (driver.rBumper()) {
            Conveyors.exgest();
        } else {
            Loader.ingestorOff();
        } 

        if (driver.rightTriggerButton()) {
            Climber.deploy();
        } else {
            Climber.holdInit();
        }
        

        /*
         * Drive Slow if Left Trigger, otherwise drive normally
         */
        if (driver.leftTriggerButton()) {
            DriveTrain.driveSlow(driver.leftStickYAxis(), driver.rightStickYAxis());
        } else {
            DriveTrain.humanDrive(driver.leftStickYAxis(), driver.rightStickYAxis());
        }
    }
    
    /**
     * Sends data to the dashboard
     */
    public void sendDataToDash() {
        Arm.sendPotData();
        SmartDashboard.putNumber("Left Joystick:", driver.leftStickYAxis());
        SmartDashboard.putNumber("Right Joystick:", driver.rightStickYAxis());
        SmartDashboard.putNumber("Left Encoder: ", DriveTrain.getLeftEncoderDistance());
        SmartDashboard.putNumber("Right Encoder: ", DriveTrain.getRightEncoderDistance());
        SmartDashboard.putBoolean("shooting mode: ", fullShootMode);
    }
}
