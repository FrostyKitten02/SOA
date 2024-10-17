package si.feri.mag.soa.model.sensors;

public interface ITimer {
    void setTimer(long timeMins);
    int getTimerRemaining();
}
