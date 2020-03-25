///////***************************************************************************************************
///////
///////!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DO NOT REFACTOR THIS CLASS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
///////
///////*****MODIFY THIS CLASS SO THAT IT COMPILES AND RUNS CORRECTLY AFTER YOU REFACTORED YOUR CODE******
///////
///////
///////***************************************************************************************************
import java.io.File;
import java.util.*;

public class Drawing
{
   private static ArrayList<Figure> allFigures= new ArrayList<>();

   public static void main(String[] args) {

      Scanner in;

      try {
      in = new Scanner(new File(args[0]));//used for testing
      }
      catch (Exception e) {
      in = new Scanner(System.in);
      }
      System.out.print("\n * * * * Welcome to the Drawing Program * * * * \n ");
      boolean done=false;

      while(!done)
         done= SetUp(in);

      //Print statistics:
      System.out.println();
      System.out.println("You have drawn "+ allFigures.size() +" figures.");
      printStatRectangles();
      /*
      more code not shown.
       */

   }


   public static boolean  SetUp(Scanner in){

      System.out.print("\nWhat do you want to draw?\n(1 - Triangle, 2 - Rectangle, 3 - Face, 4 - Circle)\nPress 0 to exit.\n");
      int kind = in.nextInt();//assume that positive integer is entered
      if(kind==0)
         return true;

      Figure f = createFigure(in,kind);
      f.drawFig();

      System.out.print("This is Figure Number "+ f.id + " you are drawing.\n");
      System.out.print("You have successfully created \n"+ f + "\n");
      allFigures.add(f);
      return false;
   }


   public static Figure createFigure(Scanner in, int type)
   {
      String symbol = "*";
      
      System.out.print("First choose a symbol to use in your drawing:\n");
      symbol = in.next();
      Figure f;

      if (type == 1)
      {
         System.out.print("Before drawing a Triangle. \nEnter sidelength:\n");
         int Side = in.nextInt();
         f = new Figure(symbol);
         f.sideLen = Side;
         f.kind = FigureType.TRIANGLE;
      }
      else if (type == 2)
      {
         System.out.print("Before drawing a Rectangle. \nWhat dimensions? \nEnter length:\n");
         int Length = in.nextInt();
         System.out.print("Enter height:\n");
         int Height = in.nextInt();
         f = new Figure(symbol);
         f.length = Length;
         f.height = Height;
         f.kind = FigureType.RECTANGLE;
      }
      else if (type == 3)
      {
         System.out.print("You are drawing a Face. \n");
         System.out.print("Enter mood (1-4=grumpy/5-9=happy)\n");
         int mood = in.nextInt();
         f = new Figure(symbol);
         f.happy = mood > 4 ? true:false;
         f.kind = FigureType.FACE;
      }
      else if (type == 4)
      {
         System.out.print("Before drawing a Circle. \nEnter the diameter:\n");
         double Diam = in.nextDouble();
         f = new Figure(symbol);
         f.diameter = Diam;
         f.kind = FigureType.CIRCLE;
      } else {
         return null;
      }

      return f;
   }

   public static void printStatRectangles()
   {
      ArrayList<Figure> rectangles= new ArrayList<>();
      for(Figure f: allFigures)
      {
         if(f.kind==FigureType.RECTANGLE) {
            rectangles.add(f);
         }
      }
      System.out.println("Numbers of Rectangles: "+ rectangles.size());

      if (rectangles.size()==0)
         return;
      boolean duplicates=false;
      for(int i=0;i<rectangles.size();i++)
      {
         Figure curr= rectangles.get(i);
         for(int k=0;k<rectangles.size();k++)
         {
            if(i!=k && curr.equals(rectangles.get(k))) {
               duplicates = true;
               System.out.println("You drew duplicate Rectangles.");
               return;
            }
         }
      }
      System.out.println("There have been no duplicate Rectangles.");
   }

}

