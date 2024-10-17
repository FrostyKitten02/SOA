package si.feri.mag.soa.smarthome.services;

import si.feri.mag.soa.smarthome.services.IAirConditionerService;
import si.feri.mag.soa.smarthome.services.IHeaterService;
import si.feri.mag.soa.smarthome.services.ISecurityService;

import javax.jws.WebService;

@WebService
public interface ISmartHomeService extends IHeaterService, IAirConditionerService, ISecurityService {
}
