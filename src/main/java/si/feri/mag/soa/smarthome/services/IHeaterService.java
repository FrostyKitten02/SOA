package si.feri.mag.soa.smarthome.services;

import javax.jws.WebMethod;

public interface IHeaterService {
    //functions for heater
    @WebMethod
    void turnOffHeater();
    @WebMethod
    void turnOnHeater();
    @WebMethod
    void setHeaterTemperature(float heaterTemperature);
    @WebMethod
    boolean isHeaterOn();
    @WebMethod
    float getAmbientTemperatureFromHeater();
}
