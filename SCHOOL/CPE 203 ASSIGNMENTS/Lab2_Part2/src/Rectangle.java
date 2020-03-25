public class Rectangle {
    // instance variables
    private final Point topLeft;
    private final Point bottomRight;

    // constructor
    public Rectangle(Point topLeft, Point bottomRight){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //accessor methods
    public Point getTopLeft(){
        return this.topLeft;
    }

    public Point getBottomRight(){
        return this.bottomRight;
    }
}
