/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.robostangs;

import edu.wpi.first.wpilibj.IterativeRobot;
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
    private double potValue;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //avoid null pointers
        Log.getInstance();
        Arm.getInstance();
        ArmCamera.getInstance();
        Camera.getInstance();
        DriveCamera.getInstance();
        DriveTrain.getInstance();
        Loader.getInstance();
        Shooter.getInstance();
        driver = XboxDriver.getInstance();
        manip = XboxManip.getInstance();
        potValue = 0;
        dashInit();
    }

    public void dashInit() {
        //TODO: init dashboard things if needed
    }
    
    public void autonomousInit() {
        Autonomous.getInstance();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Autonomous.shootThree();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        sendDataToDash();
        //System.out.println("pot: " + Arm.getPotA());
        //System.out.println("Switch" + Lifter.getPos());
        //Arm.outputPIDConstants();
        //System.out.println("top sensor: " + Lifter.getTopSensor());
        //System.out.println("pot voltage " + Arm.getPotVoltage());
        //System.out.println("Left Encoder: " + DriveTrain.getLeftEncoderDistance());
        //System.out.println("Top?? " + Lifter.getTopSensor());
        //System.out.println("Bottom?? " + Lifter.getBottomSensor());
        System.out.println("shooter status: " + Shooter.readyToShoot());

        
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
            Shooter.shoot();
        } else if (manip.leftTriggerButton()) {
            Shooter.feed();
        } else {
            Shooter.stop();
        }
        
        /*
         * Manipulator Arm Control
         * Left Stick: Coarse Manual
         * Right Stick: Fine Manual
         * A: Back pyramid pos 
         * B: Side pyramid pos
         * Y: Front pyramid pos
         * X: Use Camera to auto-set angle
         * Start: raise by one pot value
         * Back: lower by one pot value
         */        
        if (manip.rightStickYAxis() == 0) {
            //not using the joysticks to manual set, use PID
            if (manip.yButton()) {
                Arm.frontPyramidPos();
            } else if (manip.aButton()) {
                Arm.backPyramidPos();
            } else if (manip.bButton()) {
                Arm.sidePyramidPos();
            } else if (manip.xButton()) {
                //Arm.camPos();
                Arm.getPIDFromDash();
                Arm.enablePID();
            }
            /*
            else if (manip.startButton()) {
                if (potValue == 0) {
                    potValue = Arm.getPotA() + 1;
                }
                Arm.setPosition(potValue);
            } else if (manip.backButton()) {
                if (potValue == 0) {
                    potValue = Arm.getPotA() - 1;
                }
                Arm.setPosition(potValue);
            }
            */
            else {
                potValue = 0;
                Arm.stop();
            }
        } else {
            if (Math.abs(manip.rightStickYAxis()) != 0) {
                //fine control
                Arm.fineDrive(manip.rightStickYAxis());
            } else {
                potValue = 0;
                Arm.stop();
            }
        }

        if (manip.startButton()) {
            //Lifter.timedUp();
            //Lifter.raise();
            Lifter.sensorUp();
        } else if (manip.backButton()) { 
            //Lifter.timedDown();
            Lifter.currentDown();
            //Lifter.lower();
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

        if (driver.yButton()) {
//           Camera.saveImage();
        }

        /*
         * manip moves climber arm
         */
        DriveTrain.moveClimber(manip.leftStickYAxis());

        /*
         * Shifting between drive mode and climb mode
         * Driver a-button: enable climbing mode
         * Driver b-button: enable drive mode
         */
        if (driver.aButton()) {
            DriveTrain.enableClimbMode();
        } else if (driver.bButton()) {
            DriveTrain.enableDriveMode();
        }
        
        /*
        if (DriveTrain.getMode() && !DriveTrain.servoReady()) {
            DriveTrain.enableClimbMode();
        } else if (!DriveTrain.getMode() && !DriveTrain.servoReady()) {
            DriveTrain.enableDriveMode();
        }*/ 
        /*
         *  TODO: If Driver Right Trigger, Enable Auto Align
         */
        //if (driver.rightTriggerButton()) {
            
        //} else {
            /*
             * Drive Slow if Left Trigger, otherwise drive normally
             */
            if (driver.leftTriggerButton()) {
                DriveTrain.driveSlow(driver.leftStickYAxis(), driver.rightStickYAxis());
            } else {
                DriveTrain.humanDrive(driver.leftStickYAxis(), driver.rightStickYAxis());
            }
        //}
    }
    
    /**
     * Sends data to the dashboard
     */
    public void sendDataToDash() {
        Arm.sendWhichPotInUse();
        Arm.sendPotData();
    }

    public void debugToConsole() {
        
    }

    public void debugToDash() {
        System.out.println("pot: " + Arm.getPotA());
        Arm.outputPIDConstants();
    }
}
