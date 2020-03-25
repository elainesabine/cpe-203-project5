import processing.core.PImage;
import java.util.List;
import java.util.Random;

public abstract class ActiveEntity extends Entity {
    private int actionPeriod;
    public static final Random rand = new Random();

    public ActiveEntity(String id, Point position, List<PImage> images, int actionPeriod)
    {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
    }

    //getters
    protected int getActionPeriod() { return this.actionPeriod;}

    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    protected void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this, Activity.createActivityAction(this, world, imageStore), this.getActionPeriod());
    }
}
