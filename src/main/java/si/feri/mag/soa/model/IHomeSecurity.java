package si.feri.mag.soa.model;

import si.feri.mag.soa.model.sensors.IAlarmSetSensor;
import si.feri.mag.soa.model.sensors.IAlarmTriggeredSensor;
import si.feri.mag.soa.model.sensors.IDoorSensor;

public interface IHomeSecurity extends IAlarmSetSensor, IAlarmTriggeredSensor, IDoorSensor, IDevice {
    void setAlarm();
    String getAlarmSetTime();
    void disableAlarm();
}
