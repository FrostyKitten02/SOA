package si.feri.mag.soa;

import wsimport.si.feri.mag.soa.client.SmartHome;
import wsimport.si.feri.mag.soa.client.SmartHomeImplService;

public class Client {
    public static void main(String[] args) {
        SmartHomeImplService service = new SmartHomeImplService();
        SmartHome c = service.getSmartHomeImplPort();
        String str = c.getAlarmSetTime();
        System.out.println(str);
    }
}
