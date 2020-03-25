import processing.core.PImage;

import java.util.List;

public abstract class AnimatedEntity extends ActiveEntity {
    private int animationPeriod;

    public AnimatedEntity(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
    }

    //getters
    protected int getAnimationPeriod() {
        return this.animationPeriod;
    }

    //other methods
    protected void nextImage() {
        this.setImageIndex((this.getImageIndex() + 1) % this.getImages().size());
    }
    protected void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this, Activity.createActivityAction( this, world, imageStore), this.getActionPeriod());
        scheduler.scheduleEvent(this, Animation.createAnimationAction(0, this), this.getAnimationPeriod());
    }
}
