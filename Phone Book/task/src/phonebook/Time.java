package phonebook;

import java.util.concurrent.TimeUnit;

public class Time {
    private long wholeTime;
    private long ms;
    private long sec;
    private long min;

    public Time(long duration) {
        this.wholeTime = duration;
        setDetails(duration);
    }

    private void setDetails(long t) {
//        this.ms = this.wholeTime % 1000;
//        this.sec = (this.wholeTime % 60000) / 10000;
//        this.min = this.wholeTime / 60000;
        this.min = t;
        this.ms = this.min;
        this.sec = t;
    }

    public void setTime(long duration) {
        setDetails(duration - this.wholeTime);
        this.wholeTime = duration - this.wholeTime;
    }

    public long checkDuration(long duration) {
        return duration - this.wholeTime;
    }

    public long getWholeTime() {
        return wholeTime;
    }

    public long getMin() {
        return min;
    }

    public long getSec() {
        return sec;
    }

    public long getMs() {
        return ms;
    }

    public static long current() {
        return System.currentTimeMillis();
    }

    public static Time totalTime(Time t1, Time t2) {
        return new Time(t1.getWholeTime() + t2.getWholeTime());
    }

    public static long msToMin(long ms) {
        return TimeUnit.MILLISECONDS.toMinutes(ms);
    }

    public static long msToSec(long ms) {
        return TimeUnit.MILLISECONDS.toSeconds(ms) % 60;
    }

    public static long msToMs(long ms) {
        return TimeUnit.MILLISECONDS.toMillis(ms) % 1000;
    }
}
