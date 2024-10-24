package si.feri.mag.soa;

import si.feri.mag.soa.smarthome.SmartHomeImpl;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        try {
            Endpoint endpoint = Endpoint.publish("http://localhost:8080/Smarthome", new SmartHomeImpl());
            System.out.println("Storitev je objavljena");
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
