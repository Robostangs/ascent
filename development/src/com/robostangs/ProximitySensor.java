package com.robostangs;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;

public class ProximitySensor {
    private DigitalInput sensor;
    private Solenoid power;

    public ProximitySensor(int digitalPort, int solenoidPort) {
        power = new Solenoid(solenoidPort);
        sensor = new DigitalInput(digitalPort);
    }

    public void turnOn() {
        power.set(true);
    }

    public void turnOff() {
        power.set(false);
    }

    public boolean getState() {
        return sensor.get();
    }
}

