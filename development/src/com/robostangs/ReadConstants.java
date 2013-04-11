package com.robostangs;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.microedition.io.Connector;

/**
 *
 * @author sky
 */
public class ReadConstants {
    private static ReadConstants instance = null;
    private static String inputFileName = "constants.txt";
    private static Vector contents;
    private static String[] keys;
    private static double[] constants;
    private static String[] defKeys = new String[102];
    private static double[] defConstants = new double[102];

    private ReadConstants() {
        contents = new Vector();
        String line = "";
        try {
            FileConnection fc = (FileConnection) Connector.open("file:///" + inputFileName, Connector.READ); 
            BufferedReader in = new BufferedReader(new InputStreamReader(fc.openInputStream()));

            while ((line = in.readLine()) != null) {
                if (line.indexOf("=") != -1) { 
                    contents.addElement(line);
                }
            }

            fc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        contents.trimToSize();
        keys = new String[contents.size()];
        constants = new double[contents.size()];

        processText();
        
        defKeys[0] = "JAG_CONFIG_TIME";
        defConstants[0] = 0.5;
        defKeys[1] = "BENNETT_CONSTANT";
        defConstants[1] = 0.44;
        defKeys[2] = "TELEOP_DRIVE_TIME";
        defConstants[2] = 1.5;
        defKeys[3] = "ARM_JAG_POS";
        defConstants[3] = 11;
        defKeys[4] = "ARM_POT_PORT";
        defConstants[4] = 6;
        defKeys[5] = "ARM_POT_MIN_VALUE";
        defConstants[5] = 47;
        defKeys[6] = "ARM_POT_MAX_VALUE";
        defConstants[6] = 700;
        defKeys[7] = "ARM_POT_SLOW_VALUE";
        defConstants[7] = 330;
        defKeys[8] = "ARM_MIN_VOLTAGE";
        defConstants[8] = 0.07;
        defKeys[9] = "ARM_KP_SMALL";
        defConstants[9] = 0.0128;
        defKeys[10] = "ARM_KI_SMALL";
        defConstants[10] = 0.0000009;
        defKeys[11] = "ARM_KD_SMALL";
        defConstants[11] = 0.0;
        defKeys[12] = "ARM_KP_MED";
        defConstants[12] = 0.0157;
        defKeys[13] = "ARM_KI_MED";
        defConstants[13] = 0.00000001;
        defKeys[14] = "ARM_KD_MED";
        defConstants[14] = 0.0;
        defKeys[15] = "ARM_KP_LARGE";
        defConstants[15] = 0.0161;
        defKeys[16] = "ARM_KI_LARGE";
        defConstants[16] = 0.0;
        defKeys[17] = "ARM_KD_LARGE";
        defConstants[17] = 0.0;
        defKeys[18] = "ARM_KP_CAM";
        defConstants[18] = 0.0;
        defKeys[19] = "ARM_KI_CAM";
        defConstants[19] = 0.0;
        defKeys[20] = "ARM_KD_CAM";
        defConstants[20] = 0.0;
        defKeys[21] = "ARM_MIN_POWER";
        defConstants[21] = -0.95;
        defKeys[22] = "ARM_MAX_POWER";
        defConstants[22] = 0.95;
        defKeys[23] = "ARM_PID_POT_MIN";
        defConstants[23] = 318;
        defKeys[24] = "ARM_PID_POT_MAX";
        defConstants[24] = 725;
        defKeys[25] = "ARM_SHOOTING_POS";
        defConstants[25] = 430;
        defKeys[26] = "ARM_START_POS";
        defConstants[26] = 600;
        defKeys[27] = "AUTON_DRIVE_POWER";
        defConstants[27] = 0.3;
        defKeys[28] = "AUTON_TURN_POWER";
        defConstants[28] = 0.5;
        defKeys[29] = "AUTON_ARM_POS";
        defConstants[29] = defConstants[25];
        defKeys[30] = "CLIMBER_JAG_POS";
        defConstants[30] = 14;
        defKeys[31] = "CLIMBER_JAG_POWER";
        defConstants[31] = 0.5;
        defKeys[32] = "CLIMBER_LEFT_OUT_POS";
        defConstants[32] = 1.0;
        defKeys[33] = "CLIMBER_RIGHT_OUT_POS";
        defConstants[33] = 1.0;
        defKeys[34] = "CLIMBER_LEFT_IN_POS";
        defConstants[34] = 0.0;
        defKeys[35] = "CLIMBER_RIGHT_IN_POS";
        defConstants[35] = 0.0;
        defKeys[36] = "CONV_SHOOT_JAG_POS";
        defConstants[36] = 12;
        defKeys[37] = "CONV_INGEST_JAG_POS";
        defConstants[37] = 13;
        defKeys[38] = "CONV_SHOOTER_POWER";
        defConstants[38] = 1.0;
        defKeys[39] = "CONV_INGEST_POWER";
        defConstants[39] = 0.95;
        defKeys[40] = "CAM_Y_OFFSET";
        defConstants[40] = 0;
        defKeys[41] = "CAM_X_OFFSET";
        defConstants[41] = 0;
        defKeys[42] = "DT_LEFT_ENCODER_FRONT";
        defConstants[42] = 2;
        defKeys[43] = "DT_LEFT_ENCODER_BACK";
        defConstants[43] = 3;
        defKeys[44] = "DT_RIGHT_ENCODER_FRONT";
        defConstants[44] = 4;
        defKeys[45] = "DT_RIGHT_ENCODER_BACK";
        defConstants[45] = 5;
        defKeys[46] = "DT_GYRO_POS";
        defConstants[46] = 0;
        defKeys[47] = "DT_JAG_LEFT_FRONT_POS";
        defConstants[47] = 4;
        defKeys[48] = "DT_JAG_LEFT_MID_POS";
        defConstants[48] = 5;
        defKeys[49] = "DT_JAG_LEFT_BACK_POS";
        defConstants[49] = 6;
        defKeys[50] = "DT_JAG_RIGHT_FRONT_POS";
        defConstants[50] = 7;
        defKeys[51] = "DT_JAG_RIGHT_MID_POS";
        defConstants[51] = 8;
        defKeys[52] = "DT_JAG_RIGHT_BACK_POS";
        defConstants[52] = 9;
        defKeys[53] = "DT_JAG_CLIMB_POS";
        defConstants[53] = 14;
        defKeys[54] = "DT_SERVO_POS";
        defConstants[54] = 1;
        defKeys[55] = "DT_CLIMB_POS";
        defConstants[55] = 180;
        defKeys[56] = "DT_DRIVE_POS";
        defConstants[56] = 0;
        defKeys[57] = "DT_STRAIGHT_LEFT_INC";
        defConstants[57] = 0.0;
        defKeys[58] = "DT_STRAIGHT_LEFT_DEC";
        defConstants[58] = 0.0;
        defKeys[59] = "DT_STRAIGHT_RIGHT_INC";
        defConstants[59] = 0.0;
        defKeys[60] = "DT_STRAIGHT_RIGHT_DEC";
        defConstants[60] = 0.0;
        defKeys[61] = "DT_CONV_VOLT_TO_M_PER_SEC";
        defConstants[61] = 0.0;
        defKeys[62] = "DT_DELAY_TIME";
        defConstants[62] = 1.0;
        defKeys[63] = "DT_PID_K_P";
        defConstants[63] = 0.0;
        defKeys[64] = "DT_PID_K_I";
        defConstants[64] = 0.0;
        defKeys[65] = "DT_PID_K_D";
        defConstants[65] = 0.0;
        defKeys[66] = "INGEST_SWITCH_POS";
        defConstants[66] = 0;
        defKeys[67] = "SHOOT_SWITCH_POS";
        defConstants[67] = 0;
        defKeys[68] = "LIFT_SWITCH_POS";
        defConstants[68] = 0;
        defKeys[69] = "INGEST_FRISBEE_TIMER";
        defConstants[69] = 0.0;
        defKeys[70] = "LIFT_FRISBEE_TIMER";
        defConstants[70] = 0.0;
        defKeys[71] = "SHOOT_FRISBEE_TIMER";
        defConstants[71] = 0.0;
        defKeys[72] = "INGEST_RELAY_POS";
        defConstants[72] = 0;
        defKeys[73] = "LIFTER_JAG_POS";
        defConstants[73] = 10;
        defKeys[74] = "LIFTER_UP_POWER";
        defConstants[74] = 0.4;
        defKeys[75] = "LIFTER_DOWN_POWER";
        defConstants[75] = 0.8;
        defKeys[76] = "LIFTER_UP_TIME";
        defConstants[76] = 2;
        defKeys[77] = "LIFTER_DOWN_TIME";
        defConstants[77] = 1.6;
        defKeys[78] = "LIFTER_TOP_PROX_DIGITAL_PORT";
        defConstants[78] = 2;
        defKeys[79] = "LIFTER_TOP_PROX_SOLENOID_PORT";
        defConstants[79] = 2;
        defKeys[80] = "LIFTER_BOTTOM_PROX_DIGITAL_PORT";
        defConstants[80] = 1;
        defKeys[81] = "LIFTER_BOTTOM_PROX_SOLENOID_PORT";
        defConstants[81] = 1;
        defKeys[82] = "SHOOTER_JAG1_POS";
        defConstants[82] = 2;
        defKeys[83] = "SHOOTER_JAG2_POS";
        defConstants[83] = 1;
        defKeys[84] = "SHOOTER_JAG3_POS";
        defConstants[84] = 3;
        defKeys[85] = "SHOOTER_MAX_POWER";
        defConstants[85] = 1.0;
        defKeys[86] = "SHOOTER_FEED_POWER";
        defConstants[86] = 0.22;
        defKeys[87] = "TIME_TO_SHOOT_ONE";
        defConstants[87] = 5.0;
        defKeys[88] = "TIME_TO_SHOOT_TWO";
        defConstants[88] = 0.0;
        defKeys[89] = "TIME_TO_SHOOT_THREE";
        defConstants[89] = 0.0;
        defKeys[90] = "TIME_TO_SHOOT_FOUR";
        defConstants[90] = 0.0;
        defKeys[91] = "SHOOTER_FULL_BATTERY_VOLTAGE";
        defConstants[91] = 14.0;
        defKeys[92] = "SHOOTER_VOLTAGE_TOLERANCE";
        defConstants[92] = 0.0;
        defKeys[93] = "SHOOTER_READY_CURRENT_MAX";
        defConstants[93] = 0.0;
        defKeys[94] = "SHOOTER_READY_CURRENT_MIN";
        defConstants[94] = 0.0;
        defKeys[95] = "MICRO_TO_BASE";
        defConstants[95] = 0.0;
        defKeys[96] = "XBOX_DRIVER_DRIFT";
        defConstants[96] = 0.2;
        defKeys[97] = "XBOX_DRIVER_PORT";
        defConstants[97] = 1;
        defKeys[98] = "XBOX_MANIP_PORT";
        defConstants[98] = 2;
        defKeys[99] = "XBOX_MANIP_DRIFT";
        defConstants[99] = 0.15;
        defKeys[100] = "ARM_POT_B_PORT";
        defConstants[100] = 7;
        defKeys[101] = "ARM_POT_B_MIN_VALUE";
        defConstants[101] = 47;
        defKeys[102] = "ARM_POT_B_MAX_VALUE";
        defConstants[102] = 700;
    }

    public static void init() {
        if (instance == null) {
            instance = new ReadConstants();
        }
    }
    
    public static void processText() {
        int equalPos = 0;
        String line = "";
        for (int i = 0; i < contents.size(); i++) {
            line = (String) contents.elementAt(i);
            equalPos = line.indexOf("=");
            keys[i] = line.substring(0, equalPos);
            constants[i] = Double.parseDouble(line.substring(equalPos + 1, line.length()));
            System.out.println("key " + i + " " + keys[i]);
            System.out.println("constant " + i + " " + constants[i]);
        }
    }

    public static int findKey(String key) {
        try {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i].equalsIgnoreCase(key)) {
                    return i;
                }
            }
        } catch (NullPointerException ex) {            
            for(int j = 0; j < defKeys.length; j++) {
                if (defKeys[j].equalsIgnoreCase(key)) {
                    return j;
                }
            }
        }
        
        return -1;
    }

    public static double findDouble(String key) {
        try {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i].equalsIgnoreCase(key)) {
                    return constants[i];
                }
            }
        } catch (NullPointerException ex) {
            for(int j = 0; j < defKeys.length; j++) {
                if (defKeys[j].equalsIgnoreCase(key)) {
                    return defConstants[j];
                }
            }
        }

        return -1;
    }

    public static int findInt(String key) {
        try {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i].equalsIgnoreCase(key)) {
                    return (int) constants[i];
                }
            }
        } catch (NullPointerException ex) {
            for(int j = 0; j < defKeys.length; j++) {
                if (defKeys[j].equalsIgnoreCase(key)) {
                    return (int) defConstants[j];
                }
            }
        }
        
        return -1;
    }
}