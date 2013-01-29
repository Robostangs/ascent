/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robostangs;

import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 * @author sky
 * Three jags. needs to deal with feedMode
 */
public class Shooter {
    public static Shooter instance = null;
    private CANJaguar shooter;
    private boolean feedMode;
    
    private Shooter() {

    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }

        return instance;
    }

    public void shoot() {

    }

    public void feed() {

    }

    public void stop() {

    }

    public boolean isFeedMode() {
        return false;
    }

    public boolean readyToShoot() {
        return false;
    }
}
