package si.feri.mag.soa.smarthome;

import si.feri.mag.soa.model.AirConditioner;
import si.feri.mag.soa.model.Heater;

import javax.jws.WebService;

@WebService
public interface ISmartHomeService {
    //functions for heater
    void turnOffHeater();
    void turnOnHeater();
    void setHeaterTemperature(float heaterTemperature);
    boolean isHeaterOn();
    Heater getHeater();
    int getAmbientTemperatureFromHeater();

    //functions for ac
    void turnOffAc();
    void turnOnAc();
    void setAcTemperature(float acTemperature);
    boolean isAcOn();
    AirConditioner getAirConditioner();
    int getAmbientTemperatureFromAc();
}
