package si.feri.mag.soa.model.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import si.feri.mag.soa.model.Device;
import si.feri.mag.soa.model.sensors.IAmbientTemperatureSensor;
import si.feri.mag.soa.model.sensors.ITimer;

@SuperBuilder
public class AirConditioner extends Device implements ITimer, IAmbientTemperatureSensor {
    @Getter
    @Setter
    private float temperature = 22;
    //ambient temperature sensor
    @Getter
    private float ambientTemperature = 22;

    //timer sensor
    private final TimerSensor timer = new TimerSensor();

    public void setTimer(long timeMins) {
        timer.setTimer(timeMins);
    }

    public int getTimerRemaining() {
        return timer.getTimerRemaining();
    }
}
