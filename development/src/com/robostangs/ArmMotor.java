package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author sky
 * Combines the 2 arm jags into one PID output
 * possibly defunct; there might only be one motor on the shooter-arm
 * if that is confirmed, this class will be deleted.
 */
public class ArmMotor implements PIDOutput {    
    private static ArmMotor instance = null;
    private static CANJaguar jag1;
    
    private ArmMotor() {
        //TODO: Constants
        try {
            jag1 = new CANJaguar(Constants.ARM_JAG_POS);
        } catch (CANTimeoutException ex) {

        }
    }

    public static ArmMotor getInstance() {
        if (instance == null) {
            instance = new ArmMotor();
        }

        return instance;
    }
    
    public static void setX(double speed) {
        try {
            jag1.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void pidWrite(double output) {
        try {
            jag1.setX(output);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
