package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Lifter class
 * maintainer: Sam 
 * TODO: lifter: timers to always fully raise/lower lift
 */

public class Lifter {
  private static Lifter instance = null;
  private static CANJaguar lift;
  private static Timer timer;
  private static boolean atTop;
  //private static DigitalInput topSwitch;
  private static DigitalInput bottomSwitch;

  private Lifter() { 
      try{
        lift = new CANJaguar(Constants.LIFTER_JAG_POS);
      } catch (CANTimeoutException ex) {
          ex.printStackTrace();
      }
      timer = new Timer();
      //topSwitch = new DigitalInput(1, Constants.LIFTER_TOP_SWITCH_POS);
      bottomSwitch = new DigitalInput(1, Constants.LIFTER_BOTTOM_SWITCH_POS);
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
        try {
            lift.setX(Constants.LIFTER_POWER);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
  }

  /**
   * Lifter goes down
   */
  public static void lower() {
    try {
        lift.setX(-Constants.LIFTER_POWER);
    } catch (CANTimeoutException ex) {
        ex.printStackTrace();
    }
  }

  public static void constantDown() {
      try {
          lift.setX(-0.2);
      } catch (CANTimeoutException ex) {
          ex.printStackTrace();
      }
  }

  /**
   * Move the lifter from bottom to top using a timer
   * @return 0 if running, 1 if done 
   */
  public static int timedUp() {
      timer.start();
	  if (timer.get() >= Constants.LIFTER_UP_TIME || atTop) {
		  stop();
		  timer.stop();
		  timer.reset();
		  atTop = true;
		  return 1;
	  } else {
          raise();
          System.out.println("UP TIMER:" + timer.get());
          return 0;
      }
  }

  /**
   * Move the lifter from bottom to top using a timer
   * @return 0 if running, 1 if done 
   */
  public static int timedDown() {
      timer.start();
	  if (timer.get() >= Constants.LIFTER_DOWN_TIME || !atTop) {
		  stop();
		  timer.stop();
		  timer.reset();
		  atTop = false;
		  return 1;
	  } else {
          lower();
          System.out.println("DOWN TIMER:" + timer.get());
          return 0;
      }
  }

  /**
   * @return 0 if in progress, 1 if done 
   *
  public static int switchUp() {
      if (topSwitch.get()) {
          raise();
          return 0;
      } else {
          stop();
          return 1;
      }
  }
  */
  //TODO: implement
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
   *
  public static boolean getPos() {
      if (topSwitch == null) {
          return atTop;
      } else {
          return topSwitch.get();
      }
  }
  
  public static boolean getTopSwitch() {
      return topSwitch.get();
  }
  */
  public static boolean getBottomSwitch() {
      return bottomSwitch.get();
  }

  public static void manual(double speed) {
      if (speed > 0) {
  
          /*
          if (topSwitch.get()) {
              try {
                  lift.setX(0);
              } catch (CANTimeoutException ex) {
                  ex.printStackTrace();
              }
          } else {*/
              try {
                  lift.setX(speed);
              } catch (CANTimeoutException ex) {
                  ex.printStackTrace();
//              }
          }
      } else if (speed < 0) {
          if (bottomSwitch.get()) {
              try {
                  lift.setX(0);
              } catch (CANTimeoutException ex) {
                  ex.printStackTrace();
              }
          } else {
              try {
                  lift.setX(speed);
              } catch (CANTimeoutException ex) {
                  ex.printStackTrace();
              }
          }
      } else {
          try {
              lift.setX(0);
          } catch (CANTimeoutException ex) {
              ex.printStackTrace();
          }
      }
  }

  public static double getSetSpeed() {
      try {
          return lift.getX();
      } catch (CANTimeoutException ex) {
          ex.printStackTrace();
      } finally {
          return 0;
      }
  }
}
