package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author sky
 * Combines 2 jags into one PID output
 */
public class MotorOutput implements PIDOutput {    
    private CANJaguar jag1, jag2;
    
    public MotorOutput(CANJaguar one, CANJaguar two) {
        //don't declare new jags
        jag1 = one;
        jag2 = two;
    }
    
    public void set(double speed) {
        try {
            jag1.setX(speed);
            jag2.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void pidWrite(double output) {
        try {
            jag1.setX(output);
            jag2.setX(output);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
