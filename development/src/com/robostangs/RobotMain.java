/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.robostangs;

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
        Arm.printPotData();
        Arm.outputPIDConstants();
        System.out.println("Left: " + DriveTrain.getLeftEncoderDistance());
        System.out.println("Right: " + DriveTrain.getRightEncoderDistance());
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
        
        /*
         * Manipulator Arm Control
         * Right Stick: Fine Manual
         * Y: Shooting Angle
         * X: Use Camera to auto-set angle
         *        
        if (manip.rightStickYAxis() == 0) {
            //not using the joysticks to manual set, use PID
            if (manip.yButton()) {
                Arm.shootingPos();
            } else if (manip.xButton()) {
                //Arm.camPos();
                Arm.getPIDFromDash();
                Arm.enablePID();
            } else if (manip.startButton()) {
                if (potValue == 0) {
                    potValue = Arm.getPot();
                }
                Arm.setPosition(potValue + 5);
            } else if (manip.backButton()) {
                if (potValue == 0) {
                    potValue = Arm.getPot();
                }
                Arm.setPosition(potValue - 5);
            } else {
                potValue = 0;
                Arm.stop();
            }
        } else {
        */
            if (Math.abs(manip.rightStickYAxis()) != 0) {
                //fine control
                Arm.fineDrive(manip.rightStickYAxis());
            } else {
                potValue = 0;
                Arm.stop();
            }
        //}
        if(manip.startButton()) {
            Conveyors.ingest();
        } else if(manip.backButton()) {
            Conveyors.exgest();
        } else {
            Conveyors.stopIngest();
        }
        
        if (manip.leftStickYAxis() != 0) {
            Lifter.manual(manip.leftStickYAxis());
        } else {
            Lifter.stop();
        }
        
		/*
		 * Driver Ingestor Controls
		 *
    	if (driver.lBumper()) {
            Loader.ingest();
        } else if (driver.rBumper()) {
            Conveyors.exgest();
        } else {
            Loader.ingestorOff();
        } */

        if (driver.rightTriggerButton()) {
            Climber.deploy();
        } else if (driver.rBumper()) {
            Climber.retract();
        } 

        /*
         * Drive Slow if Left Trigger, otherwise drive normally
         */
        if (driver.leftTriggerButton()) {
            DriveTrain.driveSlow(driver.leftStickYAxis(), driver.rightStickYAxis());
        } else {
            DriveTrain.humanDrive(driver.leftStickYAxis(), driver.rightStickYAxis());
        }
        
        /*
         * automatic drive thru?
         * after the 4th frisbee goes through the limit switch,
         * ingests it, lifts up the loader, and gets the shooter ready
         * human control overrides this method or it should
         *
        if (FrisbeeCounter.full()) {
            timer.start();
            if (timer.get() < 2) {
                if(!manip.startButton() || !manip.backButton()) {
                    Conveyors.ingest();
                }
            } else if (timer.get() < 10) {
                if (manip.leftStickYAxis() == 0) {
                    Loader.liftUp();
                }
                if (!manip.rBumper() || !manip.lBumper()) {
                    Loader.loadShooter();
                }
            } else {
                Loader.allOff();
                timer.stop();
                timer.reset();
            }
        }
        */
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
