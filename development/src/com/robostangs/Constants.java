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
     * StopWatch
     */
    public static final double MICRO_TO_BASE = 0.0;

    /*
     * Arm
     */
    public static final int POT_A_PORT = 0;
    public static final int POT_B_PORT = 0;
    public static final int POT_MIN_VALUE = 0;
    public static final int POT_MAX_VALUE = 0;
    public static final double INIT_POT_VALUE = 0.0;
    public static final double POT_IN_DEGREES = 0.0;
    // arm pid
    public static final double ARM_KP_A = 0.0;
    public static final double ARM_KI_A = 0.0;
    public static final double ARM_KD_A = 0.0;
    public static final double ARM_KP_B = 0.0;
    public static final double ARM_KI_B = 0.0;
    public static final double ARM_KD_B = 0.0;

    /*
     * Loading System
     */
    public static final int INGEST_RELAY = 0;
    public static final int LIFTER_JAG = 0;
    public static final int INGEST_CONV_JAG = 0;
    public static final int SHOOT_CONV_JAG = 0;
    
    /*
     * DriveTrain
     */
    //Position
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
}
