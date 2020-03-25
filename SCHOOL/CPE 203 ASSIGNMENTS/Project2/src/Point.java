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


   public Entity createAtlantis(String id, List<PImage> images)
   {
      return new Entity(EntityKind.ATLANTIS, id, this, images,
              0, 0, 0, 0);
   }

   public Entity createObstacle(String id, List<PImage> images)
   {
      return new Entity(EntityKind.OBSTACLE, id, this, images,
              0, 0, 0, 0);
   }

   public Entity createOctoFull(String id, int resourceLimit, int actionPeriod, int animationPeriod,
                                List<PImage> images)
   {
      return new Entity(EntityKind.OCTO_FULL, id, this, images,
              resourceLimit, resourceLimit, actionPeriod, animationPeriod);
   }

   public Entity createOctoNotFull(String id, int resourceLimit, int actionPeriod, int animationPeriod,
                                          List<PImage> images)
   {
      return new Entity(EntityKind.OCTO_NOT_FULL, id, this, images,
              resourceLimit, 0, actionPeriod, animationPeriod);
   }

   public Entity createFish(String id, int actionPeriod, List<PImage> images)
   {
      return new Entity(EntityKind.FISH, id, this, images, 0, 0,
              actionPeriod, 0);
   }

   public Entity createCrab(String id, int actionPeriod, int animationPeriod, List<PImage> images)
   {
      return new Entity(EntityKind.CRAB, id, this, images,
              0, 0, actionPeriod, animationPeriod);
   }

   public Entity createQuake(List<PImage> images)
   {
      return new Entity(EntityKind.QUAKE, QUAKE_ID, this, images,
              0, 0, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
   }

   public Entity createSgrass(String id, int actionPeriod,
                              List<PImage> images)
   {
      return new Entity(EntityKind.SGRASS, id, this, images, 0, 0,
              actionPeriod, 0);
   }


}
