/**
 * Class to control the shooter arm
 * maintainer: Lauren
 */

package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
    private static Arm instance = null;
    private static Potentiometer potA, potB;
    private static ArmMotor motor;
    private static PIDController pidA, pidB, pidCam; 
    private static boolean useB = false;
    private static Timer timer;

    
    private Arm() {
        motor = ArmMotor.getInstance();
        potA = new Potentiometer(Constants.POT_A_PORT);
        //potB = new Potentiometer(Constants.POT_B_PORT);
        pidA = new PIDController(Constants.ARM_KP_A, Constants.ARM_KI_A, Constants.ARM_KD_A, potA, motor); 
        //pidB = new PIDController(Constants.ARM_KP_B, Constants.ARM_KI_B, Constants.ARM_KD_B, potB, motor);
        pidCam = new PIDController(Constants.ARM_KP_CAM, Constants.ARM_KI_CAM, Constants.ARM_KD_CAM, ArmCamera.getInstance(), motor);
        timer = new Timer();
        
        //configure PID
        pidA.setInputRange(Constants.ARM_PID_POT_MIN, Constants.ARM_PID_POT_MAX);
        pidA.setOutputRange(Constants.ARM_MIN_POWER, Constants.ARM_MAX_POWER);
        /*
        pidB.setInputRange(Constants.POT_B_MIN_VALUE, Constants.POT_B_MAX_VALUE);
        pidB.setOutputRange(Constants.ARM_MIN_POWER, Constants.ARM_MAX_POWER);
        */
        disablePID();
    }
    
    public static Arm getInstance() {
        if (instance == null) {
            instance = new Arm();
        }        
        return instance;
    }
    
    public static PIDController PIDInUse() {
        if (!useB) {
            return pidA;
        }
        else {
            return pidB;
        }
    }
    
    public static PIDController PIDNotInUse() {
        if (useB) {
            return pidA;
        }
        else {
            return pidB;
        }
    }
    
    /**
     * gets average value of pot A
     * @return potA.getAverageValue average value of pot A 
     */
    public static double getPotA() {
        return potA.getAverageValue(); 
    }    
    
    /**
     * gets average value of pot B
     * @return potB.getAverageValue average value of pot B
     */
    public static int getPotB() {
        return potB.getAverageValue(); 
    }
    
    /**
     * set angle equal to zero
     * retrieves value of getPotA or getPotB
     * subtracts it by the zero constant
     * multiplies everything by the constant that converts the values to degrees
     * @return angle angle of arm
     */
     public static double getAngle() {
        double angle = 0;
        if (!useB) {
            angle = (getPotA() - Constants.ARM_POT_A_ZERO) * Constants.POT_A_TO_DEGREES;
            return angle;
        } else {
            angle = (getPotB() - Constants.ARM_POT_B_ZERO) * Constants.POT_B_TO_DEGREES;
            return angle;
        }
     }
    
     /**
      * @param power power of arm jags
      * disables pid
      * sets the power of the arm jags
      */
    public static void setJags(double power) {
        double currentPot = getPotA();
        //add a buffer
        if (power > 0) {
            //arm is going up, give it a pot val one higher
            currentPot++;
        } else {
            //arm is going down, give it a pot val one lower
            currentPot--;
        }

        if (pidEnabled()) {
            disablePID();
        }

        if (power > 0) {
            if (getPotA() >= Constants.POT_A_MAX_VALUE) {
                motor.setX(0);
            } else {
                motor.setX(power);
            }
        } else if (power < 0) {
            if (getPotA() <= Constants.POT_A_MIN_VALUE) {
                motor.setX(0);
            } else {
                motor.setX(power);
            }
        } else {
            motor.setX(0);
        }
    }
    
    /**
     * For manual control
     * @param power 
     */
    public static void coarseDrive(double power) {
        setJags(power);
    }
    
    /**
     * For fine manual control
     * @param power 
     */
    public static void fineDrive(double power) {
        setJags(power / 2.0);
    }
    /**
     * @param potValue value of pot 
     * disables the other pot
     * sets the value of the pot
     * enables the pot
     * @return 0 if in progress, 1 if done
     */

    public static int setPosition(double potValue) { 
        if (onTarget()) {
            disablePID();
            return 1;
        }
        
        PIDNotInUse().disable();
        PIDInUse().setSetpoint(potValue);
        PIDInUse().enable();
        return 0;
    }
    
    /**
     * Uses PID to move to proper angle for pyramid shot
     * @return 0 if in progress, 1 if done
     */
    public static int underPyramidShotPos() {
        if (!useB) {
        return setPosition(Constants.ARM_PYRAMID_POS_A);
        }
        else {
        return setPosition(Constants.ARM_PYRAMID_POS_B);
        }
    }
    
    /**
     * Uses PID to move to flat angle
     * @return 0 if in progress, 1 if done
     */
    public static int lowestPos() {
        if (!useB) {
            return setPosition(Constants.ARM_POT_A_ZERO);
        }
        else {
            return setPosition(Constants.ARM_POT_B_ZERO);
        }
        
    }
    
    /**
     * Uses PID to move to proper angle for feeding from station
     * @return 0 if in progress, 1 if done
     */
    public static int feedPos() {
        if (!useB) {
            return setPosition(Constants.ARM_FEED_POS_A);
        }
        else {
            return setPosition(Constants.ARM_FEED_POS_B);
        }
        
    }
    
    /**
     * Uses the camera to set arm pos
     * @return 0 if in progress, 1 if done
     */
    public static int camPos() {
        if (pidCam.onTarget()) {
            return 1;
        }
        
        PIDInUse().disable();
        pidCam.setSetpoint(ArmCamera.getTarget());
        pidCam.enable();
        return 0;
    }

    public static int frontPyramidPos() {
        return setPosition(Constants.ARM_FRONT_PYRAMID_POS);
    }

    /**
     * Uses PID to move to proper angle for shooting from back of pyramid
     * @return 0 if in progress, 1 if done
     */
    public static int backPyramidPos() {
        return setPosition(Constants.ARM_BACK_PYRAMID_POS);
    }

    /**
     * Enables the pid
     */
    public static void enablePID() {
        pidA.enable();
    }
        
    public static int sidePyramidPos() {
        return setPosition(Constants.ARM_SIDE_PYRAMID_POS);
    }

    public static int pidHoldPos() {
        return setPosition(getPotA());
    }


    public static void outputPIDConstants() {

    }
    
    /**
     * checks if either pid is enabled
     * @return true if either pid is enabled, false if neither is enabled
     */
    public static boolean pidEnabled() {
        return pidA.isEnable() || pidB.isEnable() || pidCam.isEnable();   
    }
    
    /**
     * if either pid is enabled, it disables it
     */
    public static void disablePID() {
        if (PIDInUse().isEnable()) {
            PIDInUse().disable();
        }
        if (pidCam.isEnable()) {
            pidCam.disable();
        }
    }
    
    /**
     * 
     * @return true if either pid is on target, false if neither is
     */
    public static boolean onTarget() {
        return pidA.onTarget() || pidB.onTarget() || pidCam.onTarget();
    }
    
    /**
     * stops the arm motor
     */
    public static void stop() {
        setJags(0.0);
    }
    
    /**
     * makes pot A be in use by pid
     */
    public static void usePotA() {
        useB = false;
    }
    
    /**
     * makes potB be in use by pid
     */
    public static void usePotB() {
        useB = true;
    }
    
    /**
     * switches the pot used by the pid
     */
    public static void switchPot() {
        useB = !useB;        
    }
    
    /**
     * sends value of angle to SmartDashboard
     */
    public static void sendAngle() {
        SmartDashboard.putNumber("Angle: ", getAngle());
    }
    
    /**
     * sends the pot data from both pots to SmartDashboard
     */
    public static void sendPotData() {
        SmartDashboard.putNumber("Pot A: ", getPotA());
        SmartDashboard.putNumber("Pot B: ", getPotB());
    }
    /**
     * sends which pot is in use by pid to SmartDashboard
     */
    public static void sendWhichPotInUse() {
        if (useB) {
            SmartDashboard.putString("CURRENT POT: ", "POT B");
        } else {
            SmartDashboard.putString("CURRENT POT: ", "POT A");
        }
    } 
    
    /**
     * checks if pot A is within range
     * @return true if pot A is within range, false if it isn't
     */
    public boolean isPotAFunctional() {
        return getPotA() >= Constants.POT_A_MIN_VALUE  && getPotA() <= Constants.POT_A_MAX_VALUE;
    }
    
    public static void getPIDFromDash() {
        pidA.startLiveWindowMode();
        SmartDashboard.putData("PID: ", pidA);
    }     

}
