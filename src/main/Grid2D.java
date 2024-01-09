package main;

public class Grid2D extends Point2D{
    public int rows(){
        return super.getY();
    }

    public int columns(){
        return super.getX();
    }
}
