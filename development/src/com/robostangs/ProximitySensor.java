package com.robostangs;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;

public class ProximitySensor extends DigitalInput {
    private Solenoid powerSource;

    public ProximitySensor(int digitalPort, int solenoidPort) {
        super(1, digitalPort);
        powerSource = new Solenoid(solenoidPort);
        powerSource.set(true);  //give the prox sensor power
    }
}

