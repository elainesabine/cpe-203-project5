//
//
//*** YOU MAY MODIFY THIS CLASS
//
//

/*
a Point2D represents a point in 2 dimensions.
(Note that the coordinate system used runs horizontally in x direction from left to right and vertically in y direction from up to down.)
Every Point2D also has an identifier String and a color.
 */
public class Point2D {
    public String id;
    public Point p;
    public ColorRGB col;

    public Point2D(int x, int y, String id) {
        this.p= new Point(x,y);
        this.id=id;
        //make all points pretty pink
        col = new ColorRGB(255,20,147);


    }


}
