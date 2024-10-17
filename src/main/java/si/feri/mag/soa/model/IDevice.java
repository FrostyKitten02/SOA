package si.feri.mag.soa.model;

import si.feri.mag.soa.model.Status;

public interface IDevice {
    void turnOff();
    void turnOn();
    Status getStatus();

    float getPowerDraw();
    void setMaxPowerDraw(float maxPowerDraw);
}
