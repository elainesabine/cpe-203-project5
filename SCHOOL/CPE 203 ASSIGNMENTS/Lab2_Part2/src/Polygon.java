import java.util.*;

public class Polygon {
    // instance variables
    private final List<Point> points;

    //constructor
    public Polygon (List<Point> points){
        this.points = points;
    }

    public List<Point> getPoints(){
        return this.points;
    }
}
