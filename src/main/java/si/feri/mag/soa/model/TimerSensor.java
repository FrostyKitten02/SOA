package si.feri.mag.soa.model;

public class TimerSensor implements ITimer {
    private long timerEnd = 0;

    public void setTimer(long timeMins) {
        this.timerEnd = System.currentTimeMillis() + timeMins * 60 * 1000;
    }

    public int getTimerRemaining() {
        if (timerEnd == 0) {
            return 0;
        }

        return getTimeLeft();
    }

    private int getTimeLeft() {
        long millisLEft = timerEnd - System.currentTimeMillis();
        if (millisLEft <= 0) {
            this.timerEnd = 0;
            return 0;
        }

        return (int) (millisLEft / 1000);
    }
}
