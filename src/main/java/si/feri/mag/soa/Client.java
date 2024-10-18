package si.feri.mag.soa;

import lombok.Getter;
import lombok.Setter;
import wsimport.si.feri.mag.soa.client.Device;
import wsimport.si.feri.mag.soa.client.SmartHome;
import wsimport.si.feri.mag.soa.client.SmartHomeImplService;
import wsimport.si.feri.mag.soa.client.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String commandInput = listenAndReadFromTerminal();
        Command command = parseInput(commandInput);

        SmartHome service = new SmartHomeImplService().getSmartHomeImplPort();

        switch (command.getCommandStr().toLowerCase()) {
            case "totalpower":
                if (command.getCommandType() == CommandType.GET) {
                    float powerDraw = service.getTotalPowerDraw();
                    System.out.println("Total power draw: " + powerDraw);
                } else if (command.getCommandType() == CommandType.SET) {
                    service.setMaxPowerDraw(command.getDevice(), Float.parseFloat(command.getCommandValue()));
                    System.out.println("Max power draw set");
                }
                break;
            case "tempambient":
                Float temp = null;
                if (command.getDevice() == Device.HEATER) {
                    temp = service.getAmbientTemperatureFromHeater();
                } else if (command.getDevice() == Device.AIR_CONDITIONER) {
                     temp = service.getAmbientTemperatureFromAc();
                }

                if (temp == null) {
                    System.out.println("Error getting temperature, probably wrong device");
                } else {
                    System.out.println("Temperature: " + temp);
                }
                break;
            case "alarm": //turn on/off alarm and get is alarm set
                if (command.commandType == CommandType.GET) {
                    boolean isAlarmSet = service.isAlarmSet();
                    System.out.println("Is alarm set: " + isAlarmSet);
                } else if (command.commandType == CommandType.SET) {
                    Boolean alarmValue = command.getCommandValueAsBoolean();
                    if (alarmValue == null) {
                        System.out.println("Wrong value for alarm, should be on or off");
                        break;
                    }

                    if (alarmValue) {
                        service.turnOnAlarm();
                    } else {
                        service.turnOffAlarm();
                    }
                }
                break;
            case "alarmtriggered":
                boolean triggered = service.isAlarmTriggered();
                System.out.println("Is alarm triggered: " + triggered);
                break;
            case "doorclosed": //only get
                boolean closed = service.isDoorClosed();
                System.out.println("Is door closed: " + closed);
                break;
            case "doorlocked": //only get
                boolean locked = service.isDoorLocked();
                System.out.println("Is door locked: " + locked);
                break;
            case "alarmsettime": //only get
                String alarmSetTime = service.getAlarmSetTime();
                System.out.println("Alarm set time: " + alarmSetTime);
                break;
            case "status": //get and set
                if (command.getCommandType() == CommandType.GET) {
                    Status status = service.getStatus(command.getDevice());
                    System.out.println("Status: " + status);
                    break;
                }

                if (command.getCommandType() == CommandType.SET) {
                    Status status = Status.valueOf(command.getCommandValue().toUpperCase());
                    service.setStatus(command.getDevice(), status);
                    System.out.println("Status set");
                    break;
                }

                break;
            case "temperature": //get and set
                if (command.getCommandType() == CommandType.GET) {
                    float temperature = service.getTemperature(command.getDevice());
                    System.out.println("Temperature: " + temperature);
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

                    service.setTemperature(command.getDevice(), temperature);
                    System.out.println("Temperature set");
                    break;
                }
                break;
            case "timer": //get and set
                if (command.commandType == CommandType.GET) {
                    int timeLeft = service.getTimerLeft(command.getDevice());
                    System.out.println("Time left: " + timeLeft);
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

                    service.setTimer(command.getDevice(), val);
                    System.out.println("Timer set");
                }
                break;
            default:
                System.out.println("Command not recognized");
        }
    }


    private static class Command {
        @Setter
        private String command;
        @Getter
        private String[] commandWitArgs;
        @Getter
        private Device device;
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
            String deviceStr = commandWitArgs[0];

            if (deviceStr.equalsIgnoreCase("ac")) {
                this.device = Device.AIR_CONDITIONER;
            } else if (deviceStr.equalsIgnoreCase("heater")) {
                this.device = Device.HEATER;
            } else if (deviceStr.equalsIgnoreCase("hs")) {
                this.device = Device.HOME_SECURITY;
            }

            String commandTypeStr = commandWitArgs[1];//set, get
            if (commandTypeStr.equalsIgnoreCase("set")) {
                commandType = CommandType.SET;
            } else if (commandTypeStr.equalsIgnoreCase("get")) {
                commandType = CommandType.GET;
            }

            String commandStr = commandWitArgs[2];//timer, temperature, status, doorclosed, doorlocked, alarmsettime
            this.commandStr = commandStr;

            if (this.commandType == CommandType.GET) {
                //we don't need command value in this case
                return;
            }

            String commandValue = commandWitArgs[3];
            this.commandValue = commandValue;
        }
    }

    private enum CommandType {
        SET,
        GET,
    }
}
