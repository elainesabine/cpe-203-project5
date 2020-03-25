import processing.core.PImage;
import java.util.List;
import java.util.Random;

public abstract class Entity extends AStarPathingStrategy {
   private String id;
   private Point position;
   private List<PImage> images;
   private int imageIndex;




   public Entity(String id, Point position, List<PImage> images)
   {
      this.id = id;
      this.position = position;
      this.images = images;
      this.imageIndex = 0;
   }

   //getters
   protected String getId() {
      return this.id;
   }
   protected Point getPosition(){
      return this.position;
   }
   protected List<PImage> getImages() { return this.images;}
   protected int getImageIndex() {
      return this.imageIndex;
   }
   protected PImage getCurrentImage() {
      return this.getImages().get(this.imageIndex);
   }

   // modifiers
   protected void setImageIndex(int i){this.imageIndex = i;}
   protected void setPosition(Point point){this.position = point;}

}
