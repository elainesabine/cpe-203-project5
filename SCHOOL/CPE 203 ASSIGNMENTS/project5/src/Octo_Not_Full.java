import processing.core.PImage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Octo_Not_Full extends OctoAbstract {

    public Octo_Not_Full(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget = world.findNearest(this.getPosition(), Fish.class);

        if (!notFullTarget.isPresent() ||
                !moveTo(world, notFullTarget.get(), scheduler) ||
                !this.transform(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this, Activity.createActivityAction(this, world, imageStore),
                    this.getActionPeriod());
        }
    }

    public boolean transform(WorldModel world,
                                    EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.getResourceCount() >= this.getResourceLimit())
        {
            Octo_Full octo =  this.getPosition().createOctoFull(this.getId(), this.getResourceLimit(),
                    this.getActionPeriod(), this.getAnimationPeriod(),
                    this.getImages());

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(octo);
            octo.scheduleActions(world, scheduler, imageStore);

            return true;

        }

        return false;
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.getPosition().adjacent(target.getPosition()))
        {
            setResourceCount(getResourceCount()+1);
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        }
        else
        {
            moveToHelper(world, target, scheduler);
            return false;
        }
    }

}
