public class Activity extends Action {
    private WorldModel world;
    private ImageStore imageStore;

    public Activity(Entity entity, WorldModel world,
                  ImageStore imageStore)
    {
        super(entity);
        this.world = world;
        this.imageStore = imageStore;
    }


    public void executeAction(EventScheduler scheduler) {
        ((ActiveEntity)this.getEntity()).executeActivity(this.world, this.imageStore, scheduler);

    }


    public static Action createActivityAction(Entity entity, WorldModel world, ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore);
    }


}
