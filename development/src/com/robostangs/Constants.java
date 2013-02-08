/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

/**
 *
 * @author sky
 * TODO: everything pretty much.
 */
public class Constants {

    /*
     * Arm
     */
    public static final int POT_A_PORT = 0;
    public static final int POT_B_PORT = 0;
    public static final int POT_MIN_VALUE = 0;
    public static final int POT_MAX_VALUE = 0;
    public static final double ARM_POT_ZERO = 0.0;
    public static final double POT_TO_DEGREES = 0.0;
    
    // Arm PID
    public static final double ARM_KP_A = 0.0;
    public static final double ARM_KI_A = 0.0;
    public static final double ARM_KD_A = 0.0;
    public static final double ARM_KP_B = 0.0;
    public static final double ARM_KI_B = 0.0;
    public static final double ARM_KD_B = 0.0;

    /*
     * Conveyors
     */
    public static final int CONV_INGEST_JAG_POS = 0;
    public static final int CONV_SHOOT_JAG_POS = 0;
    public static final double CONV_POWER = 0.0;   

    /*
     * DriveTrain
     */
    //Positions
    public static final int DT_LEFT_ENCODER_FRONT = 0;
    public static final int DT_LEFT_ENCODER_BACK = 0;
    public static final int DT_RIGHT_ENCODER_FRONT = 0;
    public static final int DT_RIGHT_ENCODER_BACK = 0;
    public static final int DT_GYRO_POS = 0;
    public static final int DT_JAG_POS = 0;
    public static final int DT_JAG_CLIMB_POS = 0;
    public static final double DT_JAG_CONFIG_TIME = 0.5;
    
    //Drive Straight
    public static final double DT_STRAIGHT_LEFT_INC = 0.0;
    public static final double DT_STRAIGHT_LEFT_DEC = 0.0;
    public static final double DT_STRAIGHT_RIGHT_INC = 0.0;
    public static final double DT_STRAIGHT_RIGHT_DEC = 0.0;

    //Timer
    public static final double DT_CONV_VOLT_TO_M_PER_SEC = 0.0;
    public static final double DT_DELAY_TIME = 1.0;
    
    /*
     * FrisbeeTracker
     */
    //Positions
    public static final int INGEST_SWITCH_POS = 0;
    public static final int SHOOT_SWITCH_POS = 0;
    public static final int LIFT_SWITCH_POS = 0;
    
    //Timers
    public static final double INGEST_FRISBEE_TIMER = 0.0;
    public static final double LIFT_FRISBEE_TIMER = 0.0;
    public static final double SHOOT_FRISBEE_TIMER = 0.0;
    
    /*
     * Ingestor
     */
    public static final int INGEST_RELAY_POS = 0;
    
    /*
     * Lifter
     */
    public static final int LIFTER_JAG_POS = 0;
    public static final double LIFTER_POWER = 0.0;
        
    /*
     * Shooter
     */ 
    public static final int SHOOTER_JAG_POS = 0;
    public static final double SHOOTER_MAX_POWER = 1.0;
    public static final double SHOOTER_FEED_POWER = 0.0;
    
    /*
     * StopWatch
     */
    public static final double MICRO_TO_BASE = 0.0;
    
    /*
     * XboxController
     */
    //Driver
    public static final double XBOX_DRIVER_DRIFT = 0.1;
    public static final int XBOX_DRIVER_PORT = 0;
    
    //Manipulator
    public static final int XBOX_MANIP_PORT = 0;
    public static final double XBOX_MANIP_DRIFT = 0.1;
    


}
