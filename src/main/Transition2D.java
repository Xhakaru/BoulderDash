package main;

import java.time.Duration;

public class Transition2D {
    private Transition transX;
    private Transition transY;

    private UpdatableValue updatableValueX;
    private UpdatableValue updatableValueY;

    public Transition2D(Duration duration, Transition.Function function, int resolution, UpdatableValue updatableValueX, UpdatableValue updatableValueY) {
        transX = new Transition(duration, function, resolution, updatableValueX);
        transY = new Transition(duration, function, resolution, updatableValueY);
    }

    public Transition2D(Duration duration, Transition.Function function, UpdatableValue updatableValueX, UpdatableValue updatableValueY) {
        this(duration, function, Transition.DEFAULT_RESOLUTION, updatableValueX, updatableValueY);
    }
}
