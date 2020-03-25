public class Activity implements Action {
    private Entity entity;
    private WorldModel world;
    private ImageStore imageStore;

    public Activity(Entity entity, WorldModel world,
                  ImageStore imageStore)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }


    public void executeAction(EventScheduler scheduler) {
        ((Moveable)this.entity).executeActivity(this.world, this.imageStore, scheduler);

    }


    public static Action createActivityAction(Entity entity, WorldModel world, ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore);
    }


}
