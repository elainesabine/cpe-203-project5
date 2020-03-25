import processing.core.PImage;

import java.util.List;

final class Point
{
   public final int x;
   public final int y;
   public static final String QUAKE_ID = "quake";
   public static final int QUAKE_ACTION_PERIOD = 1100;
   public static final int QUAKE_ANIMATION_PERIOD = 100;

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)
   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }



   public int hashCode()
   {
      int result = 17;
      result = result * 31 + x;
      result = result * 31 + y;
      return result;
   }

   public int distanceSquared(Point other)
   {
      int deltaX = this.x - other.x;
      int deltaY = this.y - other.y;

      return deltaX * deltaX + deltaY * deltaY;
   }

   public boolean adjacent(Point other)
   {
      return (this.x == other.x && Math.abs(this.y - other.y) == 1) ||
              (this.y == other.y && Math.abs(this.x - other.x) == 1);
   }


   public Atlantis createAtlantis(String id, List<PImage> images)
   {
      return new Atlantis(id, this, images,
              0, 0);
   }

   public Obstacle createObstacle(String id, List<PImage> images)
   {
      return new Obstacle(id, this, images);
   }

   public Octo_Full createOctoFull(String id, int resourceLimit, int actionPeriod, int animationPeriod,
                                List<PImage> images)
   {
      return new Octo_Full(id, this, images,
              resourceLimit, resourceLimit, actionPeriod, animationPeriod);
   }

   public Octo_Not_Full createOctoNotFull(String id, int resourceLimit, int actionPeriod, int animationPeriod,
                                          List<PImage> images)
   {
      return new Octo_Not_Full(id, this, images,
              resourceLimit, 0, actionPeriod, animationPeriod);
   }

   public Fish createFish(String id, int actionPeriod, List<PImage> images)
   {
      return new Fish(id, this, images, actionPeriod);
   }

   public Crab createCrab(String id, int actionPeriod, int animationPeriod, List<PImage> images)
   {
      return new Crab(id, this, images,actionPeriod, animationPeriod);
   }

   public Quake createQuake(List<PImage> images)
   {
      return new Quake(QUAKE_ID, this, images,QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
   }

   public SGrass createSgrass(String id, int actionPeriod,
                              List<PImage> images)
   {
      return new SGrass(id, this, images, actionPeriod);
   }

   public boolean neighbors(Point p1, Point p2)
   {
      return p1.x+1 == p2.x && p1.y == p2.y ||
              p1.x-1 == p2.x && p1.y == p2.y ||
              p1.x == p2.x && p1.y+1 == p2.y ||
              p1.x == p2.x && p1.y-1 == p2.y;
   }
}
