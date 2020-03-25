public class Animation implements Action{
    private Entity entity;
    private int repeatCount;

    public Animation(Entity entity, int repeatCount)
    {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler)
    {
        ((Moveable)this.entity).nextImage();

        if (this.repeatCount != 1)
        {
            scheduler.scheduleEvent(this.entity,
                    Animation.createAnimationAction(Math.max(this.repeatCount - 1, 0), this.entity),
                    ((Animate)entity).getAnimationPeriod());
        }
    }


    public static Action createAnimationAction(int repeatCount, Entity entity)
    {
        return new Animation(entity, repeatCount);
    }

}
