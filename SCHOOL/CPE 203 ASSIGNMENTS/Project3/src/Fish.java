import processing.core.PImage;

import java.util.List;
import java.util.Random;

public class Fish implements Moveable {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;
    public static final Random rand = new Random();
    public static final String CRAB_KEY = "crab";
    public static final String CRAB_ID_SUFFIX = " -- crab";
    public static final int CRAB_PERIOD_SCALE = 4;
    public static final int CRAB_ANIMATION_MIN = 50;
    public static final int CRAB_ANIMATION_MAX = 150;

    public Fish(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
    }

    //getters
    public Point getPosition(){
        return this.position;
    }
    public List<PImage> getImages() { return this.images;}
    public int getImageIndex() { return this.imageIndex;}
    public int getActionPeriod() { return this.actionPeriod;}
    public PImage getCurrentImage() {
        return this.getImages().get(this.getImageIndex());
    }

    // modifiers
    public void setPosition(Point p) { this.position = p;}
    public void setImages(List<PImage> i) { this.images =i; }
    public void setImageIndex(int i){this.imageIndex = i;}


    //other methods
    public void nextImage() {
        this.setImageIndex((this.imageIndex + 1) % this.images.size());
    }

    public void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Activity.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Point pos = this.position;  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        Crab crab = pos.createCrab(this.id + CRAB_ID_SUFFIX, this.actionPeriod / CRAB_PERIOD_SCALE,
                CRAB_ANIMATION_MIN +
                        rand.nextInt(CRAB_ANIMATION_MAX - CRAB_ANIMATION_MIN),
                imageStore.getImageList(CRAB_KEY));

        world.addEntity(crab);
        crab.scheduleActions(world, scheduler, imageStore);
    }

}
