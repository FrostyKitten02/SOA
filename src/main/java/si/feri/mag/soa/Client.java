package si.feri.mag.soa;

import wsimport.si.feri.mag.soa.client.SmartHome;
import wsimport.si.feri.mag.soa.client.SmartHomeImplService;

public class Client {
    public static void main(String[] args) {
        SmartHomeImplService service = new SmartHomeImplService();
        SmartHome c = service.getSmartHomeImplPort();
        String str = c.getAlarmSetTime();
        System.out.println(str);
        c.setAcTemperature(10);
        c.setAcTimer(10);
        int timeleft = c.getTimeLeftOnAc();
        System.out.println(timeleft);
    }
}
