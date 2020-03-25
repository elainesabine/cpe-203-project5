public class Circle {
    // instance variables
    private final Point center;
    private final double radius;

    // constructor
    public Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    //accessor methods
    public Point getCenter(){
        return this.center;
    }

    public double getRadius(){
        return this.radius;
    }

}
