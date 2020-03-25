import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalyzer
{

   public static void writeToFile(List<Point> points)
   {
      try
      {
         FileWriter fileWriter = new FileWriter("drawMe.txt");

         for (Point point : points)
         {
            fileWriter.write(point.toString());
            fileWriter.write("\n");
         }
      }
      catch (Exception e)
      {
         System.out.println("Can't open output file.");
      }
   }


   private static void readInPoints(final Scanner input, ArrayList<Point> points){
      while (input.hasNextLine())
      {
         String[] pointXYZ = input.nextLine().split(", ");

         Point point = new Point(Double.parseDouble(pointXYZ[0]), Double.parseDouble(pointXYZ[1]), Double.parseDouble(pointXYZ[2]));
         points.add(point);
      }
   }


   private static String getFilename(String[] args)
   {
      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      return args[0];
   }

   public static void main(String[] args) throws IOException
   {
      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */
      final String filename = getFilename(args);
      ArrayList<Point> points = new ArrayList<>();
      try (Scanner input = new Scanner(new File(filename)))
      {
         readInPoints(input, points);
      }

      List<Point> modifiedPoints = points.stream().filter(p -> p.getZ() <= 2.0)
              .map(p -> new Point(p.getX() * 0.50, p.getY() * 0.50, p.getZ() * 0.5))
              .map(p -> new Point(p.getX() -150, p.getY() - 37, p.getZ()))
              .collect((Collectors.toList()));

      writeToFile(modifiedPoints);

   }


}
