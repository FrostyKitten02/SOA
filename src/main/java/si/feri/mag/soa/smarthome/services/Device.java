package si.feri.mag.soa.smarthome.services;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "deviceType", namespace = "http://services.smarthome.soa.mag.feri.si/")
public enum Device {
    HOME_SECURITY,
    AIR_CONDITIONER,
    HEATER
}
