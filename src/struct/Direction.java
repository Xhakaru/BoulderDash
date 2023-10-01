package struct;

public enum Direction {
    UP(new Vector2D(0, 1)),
    RIGHT(new Vector2D(1, 0)),
    DOWN(new Vector2D(0, -1)),
    LEFT(new Vector2D(-1, 0));

    private final Vector2D directionVector;
    Direction(Vector2D directionVector){
        this.directionVector = directionVector;
    }

    public Vector2D getDirectionVector() {
        return directionVector;
    }
}
