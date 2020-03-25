import java.util.LinkedList;
import java.util.List;
import processing.core.PImage;
import java.util.Optional;

public class Octo_Full implements Octo, Moveable {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public Octo_Full(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
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
        scheduler.scheduleEvent((Entity)this, Activity.createActivityAction((Entity)this, world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent((Entity)this, Animation.createAnimationAction(0, (Entity)this), this.getAnimationPeriod());
    }

    public Point nextPositionOcto(WorldModel world,Point destPos)
    {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz,
                this.position.y);

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x,
                    this.position.y + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.position;
            }
        }

        return newPos;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fullTarget = world.findNearest(this.position, Atlantis.class);

        if (fullTarget.isPresent() &&
                this.moveToFull(world, fullTarget.get(), scheduler))
        {
            //at atlantis trigger animation
            ((Atlantis)fullTarget.get()).scheduleActions(world, scheduler, imageStore);

            //transform to unfull
            this.transformFull(world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent((Entity)this, Activity.createActivityAction((Entity)this, world, imageStore), this.actionPeriod);
        }
    }

    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        Octo_Not_Full octo = this.position.createOctoNotFull(this.id, this.resourceLimit, this.actionPeriod, this.animationPeriod,
                this.images);

        world.removeEntity((Entity) this);
        scheduler.unscheduleAllEvents((Entity) this);

        world.addEntity((Entity) octo);
        octo.scheduleActions(world, scheduler, imageStore);
    }

    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.position.adjacent(target.getPosition()))
        {
            return true;
        }
        else
        {
            Point nextPos = this.nextPositionOcto(world, target.getPosition());

            if (!this.position.equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity((Entity)this, nextPos);
            }
            return false;
        }
    }
}
