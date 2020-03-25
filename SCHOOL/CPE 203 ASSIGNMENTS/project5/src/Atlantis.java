import processing.core.PImage;
import java.util.List;

public class Atlantis extends AnimatedEntity {

    public static final int ATLANTIS_ANIMATION_REPEAT_COUNT = 7;

    public Atlantis(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Animation.createAnimationAction(ATLANTIS_ANIMATION_REPEAT_COUNT, this),
                this.getAnimationPeriod());
    }

    //other methods
    public void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

}
