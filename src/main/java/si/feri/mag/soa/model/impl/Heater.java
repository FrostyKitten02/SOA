package si.feri.mag.soa.model.impl;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import si.feri.mag.soa.model.Device;
import si.feri.mag.soa.model.IHeater;
import si.feri.mag.soa.model.sensors.impl.TimerSensor;

@SuperBuilder
public class Heater extends Device implements IHeater {
    //ambient temperature sensor
    @Getter
    private float ambientTemperature = 22;
    @Getter
    private float temperature = 22;

    //timer sensor
    private final TimerSensor timer = new TimerSensor();

    public void setTimer(long timeMins) {
        timer.setTimer(timeMins);
    }

    public int getTimerRemaining() {
        return timer.getTimerRemaining();
    }
}
