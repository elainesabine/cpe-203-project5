import java.util.List;
import processing.core.PImage;
import java.util.Optional;

public class Octo_Full extends OctoAbstract {

    public Octo_Full(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), Atlantis.class);

        if (fullTarget.isPresent() &&
                this.moveTo(world, fullTarget.get(), scheduler))
        {
            //at atlantis trigger animation
            ((Atlantis)fullTarget.get()).scheduleActions(world, scheduler, imageStore);

            //transform to unfull
            this.transform(world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent(this, Activity.createActivityAction(this, world, imageStore), this.getActionPeriod());
        }
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        Octo_Not_Full octo = this.getPosition().createOctoNotFull(this.getId(), this.getResourceLimit(), this.getActionPeriod(), this.getAnimationPeriod(),
                this.getImages());

        world.removeEntity( this);
        scheduler.unscheduleAllEvents( this);

        world.addEntity( octo);
        octo.scheduleActions(world, scheduler, imageStore);

        return true;
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.getPosition().adjacent(target.getPosition()))
        {
            return true;
        }
        else
        {
            moveToHelper(world, target, scheduler);
            return false;
        }
    }
}
