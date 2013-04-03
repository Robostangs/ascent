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
    private static String inputFileName = "constants.txt";
    private static ReadConstants instance;
    private static Vector contents;
    private static String[] keys;
    private static double[] constants;

    private ReadConstants() {
        contents = new Vector();
        String line = "";
        try {
            FileConnection fc = (FileConnection) Connector.open("file:///" + inputFileName, Connector.READ); 
            BufferedReader in = new BufferedReader(new InputStreamReader(fc.openInputStream()));

            while ((line = in.readLine()) != null) {
                contents.addElement(line);
            }

            fc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.write("unable to read constants from file");
        }

        contents.trimToSize();
        keys = new String[contents.size()];
        constants = new double[contents.size()];

        processText();
    }

    public static void processText() {
        int commaPos = 0;
        String line = "";
        for (int i = 0; i < contents.size(); i++) {
            line = (String) contents.elementAt(i);
            commaPos = line.indexOf(",", i);
            keys[i] = line.substring(0, commaPos);
            constants[i] = Double.parseDouble(line.substring(commaPos + 1, line.length()));
        }
    }

    public static void init() {
        if (instance == null) {
            instance = new ReadConstants();
        }
    }
    
    public static int findKey(String key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equalsIgnoreCase(key)) {
                return i;
            }
        }

        return -1;
    }

    public static double findDouble(String key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equalsIgnoreCase(key)) {
                return constants[i];
            }
        }

        return -1;
    }

    public static int findInt(String key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equalsIgnoreCase(key)) {
                return (int) constants[i];
            }
        }

        return -1;
    }
    
    public static double getJagConfigTime() {
        double x = findDouble("JAG_CONFIG_TIME");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.JAG_CONFIG_TIME;
        }
    }

    public static double getBennettConstant() {
        double x = findDouble("BENNETT_CONSTANT");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.BENNETT_CONSTANT;
        }
    }

    public static int getArmJagPos() {
        int x = findInt("ARM_JAG_POS");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_JAG_POS;
        }
    }

    public static int getArmPotPos() {
        int x = findInt("ARM_POT_PORT");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_POT_PORT;
        }
    }

    public static int getArmPotMinValue() {
        int x = findInt("ARM_POT_MIN_VALUE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_POT_MIN_VALUE;
        }
    }

    public static int getArmPotMaxValue() {
        int x = findInt("ARM_POT_MAX_VALUE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_POT_MAX_VALUE;
        }
    }

    public static int getArmPotSlowValue() {
        int x = findInt("ARM_POT_SLOW_VALUE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_POT_SLOW_VALUE;
        }
    }

    public static double getArmMinVoltage() {
        double x = findDouble("ARM_MIN_VOLTAGE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_MIN_VOLTAGE;
        }
    }

    public static double getArmKPSmall() {
        double x = findDouble("ARM_KP_SMALL");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KP_SMALL;
        }
    }

    public static double getArmKISmall() {
        double x = findDouble("ARM_KI_SMALL");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KI_SMALL;
        }
    }

    public static double getArmKDSmall() {
        double x = findDouble("ARM_KD_SMALL");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KD_SMALL;
        }
    }

    public static double getArmKPMed() {
        double x = findDouble("ARM_KP_MED");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KP_MED;
        }
    }

    public static double getArmKIMed() {
        double x = findDouble("ARM_KI_MED");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KI_MED;
        }
    }

    public static double getArmKDMed() {
        double x = findDouble("ARM_KD_MED");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KD_MED;
        }
    }

    public static double getArmKPLarge() {
        double x = findDouble("ARM_KP_LARGE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KP_LARGE;
        }
    }

    public static double getArmKILarge() {
        double x = findDouble("ARM_KI_LARGE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KI_LARGE;
        }
    }

    public static double getArmKDLarge() {
        double x = findDouble("ARM_KD_LARGE");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KD_LARGE;
        }
    }

    public static double getArmKPCam() {
        double x = findDouble("ARM_KP_CAM");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KP_CAM;
        }
    }

    public static double getArmKICam() {
        double x = findDouble("ARM_KI_CAM");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KI_CAM;
        }
    }

    public static double getArmKDCam() {
        double x = findDouble("ARM_KD_CAM");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_KD_CAM;
        }
    }

    public static double getArmMinPower() {
        double x = findDouble("ARM_MIN_POWER");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_MIN_POWER;
        }
    }

    public static double getArmMaxPower() {
        double x = findDouble("ARM_MAX_POWER");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_MAX_POWER;
        }
    }

    public static double getArmPIDPotMin() {
        double x = findDouble("ARM_MAX_POWER");
        if (x != -1) {
            return x;
        } else {
            return DefaultConstants.ARM_MAX_POWER;
        }
    }
}
