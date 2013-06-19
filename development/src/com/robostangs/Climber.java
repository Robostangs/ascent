/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.Servo;

/**
 *
 * @author wmd
 */
public class Climber {
    private static Climber instance = null;
	private static Servo leftServo;
    private static Servo rightServo;
    private static double initRight, initLeft;
    
	private Climber(){
        System.out.println("left servo pos? " + Constants.CLIMBER_LEFT_SERVO_POS);
        System.out.println("right servo pos? " + Constants.CLIMBER_RIGHT_SERVO_POS);
        //leftServo = new Servo(Constants.CLIMBER_LEFT_SERVO_POS);
        //rightServo = new Servo(Constants.CLIMBER_RIGHT_SERVO_POS);
        leftServo = new Servo(1);
        rightServo = new Servo(2);
        leftServo.set(1);
        initLeft = leftServo.get();
        initRight = rightServo.get();
	}
    
	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber();
		}
		return instance;
	}
    
        public static void holdInit() {
            System.out.println(initLeft + " " + initRight);
            leftServo.set(initLeft);
            rightServo.set(initRight);
        }
        
	public static void deploy() {
            leftServo.set(0);
            rightServo.set(1);
            /*
            if (leftServo.get() == 0) {
                leftServo.set(1);
            } else {
                leftServo.set(0);
            }
            if (rightServo.get() == 0) {
                rightServo.set(1);
            } else {
                rightServo.set(0);
            }       */
	}
    
	public static void retract() {
        leftServo.set(0);
        //rightServo.set(Constants.CLIMBER_RIGHT_IN_POS);
	}

    public static void manual(double input) {
        leftServo.set(input);
    }
    
}
