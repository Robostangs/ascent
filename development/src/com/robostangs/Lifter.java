package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 * Lifter class
 * maintainer: 
 */

public class Lifter {
  private static Lifter instance = null;
  private CANJaguar lift;

  private Lifter() {

  }

  public static Lifter getInstance() {
    if (instance == null) {
      instance = new Lifter();
    }
    
    return instance;
  }

  public void enable() {

  }

  public void reverse() {

  }

  public void stop() {

  }

}


