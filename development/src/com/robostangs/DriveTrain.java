/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author sky
 * DT has 6 jags, encoder on each side, and a gyro
 * Needs basic drive, turning, human drive, methods to deal with sensors, driveSlow
 */
public class DriveTrain {
    private CANJaguar leftFront, leftMid, leftBack, rightFront, rightMid, rightBack;
    private Encoder leftEncoder, rightEncoder;
    private Gyro gyro;
}
