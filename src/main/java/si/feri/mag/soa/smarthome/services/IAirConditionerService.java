package si.feri.mag.soa.smarthome.services;

import javax.jws.WebMethod;

public interface IAirConditionerService {
    //functions for ac
    @WebMethod
    void turnOffAc();
    @WebMethod
    void turnOnAc();
    @WebMethod
    void setAcTemperature(float acTemperature);
    @WebMethod
    boolean isAcOn();
    @WebMethod
    float getAmbientTemperatureFromAc();
    @WebMethod
    void setAcTimer(int timeMins);
    @WebMethod
    int getTimeLeftOnAc();
}
