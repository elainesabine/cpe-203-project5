//
//
//*** YOU MAY MODIFY THIS CLASS
//
//
/* a Line represents a line which is given by one points (left start point = start) and the length.
 (Note that the coordinate system used runs horizontally in x direction from left to right and vertically in y direction from up to down.)
Every Line also has an identifier String and a color.
 */
public class Line {
    public String id;
    public Point start;
    public int length;
    public ColorRGB col;

    public Line(Point s, int length, String id, ColorRGB col) {
        this.start=s;
        this.length=length;
        this.id=id;
        this.col=col;

    }
}
