import java.util.Arrays;
import java.util.Objects;

/*
Class for simple character graphics figures to send to the screen.
 */
public class Figure {

    public FigureType kind;

    public static int nextid=0;
    public int id;

    public int sideLen;
    public int length;
    public int height;
    public double diameter;
    public String symbol;
    public boolean happy;

    //Do not change the argument list of the constructor!
    public Figure(String symbol) {
        this.id = this.nextid;
        this.nextid++;
        this.symbol=symbol;
        happy = true;
    }

    public void setSymbol(String newS) { symbol = newS; }

    public void drawFig() {

        switch(kind){
            case TRIANGLE:
                drawTriangle();
                break;
            case RECTANGLE:
                drawRectangle();
                break;
            case FACE:
                drawFace();
                break;
            case CIRCLE:
                drawCircle();
                break;
        }
    }

    //////////////////////////////////////////
    public void drawTriangle()
    {

        for(int i=1; i<=sideLen; i++)
        {
            for(int j=0; j<sideLen-i; j++)
                System.out.print(" ");
            for(int j=0; j<(2*i-1); j++)
                System.out.print(symbol);
            System.out.println();
        }
    }

   ////////////////////////////////////////
    public void drawRectangle()
    {
        for(int i=0;i<length;i++)
            System.out.print(symbol);
        System.out.println();
        int h=1;
        while (h<height-1) {
            for(int i =0 ; i< length; i++)
             if(i==0 || i==length-1)
                System.out.print(symbol);
              else
                for(int l=0;l<symbol.length();l++)
                  System.out.print(" ");

            System.out.println();
            h++;
        }
        for(int i=0;i<length;i++)
            System.out.print(symbol);
        System.out.println();
    }

    ///////////////////////////////////////////
    public void drawFace()
    {
        //building a more symmetric hairstyle for each face
        char[] array = new char[(symbol.length()-1)*2];
        Arrays.fill(array,' ');
        String offset= new String(array);

        if(happy) {
            System.out.println(" +" + symbol+symbol+symbol+symbol+symbol+"+ ");
            System.out.println(offset+ "[| o o |]");
            System.out.println(offset+ " |  ^  |");
            System.out.println(offset+ " | '-' |");
            System.out.println(offset+ " +-----+");
        }
        else
        {
            System.out.println(" +" + symbol+symbol+symbol+symbol+symbol+"+ ");
            System.out.println(offset+ "[| o o |]");
            System.out.println(offset+ " |  ^  |");
            System.out.println(offset+ " |  -  |");
            System.out.println(offset+ " +-----+");
        }

    }

    ////////////////////////////////////////////
    public void drawCircle()
    {
        // dist represents distance to the center
        double dist;
        // for horizontal movement
        for (int i = 0; i <= diameter; i++) {
            // for vertical movement
            for (int j = 0; j <= diameter; j++) {
                dist = Math.sqrt((i - diameter/2.0) * (i - diameter/2.0) + (j - diameter/2.0) * (j - diameter/2.0));
                if(dist> diameter/2.0 -0.5 && dist<diameter/2.0 +0.5)
                    System.out.print("  "+symbol);
                else
                    System.out.print("   ");
            }
            System.out.println();
        }
    }

    ////////

    public String toString() {
        return "Id: " + id + ", " + getClass().getName();
    }

    public boolean equals(Object o) {
      if (o == null) return false;
      if (getClass() != o.getClass()) return false;
      Figure oF = (Figure) o;
      return Objects.equals(symbol,oF.symbol);
   }

}
