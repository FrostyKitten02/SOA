package si.feri.mag.soa.model;

public interface IDevice {
    String getName();
    String getDeviceNumber();
    String getManufacturer();
    void turnOff();
    void turnOn();
    Status getStatus();
    void setStatus(Status status);

    float getPowerDraw();
    void setMaxPowerDraw(float maxPowerDraw);
}
