package si.feri.mag.soa;

import si.feri.mag.soa.smarthome.services.ISmartHomeService;
import si.feri.mag.soa.smarthome.SmartHomeImpl;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        ISmartHomeService service = new SmartHomeImpl();

        System.out.println("YEY");

        try {
            //TODO add my own implementation of a class
            Endpoint endpoint = Endpoint.publish("http://localhost:8080/Smarthome", service);
            System.out.println("Storitev je objavljena");
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
