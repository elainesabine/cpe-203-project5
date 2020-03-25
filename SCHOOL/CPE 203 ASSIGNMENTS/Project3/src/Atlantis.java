import processing.core.PImage;

import java.util.List;

public class Atlantis implements Animate, Moveable {
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;
    public static final int ATLANTIS_ANIMATION_REPEAT_COUNT = 7;

    public Atlantis(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
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
    public int getActionPeriod() { return this.actionPeriod;}
    public int getAnimationPeriod() {
        return this.animationPeriod;
    }
    public PImage getCurrentImage() {
        return this.getImages().get(this.imageIndex);
    }

    // modifiers
    public void setImages(List<PImage> i) { this.images =i; }
    public void setImageIndex(int i){this.imageIndex = i;}
    public void setPosition(Point point){this.position = point;}

    //other methods

    public void nextImage() {
        this.setImageIndex((this.imageIndex + 1) % this.images.size());
    }


    public void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Animation.createAnimationAction(ATLANTIS_ANIMATION_REPEAT_COUNT, this),
                this.getAnimationPeriod());
    }

    public void executeActivity(WorldModel world,
                                        ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }


}
