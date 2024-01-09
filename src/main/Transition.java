package main;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class Transition {
    public static final int DEFAULT_RESOLUTION = 100;
    private UpdatableValue updatableValue;
    private float t;
    private Function function;
    private Timer timer;

    public static Function LINEAR = t -> t;
    public static Function EXPONENTIAL = t -> t*t;
    public static Function SINUS = t -> (float) Math.sin(Math.toRadians(t*90));
    public static Function SINUS_SINUS = t -> SINUS.compute(t) * SINUS.compute(t);

    public Transition(Duration duration, Function function, int resolution, UpdatableValue updatableValue) {
        this.updatableValue = updatableValue;
        this.function = function;

        Duration stepTime = duration.dividedBy(resolution);

        float tStepSize = 1f/resolution;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private int step = 0;

            @Override
            public void run() {
                t+=tStepSize;
                step++;

                updatableValue.update(function.compute(t));

                if(step == resolution) {
                    cancel();
                }
            }
        }, 0, stepTime.toMillis());
    }

    public Transition(Duration duration, Function function, UpdatableValue updatableValue) {
        this(duration, function, DEFAULT_RESOLUTION, updatableValue);
    }

    public void kill(){
        if(timer != null) timer.cancel();
    }

    public interface Function{
        float compute(float t);
    }
}
