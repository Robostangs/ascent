package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Lifter class
 * maintainer: Sam 
 * TODO: lifter: timers to always fully raise/lower lift
 */

public class Lifter {
  private static Lifter instance = null;
  private static CANJaguar lift;
  private static StopWatch timer;
  private static boolean atTop;
  private static DigitalInput topSwitch;
  private static DigitalInput bottomSwitch;

  private Lifter() { 
      try{
        lift = new CANJaguar(Constants.LIFTER_JAG_POS);
      } catch (CANTimeoutException ex) {
          ex.printStackTrace();
      }
      timer = new StopWatch();
      timer.stop();
      timer.reset();
      atTop = false;
  }
  
  public static Lifter getInstance() {
    if (instance == null) {
      instance = new Lifter();
    }
    return instance;
  }

  /**
   * Lifter goes up
   */
  public static void raise() {
      //if (topSwitch.get()){
        try {
            lift.setX(Constants.LIFTER_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
      //} else {
       //   stop();
      //}
  }

  /**
   * Move the lifter from bottom to top using a timer
   * @return 0 if running, 1 if done 
   */
  public static int timedUp() {
      timer.start();
	  if (timer.getSeconds() >= Constants.LIFTER_UP_TIME || atTop) {
		  stop();
		  timer.stop();
		  timer.reset();
		  atTop = true;
		  return 1;
	  } else {
          raise();
          System.out.println("UP TIMER:" + timer.getSeconds());
          return 0;
      }
  }

  /**
   * Move the lifter from bottom to top using a timer
   * @return 0 if running, 1 if done 
   */
  public static int timedDown() {
      timer.start();
	  if (timer.getSeconds() >= Constants.LIFTER_DOWN_TIME || !atTop) {
		  stop();
		  timer.stop();
		  timer.reset();
		  atTop = false;
		  return 1;
	  } else {
          lower();
          System.out.println("DOWN TIMER:" + timer.getSeconds());
          return 0;
      }
  }

  /**
   * Lifter goes down
   */
  public static void lower() {
      //if (bottomSwitch.get()){
        try {
            lift.setX(-Constants.LIFTER_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
      //} else {
          //stop();
      //}
  }

  /*
   * 
   */
  public static void switchUp() {
      if (topSwitch.get()) {
          stop();
      } else {
          raise();
      }
  }

  public static void switchDown() {
      if (bottomSwitch.get()) {
          stop();
      } else {
          lower();
      }
  }

  /**
   * Stops the lifter
   */
  public static void stop() {
        try {
            lift.setX(0.0);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
  }

  /**
   * @return true if at top, false if at bottom
   */
  public static boolean getPos() {
      if (topSwitch == null) {
          return atTop;
      } else {
          return topSwitch.get();
      }
  }
}
