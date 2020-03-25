import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class OctoAbstract extends CrabOctoAbstract {

    private int resourceLimit;
    private int resourceCount;

    public OctoAbstract(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceCount = resourceCount;
        this.resourceLimit = resourceLimit;
    }

    protected void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }
    protected int getResourceLimit() { return this.resourceLimit; }
    protected int getResourceCount() { return this.resourceCount; }

    protected abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

}
