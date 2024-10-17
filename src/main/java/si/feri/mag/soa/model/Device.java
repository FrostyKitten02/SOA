package si.feri.mag.soa.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import si.feri.mag.soa.model.sensors.IDevice;

@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public abstract class Device implements IDevice {
    private final String name;
    private final String deviceNumber;
    private final String manufacturer;

    @Getter
    private Status status = Status.OFF;

    //power draw sensor
    @Getter
    private float powerDraw;

    @Getter
    private float maxPowerDraw;

    @Override
    public void turnOff() {
        status = Status.OFF;
    }

    @Override
    public void turnOn() {
        status = Status.ON;
    }

    @Override
    public void setMaxPowerDraw(float maxPowerDraw) {
        this.maxPowerDraw = maxPowerDraw;
        this.powerDraw = maxPowerDraw;
    }

}
