package main;

public class Dimension2D extends Point2D{
    public int getWidth(){
        return super.getX();
    }

    public int getHeight(){
        return super.getY();
    }

    public void setWidth(int width){
        super.setX(width);
    }

    public void setHeight(int height){
        super.setY(height);
    }
}
