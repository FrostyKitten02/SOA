package si.feri.mag.soa.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Heater extends Device {
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
