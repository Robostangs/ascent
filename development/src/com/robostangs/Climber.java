/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author wmd
 */
public class Climber {
	private static CANJaguar windowMotor;
	private static Climber instance = null;
	private Climber(){
        try {
            windowMotor = new CANJaguar(Constants.CLIMBER_JAG_POS);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	}
	public static Climber getInstance() {
		if(instance == null) {
			instance = new Climber();
		}
		return instance;
	}
	public static void deploy() {
        try {
            windowMotor.setX(Constants.CLIMBER_JAG_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
	}
	public static void retract() {
        try {
            windowMotor.setX(-Constants.CLIMBER_JAG_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	}
    public static void stop() {
        try {
            windowMotor.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
