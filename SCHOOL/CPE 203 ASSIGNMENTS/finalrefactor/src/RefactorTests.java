import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RefactorTests {

 /* //COMMENTED out for first compile  - uncomment to test
    public static final double DELTA = 0.00001;
    private static ByteArrayOutputStream os;
    private static PrintStream ps;
    private static String Output1;
    private static String Output2;


    public static void setup() throws IOException
    {
        // Set System.out to print to a ByteArray (so we can get a String)
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
        System.setOut(ps);

        String[] args = {"draw1.txt"};
        Drawing.main(args);
        Output1 = os.toString("UTF8");
        System.out.println("OUTPUT: " + Output1);

//        // Reset the streams for the next file.
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
        System.setOut(ps);

        args[0] = "draw2.txt";
        Drawing.main(args);
        Output2 = os.toString("UTF8");

//        // Reset the streams for the next file.
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
        System.setOut(ps);


    }

    //Make sure FigureType is not used anymore
    @Test
    public void test_00_noFigureType(){
        try{
            int num= Class.forName("FigureType").getEnumConstants().length;
            assertTrue(num==0);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }
    //Further Test for refactoring not shown
    @Test
    public void test_01_toString() throws FileNotFoundException {
        Figure hf = new Face("*", true);
        Figure sf = new Face("*", false);
        assertEquals("toString()","Id: 0, Face (happy)",hf.toString());
        assertEquals("toString()","Id: 1, Face",sf.toString());
        //
        Scanner in = new Scanner(new File("triangle.txt"));
        Figure c = Drawing.createFigure(in,1);
        assertEquals("toString()","Id: 2, Triangle (sideLen=5)",c.toString());
        in = new Scanner(new File("rectangle.txt"));
        Figure r = Drawing.createFigure(in,2);
        assertEquals("toString()","Id: 3, Rectangle (l=12, h=5)",r.toString());
    }

    @Test
    public void test_02_equals(){
        Figure f1 = new Circle("*", 12.5);
        Figure f2 = new Rectangle("**", 12,10);
        Figure f3 = new Circle("*", 12.5);
        assertTrue("Different Figures",!f1.equals(f2));
        assertTrue("Same Figures", f1.equals(f3));
    }


    @Test(timeout=1000)
    public void test_04_draw1() throws IOException
    {
        setup();
        assertTrue("Draw all figures", Output1.contains("drawing a Circle"));
        assertTrue(" toString for circle used", Output1.contains("(r=4.5)"));
    }

    @Test(timeout=1000)
    public void test_05_draw2() throws IOException
    {
        assertTrue("Draw same rectangles ", Output2.contains("Numbers of Rectangles: 3"));
        assertTrue(" toString for rectangles used", Output2.contains("(l=10, h=11)"));
        assertTrue("Draw same rectangles ", Output2.contains("You drew duplicate Rectangles"));
    }
  */

}
