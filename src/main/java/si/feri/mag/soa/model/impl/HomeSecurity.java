package si.feri.mag.soa.model.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import si.feri.mag.soa.model.Device;
import si.feri.mag.soa.model.IHomeSecurity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuperBuilder
public class HomeSecurity extends Device implements IHomeSecurity {
    //alarm set sensor
    @Getter
    private boolean alarmSet = false;
    private long alarmSetTime = 0;
    //alarm triggered sensor
    @Getter
    @Setter
    private boolean alarmTriggered = false;

    @Getter
    private boolean isDoorOpen = false;

    @Getter
    private boolean isDoorLocked = false;

    public void setAlarm() {
        this.alarmSet = true;
        this.alarmSetTime = System.currentTimeMillis();
    }

    public String getAlarmSetTime() {
        if (alarmSetTime == 0) {
            return "Alarm not set";
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(alarmSetTime);

        return df.format(date);
    }



}
