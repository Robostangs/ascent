/**
 * Class to control the shooter arm
 * maintainer: Lauren
 */

package com.robostangs;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
    private static Arm instance = null;
    private static Potentiometer pot;
    private static Potentiometer potB;
    private static ArmMotor motor;
    private static PIDController pidA; // pidCam;
    private static PIDController pidB;
    private static boolean useB;
    
    private Arm() {
        pot = new Potentiometer(Constants.ARM_POT_PORT);
        potB = new Potentiometer(Constants.ARM_POT_B_PORT);
        useB = false;
        motor = ArmMotor.getInstance();
        pidA = new PIDController(Constants.ARM_KP_MED, Constants.ARM_KI_MED, Constants.ARM_KD_MED, pot, motor);
        //pidCam = new PIDController(Constants.ARM_KP_CAM, Constants.ARM_KI_CAM, Constants.ARM_KD_CAM, ArmCamera.getInstance(), motor);
        
        //configure PID
        pidA.setInputRange(Constants.ARM_POT_MIN_VALUE, Constants.ARM_POT_MAX_VALUE);
        pidA.setOutputRange(Constants.ARM_MIN_POWER, Constants.ARM_MAX_POWER);
        pidA.setAbsoluteTolerance(3);
        //pidCam.setInputRange(Constants.ARM_POT_MIN_VALUE, Constants.ARM_POT_MAX_VALUE);
        //pidCam.setOutputRange(Constants.ARM_MIN_POWER, Constants.ARM_MAX_POWER);
        pidB = new PIDController(Constants.ARM_KP_MED, Constants.ARM_KI_MED, Constants.ARM_KD_MED, pot, motor);
        //pidCam = new PIDController(Constants.ARM_KP_CAM, Constants.ARM_KI_CAM, Constants.ARM_KD_CAM, ArmCamera.getInstance(), motor);
        
        //configure PID
        pidB.setInputRange(Constants.ARM_POT_MIN_VALUE, Constants.ARM_POT_MAX_VALUE);
        pidB.setOutputRange(Constants.ARM_MIN_POWER, Constants.ARM_MAX_POWER);
        pidB.setAbsoluteTolerance(3);
        
        disablePID();
    }
    
    public static Arm getInstance() {
        if (instance == null) {
            instance = new Arm();
        }        
        return instance;
    }
    
    public static Potentiometer getActivePot() {
        if (useB) {
            return potB;
        } else {
            return pot;
        }
    }
    public static PIDController getActivePID() {
        if (useB) {
            return pidB;
        } else {
            return pidA;
        }
    }
    /**
     * gets average value of pot A
     * @return pot.getAverageValue average value of pot A 
     */
    public static double getPot() {
        return getActivePot().getAverageValue();
    }    

    /**
     * @param power of arm jags
     * disables pid
     * sets the power of the arm jags
     */
    public static void setJags(double power) {
        double currentPot = getPot();
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

        if (currentPot >= Constants.ARM_POT_MAX_VALUE && power > 0) {
            System.out.println("AT MAX");
            power = 0.0;
        } else if (currentPot <= Constants.ARM_POT_MIN_VALUE && power < 0) {
            System.out.println("AT MIN");
            power = 0.0;
        }

        motor.setX(power);
     }
    
    /**
     * For fine manual control
     * @param power 
     */
    public static void fineDrive(double power) {
        setJags(power / 2.0);
    }

    /**
     * Sets the PID controller to move to a certain position
     * @param potValue value of pot 
     * @return 0 if in progress, 1 if done
     */

    public static int setPosition(double potValue) { 
        if (onTarget()) {
            return 1;
        }

        getActivePID().setSetpoint(potValue);
        getActivePID().enable();
        
        return 0;
    }
    
    public static void setPIDSmall() {
	    getActivePID().setPID(Constants.ARM_KP_SMALL, Constants.ARM_KI_SMALL, Constants.ARM_KD_SMALL);
    }

    public static void setPIDMedium() {
	    getActivePID().setPID(Constants.ARM_KP_MED, Constants.ARM_KI_MED, Constants.ARM_KD_MED);
    }

    public static void setPIDLarge() {
	    getActivePID().setPID(Constants.ARM_KP_LARGE, Constants.ARM_KI_LARGE, Constants.ARM_KD_LARGE);
    }

    public static void shootingPos() {
        setPosition(Constants.ARM_SHOOTING_POS);
    }
    /**
     * Uses PID to hold the current position
     */
    public static void pidHoldPos() {
        setPosition(getPot());
    }

    /**
     * Enables the pid
     */
    public static void enablePID() {
        getActivePID().enable();
    }

    /**
     * checks if either pid is enabled
     * @return true if either pid is enabled, false if neither is enabled
     */
    public static boolean pidEnabled() {
        return getActivePID().isEnable(); // || pidCam.isEnable();   
    }
    
    /**
     * if either pid is enabled, it disables it
     */
    public static void disablePID() {
        if (getActivePID().isEnable()) {
            getActivePID().disable();
        }
        /*
        if (pidCam.isEnable()) {
            pidCam.disable();
        }
        * */
    }
    
    /**
     * @return true if either pid is on target, false if neither is
     */
    public static boolean onTarget() {
        return getActivePID().onTarget(); // || pidCam.onTarget();
    }
    
    /**
     * stops the arm motor
     */
    public static void stop() {
        setJags(0.0);
    }
    
    /**
     * sends the pot data from both pots to SmartDashboard
     */
    public static void sendPotData() {
        SmartDashboard.putNumber("PotA: ", getPot());
        SmartDashboard.putNumber("PotB: ", potB.getAverageValue());
    }
    
    /**
     * checks if pot A is within range
     * @return true if pot A is within range, false if it isn't
     */
    public static boolean isPotFunctional() {
        return getPot() >= Constants.ARM_POT_MIN_VALUE  && getPot() <= Constants.ARM_POT_MAX_VALUE;
    }

    public static void getPIDFromDash() {
        getActivePID().startLiveWindowMode();
        SmartDashboard.putData("PID: ", getActivePID());
    }

    public static void outputPIDConstants() {
        System.out.println("KP: " + getActivePID().getP());
        System.out.println("KI: " + getActivePID().getI());
        System.out.println("KD: " + getActivePID().getD());
        System.out.println("setpoint:" + getActivePID().getSetpoint());
    }

    /**
     * Uses the camera to set arm pos
     * @return 0 if in progress, 1 if done
     *
    public static int camPos() {
        if (pidCam.onTarget()) {
            return 1;
        }
        if (getActivePID().isEnable()) {
            getActivePID().disable();
        }
        pidCam.setSetpoint(ArmCamera.getTarget());
        pidCam.enable();
        return 0;
    }*/

    public static void checkPots() {

    }
}
