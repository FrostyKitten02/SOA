package si.feri.mag.soa.model;

public interface IDevice {
    void turnOff();
    void turnOn();
    Status getStatus();
    void setStatus(Status status);

    float getPowerDraw();
    void setMaxPowerDraw(float maxPowerDraw);
}
