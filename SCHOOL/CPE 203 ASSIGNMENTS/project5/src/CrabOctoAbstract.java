import processing.core.PImage;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class CrabOctoAbstract extends AnimatedEntity {

    public CrabOctoAbstract(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    protected abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    protected void moveToHelper(WorldModel world, Entity target, EventScheduler scheduler){
        Point nextPos = this.nextPosition(world, target.getPosition());

        if (!this.getPosition().equals(nextPos))
        {
            Optional<Entity> occupant = world.getOccupant(nextPos);
            if (occupant.isPresent())
            {
                scheduler.unscheduleAllEvents(occupant.get());
            }
            world.moveEntity(this, nextPos);
        }
    }
    protected Point nextPosition(WorldModel world, Point destPos)
    {
        AStarPathingStrategy pathingStrategy = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = (point) -> world.withinBounds(point) && !world.isOccupied(point);
        BiPredicate<Point, Point> withinReach = Point::adjacent;

        List<Point> path = pathingStrategy.computePath(this.getPosition(), destPos, canPassThrough, withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.isEmpty()){
            return this.getPosition();
        }

        return path.get(0);
    }

}