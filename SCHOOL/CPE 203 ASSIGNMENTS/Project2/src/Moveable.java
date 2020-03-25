public interface Moveable extends Entity {
    void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
    void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
}
