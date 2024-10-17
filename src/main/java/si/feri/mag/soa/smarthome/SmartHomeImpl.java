package si.feri.mag.soa.smarthome;

import si.feri.mag.soa.model.IAirConditioner;
import si.feri.mag.soa.model.IDevice;
import si.feri.mag.soa.model.IHeater;
import si.feri.mag.soa.model.IHomeSecurity;
import si.feri.mag.soa.model.Status;
import si.feri.mag.soa.model.impl.AirConditioner;
import si.feri.mag.soa.model.impl.Heater;
import si.feri.mag.soa.model.impl.HomeSecurity;
import si.feri.mag.soa.smarthome.services.Device;
import si.feri.mag.soa.smarthome.services.ISmartHomeService;

import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "si.feri.mag.soa.smarthome.services.ISmartHomeService")
public class SmartHomeImpl implements ISmartHomeService {
    private final IAirConditioner ac;
    private final IHeater heater;
    private final IHomeSecurity homeSecurity;

    private final List<IDevice> devices;


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

        devices = Arrays.asList(ac, heater, homeSecurity);
    }

    @Override
    public void turnOffHeater() {
        heater.turnOff();
    }

    @Override
    public void turnOnHeater() {
        heater.turnOn();
    }

    @Override
    public void setHeaterTemperature(float heaterTemperature) {
        heater.setTemperature(heaterTemperature);
    }

    @Override
    public boolean isHeaterOn() {
        return heater.getStatus() == Status.ON;
    }

    @Override
    public float getAmbientTemperatureFromHeater() {
        return heater.getAmbientTemperature();
    }

    @Override
    public void turnOffAc() {
        ac.setStatus(Status.OFF);
    }

    @Override
    public void turnOnAc() {
        ac.setStatus(Status.ON);
    }

    @Override
    public void setAcTemperature(float acTemperature) {
        ac.setTemperature(acTemperature);
    }

    @Override
    public boolean isAcOn() {
        return ac.getStatus() == Status.ON;
    }

    @Override
    public float getAmbientTemperatureFromAc() {
        return ac.getAmbientTemperature();
    }

    @Override
    public void setAcTimer(int timeMins) {
        ac.setTimer(timeMins);
    }

    @Override
    public int getTimeLeftOnAc() {
        return ac.getTimerRemaining();
    }

    @Override
    public void turnOffAlarm() {
        homeSecurity.disableAlarm();
    }

    @Override
    public void turnOnAlarm() {
        homeSecurity.setAlarm();
    }

    @Override
    public boolean isAlarmSet() {
        return homeSecurity.isAlarmSet();
    }

    @Override
    public boolean isAlarmTriggered() {
        return homeSecurity.isAlarmSet();
    }

    @Override
    public void turnOffHomeSecurity() {
        homeSecurity.disableAlarm();
        homeSecurity.setStatus(Status.OFF);
    }

    @Override
    public void turnOnHomeSecurity() {
        homeSecurity.setStatus(Status.ON);
        homeSecurity.setAlarm();
    }

    @Override
    public boolean isDoorClosed() {
        return !homeSecurity.isDoorOpen();
    }

    @Override
    public boolean isDoorLocked() {
        return homeSecurity.isDoorLocked();
    }

    @Override
    public String getAlarmSetTime() {
        return homeSecurity.getAlarmSetTime();
    }

    @Override
    public float getTotalPowerDraw() {
        return devices.stream().map(IDevice::getPowerDraw).reduce(0f, Float::sum);
    }

    @Override
    public void setMaxPowerDraw(Device device, float maxPowerDraw) {
        switch (device) {
            case AIR_CONDITIONER:
                ac.setMaxPowerDraw(maxPowerDraw);
                break;
            case HEATER:
                heater.setMaxPowerDraw(maxPowerDraw);
                break;
            case HOME_SECURITY:
                homeSecurity.setMaxPowerDraw(maxPowerDraw);
                break;
        }
    }
}
