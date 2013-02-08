/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
 * Development group's code. please don't change anything unless you have the permission of the maintainer
 * Maintainer list:
 * @sky : RobotMain
 */
package com.robostangs;

/*
 * This class is maintained by @sky! (Sydney) 
 * Do not make any changes unless you have her explicit permission!
 */
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //avoid null pointers
        Arm.getInstance();
        Autonomous.getInstance();
        Camera.getInstance();
        DriveTrain.getInstance();
        FrisbeeTracker.getInstance();
        Loader.getInstance();
        Shooter.getInstance();
        XboxDriver.getInstance();
        XboxManip.getInstance();
    }
    
    public void autonomousInit() {
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
                
        /*
         * Driver left trigger: Run the standalone climber
         */
        if (XboxDriver.leftTriggerButton()) {
            //TODO: enable solo climber
        }
        
        /*
         * Shifting between drive mode and climb mode
         * Driver a-button: enable climbing mode
         * Driver b-button: enable drive mode
         */
        if (XboxDriver.aButton()) {
            DriveTrain.enableClimbMode();
        } else if (XboxDriver.bButton()) {
            DriveTrain.enableDriveMode();
        }
        
        /*
         * TODO: Driver Right Bumper *OR* Manip Start: Take Picture
         */
        if (XboxDriver.rBumper() || XboxManip.startButton()) {
            
        }
        /*
         *  TODO: If Driver Right Trigger, Enable Auto Align
         */
        if (XboxDriver.rightTriggerButton()) {
            
        } else {
            /*
             * Drive Slow if Left Bumper, otherwise drive normally
             */
            if (XboxDriver.lbumper()) {
                DriveTrain.driveSlow(XboxDriver.leftStickYAxis(), XboxDriver.rightStickYAxis());
            } else {
                DriveTrain.humanDrive(XboxDriver.leftStickYAxis(), XboxDriver.rightStickYAxis());
            }
        }
    }
    
}
