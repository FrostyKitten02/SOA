package si.feri.mag.soa.smarthome.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "SmartHome")
public interface ISmartHome extends IHeaterService, IAirConditionerService, ISecurityService {
    @WebMethod
    float getTotalPowerDraw();
    @WebMethod
    void setMaxPowerDraw(Device device, float maxPowerDraw);
}
