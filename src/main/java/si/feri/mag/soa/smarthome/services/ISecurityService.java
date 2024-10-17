package si.feri.mag.soa.smarthome.services;

import javax.jws.WebMethod;

public interface ISecurityService {
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

    //turns off home security and also sets alarm to off
    @WebMethod
    void turnOffHomeSecurity();
    @WebMethod
    void turnOnHomeSecurity();

    //door sensor
    @WebMethod
    boolean isDoorClosed();
    @WebMethod
    boolean isDoorLocked();
}
