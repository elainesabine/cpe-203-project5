import java.util.List;
import java.util.Map;
import processing.core.PImage;

public interface Entity {
   Point getPosition();
   List<PImage> getImages();
   int getActionPeriod();
   void setImages(List<PImage> i);
   PImage getCurrentImage();


}