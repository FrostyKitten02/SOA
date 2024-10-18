package si.feri.mag.soa.smarthome.services;

import si.feri.mag.soa.model.Status;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "SmartHome")
public interface ISmartHome {
    @WebMethod
    float getTotalPowerDraw();
    @WebMethod
    void setMaxPowerDraw(Device device, float maxPowerDraw);


    @WebMethod
    float getAmbientTemperatureFromHeater();
    @WebMethod
    float getAmbientTemperatureFromAc();

    //functions for home security
    //keeps device on but sets alarm to off
    @WebMethod
    void turnOffAlarm();
    //if security is off then is turns it on when turn on alarm
    @WebMethod
    void turnOnAlarm();

    //alarm sensor
    @WebMethod
    boolean isAlarmSet();
    @WebMethod
    boolean isAlarmTriggered();

    //door sensor
    @WebMethod
    boolean isDoorClosed();
    @WebMethod
    boolean isDoorLocked();
    @WebMethod
    String getAlarmSetTime();


    void setStatus(Device device, Status status);
    Status getStatus(Device device);

    void setTemperature(Device device, float temperature);
    float getTemperature(Device device);


    void setTimer(Device device, int timeMins);
    int getTimerLeft(Device device);

}
