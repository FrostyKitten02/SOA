package si.feri.mag.soa.smarthome;

import si.feri.mag.soa.model.IAirConditioner;
import si.feri.mag.soa.model.IDevice;
import si.feri.mag.soa.model.IHeater;
import si.feri.mag.soa.model.IHomeSecurity;
import si.feri.mag.soa.model.ITemperatureDevice;
import si.feri.mag.soa.model.Status;
import si.feri.mag.soa.model.impl.AirConditioner;
import si.feri.mag.soa.model.impl.Heater;
import si.feri.mag.soa.model.impl.HomeSecurity;
import si.feri.mag.soa.model.impl.InfoOnly;
import si.feri.mag.soa.model.sensors.ITimer;
import si.feri.mag.soa.smarthome.services.Device;
import si.feri.mag.soa.smarthome.services.ISmartHome;

import javax.jws.WebService;
import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "si.feri.mag.soa.smarthome.services.ISmartHome")
public class SmartHomeImpl implements ISmartHome {
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
                .temperature(22)
                .ambientTemperature(22)
                .build();
        heater = Heater.builder()
                .name("Heat pump 1")
                .deviceNumber("VERSI-X")
                .manufacturer("VERSI")
                .status(Status.ON)
                .temperature(22)
                .ambientTemperature(22)
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
    public float getAmbientTemperatureFromHeater() {
        return heater.getAmbientTemperature();
    }

    @Override
    public float getAmbientTemperatureFromAc() {
        return ac.getAmbientTemperature();
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

    @Override
    public void setStatus(Device device, Status status) {
        IDevice dev = getDevice(device);
        if (dev == null) {
            return;
        }

        dev.setStatus(status);
    }

    @Override
    public void setTemperature(Device device, float temperature) {
        ITemperatureDevice dev = getTemperatureDevice(device);
        if (dev == null) {
            return;
        }

        dev.setTemperature(temperature);
    }

    @Override
    public float getTemperature(Device device) {
        ITemperatureDevice dev = getTemperatureDevice(device);
        if (dev == null) {
            return 0;
        }

        return dev.getTemperature();
    }

    @Override
    public Status getStatus(Device device) {
        IDevice dev = getDevice(device);
        if (dev == null) {
            return null;
        }

        return dev.getStatus();
    }

    @Override
    public void setTimer(Device device, int timeMins) {
        ITimer dev = getTimerDevice(device);
        if (dev == null) {
            return;
        }

        dev.setTimer(timeMins);
    }

    @Override
    public int getTimerLeft(Device device) {
        ITimer dev = getTimerDevice(device);
        if (dev == null) {
            return 0;
        }

        return dev.getTimerRemaining();
    }

    @Override
    public InfoOnly getInfo(Device device) {
        IDevice dev = getDevice(device);
        if (dev == null) {
            return null;
        }

        return InfoOnly.fromDevice(dev);
    }


    private IDevice getDevice(Device device) {
        switch (device) {
            case AIR_CONDITIONER:
                return ac;
            case HEATER:
                return heater;
            case HOME_SECURITY:
                return homeSecurity;
            default:
                return null;
        }
    }

    private ITemperatureDevice getTemperatureDevice(Device device) {
        switch (device) {
            case AIR_CONDITIONER:
                return ac;
            case HEATER:
                return heater;
            default:
                return null;
        }
    }


    private ITimer getTimerDevice(Device device) {
        switch (device) {
            case AIR_CONDITIONER:
                return ac;
            case HEATER:
                return heater;
            default:
                return null;
        }
    }

}
