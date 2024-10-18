package si.feri.mag.soa.model;

import si.feri.mag.soa.model.sensors.IAmbientTemperatureSensor;
import si.feri.mag.soa.model.sensors.ITimer;

public interface IHeater extends ITimer, IAmbientTemperatureSensor, IDevice, ITemperatureDevice {
}
