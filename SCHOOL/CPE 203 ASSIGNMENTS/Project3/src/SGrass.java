import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SGrass implements Moveable{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;
    public static final Random rand = new Random();
    public static final String FISH_ID_PREFIX = "fish -- ";
    public static final int FISH_CORRUPT_MIN = 20000;
    public static final int FISH_CORRUPT_MAX = 30000;
    public static final String FISH_KEY = "fish";

    public SGrass(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod)
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
                Activity.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }

    public void executeActivity(WorldModel world,
                                      ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Point> openPt = world.findOpenAround(this.position);

        if (openPt.isPresent())
        {
            Fish fish = openPt.get().createFish(FISH_ID_PREFIX + this.id, FISH_CORRUPT_MIN +
                            rand.nextInt(FISH_CORRUPT_MAX - FISH_CORRUPT_MIN),
                    imageStore.getImageList(FISH_KEY));
            world.addEntity(fish);
            fish.scheduleActions(world, scheduler, imageStore);
        }

        scheduler.scheduleEvent(this,
                Activity.createActivityAction(this, world, imageStore),
                this.actionPeriod);
    }
}
