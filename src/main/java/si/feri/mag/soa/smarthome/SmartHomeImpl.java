package si.feri.mag.soa.smarthome;

import si.feri.mag.soa.model.Status;
import si.feri.mag.soa.model.impl.AirConditioner;
import si.feri.mag.soa.model.impl.Heater;
import si.feri.mag.soa.model.impl.HomeSecurity;
import si.feri.mag.soa.smarthome.services.ISmartHomeService;

import javax.jws.WebService;

@WebService(endpointInterface = "si.feri.mag.soa.smarthome.services.ISmartHomeService")
public class SmartHomeImpl implements ISmartHomeService {
    private final AirConditioner ac;
    private final Heater heater;
    private final HomeSecurity homeSecurity;


    public SmartHomeImpl() {
        ac = AirConditioner.builder()
                .name("Sam-Ac-1")
                .deviceNumber("SAMSUNG-AC-1234")
                .manufacturer("Samsung")
                .status(Status.ON)
                .build();
        heater = Heater.builder()
                .name("Heat pump 1")
                .deviceNumber("VERSI-X")
                .manufacturer("VERSI")
                .status(Status.ON)
                .build();
        homeSecurity = HomeSecurity.builder()
                .name("CNET Security Pro")
                .deviceNumber("CNET-SEC-PRO-B643")
                .manufacturer("CNET")
                .status(Status.ON)
                .build();
    }

    @Override
    public void turnOffHeater() {

    }

    @Override
    public void turnOnHeater() {

    }

    @Override
    public void setHeaterTemperature(float heaterTemperature) {

    }

    @Override
    public boolean isHeaterOn() {
        return false;
    }

    @Override
    public int getAmbientTemperatureFromHeater() {
        return 0;
    }

    @Override
    public void turnOffAc() {

    }

    @Override
    public void turnOnAc() {

    }

    @Override
    public void setAcTemperature(float acTemperature) {

    }

    @Override
    public boolean isAcOn() {
        return false;
    }

    @Override
    public int getAmbientTemperatureFromAc() {
        return 0;
    }

    @Override
    public void setAcTimer(int timeMins) {

    }

    @Override
    public int getTimeLeftOnAc() {
        return 0;
    }

    @Override
    public void turnOffAlarm() {

    }

    @Override
    public void turnOnAlarm() {

    }

    @Override
    public boolean isAlarmSet() {
        return false;
    }

    @Override
    public boolean isAlarmTriggered() {
        return false;
    }

    @Override
    public void turnOffHomeSecurity() {

    }

    @Override
    public void turnOnHomeSecurity() {

    }

    @Override
    public boolean isDoorClosed() {
        return false;
    }

    @Override
    public boolean isDoorLocked() {
        return false;
    }
}
