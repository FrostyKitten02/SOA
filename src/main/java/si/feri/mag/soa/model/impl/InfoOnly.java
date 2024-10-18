package si.feri.mag.soa.model.impl;

import lombok.experimental.SuperBuilder;
import si.feri.mag.soa.model.Device;
import si.feri.mag.soa.model.IDevice;


@SuperBuilder
public class InfoOnly extends Device {
    public InfoOnly() {
        super(null, null, null, null, 0, 0);
    }

    public static InfoOnly fromDevice(IDevice device) {
        //todo copy info
        return InfoOnly.builder()
                .name(device.getName())
                .deviceNumber(device.getDeviceNumber())
                .manufacturer(device.getManufacturer())
                .status(device.getStatus())
                .build();
    }
}
