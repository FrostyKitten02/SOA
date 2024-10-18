package si.feri.mag.soa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class Device implements IDevice {
    @XmlElement
    private final String name;
    @XmlElement
    private final String deviceNumber;
    @XmlElement
    private final String manufacturer;

    @Getter
    @Setter
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
