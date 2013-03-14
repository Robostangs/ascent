import edu.first.wpilib.Solenoid;
import edu.first.wpilib.DigitalInput;

public class ProximitySensor extends DigitalInput {
    private Solenoid powerSource;

    public ProximitySensor(int digitalPort, int solenoidPort) {
        super(port);
        powerSource = new Solenoid(solenoidPort);
        powerSource.set(true);  //give the prox sensor power
    }

