package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * Lifter class
 * maintainer: Sam 
 */

public class Lifter {
    private static Lifter instance = null;
    private static CANJaguar lift;
    private static Timer timer;
    private static boolean atTop;
    private static boolean atBottom;
    private static boolean goingToTop;
    private static boolean goingToBottom;
    private static int downcount = 0, upCount = 0;
    private static DigitalInput limit;
    //private static ProximitySensor topProx;
    private static ProximitySensor bottomProx;

    private Lifter() { 
        try{
            lift = new CANJaguar(Constants.LIFTER_JAG_POS);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        limit = new DigitalInput(14);
        timer = new Timer();
        timer.stop();
        timer.reset();
        /*
        topProx = new ProximitySensor(Constants.LIFTER_TOP_PROX_DIGITAL_PORT, 
                  Constants.LIFTER_TOP_PROX_SOLENOID_PORT);
        */
        bottomProx = new ProximitySensor(10, 8);
        atTop = false;
        atBottom = true;
        goingToBottom = false;
        goingToTop = false;
    }
  
    public static Lifter getInstance() {
        if (instance == null) {
            instance = new Lifter();
        }
        return instance;
    }

    public static boolean atBottom() {
        return limit.get();
    }
   /**
    * Lifter goes up
    */
    public static void raise() {
        try {
            lift.setX(1);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

   /**
    * Lifter goes down
    */
    public static void lower() {
        try {
            lift.setX(-1);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void constantDown() {
        try {
            lift.setX(-0.15);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static void constantUp() {
        try {
            lift.setX(0.09);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @return the absolute value of the current on the lifter jag
     */
    public static double getJagCurrent() {
        try {
            return Math.abs(lift.getOutputCurrent());
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static void currentDown() {
        if(atTop) {
          atTop = false;
          lower();
        }

        System.out.println("jag current: " + getJagCurrent());
        if (getJagCurrent() >= 1.7 && !atBottom) {
          lower();
          goingToBottom = true;
          downcount++;
        } else {
          stop();
          atBottom = true;
          goingToBottom = false;
          downcount = 0;
        }
    }
    
    public static void currentUp() {
        System.out.println("current up!");
        atBottom = !getBottomSensor();
        if(atBottom) {
          atBottom = false;
          raise();
          System.out.println("if at bottom is true stuff");
        }

        System.out.println("jag current: " + getJagCurrent());
        if (getJagCurrent() <= 5.0 && !atTop) {
          System.out.println("jag current < 5");
          raise();
          goingToTop = true;
        } else if (atTop) {
            System.out.println("at top is true");
            constantUp();
        } else {
            System.out.println("current more than 5!!!");
          constantUp();
          atTop = true;
          goingToTop = false;
        }
    }

  public static void timedDown() {
      if(atTop) {
          timer.stop();
          timer.reset();
          timer.start();
          atTop = false;
      }

      if (timer.get() <= Constants.LIFTER_DOWN_TIME && !atBottom) {
          lower();
          goingToBottom = true;
      } else {
          stop();
          atBottom = true;
          goingToBottom = false;
      }
  }


  public static void timedUp() {
      if (atBottom) {
          timer.stop();
          timer.reset();
          timer.start();
          atBottom = false;
      }

      if (timer.get() <= Constants.LIFTER_UP_TIME && !atTop) {
          goingToTop = true;
          raise();
      } else {
          timer.stop();
          stop();
          atTop = true;
          goingToTop = false;
      }
  }

  /*
  public static boolean getTopSensor() {
      return topProx.get();
  }*/

  public static boolean getBottomSensor() {
      return bottomProx.get();
  }
  /*
  public static void sensorUp() {
      if (topProx.get()) {
          raise();
      } else {
          stop();
      }
  }*/

  public static void sensorDown() { 
      if (bottomProx.get()) {
          lower();
      } else {
          stop();
          atTop = false;
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

  public static void manual(double speed) {
      timer.stop();
      atTop = false;
      atBottom = !getBottomSensor();
      /*
      if (goingToBottom || atBottom) {
          atTop = false;
      } else if (goingToTop || atTop) {
          atBottom = false;
      }
      */
      try {
          lift.setX(speed);
      } catch (CANTimeoutException ex) {
          ex.printStackTrace();
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
