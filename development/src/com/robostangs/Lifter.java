package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * Lifter class
 * maintainer: Sam
 */

public class Lifter {
  private static Lifter instance = getInstance();
  private CANJaguar lift;

  private Lifter() {

  }

  public static Lifter getInstance() {
    if (instance == null) {
      instance = new Lifter();
    }
    
    return instance;
  }

  public static void enable() {

  }

  public static void reverse() {

  }

  public static void stop() {

  }

}


