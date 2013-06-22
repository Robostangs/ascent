package com.robostangs;

/**
 *
 * @author sky
 * TODO: everything pretty much.
 */
public class Constants {
    private static Constants instance = null;
    
    private Constants() {
        ReadConstants.init();
    }

    public static void init() {
        if (instance == null) {
            instance = new Constants();
        }
    }
    /*
     * Used in multiple classes
     */
    public static final double JAG_CONFIG_TIME = ReadConstants.findDouble("JAG_CONFIG_TIME");
    public static final double BENNETT_CONSTANT = ReadConstants.findDouble("BENNETT_CONSTANT");
    public static final double TELEOP_DRIVE_TIME = ReadConstants.findDouble("TELEOP_DRIVE_TIME");

    /*
     * Arm
     */
    public static final int ARM_JAG_POS = ReadConstants.findInt("ARM_JAG_POS");
    public static final int ARM_POT_PORT = ReadConstants.findInt("ARM_POT_PORT");
    public static final int ARM_POT_MIN_VALUE = ReadConstants.findInt("ARM_POT_MIN_VALUE");
    public static final int ARM_POT_MAX_VALUE = ReadConstants.findInt("ARM_POT_MAX_VALUE");
    public static final int ARM_POT_B_PORT = ReadConstants.findInt("ARM_POT_B_PORT");
    public static final int ARM_POT_B_MIN_VALUE = ReadConstants.findInt("ARM_POT_B_MIN_VALUE");
    public static final int ARM_POT_B_MAX_VALUE = ReadConstants.findInt("ARM_POT_MAX_B_VALUE");
    public static final int ARM_POT_SLOW_VALUE = ReadConstants.findInt("ARM_POT_SLOW_VALUE");
    public static final double ARM_MIN_VOLTAGE = ReadConstants.findDouble("ARM_MIN_VOLTAGE");
    // Arm PID
    public static final double ARM_KP_SMALL = ReadConstants.findDouble("ARM_KP_SMALL");
    public static final double ARM_KI_SMALL = ReadConstants.findDouble("ARM_KI_SMALL");
    public static final double ARM_KD_SMALL = ReadConstants.findDouble("ARM_KD_SMALL");
    public static final double ARM_KP_MED = ReadConstants.findDouble("ARM_KP_MED");
    public static final double ARM_KI_MED = ReadConstants.findDouble("ARM_KI_MED");
    public static final double ARM_KD_MED = ReadConstants.findDouble("ARM_KD_MED");
    public static final double ARM_KP_AUTON = ReadConstants.findDouble("ARM_KP_AUTON");
    public static final double ARM_KI_AUTON = ReadConstants.findDouble("ARM_KI_AUTON");
    public static final double ARM_KD_AUTON = ReadConstants.findDouble("ARM_KD_AUTON");
    public static final double ARM_KP_CAM = ReadConstants.findDouble("ARM_KP_CAM");
    public static final double ARM_KI_CAM = ReadConstants.findDouble("ARM_KI_CAM");
    public static final double ARM_KD_CAM = ReadConstants.findDouble("ARM_KD_CAM");
    public static final double ARM_MIN_POWER = ReadConstants.findDouble("ARM_MIN_POWER");
    public static final double ARM_MAX_POWER = ReadConstants.findDouble("ARM_MAX_POWER");
    public static final double ARM_PID_POT_MIN = ReadConstants.findDouble("ARM_PID_POT_MIN");
    public static final double ARM_PID_POT_MAX = ReadConstants.findDouble("ARM_PID_POT_MAX");

    /**
     * 0.015
     * 1.0 * 10 ^ -6
     * 0
     */
    
    /**
     * 0.0151
     * 1.0 * 10 ^ -8
     * 0
     */
    /**
     * 0.012
     * 1.0 * 10 ^ -6
     * 0
     */
    //positions
    public static final double ARM_SHOOTING_POS = ReadConstants.findDouble("ARM_SHOOTING_POS");
    public static final double ARM_START_POS = ReadConstants.findDouble("ARM_START_POS");

    /*
     * Autonomous
     */
    public static final double AUTON_DRIVE_POWER = ReadConstants.findDouble("AUTON_DRIVE_POWER");
    public static final double AUTON_TURN_POWER = ReadConstants.findDouble("AUTON_TURN_POWER");
    public static final double AUTON_ARM_POS = ARM_SHOOTING_POS;
    
    /*
     * Climber
     */
    public static final int CLIMBER_RIGHT_SERVO_POS = ReadConstants.findInt("CLIMBER_RIGHT_SERVO_POS");
    public static final int CLIMBER_LEFT_SERVO_POS = ReadConstants.findInt("CLIMBER_LEFT_SERVO_POS");
    public static final double CLIMBER_LEFT_OUT_POS = ReadConstants.findDouble("CLIMBER_LEFT_OUT_POS");
    public static final double CLIMBER_RIGHT_OUT_POS = ReadConstants.findDouble("CLIMBER_RIGHT_OUT_POS");
    public static final double CLIMBER_LEFT_IN_POS = ReadConstants.findDouble("CLIMBER_LEFT_IN_POS");
    public static final double CLIMBER_RIGHT_IN_POS = ReadConstants.findDouble("CLIMBER_RIGHT_IN_POS");
    
    /*
     * Conveyors
     */
    public static final int CONV_SHOOT_JAG_POS = ReadConstants.findInt("CONV_SHOOT_JAG_POS");
    public static final int CONV_INGEST_JAG_POS = ReadConstants.findInt("CONV_INGEST_JAG_POS");
    public static final double CONV_SHOOTER_POWER = ReadConstants.findDouble("CONV_SHOOTER_POWER");
    public static final double CONV_INGEST_POWER = ReadConstants.findDouble("CONV_INGEST_POWER");
    
    /*
     * Camera
     */
    public static final int CAM_Y_OFFSET = ReadConstants.findInt("CAM_Y_OFFSET");
    public static final int CAM_X_OFFSET = ReadConstants.findInt("CAM_X_OFFSET");

    /*
     * DriveTrain
     */
    //Positions
    public static final int DT_LEFT_ENCODER_FRONT = ReadConstants.findInt("DT_LEFT_ENCODER_FRONT");
    public static final int DT_LEFT_ENCODER_BACK = ReadConstants.findInt("DT_LEFT_ENCODER_BACK");
    public static final int DT_RIGHT_ENCODER_FRONT = ReadConstants.findInt("DT_RIGHT_ENCODER_FRONT");
    public static final int DT_RIGHT_ENCODER_BACK = ReadConstants.findInt("DT_RIGHT_ENCODER_BACK");
    public static final int DT_GYRO_POS = ReadConstants.findInt("DT_GYRO_POS");
    public static final int DT_JAG_LEFT_FRONT_POS = ReadConstants.findInt("DT_JAG_LEFT_FRONT_POS");
    public static final int DT_JAG_LEFT_MID_POS = ReadConstants.findInt("DT_JAG_LEFT_MID_POS");
    public static final int DT_JAG_LEFT_BACK_POS = ReadConstants.findInt("DT_JAG_LEFT_BACK_POS");
    public static final int DT_JAG_RIGHT_FRONT_POS = ReadConstants.findInt("DT_JAG_RIGHT_FRONT_POS");
    public static final int DT_JAG_RIGHT_MID_POS = ReadConstants.findInt("DT_JAG_RIGHT_MID_POS");
    public static final int DT_JAG_RIGHT_BACK_POS = ReadConstants.findInt("DT_JAG_RIGHT_BACK_POS");
    //Need to Test
    public static final double DT_STRAIGHT_LEFT_INC = ReadConstants.findDouble("DT_STRAIGHT_LEFT_INC");
    public static final double DT_STRAIGHT_LEFT_DEC = ReadConstants.findDouble("DT_STRAIGHT_LEFT_DEC");
    public static final double DT_STRAIGHT_RIGHT_INC = ReadConstants.findDouble("DT_STRAIGHT_RIGHT_INC");
    public static final double DT_STRAIGHT_RIGHT_DEC = ReadConstants.findDouble("DT_STRAIGHT_RIGHT_DEC");
    public static final double DT_DELAY_TIME = ReadConstants.findDouble("DT_DELAY_TIME");
    public static final double DT_ENCODER_TOLERANCE = ReadConstants.findDouble("DT_ENCODER_TOLERANCE");
    
    /*
     * Lifter
     */
    public static final int LIFTER_JAG_POS = ReadConstants.findInt("LIFTER_JAG_POS");
    public static final double LIFTER_UP_POWER = ReadConstants.findDouble("LIFTER_UP_POWER");
    public static final double LIFTER_DOWN_POWER = ReadConstants.findDouble("LIFTER_DOWN_POWER");
    public static final double LIFTER_SLOW_UP_POWER = ReadConstants.findDouble("LIFTER_SLOW_UP_POWER");
    public static final double LIFTER_SLOW_DOWN_POWER = ReadConstants.findDouble("LIFTER_SLOW_DOWN_POWER");
    public static final int LIFTER_TOP_PROX_DIGITAL_PORT = ReadConstants.findInt("LIFTER_TOP_PROX_DIGITAL_PORT");
    public static final int LIFTER_TOP_PROX_SOLENOID_PORT = ReadConstants.findInt("LIFTER_TOP_PROX_SOLENOID_PORT");
    public static final int LIFTER_BOTTOM_PROX_DIGITAL_PORT = ReadConstants.findInt("LIFTER_BOTTOM_PROX_DIGITAL_PORT");
    public static final int LIFTER_BOTTOM_PROX_SOLENOID_PORT = ReadConstants.findInt("LIFTER_BOTTOM_PROX_SOLENOID_PORT");
        
    /*
     * Shooter
     */ 
    public static final int SHOOTER_JAG1_POS = ReadConstants.findInt("SHOOTER_JAG1_POS");
    public static final int SHOOTER_JAG2_POS = ReadConstants.findInt("SHOOTER_JAG2_POS");
    public static final int SHOOTER_JAG3_POS = ReadConstants.findInt("SHOOTER_JAG3_POS");

    public static final double SHOOTER_MAX_POWER = ReadConstants.findDouble("SHOOTER_MAX_POWER");
    public static final double SHOOTER_FEED_POWER = ReadConstants.findDouble("SHOOTER_FEED_POWER");
    public static final double SHOOTER_OUTER_WHEEL_POWER = ReadConstants.findDouble("SHOOTER_OUTER_WHEEL_POWER");
    public static final double SHOOTER_INNER_WHEEL_POWER = ReadConstants.findDouble("SHOOTER_INNER_WHEEL_POWER");
    
    /*
     * XboxController
     */
    //Driver
    public static final double XBOX_DRIVER_DRIFT = ReadConstants.findDouble("XBOX_DRIVER_DRIFT");
    public static final int XBOX_DRIVER_PORT = ReadConstants.findInt("XBOX_DRIVER_PORT");
    
    //Manipulator
    public static final int XBOX_MANIP_PORT = ReadConstants.findInt("XBOX_MANIP_PORT");
    public static final double XBOX_MANIP_DRIFT = ReadConstants.findDouble("XBOX_MANIP_DRIFT");
}
