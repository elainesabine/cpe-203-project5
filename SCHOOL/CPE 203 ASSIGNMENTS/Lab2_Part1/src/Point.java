public class Point {

    //data members of the class
    private double x;
    private double y;

    // constructor
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    // methods
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getAngle(){
        double yLength = getY();
        double xLength = getX();

        double result = Math.atan2(yLength, xLength);
        return result;
    }

    public double getRadius(){
        double xDist = getX();
        double yDist = getY();
        double hypotenuse = Math.pow(xDist, 2) + Math.pow(yDist, 2);
        return Math.sqrt(hypotenuse);
    }

    public Point rotate90(){
        //(X, Y) --> (-Y, X)
        double flippedY = (getY() * -1);
        Point rotated = new Point(flippedY, getX());
        return rotated;
    }
}
