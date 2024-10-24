package si.feri.mag.soa;

import lombok.Getter;
import wsimport.si.feri.mag.soa.client.DeviceType;
import wsimport.si.feri.mag.soa.client.InfoOnly;
import wsimport.si.feri.mag.soa.client.SmartHome;
import wsimport.si.feri.mag.soa.client.SmartHomeImplService;
import wsimport.si.feri.mag.soa.client.Status;

import javax.xml.ws.BindingProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class Client {

    private static Command parseInput(String input) {
        Command c = new Command();
        c.setCommand(input);
        return c;
    }

    private static String listenAndReadFromTerminal() throws IOException {
        System.out.println("Enter command:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Get command format: [get] [command] [device(optional)]");
        System.out.println("Set command format: [set] [command] [value] [device(optional)]");
        System.out.println("Use q command to quit");

        SmartHomeImplService service = new SmartHomeImplService();
        SmartHome port = service.getSmartHomeImplPort();

        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

        while (true) {
            String commandInput = listenAndReadFromTerminal();
            if (commandInput.equalsIgnoreCase("q")) {
                System.out.println("Exiting");
                return;
            }
            Command command = parseInput(commandInput);

            switch (command.getCommandStr().toLowerCase()) {
                case "totalpower":
                    if (command.getCommandType() == CommandType.GET) {
                        float powerDraw = port.getTotalPowerDraw();
                        System.out.println("Total power draw: " + powerDraw);
                    } else if (command.getCommandType() == CommandType.SET) {
                        Float powerDraw = null;
                        try {
                            powerDraw = Float.parseFloat(command.getCommandValue());
                        } catch (Exception e) {
                            System.out.println("Error parsing power draw" + command.getCommandValue());
                            break;
                        }


                        try {
                            port.setMaxPowerDraw(command.getDeviceType(), powerDraw);
                            System.out.println("Max power draw set");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                        }
                    }
                    break;
                case "tempambient":
                    Float temp = null;
                    if (command.getDeviceType() == DeviceType.HEATER) {
                        try {
                            temp = port.getAmbientTemperatureFromHeater();
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                            break;
                        }
                    } else if (command.getDeviceType() == DeviceType.AIR_CONDITIONER) {
                        try {
                            temp = port.getAmbientTemperatureFromAc();
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                            break;
                        }
                    }

                    if (temp == null) {
                        System.out.println("Error getting temperature, probably wrong device");
                    } else {
                        System.out.println("Temperature: " + temp);
                    }
                    break;
                case "alarm": //turn on/off alarm and get is alarm set
                    if (command.commandType == CommandType.GET) {
                        try {
                            boolean isAlarmSet = port.isAlarmSet();
                            System.out.println("Is alarm set: " + isAlarmSet);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                            break;
                        }
                    } else if (command.commandType == CommandType.SET) {
                        Boolean alarmValue = command.getCommandValueAsBoolean();
                        if (alarmValue == null) {
                            System.out.println("Wrong value for alarm, should be on or off");
                            break;
                        }

                        try {
                            if (alarmValue) {
                                port.turnOnAlarm();
                                System.out.println("Alarm on");
                            } else {
                                port.turnOffAlarm();
                                System.out.println("Alarm off");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                            break;
                        }
                    }
                    break;
                case "alarmtriggered":
                    try {
                        boolean triggered = port.isAlarmTriggered();
                        System.out.println("Is alarm triggered: " + triggered);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error making request");
                        break;
                    }
                    break;
                case "doorclosed": //only get
                    try {
                        boolean closed = port.isDoorClosed();
                        System.out.println("Is door closed: " + closed);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error making request");
                        break;
                    }
                    break;
                case "doorlocked": //only get
                    try {
                        boolean locked = port.isDoorLocked();
                        System.out.println("Is door locked: " + locked);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error making request");
                        break;
                    }
                    break;
                case "alarmsettime": //only get
                    try {
                        String alarmSetTime = port.getAlarmSetTime();
                        System.out.println("Alarm set time: " + alarmSetTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error making request");
                        break;
                    }
                    break;
                case "status": //get and set
                    try {
                        if (command.getCommandType() == CommandType.GET) {
                            Status status = port.getStatus(command.getDeviceType());
                            System.out.println("Status: " + status);
                            break;
                        }

                        if (command.getCommandType() == CommandType.SET) {
                            Status status = Status.valueOf(command.getCommandValue().toUpperCase());
                            port.setStatus(command.getDeviceType(), status);
                            System.out.println("Status set");
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error making request");
                        break;
                    }

                    break;
                case "temperature": //get and set
                    if (command.getCommandType() == CommandType.GET) {
                        try {
                            float temperature = port.getTemperature(command.getDeviceType());
                            System.out.println("Temperature: " + temperature);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                        }
                        break;
                    }

                    if (command.getCommandType() == CommandType.SET) {
                        Float temperature = null;
                        try {
                            temperature = Float.parseFloat(command.getCommandValue());
                        } catch (Exception e) {
                            System.out.println("Error parsing temperature" + command.getCommandValue());
                            break;
                        }

                        try {
                            port.setTemperature(command.getDeviceType(), temperature);
                            System.out.println("Temperature set");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                        }

                        break;
                    }
                    break;
                case "timer": //get and set
                    if (command.commandType == CommandType.GET) {
                        try {
                            int timeLeft = port.getTimerLeft(command.getDeviceType());
                            System.out.println("Time left: " + timeLeft);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                        }

                        break;
                    }

                    if (command.commandType == CommandType.SET) {
                        Integer val = null;
                        try {
                            val = Integer.parseInt(command.getCommandValue());
                        } catch (Exception e) {
                            System.out.println("Error parsing time" + command.getCommandValue());
                            break;
                        }

                        try {
                            port.setTimer(command.getDeviceType(), val);
                            System.out.println("Timer set");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error making request");
                        }
                    }
                    break;
                case "info":
                    try {
                        InfoOnly info = port.getInfo(command.getDeviceType());
                        System.out.println("Name: " + info.getName());
                        System.out.println("Manufacturer: " + info.getManufacturer());
                        System.out.println("Device number: " + info.getDeviceNumber());
                        System.out.println("Device status: " + info.getStatus());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error making request");
                        break;
                    }
                    break;
                default:
                    System.out.println("Command not recognized");
            }
        }
    }


    private static class Command {
        private String command;
        @Getter
        private String[] commandWitArgs;
        @Getter
        private DeviceType deviceType;
        @Getter
        private CommandType commandType;
        @Getter
        private String commandStr;
        @Getter
        private String commandValue;

        public Boolean getCommandValueAsBoolean() {
            if (this.commandValue.equalsIgnoreCase("on")) {
                return true;
            } else if (this.commandValue.equalsIgnoreCase("off")) {
                return false;
            }

            return null;
        }
        public void setCommand(String command) {
            this.command = command;
            this.commandWitArgs = command.split(" ");

            String commandTypeStr = commandWitArgs[0];//set, get
            if (commandTypeStr.equalsIgnoreCase("set")) {
                commandType = CommandType.SET;
            } else if (commandTypeStr.equalsIgnoreCase("get")) {
                commandType = CommandType.GET;
            }

            String commandStr = commandWitArgs[1];//timer, temperature, status, doorclosed, doorlocked, alarmsettime
            this.commandStr = commandStr;

            if (commandWitArgs.length < 3) {
                this.deviceType = null;
                this.commandValue = "";
                return;
            }

            if (this.commandType == CommandType.GET) {
                 String deviceStr = commandWitArgs[2];
                 this.deviceType = parseDeviceStr(deviceStr);
                 this.commandValue = "";
                 return;
            }

            String commandValue = commandWitArgs[2];
            this.commandValue = commandValue;
            if (commandWitArgs.length >= 4) {
                String deviceStr = commandWitArgs[3];
                this.deviceType = parseDeviceStr(deviceStr);
            } else {
                this.deviceType = null;
            }
        }

        private DeviceType parseDeviceStr(String deviceStr) {
            if (deviceStr == null) {
                return null;
            }

            if (deviceStr.equalsIgnoreCase("ac")) {
                return DeviceType.AIR_CONDITIONER;
            } else if (deviceStr.equalsIgnoreCase("heater")) {
                return DeviceType.HEATER;
            } else if (deviceStr.equalsIgnoreCase("hs")) {
               return DeviceType.HOME_SECURITY;
            }

            return null;
        }

    }

    private enum CommandType {
        SET,
        GET,
    }
}
