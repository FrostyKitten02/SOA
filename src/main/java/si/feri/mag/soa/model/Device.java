package si.feri.mag.soa.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public abstract class Device {
    private final String name;
    private final String deviceNumber;
    private final String manufacturer;

    private Status status = Status.OFF;
    private float powerDraw;
}
