package si.feri.mag.soa;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        try {
            //TODO add my own implementation of a class
            Endpoint koncnaTocka = Endpoint.publish("http://localhost:8080/Hladilnik", new Object());
            System.out.println("Storitev je objavljena");
            Thread.sleep(100000);
            koncnaTocka.stop();
            System.out.println("Storitev je zaustavljena");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
