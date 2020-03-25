//
//
//*** YOU MAY MODIFY THIS CLASS
//
//

/* a Rectangle represents a rectangle which is given by one points (upper left corner = p) and the length and width.
 (Note that the coordinate system used runs horizontally in x direction from left to right and vertically in y direction from up to down.)
Every Rectangle also has an identifier String and a color.
 */

public class Rectangle {
    public Point p;
    public int length;
    public int width;
    public String id;
    public ColorRGB col;


    public Rectangle(Point p, int l, int w, String id, ColorRGB col){
        this.p= p;
        this.length=l;
        this.width=w;
        this.id=id;
        this.col=col;
    }
}
