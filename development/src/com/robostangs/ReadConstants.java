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
    private static String[] defKeys = new String[103];
    private static double[] defConstants = new double[103];

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
        defConstants[4] = 7;
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
        defKeys[15] = "ARM_KP_AUTON";
        defConstants[15] = 0.0161;
        defKeys[16] = "ARM_KI_AUTON";
        defConstants[16] = 0.0;
        defKeys[17] = "ARM_KD_AUTON";
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
        defKeys[30] = "CLIMBER_RIGHT_SERVO_POS";
        defConstants[30] = 1;
        defKeys[31] = "CLIMBER_LEFT_SERVO_POS";
        defConstants[31] = 2;
        defKeys[32] = "CLIMBER_LEFT_OUT_POS";
        defConstants[32] = 0.0;
        defKeys[33] = "CLIMBER_RIGHT_OUT_POS";
        defConstants[33] = 1.0;
        defKeys[34] = "CLIMBER_LEFT_IN_POS";
        defConstants[34] = 1.0;
        defKeys[35] = "CLIMBER_RIGHT_IN_POS";
        defConstants[35] = 0.0;
        defKeys[36] = "CONV_SHOOT_JAG_POS";
        defConstants[36] = 12;
        defKeys[37] = "CONV_INGEST_JAG_POS";
        defConstants[37] = 13;
        defKeys[38] = "CONV_SHOOTER_POWER";
        defConstants[38] = 1.0;
        defKeys[39] = "CONV_INGEST_POWER";
        defConstants[39] = 1.0;
        defKeys[40] = "CAM_Y_OFFSET";
        defConstants[40] = 0;
        defKeys[41] = "CAM_X_OFFSET";
        defConstants[41] = 0;
        defKeys[42] = "DT_LEFT_ENCODER_FRONT";
        defConstants[42] = 2;
        defKeys[43] = "DT_LEFT_ENCODER_BACK";
        defConstants[43] = 3;
        defKeys[44] = "DT_RIGHT_ENCODER_FRONT";
        defConstants[44] = 5;
        defKeys[45] = "DT_RIGHT_ENCODER_BACK";
        defConstants[45] = 6;
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
        defKeys[54] = "DT_STRAIGHT_LEFT_INC";
        defConstants[54] = 0.0;
        defKeys[55] = "DT_STRAIGHT_LEFT_DEC";
        defConstants[55] = 0.0;
        defKeys[56] = "DT_STRAIGHT_RIGHT_INC";
        defConstants[56] = 0.0;
        defKeys[57] = "DT_STRAIGHT_RIGHT_DEC";
        defConstants[57] = 0.0;
        defKeys[58] = "DT_DELAY_TIME";
        defConstants[58] = 1.0;
        defKeys[59] = "DT_PID_K_P";
        defConstants[59] = 0.0;
        defKeys[60] = "DT_PID_K_I";
        defConstants[60] = 0.0;
        defKeys[61] = "DT_PID_K_D";
        defConstants[61] = 0.0;
        defKeys[62] = "LIFTER_JAG_POS";
        defConstants[62] = 10;
        defKeys[63] = "LIFTER_UP_POWER";
        defConstants[63] = 1.0;
        defKeys[64] = "LIFTER_DOWN_POWER";
        defConstants[64] = 1.0;
        defKeys[65] = "LIFTER_SLOW_UP_POWER";
        defConstants[65] = 0.09;
        defKeys[66] = "LIFTER_SLOW_DOWN_POWER";
        defConstants[66] = 0.15;
        defKeys[67] = "LIFTER_TOP_PROX_DIGITAL_PORT";
        defConstants[67] = 9;
        defKeys[68] = "LIFTER_TOP_PROX_SOLENOID_PORT";
        defConstants[68] = 1;
        defKeys[69] = "LIFTER_BOTTOM_PROX_DIGITAL_PORT";
        defConstants[69] = 10;
        defKeys[70] = "LIFTER_BOTTOM_PROX_SOLENOID_PORT";
        defConstants[70] = 8;
        defKeys[71] = "SHOOTER_JAG1_POS";
        defConstants[71] = 2;
        defKeys[72] = "SHOOTER_JAG2_POS";
        defConstants[72] = 1;
        defKeys[73] = "SHOOTER_JAG3_POS";
        defConstants[73] = 3;
        defKeys[74] = "SHOOTER_MAX_POWER";
        defConstants[74] = 1.0;
        defKeys[75] = "SHOOTER_FEED_POWER";
        defConstants[75] = 0.22;
        defKeys[76] = "XBOX_DRIVER_DRIFT";
        defConstants[76] = 0.15;
        defKeys[77] = "XBOX_DRIVER_PORT";
        defConstants[77] = 1;
        defKeys[78] = "XBOX_MANIP_PORT";
        defConstants[78] = 2;
        defKeys[79] = "XBOX_MANIP_DRIFT";
        defConstants[79] = 0.1;
        defKeys[80] = "ARM_POT_B_PORT";
        defConstants[80] = 2;
        defKeys[81] = "ARM_POT_B_MIN_VALUE";
        defConstants[81] = 47;
        defKeys[82] = "ARM_POT_B_MAX_VALUE";
        defConstants[82] = 700;
        defKeys[83] = "SHOOTER_OUTER_WHEEL_POWER";
        defConstants[83] = 0.65;
        defKeys[84] = "SHOOTER_INNER_WHEEL_POWER";
        defConstants[84] = 0.75;
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
        double x = 0;
        for(int j = 0; j < defKeys.length; j++) {
                if (defKeys[j].equalsIgnoreCase(key)) {
                    x = defConstants[j];
                }
        }
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
        
        return x;
    }

    public static int findInt(String key) {
        int x = 0;
        for(int j = 0; j < defKeys.length; j++) {
                if (defKeys[j].equalsIgnoreCase(key)) {
                    x = (int) defConstants[j];
                }
        }
        
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
        
        return x;
    }
}
